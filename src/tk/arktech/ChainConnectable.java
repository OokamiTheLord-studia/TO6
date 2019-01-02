package tk.arktech;

public abstract class ChainConnectable extends Chain implements Addable<ChainConnectable>{

//    public ChainConnectable(Keylogger keylogger) {
//        super(keylogger);
//    }

    public ChainConnectable add(ChainConnectable p )
    {
        this.next = p;
        return p;
    }

    public void addEnd(Chain p)
    {
        this.next = p;
    }

}
