package factory;

import game.arenas.Arena;
import game.racers.Racer;
import game.racers.land.Car;
import game.racers.prototype.PrototypeFactory;

import java.util.ArrayList;
//@author : Tzvi Puchinsky 
/**
 * Builder Design Pattern - Builds entire Race includes Land Arena and Racers using prototype
 */
public class CarRaceBuilder {
    private Arena carArena;

    public static class Builder{
        private Arena carArena;
        private ArrayList<Racer> racers;

        /**
         * Builds the Arena
         * @param arenaType
         * String - Wich arena to build
         * @param maxRacers
         * Number of racers needed
         * @return
         * This Builder to use Builder Design Pattern
         */
        public Builder setArena(String arenaType, int maxRacers){
            ArenaFactory factory = new ArenaFactory();
            this.carArena = factory.getArena(arenaType,1000,maxRacers);
            return this;
        }

        /**
         * Adds Racers to arena using Prototype
         * @return
         * This Builder to use Builder Design Pattern
         */
        public Builder setRacerUsingPrototype(){
            racers = new ArrayList<>();
            Car defaultCar = new Car();
            racers.add(defaultCar);
            PrototypeFactory.setPrototypes("Car",defaultCar);
            for (int i =1 ; i < carArena.getMAX_RACERS(); i++){
                Racer clone = PrototypeFactory.getPrototype("Car");
                if (clone instanceof Car){
                    ((Car) clone).setDefaultName();
                }
                racers.add(clone);
            }
            carArena.setActiveRacers(racers);
            return this;
        }

        /**
         * The functions build the CarRace object and sets it as the Object of the super Class
         * @return
         * Car Race Builder - Car Race
         */
        public CarRaceBuilder build(){
            CarRaceBuilder race = new CarRaceBuilder();
            race.carArena = this.carArena;
            return race;
        }
    }

    /**
     * Getter - return this Car Arena
     * @return
     * Car Arena
     */
    public Arena getCarArena(){
        return this.carArena;
    }
    //Private Constructor
    private CarRaceBuilder(){

    }

}
