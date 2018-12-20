package tk.arktech;

public class StateIdle extends State {

    public StateIdle(Context context) {
        super(context);
    }

    @Override
    void doAction() {
//        context.setCanBeRunning(true);
        context.setState(new StateListen(context));
        context.doAction();
    }
}
