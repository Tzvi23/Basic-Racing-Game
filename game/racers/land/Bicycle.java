package game.racers.land;

import game.racers.Racer;
import game.racers.decorator.Wheeled;
import game.racers.prototype.Prototype;
import utilities.EnumContainer;

/**
 * Bicycle Class extends abstract Racer Class
 * @author : Tzvi Puchinsky 
 */
public class Bicycle extends Racer implements LandRacer, Prototype {

    private final int DefaultNumberOfWheels = 2;
    private EnumContainer.Type type;

    private Wheeled wheels;

    /**
     * Default Constructor
     */
    public Bicycle(){
        setName("Bicycle #" + getSerialNumber());
        setMaxSpeed(270);
        setAcceleration(10);
        setColor(EnumContainer.Color.GREEN);
        setType(EnumContainer.Type.MOUNTAIN);
        wheels = new Wheeled(DefaultNumberOfWheels);
    }

    /**
     * Bicycle Constructor
     * @param name
     * String name for Racer
     * @param maxSpeed
     * Double max Speed value
     * @param acceleration
     * Double acceleration value
     * @param color
     * Enum type color for Racer
     * @param numOfWheels
     * Int type number of wheels
     */
    public Bicycle(String name, double maxSpeed, double acceleration, EnumContainer.Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        setType(EnumContainer.Type.MOUNTAIN);
        wheels = new Wheeled(numOfWheels);
    }

    /**
     * Getter - return enum type of the bicycle
     * @return
     * Enum Type: MOUNTAIN, HYBRID , CRUISER, ROAD
     */
    public EnumContainer.Type getType() {
        return type;
    }

    /**
     * Setter - sets Type bicycle
     * @param type
     * Enum type : MOUNTAIN, HYBRID, CRUISER, ROAD
     */
    public void setType(EnumContainer.Type type) {
        this.type = type;
    }

    /**
     * Override for abstract describeSpecific
     * @return
     * String color: Number of Wheels: Bicycel Type:
     */
    @Override
    public String describeSpecific() {
        return ", color: " + getColor() + " ,Number of Wheels: " + String.valueOf(wheels.getNumOfWheels()) + " ,Bicycle Type: " + type.name();
    }

    /**
     * Getter - return number of wheels
     * @return
     * Int type number of wheels
     */
    public int getNumOfWheels() {
        return wheels.getNumOfWheels();
    }

    /**
     * Setter -  sets number of wheels
     * @param num
     * Int type number of wheels
     * @return
     * Boolean True - if set is succeed else false
     */
    public boolean setNumOfWheels(int num) {
        if (wheels.setNumOfWheels(num))return true;
        else return false;
    }

    @Override
    public Racer clone() {
        return new Bicycle(getName(),getMaxSpeed(),getAcceleration(),getColor(),getNumOfWheels());
    }

    @Override
    public void addAtrribute(String value, Object racer) {

    }
}
