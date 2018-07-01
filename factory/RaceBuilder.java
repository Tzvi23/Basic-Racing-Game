package factory;

import game.arenas.Arena;
import game.racers.Racer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Race Builder is a singelton that can Build Arena, Racer and Wheeled Racer (Racer that have also Wheel parameter)
 * @author : Tzvi Puchinsky 
 */
public class RaceBuilder {

    private static RaceBuilder single_instance = null;

    /**
     * Checks if single_instance is not null. If null creates new object, if not return the instance
     *
     * @return single_instance
     */
    public static RaceBuilder getInstance() {
        if (single_instance == null)
            single_instance = new RaceBuilder();

        return single_instance;
    }


    /**
     * Singleton Constructor
     */
    private RaceBuilder(){

    }

    /**
     * Builds arena - using Reflection -  need to provide the path to arena type builder
     * @param arenaType
     * String type - path to package class to get constructor
     * @param length
     * length of the arena
     * @param maxRacers
     * number of maximum racers arena can take
     * @return
     * Return Racer Object
     * @throws ClassNotFoundException
     * Exception
     * @throws NoSuchMethodException
     * Exception
     * @throws IllegalAccessException
     * Exception
     * @throws InvocationTargetException
     * Exception
     * @throws InstantiationException
     * Exception
     */
    public Arena buildArena(String arenaType, double length, int maxRacers) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class c ;
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        c = cl.loadClass(arenaType);
        Constructor con = c.getConstructor(double.class, int.class);
        return (Arena) con.newInstance(length, maxRacers);


        }

    /**
     * Builds Racer object using reflection path for constructor
     * @param racerType
     * path to package class constructor
     * @param name
     * String name of the racer to be built
     * @param maxSpeed
     * double max Speed of the racer
     * @param acceleration
     * double acceleration of the racer
     * @param color
     * color of the racer using enum
     * @return
     * Return Racer Object
     * @throws ClassNotFoundException
     * Exception
     * @throws NoSuchMethodException
     * Exception
     * @throws IllegalAccessException
     * Exception
     * @throws InvocationTargetException
     * Exception
     * @throws InstantiationException
     * Exception
     */
    public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class c ;
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        c = cl.loadClass(racerType);
        Constructor con = c.getConstructor(String.class, double.class, double.class, utilities.EnumContainer.Color.class );
        return (Racer) con.newInstance(name, maxSpeed, acceleration, color);

    }

    /**
     * Builder for Racers objects that have wheels object in them, using reflection path for constructor
     * @param racerType
     * path to package class constructor
     * @param name
     * String name of the racer to be built
     * @param maxSpeed
     * double max Speed of the racer
     * @param acceleration
     * double acceleration of the racer
     * @param color
     * color of the racer using enum
     * @return
     * Return Racer Object
     * @throws ClassNotFoundException
     * Exception
     * @throws NoSuchMethodException
     * Exception
     * @throws IllegalAccessException
     * Exception
     * @throws InvocationTargetException
     * Exception
     * @throws InstantiationException
     * Exception
     */
    public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, utilities.EnumContainer.Color color, int numOfWheels) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class c ;
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        c = cl.loadClass(racerType);
        Constructor con = c.getConstructor(String.class, double.class, double.class, utilities.EnumContainer.Color.class, int.class );
        return (Racer) con.newInstance(name, maxSpeed, acceleration, color, numOfWheels);
    }
}
