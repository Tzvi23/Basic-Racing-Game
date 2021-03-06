package game.racers.naval;

import game.racers.Racer;
import game.racers.prototype.Prototype;
import utilities.EnumContainer;

/**
 * RowBoat Racer Class extends abstract Racer Class
 * @author : Tzvi Puchinsky 
 */
public class RowBoat extends Racer implements NavalRacer, Prototype {

    private EnumContainer.NType type;
    private EnumContainer.Team team;

    /**
     * Default RowBoat
     */
    public RowBoat(){
        super();
        setName("RowBoat #" + getSerialNumber());
        setMaxSpeed(75);
        setAcceleration(10);
        setColor(EnumContainer.Color.RED);
        setType(EnumContainer.NType.SKULLING);
        setTeam(EnumContainer.Team.SINGLE);
    }

    /**
     * Constructor
     * @param name
     * String name for Racer
     * @param maxSpeed
     * Double Max speed for Racer
     * @param acceleration
     * Double Acceleration for Racer
     * @param color
     * Enum type color for Racer
     */
    public RowBoat(String name, double maxSpeed, double acceleration, EnumContainer.Color color) {
        super(name, maxSpeed, acceleration, color);
        //Default values
        setType(EnumContainer.NType.SKULLING);
        setTeam(EnumContainer.Team.SINGLE);
    }

    //Getters

    /**
     * Getter - return Naval Type Enum
     * @return
     * Naval Type  - SKULLING, SWEEP
     */
    public EnumContainer.NType getType() {
        return type;
    }

    /**
     * Getter - return Team Enum
     * @return
     * Team enum - SINGLE, DOUBLE, QUAD, EIGHT
     */
    public EnumContainer.Team getTeam() {
        return team;
    }

    //Setters

    /**
     * Setter - set Type Enum
     * @param type
     * Ntype - SKULLING, SWEEP
     */
    public void setType(EnumContainer.NType type) {
        this.type = type;
    }

    /**
     * Setter - sets Team Enum
     * @param team
     * Team enum - SINGLE, DOUBLE, QUAD, EIGHT
     */
    public void setTeam(EnumContainer.Team team) {
        this.team = team;
    }

    /**
     * Override abstract describeSpecific function from Racer Class
     * @return
     * String Type: Team:
     */
    @Override
    public String describeSpecific() {
        return " ,Type: " + type.name() + " ,Team: " + team.name();
    }

    @Override
    public Racer clone() {
        return new RowBoat(getName(),getMaxSpeed(),getAcceleration(),getColor());
    }

    @Override
    public void addAtrribute(String value, Object racer) {

    }
}
