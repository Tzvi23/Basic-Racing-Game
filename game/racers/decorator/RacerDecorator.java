package game.racers.decorator;

import game.racers.IRacer;
//@author : Tzvi Puchinsky 
/**
 * Abstract class for the Decorator -  Saves the Decorated Racer
 */
public abstract class RacerDecorator implements IRacer {

    protected IRacer decoratedRacer;


    public RacerDecorator(IRacer decoratedRacer){
        this.decoratedRacer = decoratedRacer;
    }
}
