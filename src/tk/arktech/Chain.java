package tk.arktech;

import java.util.Scanner;

public abstract class Chain {
    protected Chain next = null;
    //protected Keylogger keylogger;

//    public Chain(Keylogger keylogger) {
//        this.keylogger = keylogger;
//    }



    public abstract void handle(String st);

}
