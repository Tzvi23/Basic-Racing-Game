package game.racers.decorator;

import game.racers.IRacer;

import java.util.ArrayList;
import java.util.Hashtable;
//@author : Tzvi Puchinsky 
/**
 * Decorator Design Pattern - Wheeled Racer - adds Racer to any type of Racer
 */
public class WheeledRacer extends RacerDecorator {

    private String ATTRIBUTENAME = "numOfWheels";
    private Hashtable decorator;
    private int numOfWheels;

    /**
     * Constructor
     * @param decoratedRacer
     * IRacer Object
     * @param numOfWheels
     * Number of wheels to be added
     */
    public WheeledRacer(IRacer decoratedRacer, int numOfWheels) {
        super(decoratedRacer);
        if (decoratedRacer.getDecoration() != null){
        decorator = this.decoratedRacer.getDecoration();
        }
        this.numOfWheels = numOfWheels;
        addAtrribute(this.ATTRIBUTENAME, decoratedRacer);
    }

    /**
     * Adds the number of wheels to decoration of the racer
     * @param value
     * String "numOfWheels"
     * @param racer
     * Number of wheels to be added
     */
    @Override
    public void addAtrribute(String value, Object racer) {
        if (value.equalsIgnoreCase("numOfWheels")) {
            if (!decorator.containsKey("numOfWheels")) {
                ArrayList list = new ArrayList();
                list.add(this.numOfWheels);
                decorator.put("numOfWheels", list);
            } else {
                ArrayList list = (ArrayList) decorator.get("numOfWheels");
                list.add(numOfWheels);
                decorator.put("numOfWheels", list);
            }
        }
    }

    /**
     * Returns local decorator HashTable
     * @return
     * Returns local decorator HashTable
     */
    @Override
    public Hashtable getDecoration() {
        return decorator;
    }

}
