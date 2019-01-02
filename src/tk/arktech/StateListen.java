package tk.arktech;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyListener;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class StateListen extends State {

    private boolean startListening = true;
    private NativeKeyListener keyListener;
    BlockingQueue<String> queue;
    CharBuffer buf;


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

                try {
                    GlobalScreen.registerNativeHook();
                } catch (NativeHookException e) {
                    e.printStackTrace();
                }
                keyListener = new KeyListener(queue, buf);
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
                buf.rewind();
                queue.put(2 + buf.toString());
//                queue.put("2" + buf.)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            context.setState(new StateIdle(context));
        }
    }
}
