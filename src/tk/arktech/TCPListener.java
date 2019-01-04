package tk.arktech;


import java.io.InputStream;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class TCPListener implements Runnable {

    private final BlockingQueue<String> queue;
    private InputStream instream;
    private final int myNumber;
    private boolean runningFlag = true;
    TCPListener(BlockingQueue<String> queue, InputStream instream, int myNumber)
    {
        this.queue = queue;
        this.instream = instream;
        this.myNumber = myNumber;
    }


    @Override
    public void run() {

//        ServerSocket svr = null;
        try {




                Scanner scanner = new Scanner(instream);
                scanner.useDelimiter("\0");

                while (runningFlag) {

                    try {
                        String data = myNumber + scanner.next();
                        queue.put(data);
                    }
                    catch(NoSuchElementException e)
                    {
                        runningFlag = false;
                    }

                }



        } catch (InterruptedException inter)
        {
            runningFlag = false;
        }
    }
}
