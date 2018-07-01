package game.arenas.naval;

import game.arenas.Arena;
import utilities.EnumContainer;

/**
 * Naval Arena extends from abstract Arena
 * @author : Tzvi Puchinsky ID: 203195706 and Alla Kitayeva ID:336382833
 */
public class NavalArena extends Arena {

    private EnumContainer.WaterSurface surface;
    private EnumContainer.Water water;
    private EnumContainer.Body body;

    //Getters

    /**
     * Getter - Return surface of the Arena
     * @return
     * Surface - FLAT, WAVY
     */
    public EnumContainer.WaterSurface getSurface() {
        return surface;
    }

    /**
     * Getter - Return water type of the Arean
     * @return
     * Water - SALTED, SWEET
     */
    public EnumContainer.Water getWater() {
        return water;
    }

    /**
     * Getter - Return Body type of the Arena
     * @return
     * Body - SEA, LAKE, RIVER, OCEAN
     */
    public EnumContainer.Body getBody() {
        return body;
    }

    //Setters

    /**
     * Setter - set the Surface of the Arena
     * @param surface
     * Surface - FLAT, WAVY
     */
    public void setSurface(EnumContainer.WaterSurface surface) {
        this.surface = surface;
    }

    /**
     * Setter - set the water type of the Arena
     * @param water
     * Water - SALTED, SWEET
     */
    public void setWater(EnumContainer.Water water) {
        this.water = water;
    }

    /**
     * Setter - set the body type of the Arena
     * @param body
     * Body - SEA, LAKE, RIVER, OCEAN
     */
    public void setBody(EnumContainer.Body body) {
        this.body = body;
    }

    //Constructors

    /**
     * Default Constructor for Naval Arena
     */
    public NavalArena(){
        super(1000, 5, 0.7);
        setWater(EnumContainer.Water.SWEET);
        setSurface(EnumContainer.WaterSurface.FLAT);
        setBody(EnumContainer.Body.LAKE);
    }

    /**
     * Constructor for Naval Arena
     * @param length
     * Lenght of the Race
     * @param maxRacers
     * Max number of Racer for the Arena
     */
    public NavalArena(double length, int maxRacers){
        super(length, maxRacers, 0.7);
        //Default values
        setWater(EnumContainer.Water.SWEET);
        setSurface(EnumContainer.WaterSurface.FLAT);
        setBody(EnumContainer.Body.LAKE);
    }
}
