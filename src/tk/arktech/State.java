package tk.arktech;

public abstract class State {
    protected Context context;

    public State(Context context)
    {
        this.context = context;
    }

    abstract void doAction();

}
