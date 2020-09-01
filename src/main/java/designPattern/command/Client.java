package designPattern.command;

public class Client {
    public static void main(String[] args) {
        RemoteController controller = new RemoteController();
        Light light = new Light();
        controller.setOffCommands(0,new LightOnCommand(light),new LightOffCommand(light));

        controller.onButtonWasPush(0);
        controller.undoButtonWasPush();

        controller.offButtonWasPush(0);
        controller.undoButtonWasPush();


    }
}
