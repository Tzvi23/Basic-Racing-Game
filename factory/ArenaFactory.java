package factory;


import game.arenas.Arena;

import java.lang.reflect.InvocationTargetException;
//@author : Tzvi Puchinsky 
/**
 * Factory Method - Builds Arena Using String request
 * Returns Arena Object
 */
public class ArenaFactory {

    private RaceBuilder builder = RaceBuilder.getInstance();

    public Arena getArena(String arenaType, double arenaLength, int maxRacers) {
        if (arenaType == null) {
            return null;
        }
        try {

            if (arenaType.equalsIgnoreCase("Land")) {
                return builder.buildArena("game.arenas.land.LandArena", arenaLength, maxRacers);
            } else if (arenaType.equalsIgnoreCase("Air")) {
                return builder.buildArena("game.arenas.air.AerialArena", arenaLength, maxRacers);
            } else if (arenaType.equalsIgnoreCase("Navy")) {
                return builder.buildArena("game.arenas.naval.NavalArena", arenaLength, maxRacers);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}


