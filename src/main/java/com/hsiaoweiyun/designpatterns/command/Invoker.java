package com.hsiaoweiyun.designpatterns.command;

import java.util.List;

public class Invoker {

    private Command command = null;
    private List<Command> commandList = null;


    public void setCommand(Command command) {
        this.command = command;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public void action() {
        if (command != null) command.execute();
    }

    public void actions() {
        if(commandList != null) commandList.forEach(Command::execute);
    }


}
