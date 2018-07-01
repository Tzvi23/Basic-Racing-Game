package game.racers.state;

import game.racers.Racer;
//@author : Tzvi Puchinsky 
/**
 * State Design Pattern - Broken State - states that the racer Got a mishap
 */
public class BrokenState implements racerState {
    private final String state = "Broken Racer";

    String getState(){
        return this.state;
    }

    /**
     * Prints that the racer got mishap and broken and the time in race
     * @param racer
     * Racer Object
     */
    @Override
    public void stateAction(Racer racer) {
        System.out.println(racer.getName() + " is Broken! Time in Race: " + racer.getTimeElapsed() + " seconds");
    }
}
