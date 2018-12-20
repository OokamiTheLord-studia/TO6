package tk.arktech;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class TCPListener implements Runnable {

    private final BlockingQueue<String> queue;
    private InputStream instream;
    private final int myNumber;

    TCPListener(BlockingQueue<String> queue, InputStream instream, int myNumber)
    {
        this.queue = queue;
        this.instream = instream;
        this.myNumber = myNumber;
    }


    @Override
    public void run() {

        ServerSocket svr = null;
        try {


                Scanner scanner = new Scanner(instream);
                scanner.useDelimiter("\0");

                while (true) {
                    String data = myNumber + scanner.next();
                    queue.put(data);

                }



        } catch (InterruptedException inter)
        {
            //TODO: poprawne zamknięcięcie czy coś


            inter.printStackTrace();
        }
    }
}