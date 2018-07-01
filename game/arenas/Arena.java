package game.arenas;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.Car;
import game.racers.state.ActiveState;
import game.racers.state.BrokenState;
import game.racers.state.CompletedState;
import game.racers.state.DisabledState;
import utilities.EnumContainer;
import utilities.Point;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Arena class that holds all the racers and arena length to manage the race. Also implements Observer to watch
 * over Racers events during race.
 * @author : Tzvi Puchinsky ID: 203195706 and Alla Kitayeva ID:336382833
 */
public abstract class Arena implements Observer {
    private ArrayList<Racer> activeRacers;
    private ArrayList<Racer> completedRacers;
    private ArrayList<Racer> brokenRacers;
    private ArrayList<Racer> disabledRacers;
    private final double FRICTION;
    private final int MAX_RACERS;
    private final static int MIN_Y_GAP = 10;
    private double length;

    private ArrayList<Racer> racerPlace;

    private String events = "";

    public void updatePlace(){
        this.racerPlace = new ArrayList<>(); //Creating table for the places of the racers
        if (activeRacers != null){
            racerPlace.addAll(activeRacers);
        }
        if (completedRacers != null){
            racerPlace.addAll(completedRacers);
        }
        if (brokenRacers != null){
            racerPlace.addAll(brokenRacers);
        }
        if (disabledRacers != null){
            racerPlace.addAll(disabledRacers);
        }
        for (int i = 0 ; i < racerPlace.size() ; i++){
            for (int j = 0; j < racerPlace.size() - 1; j++){
                if (racerPlace.get(j).getCurrentLocation().getX() < racerPlace.get(j+1).getCurrentLocation().getX()){
                    Racer temp = racerPlace.get(j);
                    racerPlace.set(j,racerPlace.get(j+1));
                    racerPlace.set(j+1,temp);
                }
            }
        }
    }

    public int returnPlace(Racer racer){
        if (racerPlace != null){
            return racerPlace.indexOf(racer) + 1;
        }
        return -1;
    }

    //Getters

    /**
     * Getter - ActiveRacers
     * @return
     * Return activeRacers array
     */
    public ArrayList<Racer> getActiveRacers() {
        return activeRacers;
    }

    /**
     * Getter - CompleteRacers
     * @return
     * Return completedRacers array
     */
    public ArrayList<Racer> getCompletedRacers() {
        return completedRacers;
    }

    /**
     * Getter - FRICTION
     * @return
     * Return Friction variable
     */
    public double getFRICTION() {
        return FRICTION;
    }

    /**
     * Getter - Max Racers
     * @return
     * Return int Max Racers
     */
    public int getMAX_RACERS() {
        return MAX_RACERS;
    }

    /**
     * Getter - Min Y Gap  variable
     * @return
     * Return int get Min Y Gap variable
     */
    public static int getMinYGap() {
        return MIN_Y_GAP;
    }

    /**
     * Getter - Length variable
     * @return
     * Return double Length variable
     */
    public double getLength() {
        return length;
    }

    public ArrayList<Racer> getBrokenRacers() {
        return brokenRacers;
    }

    public ArrayList<Racer> getDisabledRacers() {
        return disabledRacers;
    }

    public String getEvents(){
        return events;
    }

    //Setters

    /**
     * Setter - set activeRacers
     * @param activeRacers
     * Array of active Racers in Arena
     * @return
     * Return Boolean True after sets
     */
    public boolean setActiveRacers(ArrayList<Racer> activeRacers) {
        this.activeRacers = activeRacers;
        return true;
    }

    /**
     * Setter - set completedRacers
     * @param completedRacers
     * Array of completedRacers the finished the race
     * @return
     * Return Boolean True after set
     */
    public boolean setCompletedRacers(ArrayList<Racer> completedRacers) {
        this.completedRacers = completedRacers;
        return true;
    }

    /**
     * Setter - sets the length of the arena
     * @param length
     * double length - the length of the arena
     * @return
     * Return Boolean True after set. If length < 0 returns false
     */
    public boolean setLength(double length) {
        if (length < 0)return false;
        this.length = length;
        return true;
    }

    //Constructors

    /**
     * Default Constructor for Arena. Sets FRICTION = 0; Max Racers = 0
     */
    public Arena(){
        this.FRICTION = 0;
        this.MAX_RACERS = 0;
    }

    /**
     * Constructor for Arena. Sets values for length, max Racers, friction and creates ArrayLists for activeRacers and completedRacers
     * @param length
     * Sets Length of the arena
     * @param maxRacers
     * Sets the maxRacers for the arena
     * @param friction
     * Sets friction for the arena
     */
    public Arena(double length, int maxRacers, double friction){
        setLength(length);
        this.MAX_RACERS = maxRacers;
        this.FRICTION = friction;
        this.activeRacers = new ArrayList<>();
        this.completedRacers = new ArrayList<>();
        this.brokenRacers = new ArrayList<>();
        this.disabledRacers = new ArrayList<>();


    }

    //Methods

