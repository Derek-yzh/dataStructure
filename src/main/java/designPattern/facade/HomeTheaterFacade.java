package designPattern.facade;

public class HomeTheaterFacade {

    private DVD dvd;
    private Popcorn popcorn;

    public HomeTheaterFacade() {
        this.dvd = DVD.getInstance();
        this.popcorn = Popcorn.getInstance();
    }

    public void ready(){
        dvd.on();
        popcorn.on();
    }

    public void play(){
        dvd.play();
    }

    public void pause(){
        dvd.pause();
    }

    public void end(){
        dvd.off();
        popcorn.off();
    }

}
