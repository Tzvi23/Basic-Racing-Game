package game.racers.state;

import game.racers.Racer;
//@author : Tzvi Puchinsky 
/**
 * State Design Pattern - Completed State - Indicates that the Racer finished the race
 */
public class CompletedState implements racerState {
    private final String state = "Completed State";

    String getState(){
        return this.state;
    }

    /**
     * States that the racer finished the race and prints his name
     * @param racer
     * Racer Object
     */
    @Override
    public void stateAction(Racer racer) {
        System.out.println(racer.getName() + " finished the Race!");
    }
}
