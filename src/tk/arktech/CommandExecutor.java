package tk.arktech;

import java.util.Scanner;
import java.util.function.Consumer;

public class CommandExecutor extends ChainConnectable{

    private final Runnable action;
    private final String command;
    private final Runnable illegalCommandAction;

//    public CommandExecutor(Keylogger keylogger, Runnable action, String command) {
//        super(keylogger);
public CommandExecutor(Runnable action, String command) {
        this.action = action;
        this.command = command;
        this.illegalCommandAction = null;
    }

//    public CommandExecutor(Keylogger keylogger, Runnable action, String command, Runnable illegalCommandAction) {
//        super(keylogger);
public CommandExecutor(Runnable action, String command, Runnable illegalCommandAction) {
        this.action = action;
        this.command = command;
        this.illegalCommandAction = illegalCommandAction;
    }


    @Override
    public void handle(String st) {
        Scanner sc = new Scanner(st);
        if(sc.next().equals(command))
        {
            action.run();
        }
        else
        {
            if(next != null)
            {
                next.handle(st);
            }
            else
            {
                if (illegalCommandAction!=null)
                {
                    illegalCommandAction.run();
                }
            }
        }
    }
}
