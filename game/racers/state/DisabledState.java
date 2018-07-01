package game.racers.state;

import game.racers.Racer;
//@author : Tzvi Puchinsky 
/**
 * State Design Pattern - Disabled State - states that the racer is Disabled
 */
public class DisabledState implements racerState {
    private final String state = "Disabled State";

    String getState(){
        return this.state;
    }

    /**
     * Prints that the racer Disabled and Cant move anymore
     * @param racer
     * Race Object
     */
    @Override
    public void stateAction(Racer racer) {
        System.out.println(racer.getName() + " is Disabled! Update with Failed Status");
    }
}
