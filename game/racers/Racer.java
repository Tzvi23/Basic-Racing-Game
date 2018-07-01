package game.racers;

import game.arenas.Arena;
import game.racers.state.ActiveState;
import game.racers.state.racerState;
import utilities.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;

/**
 * Abstract Class Racer for all the Racers type in the game and functions
 * @author : Tzvi Puchinsky 
 */
public abstract class Racer extends Observable implements Runnable, IRacer{
    private static int counter = 0;
    private int serialNumber;
    private String name;
    private Point currentLocation;
    private Point finish;
    private double maxSpeed;
    private double acceleration;
    private double currentSpeed;
    private double failureProbability;
    private EnumContainer.Color color;
    private Arena arena;

    private Mishap breakdown;

    protected Hashtable decoration = new Hashtable();

    private racerState state;

    private long startTime;
    private long endTime;
    private double timeElapsed;

    //Getters

    /**
     * Getter - Return Serial Number of the Racer
     * @return
     * Serial Number of the THIS racer
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Getter - Return String name of the Racer
     * @return
     * String name Racer
     */
    public String getName() {
        return name;
    }

    /**
     * Getter - Return POINT current location
     * @return
     * POINT type current location of the racer
     */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Getter - Return POINT finish
     * @return
     * POINT type finish
     */
    public Point getFinish() {
        return finish;
    }

    /**
     * Getter - returns max speed of the racer
     * @return
     * Double type max speed for racer
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Getter - returns acceleration of the racer
     * @return
     * Double type acceleration value
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * Getter - returns current speed value of Racer
     * @return
     * Double type current speed for racer value
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Getter - returns Failure Probability value
     * @return
     * Double type value for failure Probability
     */
    public double getFailureProbability() {
        return failureProbability;
    }

    /**
     * Getter - return Color Enum for racer
     * @return
     * Enum type color - RED, GREEN, BLUE, BLACK, YELLOW
     */
    public EnumContainer.Color getColor() {
        return color;
    }

    /**
     * Getter - returns arena
     * @return
     * Arena type value
     */
    public Arena getArena(){
        return arena;
    }

    /**
     * Getter - returns static counter
     * @return
     * Int type value static counter
     */
    public int getCounter(){
        return counter;
    }

    public racerState getState(){
        return state;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    //Setters

    /**
     * Setter - sets serial Number
     * @param counter
     * counter is the static variable that counts each racer that added
     * @return
     * Boolean true after statement
     */
    private boolean setSerialNumber(int counter) {
        this.serialNumber = counter;
        return true;
    }

    /**
     * Setter - sets String name for Racer
     * @param name
     * String name of the Racer
     * @return
     * Boolean true after statement
     */
    protected boolean setName(String name) {
        this.name = name;
        return true;
    }

    /**
     * Setter - sets Current Location for Racer
     * @param currentLocation
     * Current Location is Point Type
     * @return
     * Boolean true after statement
     */
    private boolean setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
        return true;
    }

    /**
     * Setter - sets Finish Point for Racer
     * @param finish
     * Finish is Point Type
     * @return
     * Boolean true after statement
     */
    private boolean setFinish(Point finish) {
        this.finish = finish;
        return true;
    }

