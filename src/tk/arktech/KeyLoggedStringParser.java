package tk.arktech;

import java.util.function.Consumer;

public class KeyLoggedStringParser extends ChainConnectable {

    private final int myNumber;
    private final Consumer<String> action;

//    public KeyLoggedStringParser(Keylogger keylogger, Consumer<String> action, int myNumber) {
//        super(keylogger);
public KeyLoggedStringParser(Consumer<String> action, int myNumber) {
        this.action = action;

        this.myNumber = myNumber;
    }

    @Override
    public void handle(String st) {

        if(Integer.parseInt(st.substring(0,1)) == myNumber)
        {
            action.accept(st);
        }
        else
        {
            if(next!=null)
            {
                next.handle(st);
            }
            else
            {
                System.out.println("illegal message");
            }
        }

    }
}
