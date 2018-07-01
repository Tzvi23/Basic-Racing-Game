package game.arenas.exceptions;

/**
 * Exception when Racer type don't match the arena type
 * @author : Tzvi Puchinsky ID: 203195706 and Alla Kitayeva ID:336382833
 */
public class RacerTypeException extends Exception {
    /**
     * Racer Type Exception
     * @param message
     * Info about the racer
     */
    public RacerTypeException(String message){
        super("Invalid Racer of type " + message);
    }
}
