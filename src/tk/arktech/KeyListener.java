package tk.arktech;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.nio.CharBuffer;
import java.util.concurrent.BlockingQueue;

public class KeyListener implements NativeKeyListener {

    private final BlockingQueue<String> queue;
    private CharBuffer buf;

    public KeyListener(BlockingQueue<String> queue, CharBuffer buf) {
        this.queue = queue;
        this.buf = buf;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if(buf.length()<=1)
        {
            try {
                queue.put(2 + buf.toString());
            } catch (InterruptedException e) {
                //TODO: Errorhandling
                e.printStackTrace();
            }
            buf.clear();
        }

//        buf.put(nativeKeyEvent.getKeyChar());
        buf.put(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
