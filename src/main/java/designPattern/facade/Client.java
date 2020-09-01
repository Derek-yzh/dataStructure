package designPattern.facade;

public class Client {
    public static void main(String[] args) {
        HomeTheaterFacade theaterFacade = new HomeTheaterFacade();
        theaterFacade.ready();
        theaterFacade.play();
        theaterFacade.pause();
        theaterFacade.end();
    }
}
