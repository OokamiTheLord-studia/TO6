package tk.arktech;

import java.util.function.Consumer;

public class OneCommandConsumer extends Chain {

    private final Consumer<String> action;

    public OneCommandConsumer(Consumer<String> action)
    {
        this.action = action;
    }

    @Override
    public void handle(String st) {
        this.action.accept(st);
    }
}
