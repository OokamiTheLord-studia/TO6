package tk.arktech;

import java.util.ArrayList;

public class Context {

    private State state;
//    private boolean canBeRunning = true;
    protected Object memory;


//    public boolean canBeRunning() {
//        return canBeRunning;
//    }

//    public void setCanBeRunning(boolean canBeRunning) {
//        this.canBeRunning = canBeRunning;
//    }

    public Context(Object memory) {
        this.memory = memory;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void doAction()
    {
        if(state != null)
        {
            state.doAction();
        }
    }
}
