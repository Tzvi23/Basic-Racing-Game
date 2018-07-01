package game.arenas.air;

import game.arenas.Arena;
import utilities.EnumContainer;

/**
 * Aerial Arena extends from abstract Arena
 * @author : Tzvi Puchinsky ID: 203195706 and Alla Kitayeva ID:336382833
 */
public class AerialArena extends Arena {

    private EnumContainer.Vision Vision;
    private EnumContainer.Weather Weather;
    private EnumContainer.Height Height;
    private EnumContainer.Wind Wind;

    //Getters

    /**
     * Getter - returns Vision Type enum
     * @return
     * Type of vision in arena (enum)
     */
    public EnumContainer.Vision getVision() {
        return Vision;
    }

    /**
     * Getter - returns Weather Type enum
     * @return
     * Type of weather in arena (enum)
     */
    public EnumContainer.Weather getWeather() {
        return Weather;
    }

    /**
     * Getter - returns Height Type enum
     * @return
     * Height of the arena in arena (enum)
     */
    public EnumContainer.Height getHeight() {
        return Height;
    }

    /**
     * Getter - returns wind type enum
     * @return
     * Wind of the arena (enum)
     */
    public EnumContainer.Wind getWind() {
        return Wind;
    }

    //Setters

    /**
     * Setter - set the Vision of the Arena
     * @param vision
     * Enum type can be CLOUDS, SUNNY, FOG
     * @return
     * Returns boolean true after set
     */
    public boolean setVision(EnumContainer.Vision vision) {
        Vision = vision;
        return true;
    }

    /**
     * Setter - set the Weather of the Arena
     * @param weather
     * Enum type can be DRY, RAIN, SNOW
     * @return
     * Returns boolean true after set
     */
    public boolean setWeather(EnumContainer.Weather weather) {
        Weather = weather;
        return true;
    }

    /**
     * Setter - set the Height of the Arena
     * @param height
     * Enum type can be LOW, MEDIUM, HIGH
     * @return
     * Returns boolean true after set
     */
    public boolean setHeight(EnumContainer.Height height) {
        Height = height;
        return true;
    }

    /**
     * Setter - set the Wind of the Arena
     * @param wind
     * Enum type can be LOW, MEDIUM, HIGH
     * @return
     * Returns boolean true after set
     */
    public boolean setWind(EnumContainer.Wind wind) {
        Wind = wind;
        return true;
    }

    //Constructor
    //Default Values

    /**
     * Default Constructor for Aerial Arena
     */
    public AerialArena(){
        super(1500,4,0.4);
        setVision(EnumContainer.Vision.SUNNY);
        setWeather(EnumContainer.Weather.DRY);
        setHeight(EnumContainer.Height.HIGH);
        setWind(EnumContainer.Wind.HIGH);
    }

    /**
     * Constructor for Aerial Arena
     * @param length
     * Length of the Arena Race
     * @param maxRacers
     * Number of max racers for Arena
     */
    public AerialArena(double length, int maxRacers){
        super(length, maxRacers, 0.4);
        setVision(EnumContainer.Vision.SUNNY);
        setWeather(EnumContainer.Weather.DRY);
        setHeight(EnumContainer.Height.HIGH);
        setWind(EnumContainer.Wind.HIGH);
    }



}
