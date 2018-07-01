package game.racers.state;

import game.racers.Racer;
//@author : Tzvi Puchinsky 
/**
 * State Design Pattern - Active State - Indicated that the racer active
 */
public class ActiveState implements racerState {
    private final String state = "Active Racer";

    String getState(){
        return this.state;
    }

    /**
     * States that the racer is Active and Prints racer Place
     * @param racer
     * Racer Object
     */
    @Override
    public void stateAction(Racer racer) {
        /* Needs to Show the current place of the Racer */
        racer.getArena().updatePlace();
        System.out.println("Place of " + racer.getName() + " is: " + racer.getArena().returnPlace(racer) );

    }
}
