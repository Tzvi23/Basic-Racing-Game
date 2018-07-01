package game.racers.decorator;

/**
 * Wheeled Class to use as parameter for wheeled Racers
 * @author : Tzvi Puchinsky 
 */
public class Wheeled {

    private int numOfWheels;

    /**
     * Getter - return Number of Wheels
     * @return
     * Int type value Number of Wheels
     */
    public int getNumOfWheels(){
        return numOfWheels;
    }

    /**
     * Setter - sets Number of Wheels
     * @param numOfWheels
     * Int type Value
     * @return
     * Boolean True if Number of wheels is 2,3,4 else returns False
     */
    public boolean setNumOfWheels(int numOfWheels) {
        if (numOfWheels > 0) {
            this.numOfWheels = numOfWheels;
            return true;
        }
        else {
            System.out.println("Set NumOfWheels Failed, set to Null");
            return false;
        }
    }

    //Constructors

    /**
     * Constructor for Wheeled
     * @param numOfWheels
     * Sets number of Wheels using setNumOfWheels setter
     */
    public Wheeled(int numOfWheels){
        setNumOfWheels(numOfWheels);

    }

}
