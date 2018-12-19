package tk.arktech;

public class Context {

    private State state;

    public Context() {
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
