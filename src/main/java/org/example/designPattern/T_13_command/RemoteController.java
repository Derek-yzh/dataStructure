package org.example.designPattern.T_13_command;

public class RemoteController {

    private Command[] onCommands;
    private Command[] offCommands;

    private Command undoCommend;

    public RemoteController() {
        this.onCommands = new Command[5];
        this.offCommands = new Command[5];
        for (int i = 0; i < 5; i++) {
            onCommands[i] = new NoCommand();
            offCommands[i] = new NoCommand();
        }
    }

    public void setOffCommands(int no,Command onCommand, Command offCommand) {
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }

    public void onButtonWasPush(int no){
        onCommands[no].execute();
        undoCommend = onCommands[no];
    }

    public void offButtonWasPush(int no){
        offCommands[no].execute();
        undoCommend = offCommands[no];
    }

    public void undoButtonWasPush(){
        undoCommend.undo();
    }
}