    /**
     * Setter - sets maximum speed for Racer
     * @param maxSpeed
     * Double type value maximum Speed
     * @return
     * Boolean true after statement
     */
    protected boolean setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
        return true;
    }

    /**
     * Setter - sets acceleration value for Racer
     * @param acceleration
     * Double type value for acceleration
     * @return
     * Boolean true after statement
     */
    protected boolean setAcceleration(double acceleration) {
        this.acceleration = acceleration;
        return true;
    }

    /**
     * Setter - sets Current Speed value for Racer
     * @param currentSpeed
     * Double type value for CurrentSpeed
     * @return
     * Boolean true after statement
     */
    protected boolean setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
        return true;
    }

    /**
     * Setter - sets Failure Probability for Racer
     * @param failureProbability
     * Double type value
     * @return
     * Boolean true after statement
     */
    protected boolean setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
        return true;
    }

    /**
     * Setter - sets Color Enum for Racer
     * @param color
     * Color enum - RED, GREEN, BLUE, BLACK, YELLOW
     * @return
     * Boolean true after statement
     */
    public boolean setColor(EnumContainer.Color color) {
        this.color = color;
        return true;
    }

    /**
     * Setter - sets Arena for Racer
     * @param arena
     * Arena type variable
     * @return
     * Boolean true after statement
     */
    public boolean setArena(Arena arena){
        this.arena = arena;
        return true;
    }

    public void setState(racerState state){
        this.state = state;
    }

    //Constructor

    /**
     * Constructor for Racer object.
     * @param name
     * String name for Racer
     * @param maxSpeed
     * Double type max speed for Racer
     * @param acceleration
     * Double type acceleration for Racer
     * @param color
     * Enum type Color for Racer
     */
    public Racer(String name, double maxSpeed, double acceleration, EnumContainer.Color color){
        counter++;
        setName(name);
        setMaxSpeed(maxSpeed);
        setAcceleration(acceleration);
        setColor(color);
        setSerialNumber(counter);
        setCurrentLocation(new Point(0,0));
        setState(new ActiveState());




    }


    /**
     * Default Constructor. Still sets increments Counter and sets Serial Number
     */
    public  Racer(){
        counter++;
        setSerialNumber(counter);
        setCurrentLocation(new Point(0,0));
    }

    //Methods

    /**
     * Sets values for Racer to begin Race
     * @param arena
     * Arena in which Racer is participates
     * @param start
     * Point type value Start position
     * @param finish
     * Point type value Finish position
     */
    public void initRace(Arena arena, Point start, Point finish){
        setArena(arena);
        setCurrentLocation(new Point(start));
        setFinish(new Point(finish));
    }

    /**
     * Run override function for threading.
     * This function moves the racer across the board. Checks if Racer have breakDown, if not use fate probability
     * to decide if breakDown needed to be created.
     */
    public synchronized void run(){
        if (startTime == 0)startTime = System.currentTimeMillis(); // Sets the current start time
        try {
            while (!(currentLocation.getX() >= finish.getX())) {
                if ((breakdown != null && !breakdown.isFixable())) break;
                if (breakdown != null && breakdown.getTurnsToFix() > 0 && breakdown.isFixable()) {
                    currentLocation.setX(currentLocation.getX() + (currentSpeed * breakdown.getReductionFactor()));
                    setChanged();
                    notifyObservers("Moved");
                    breakdown.nextTurn();
                    if (breakdown.getTurnsToFix() == 0) {
                        setChanged();
                        notifyObservers(EnumContainer.RacerEvent.REPAIRED);
                    }
                    if (breakdown != null && breakdown.getTurnsToFix() == 0) breakdown = null;
                } else {
                    if ((breakdown != null && breakdown.isFixable()) || breakdown == null) {
                        //~~~~~~~ BreakDown ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       if (Fate.breakDown(0.05)) {
                           breakdown = Fate.generateMishap();
                           System.out.println(getName() + " Has a new mishap!" + breakdown);
                           if (!breakdown.isFixable()) {
                               setChanged();
                               notifyObservers(EnumContainer.RacerEvent.DISABLED);
                               break;
                           } else {
                               setChanged();
                               endTime = System.currentTimeMillis();
                               timeElapsed = (endTime - startTime) / 1000.0;
                               notifyObservers(EnumContainer.RacerEvent.BROKEDOWN);
                           }
                       }
                        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                        if ((breakdown != null && breakdown.isFixable())) {
                            currentLocation.setX(currentLocation.getX() + (currentSpeed * breakdown.getReductionFactor()));
                            setChanged();
                            notifyObservers("Moved");
                        } else if(breakdown == null) {
                            if (currentSpeed <= maxSpeed) {
                                currentSpeed += acceleration * arena.getFRICTION();
                                if (currentSpeed > maxSpeed) currentSpeed = maxSpeed;
                            }
                            currentLocation.setX(currentLocation.getX() + currentSpeed);
                            setChanged();
                            notifyObservers("Moved");
                        }
                    }

                }

                Thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (currentLocation.getX() >= finish.getX()) {
                setChanged();
                notifyObservers(EnumContainer.RacerEvent.FINISHED);
            }

        }


    /**
     * Abstract method to describe each specific values of each Racer
     * @return
     * String of the speficic values
     */
    public abstract String  describeSpecific();

    /**
     * Describes Racer values
     * @return
     * String name,serialnumber,maxSpeed,acceleration + describeSpecific()
     */
    public String describeRacer(){
        return
                " Name: " + getName() +
                ", SerialNumber: " + getSerialNumber() +
                ", maxSpeed: " + getMaxSpeed() +
                ", acceleration: " + getAcceleration() +
                describeSpecific() +"\n";
    }

    /**
     * Introduce the Racer using System.out. Prints name,serialnumber,maxSpeed,acceleration + describeSpecific()
     */
    public void introduce(){
        System.out.println ("[" + className() + "]" +
                " Name: " + getName() +
                ", SerialNumber: " + getSerialNumber() +
                ", maxSpeed: " + getMaxSpeed() +
                ", acceleration: " + getAcceleration() +
                describeSpecific());
    }

    /**
     * To string override for Racer
     * @return
     * describeRacer() String
     */
    @Override
    public String toString() {
        return (describeRacer());
    }

    /**
     * Return String Class Name - Racer
     * @return
     * String Class Name
     */
    public String className(){
        return this.getClass().getSimpleName();
    }

    /**
     * Method for increment of the static counter
     */
    public static void incrementCounter(){
        counter++;
    }

    /**
     * Sets the base Decoration of the racer
     * @param numOfWheels
     * If racer supposed to have default wheels
     * @param color
     * Default Color of the racer
     */
    public void setBasicDecoration(int numOfWheels, EnumContainer.Color color){
        ArrayList listNumOfWheels = new ArrayList();
        ArrayList listColors = new ArrayList();
        if (numOfWheels > 0){
            listNumOfWheels.add(numOfWheels);
            decoration.put("numOfWheels",listNumOfWheels);
        }
        listColors.add(color);
        decoration.put("color",listColors);

    }

    /**
     * Getter - returns the decoration Hash Table
     * @return
     * decoration Hash Table
     */
    public Hashtable getDecoration(){
        return decoration;
    }

    /**
     * Part of the State Design Pattern, the racer states his state
     */
    public void stateAction(){
        if (this.state != null){
            state.stateAction(this);
        }
    }

}
