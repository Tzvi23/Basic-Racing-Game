package game.racers.land;

import game.racers.Racer;
import game.racers.decorator.Wheeled;
import game.racers.prototype.Prototype;
import utilities.EnumContainer;

/**
 * Car class extends abstract Racer class
 * @author : Tzvi Puchinsky 
 */
public class Car extends Racer implements LandRacer, Prototype {

    private final int DefaultNumberOfWheels = 4;
    private Wheeled wheels;

    private EnumContainer.Engine engine;

    /**
     * Default Constructor
     */
    public Car(){
        super();
        setName("Car #" + getSerialNumber());
        setMaxSpeed(400);
        setAcceleration(20);
        setColor(EnumContainer.Color.RED);
        setEngine(EnumContainer.Engine.FOURSTROKE);
        wheels = new Wheeled(DefaultNumberOfWheels);
    }

    /**
     * Car constructor
     * @param name
     * String name for Racer
     * @param maxSpeed
     * Double max Speed for Racer
     * @param acceleration
     * Double acceleration for Racer
     * @param color
     * Enum type Color for Racer
     * @param numOfWheels
     * Int number of wheels
     */
    public Car(String name, double maxSpeed, double acceleration, EnumContainer.Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        setEngine(EnumContainer.Engine.FOURSTROKE);
        wheels = new Wheeled(numOfWheels);
    }

    /**
     * Getter - get Engine enum type
     * @return
     * Engine enum - FOURSTROKE, TYPE, STRAIGHT, BOXER, ROTARY
     */
    public EnumContainer.Engine getEngine() {
        return engine;
    }

    /**
     * Setter - set engine enum value
     * @param type
     * Enum type - FOURSTROKE, TYPE, STRAIGHT, BOXER, ROTARY
     * @return
     * Boolean true after statement
     */
    public boolean setEngine(EnumContainer.Engine type){
        this.engine = type;
        return true;
    }

    /**
     * Getter - return number of wheels
     * @return
     * Int type Number of wheels
     */
    public int getNumOfWheels() {
        return wheels.getNumOfWheels();
    }

    /**
     * Setter - sets Number of Wheels
     * @param num
     * Int number of wheels
     * @return
     * Boolean True after set succeed else false
     */
    public boolean setNumOfWheels(int num) {
        if (wheels.setNumOfWheels(num))return true;
        else return false;
    }

    public void setDefaultName(){
        setName("Car #" + getSerialNumber());
    }
    /**
     * Override abstract describeSpecific
     * @return
     * String color: Number Of wheels: Engine Type:
     */
    @Override
    public String describeSpecific() {
        return ", color: " + getColor() + " ,Number of Wheels: " + String.valueOf(wheels.getNumOfWheels() + " , Engine Type: " + getEngine());
    }

    @Override
    public Racer clone() {
        return new Car(getName(),getMaxSpeed(),getAcceleration(),getColor(),getNumOfWheels());
    }

    @Override
    public void addAtrribute(String value, Object racer) {

    }
}
