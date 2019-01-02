package tk.arktech;

import java.util.Scanner;

public class CommandClientParser extends ChainConnectable {

    private final Chain handler;
    private final int myNumber;

//    public CommandClientParser(Keylogger keylogger, CommandExecutor executor, int myNumber) {
//        super(keylogger);
public CommandClientParser(Chain executor, int myNumber) {
        this.handler = executor;
        this.myNumber = myNumber;
    }

    public int getMyNumber() {
        return myNumber;
    }

    @Override
    public void handle(String st) {



        if(Integer.parseInt(st.substring(0,1)) == myNumber)
        {
            handler.handle(st.substring(1));
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
