package game.racers.land;

import game.racers.Racer;
import game.racers.prototype.Prototype;
import utilities.EnumContainer;

/**
 * Horse Racer Class extends from abstract Racer Class
 * @author : Tzvi Puchinsky 
 */
public class Horse extends Racer implements LandRacer, Prototype {
    private EnumContainer.Breed type;

    /**
     * Default Constructor
     */
    public Horse() {
        setName("Horse #" + getSerialNumber());
        setMaxSpeed(50);
        setAcceleration(3);
        setColor(EnumContainer.Color.GREEN);
        setType(EnumContainer.Breed.THOROUGHBRED);
    }

    /**
     * Horse Constructor
     * @param name
     * String name for Racer
     * @param maxSpeed
     * Double max Speed value for Racer
     * @param acceleration
     * Double acceleration value for Racer
     * @param color
     * Enum type color
     */
    public Horse(String name, double maxSpeed, double acceleration, EnumContainer.Color color) {
        super(name, maxSpeed, acceleration, color);
        setType(EnumContainer.Breed.THOROUGHBRED);
    }

    /**
     * Override describeSpecific function from Racer Class
     * @return
     * String Breed:
     */
    @Override
    public String describeSpecific() {
        return " ,Breed: " + String.valueOf(type);
    }

    /**
     * Getter - returns Enum type
     * @return
     * Enum BREED - THOROUGHBRED, STANDARDBRED, MORGAN, FRIESIAN
     */
    public EnumContainer.Breed getType() {
        return type;
    }

    /**
     * Setter - sets Breed Enum
     * @param type
     * Enum Breed - THOROUGHBRED, STANDARDBRED, MORGAN, FRIESIAN
     */
    public void setType(EnumContainer.Breed type) {
        this.type = type;
    }

    @Override
    public Racer clone() {
        return new Horse(getName(),getMaxSpeed(),getAcceleration(),getColor());
    }

    @Override
    public void addAtrribute(String value, Object racer) {

    }
}


