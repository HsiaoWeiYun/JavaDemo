package com.hsiaoweiyun.designpatterns.command;

public class StopCommand implements Command {
    private Receiver receiver = null;

    public StopCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.stop();
    }
}
