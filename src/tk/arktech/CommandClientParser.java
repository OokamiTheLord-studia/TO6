package tk.arktech;

import java.util.Scanner;

public class CommandClientParser extends ChainConnectable {

    private final CommandExecutor executor;
    private final int myNumber;

//    public CommandClientParser(Keylogger keylogger, CommandExecutor executor, int myNumber) {
//        super(keylogger);
public CommandClientParser(CommandExecutor executor, int myNumber) {
        this.executor = executor;
        this.myNumber = myNumber;
    }

    public int getMyNumber() {
        return myNumber;
    }

    @Override
    public void handle(String st) {



        if(Integer.parseInt(st.substring(0,1)) == myNumber)
        {
            executor.handle(st.substring(1));
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
