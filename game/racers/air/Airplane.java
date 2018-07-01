package game.racers.air;
import game.racers.Racer;
import game.racers.decorator.Wheeled;
import game.racers.prototype.Prototype;
import utilities.EnumContainer;

/**
 * Airplane class extends Racer type
 * @author : Tzvi Puchinsky 
 */

public class Airplane extends Racer implements AerialRacer, Prototype {

    private final int DefaultNumberOfWheels = 3;
    private Wheeled wheels;

    /**
     * Default Constructor
     */
    public Airplane(){
        super();
        setName("Airplane #" + getSerialNumber());
        setMaxSpeed(885);
        setAcceleration(100);
        setColor(EnumContainer.Color.BLACK);
        wheels = new Wheeled(DefaultNumberOfWheels);
    }

    /**
     * Constructor for AirPlane
     * @param name
     * String Name for Racer
     * @param maxSpeed
     * Double type maxSpeed for Racer
     * @param acceleration
     * Double type acceleration for Racer
     * @param color
     * Enum type color for Racer
     * @param numOfWheels
     * Int type Number of Wheels for Racer
     */
    public Airplane(String name, double maxSpeed, double acceleration, EnumContainer.Color color, int numOfWheels) {
        super(name, maxSpeed, acceleration, color);
        wheels = new Wheeled(numOfWheels);
    }

    /**
     * Overrride for describeSpecif  - adds color and number of wheels
     * @return
     * Stirng color: number of Wheels:
     */
    @Override
    public String describeSpecific() {
        return ", color: " + getColor() + " ,Number of Wheels: " + String.valueOf(wheels.getNumOfWheels());
    }

    /**
     * Getter - gets number of wheels
     * @return
     * Int type number of wheels
     */
    public int getNumOfWheels() {
        return wheels.getNumOfWheels();
    }

    /**
     * Setter - sets number of wheels
     * @param num
     * Int type number of wheels
     * @return
     * Boolean trues if succeed to set number of wheels else false
     */
    public boolean setNumOfWheels(int num) {
        if (wheels.setNumOfWheels(num))return true;
        else return false;
    }

    @Override
    public Racer clone() {
        return new Airplane(getName(),getMaxSpeed(),getAcceleration(),getColor(),getNumOfWheels());
    }

    @Override
    public void addAtrribute(String value, Object racer) {

    }

//    @Override
//    public void addAtrribute(String value, Object racer) {
//        if (!decoration.containsKey("numOfWheels")) {
//            Scanner reader = new Scanner(System.in);  // Reading from System.in
//            System.out.println("Enter a number of wheels: ");
//            int n = reader.nextInt();
//            IRacer numWheels = new WheeledRacer(this,n);
//            ArrayList list = new ArrayList();
//            list.add(numWheels);
//            decoration.put("numOfWheels", list);
//            reader.close();
//        }
//        else{
//            Scanner reader = new Scanner(System.in);  // Reading from System.in
//            System.out.println("Enter a number of wheels: ");
//            int n = reader.nextInt();
//            IRacer numWheels = new WheeledRacer(this,n);
//            ArrayList list = (ArrayList)decoration.get("numOfWheels");
//            list.add(numWheels);
//            decoration.put("numOfWheels",list);
//        }
//
//        if (!decoration.containsKey("color")) {
//            Scanner reader = new Scanner(System.in);
//            System.out.println("Enter number of color:");
//            String n = reader.next();
//            IRacer color = new ColoredRacer(this,EnumContainer.Color.valueOf(n));
//            ArrayList list = new ArrayList();
//            list.add(color);
//            decoration.put("color", list);
//            reader.close();
//        }
//        else{
//            Scanner reader = new Scanner(System.in);
//            System.out.println("Enter number of color:");
//            String n = reader.next();
//            IRacer color = new ColoredRacer(this,EnumContainer.Color.valueOf(n));
//            ArrayList list = (ArrayList)decoration.get("color");
//            list.add(color);
//            decoration.put("color",list);
//            reader.close();
//        }
//    }
}
