package game.racers.decorator;

import game.racers.IRacer;
import utilities.EnumContainer;

import java.util.ArrayList;
import java.util.Hashtable;
//@author : Tzvi Puchinsky 
/**
 * Decorator Design Pattern - Colored Racer - adds new color to Racer
 */
public class ColoredRacer extends RacerDecorator {

    private String ATTRIBUTENAME = "color";
    private Hashtable decorator;
    private EnumContainer.Color color;

    /**
     * Constructor
     * @param decoratedRacer
     * IRacer Object
     * @param color
     * New Color to be added
     */
    public ColoredRacer(IRacer decoratedRacer, EnumContainer.Color color) {
        super(decoratedRacer);
        if (decoratedRacer.getDecoration() != null) {
            decorator = this.decoratedRacer.getDecoration();
        }
        this.color = color;
        addAtrribute(this.ATTRIBUTENAME, decoratedRacer);
    }

    /**
     * Adds the new attribute to decoration HashTable of the racer
     * @param value
     * String indicates what to be added
     * @param racer
     * IRacer Object
     */
    @Override
    public void addAtrribute(String value, Object racer) {
        if (value.equalsIgnoreCase("Color")) {
            if (!decorator.containsKey("color")) {
                ArrayList list = new ArrayList();
                list.add(this.color);
                decorator.put("color", list);
            } else {
                ArrayList list = (ArrayList) decorator.get("color");
                list.add(this.color);
                decorator.put("color", list);
            }
        }
    }

    /**
     * Returns local HashTable
     * @return
     * Returns local HashTable
     */
    @Override
    public Hashtable getDecoration() {
        return decorator;
    }
}
