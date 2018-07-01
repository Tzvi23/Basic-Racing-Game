package game.arenas.land;

import game.arenas.Arena;
import utilities.EnumContainer;

/**
 * LandArena extends from abstract Arena
 * @author : Tzvi Puchinsky ID: 203195706 and Alla Kitayeva ID:336382833
 */
public class LandArena extends Arena {

    private EnumContainer.Coverage coverage;
    private EnumContainer.LandSurface surface;

    //Getters

    /**
     * Getter - return coverage enum of the Arena
     * @return
     * Coverage - SAND, GRASS, MUD
     */
    public EnumContainer.Coverage getCoverage() {
        return coverage;
    }

    /**
     * Getter - return surface enum of the Arena
     * @return
     * Surface - FLAT, MOUNTAIN
     */
    public EnumContainer.LandSurface getSurface() {
        return surface;
    }

    //Setters

    /**
     * Setter - sets coverage for the Arena
     * @param coverage
     * Coverage - SAND, GRASS, MUD
     * @return
     * Boolean true after statement
     */
    private boolean setCoverage(EnumContainer.Coverage coverage) {
        this.coverage = coverage;
        return true;
    }

    /**
     * Setter - sets surface for the Arena
     * @param surface
     * Surface - FLAT, MOUNTAIN
     * @return
     * Boolean true after statement
     */
    private boolean setSurface(EnumContainer.LandSurface surface) {
        this.surface = surface;
        return true;
    }

    //Constructors
    //Default Values

    /**
     * Default Constructor for Land Arena
     */
    public LandArena(){
        super(800, 8, 0.5);
        setCoverage(EnumContainer.Coverage.GRASS);
        setSurface(EnumContainer.LandSurface.FLAT);
    }

    /**
     * Constructor for Land Arena
     * @param lenght
     * Double lenght of the Race
     * @param maxRacers
     * Maximum Racers for Arena
     */
    public LandArena(double lenght, int maxRacers){
        super(lenght , maxRacers, 0.5);
        setCoverage(EnumContainer.Coverage.GRASS);
        setSurface(EnumContainer.LandSurface.FLAT);
    }
}
