package utilities;

import java.util.Random;

/**
 * Fate Class generates Randoms for mishaps
 */
public class Fate {

	private static Random rand = new Random();

    /**
     * Sets seed for random values
     * @param seed
     * value for random clock
     */
	public static void setSeed(int seed) {
		rand.setSeed(seed);
	}

    /**
     * Generates int 0-10 and check if larger then 7. Return true or false
     * @return
     * Boolean True if number is larger than 7 else false
     */
	public static boolean generateFixable() {
		return rand.nextInt(10) > 2;
	}

    /**
     * Generates random float number
     * @return
     * Float Random number
     */
	private static float generateReduction() {
		return rand.nextFloat();
	}

    /**
     * Generates Random Int 0-5
     * @return
     * Int number 0-5
     */
	private static int generateTurns() {
		return rand.nextInt(5) + 1;
	}

    /**
     * Generates random boolean True/False
     * @return
     * Random True/False
     */
	public static boolean breakDown(double failureProbability) {
		return rand.nextFloat() <= failureProbability;
	}

    /**
     * Creates Mishap object using functions:generateFixable(), generateTurns(), generateReduction()
     * @return
     * Mishap Object
     */
	public static Mishap generateMishap() {
			return new Mishap(generateFixable(), generateTurns(), generateReduction());
	}

}
