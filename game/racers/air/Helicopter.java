package game.racers.air;

import game.racers.Racer;
import game.racers.prototype.Prototype;
import utilities.EnumContainer;

/**
 * Helicopter Racer extends Racer abstract class
 * @author : Tzvi Puchinsky 
 */
public class Helicopter extends Racer implements AerialRacer, Prototype {

    /**
     * Default Constructor
     */
    public Helicopter(){
        setName("Helicopter #" + getSerialNumber());
        setMaxSpeed(885);
        setAcceleration(100);
        setColor(EnumContainer.Color.BLUE);
    }

    /**
     * Helicopter Constructor
     * @param name
     * String Name for Racer
     * @param maxSpeed
     * Double type Max Speed value
     * @param acceleration
     * Double type acceleration value
     * @param color
     * Enum type color for racer
     */
    public Helicopter(String name, double maxSpeed, double acceleration, EnumContainer.Color color) {
        super(name, maxSpeed, acceleration, color);
    }

    /**
     * Override for abstract function from Racer Class
     * @return
     * String color of the Racer
     */
    @Override
    public String describeSpecific() {
        return ", color: " + getColor();
    }

    @Override
    public Racer clone() {
        return new Helicopter(getName(),getMaxSpeed(),getAcceleration(),getColor());
    }

    @Override
    public void addAtrribute(String value, Object racer) {

    }
}
