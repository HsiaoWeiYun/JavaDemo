package com.hsiaoweiyun.designpatterns.command;

public class RewindCommand implements Command {
    private Receiver receiver = null;

    public RewindCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.rewind();
    }
}
