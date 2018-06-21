package com.hsiaoweiyun.designpatterns.command;

public class PlayCommand implements Command {

    private Receiver receiver = null;

    public PlayCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.play();
    }
}
