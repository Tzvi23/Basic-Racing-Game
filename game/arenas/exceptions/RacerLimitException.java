package game.arenas.exceptions;

/**
 * Exception When trying to add Racers and the Racer Max limit reached in arena
 * @author : Tzvi Puchinsky ID: 203195706 and Alla Kitayeva ID:336382833
 */
public class RacerLimitException extends Exception {
    /**
     * Racer Limit Exception
     * @param message
     * message - is the info about the racers trying to be added
     */
    public RacerLimitException(String message){
        super("Arena is full! " + message);
    }
}
