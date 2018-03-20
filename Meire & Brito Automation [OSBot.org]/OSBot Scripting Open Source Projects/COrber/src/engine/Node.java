package org.script.orb.engine;

public abstract class Node {

    private Context context;

    public Node(Context context) {
        this.context = context;
    }

    public abstract void execute() throws InterruptedException;

    public abstract boolean validate();

    public abstract String status();

    public Context getContext() {
        return context;
    }
}