package tk.arktech;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyListener;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StateListen extends State {

    private boolean startListening = true;
    private NativeKeyListener keyListener;
    BlockingQueue<String> queue;
    CharBuffer buf;
    private int mynumber;


    public StateListen(Context context) {
        super(context);
    }

    @Override
    void doAction() {
        //TODO: zrobić doAction()

        if(startListening) {
            if (context.memory instanceof ArrayList) {
                ArrayList<Object> arr = (ArrayList<Object>) context.memory;
                queue = (BlockingQueue<String>) arr.get(0);
                buf = (CharBuffer) arr.get(1);
                mynumber = (int) arr.get(2);

                // Get the logger for "org.jnativehook" and set the level to off.
                Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                logger.setLevel(Level.OFF);

                // Don't forget to disable the parent handlers.
                logger.setUseParentHandlers(false);

                try {
                    GlobalScreen.registerNativeHook();
                } catch (NativeHookException e) {
                    e.printStackTrace();
                }
                keyListener = new KeyListener(queue, buf, mynumber);
                GlobalScreen.addNativeKeyListener(keyListener);



            }
            startListening = false;
        }
        else {

            GlobalScreen.removeNativeKeyListener(keyListener);
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                e.printStackTrace();
            }

            try {
                var temp = buf.position();
                buf.rewind();
                queue.put(mynumber + buf.subSequence(0,temp).toString());
//                queue.put("2" + buf.)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            context.setState(new StateIdle(context));
        }
    }
}