    /**
     * Function for adding new racers type objects to arena. Check if arena if full and type of racer is defined to specific arena
     * @param NewRacer
     * Racer type object
     * @throws RacerLimitException
     * Throws exception if trying to add more racers than max Racers defined in arena
     * @throws RacerTypeException
     * Throws exception if type racer is not defined to arena
     */
    public void addRacer(Racer NewRacer) throws RacerLimitException, RacerTypeException{
        switch(this.getClass().getSimpleName()){
            case "AerialArena":
                if (!("Airplane".equals(NewRacer.className()) || "Helicopter".equals(NewRacer.className()))){
                    throw new RacerTypeException(NewRacer.className() + " for " + this.getClass().getSimpleName() );
                }
                break;
            case "LandArena":
                if (!("Bicycle".equals(NewRacer.className()) || "Car".equals(NewRacer.className()) || "Horse".equals(NewRacer.className()))){
                    if (!(NewRacer.getDecoration().containsKey("numOfWheels")))
                    throw new RacerTypeException(NewRacer.className() + " for " + this.getClass().getSimpleName() );
                }
                break;

            case "NavalArena":
            if (!("RowBoat".equals(NewRacer.className()) || "SpeedBoat".equals(NewRacer.className()))){
                throw new RacerTypeException(NewRacer.className() + " for " + this.getClass().getSimpleName() );
            }
            break;
        }


        if (activeRacers.size() < MAX_RACERS) {
            activeRacers.add(NewRacer);
            NewRacer.setArena(this);
        } else {
            throw new RacerLimitException("(" + getActiveRacers().size() + " active racers exist). racer #" + NewRacer.getSerialNumber() +
                    " was not added");
        }
    }

    /**
     * Activates all the initRace functions in all Racers in activeRacers array
     */
    public void initRace(){
        for (Racer activeRacer : activeRacers) {
            activeRacer.initRace(this, new Point(0, 0), new Point(length, MIN_Y_GAP * activeRacer.getSerialNumber()));
        }
    }

    /**
     * Checks if there any active Racers in arena
     * @return
     * Boolean True - there active Racers False - no active Racers
     */
    public boolean hasActiveRacers(){
        return activeRacers.size() != 0;
    }

    /**
     * This function manages all the race process using threadPool. Each racer starts thread for moving.
     * @param frame
     * Base Frame sends here to add as runnable object to threadPool to update the places of the icons.
     */
    public void startRace(JPanel frame){
        ArrayList<Runnable> threads = new ArrayList<>();
        for (Racer activeRacer : activeRacers)threads.add(activeRacer);
        for (Racer activeRacer : activeRacers)activeRacer.addObserver(this);
        ExecutorService  pool = Executors.newFixedThreadPool(threads.size());
        for (int i = 0 ; i < threads.size() ; i++) {
            if (threads.get(i) != null) {
                pool.execute(threads.get(i));
            }
        }
        pool.shutdown();
    }

    /**
     * Prints the score of the Race. Each Racer and its place
     */
    public void showResults(){
        int i = 0;
        for (Racer racer : completedRacers){
            System.out.print("#" + String.valueOf(i) + " -> ");
            System.out.print(racer.describeRacer());
            i ++;
        }
    }

    /**
     * Update function for Arena Observer, racer are the observables that notify the arena for each event.
     * @param o
     * Object that did the notify
     * @param arg
     * The argument for the notify
     */
    public synchronized void update(Observable o, Object arg){
        if (arg == EnumContainer.RacerEvent.BROKEDOWN){
                addBrokenRacers((Racer)o);
            ((Racer) o).setState(new BrokenState());
            ((Racer) o).stateAction();
            events = events + (((Racer) o).getName() + " is Broken! Time in Race: " + ((Racer) o).getTimeElapsed() + " seconds\n");
        } else if (arg == EnumContainer.RacerEvent.DISABLED) {
                addDisabledRacers((Racer)o);
            ((Racer) o).setState(new DisabledState());
            ((Racer) o).stateAction();
            events = events + (((Racer) o).getName() + " is Disabled! Update with Failed Status \n");
        }
        else if(arg == EnumContainer.RacerEvent.REPAIRED){
            brokenRacers.remove(o);
            activeRacers.add((Racer)o);
            ((Racer) o).setState(new ActiveState());
            ((Racer) o).stateAction();
            events = events + ("Place of " + ((Racer) o).getName() + " is: " + ((Racer) o).getArena().returnPlace(((Racer) o)) +"\n" );

        }
        else if (arg == EnumContainer.RacerEvent.FINISHED){
            activeRacers.remove(o);
            completedRacers.add((Racer) o);
            ((Racer) o).setState(new CompletedState());
            ((Racer) o).stateAction();
            events = events + (((Racer) o).getName() + " finished the Race!\n");
        }

    }

    /**
     * If Racers completed the race adds the racer object to completed racer array
     * @param racer
     * Racer Object
     */
    public void addCompletedRacers(Racer racer){
        this.completedRacers.add(racer);
    }

    /**
     * If racer get breakDown the function removes the racer from active racer array and adds to broken racer array
     * @param racer
     * Racer Object
     */
    public void addBrokenRacers(Racer racer){
        this.activeRacers.remove(racer);
        this.brokenRacers.add(racer);
    }

    /**
     * If racer gets breakDown that cannot be fixed the function removes the racer from active racers array
     * and adds the racer object to disabled racers array.
     * @param racer
     */
    public void addDisabledRacers(Racer racer){
        this.activeRacers.remove(racer);
        this.disabledRacers.add(racer);
    }
}
