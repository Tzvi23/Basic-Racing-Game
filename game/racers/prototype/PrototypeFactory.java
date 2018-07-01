package game.racers.prototype;

import game.racers.Racer;

import java.util.HashMap;
import java.util.Map;
//@author : Tzvi Puchinsky
/**
 * Prototype Design Pattern - Saves prototype to be duplicated later
 */
public class PrototypeFactory {

    private static final Map<String, Prototype> prototypes = new HashMap<>();

    /**
     * Adds to static HashMap prototypes
     * @param racerType
     * String Key
     * @param racer
     * The prototype needed to be saved
     */
    public static void setPrototypes(String racerType, Prototype racer){
        prototypes.put(racerType, racer);
    }

    /**
     * Returns cloned via prototype Racer
     * @param type
     * String key
     * @return
     * Cloned Racer
     */
    public static Racer getPrototype(String type){
        try {
            return prototypes.get(type).clone();
        } catch (NullPointerException ex) {
            System.out.println("Prototype with name: " + type + ", doesn't exist");
            return null;
        }
    }
}
