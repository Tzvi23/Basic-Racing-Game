package utilities;

import java.text.DecimalFormat;

/**
 * Mishap Class - to define break downs and reduction for racers
 * @author : Tzvi Puchinsky 
 */
public class Mishap {

    private boolean fixable;
    private double reductionFactor;
    private int turnsToFix;

    //Getters

    /**
     * Getter - returns boolean true/false fixable mishap
     * @return
     * Boolean True/False
     */
    public boolean isFixable() {
        return fixable;
    }

    /**
     * Getter - return double Reduction Factor
     * @return
     * Double value reduction Factor
     */
    public double getReductionFactor() {
        return reductionFactor;
    }

    /**
     * Getter - return int Turns that needed to fix
     * @return
     * Int number of turns
     */
    public int getTurnsToFix() {
        return turnsToFix;
    }

    //Setters

    /**
     * Setter - sets Boolean if fixable mishap
     * @param fixable
     * Boolean True/False
     * @return
     * Boolean True after set statement
     */
    public boolean setFixable(boolean fixable) {
        this.fixable = fixable;
        return true;
    }

    /**
     * Setter - sets Reduction Factor
     * @param reductionFactor
     * Double value for Reduction Factor
     * @return
     * Boolean True after set statement
     */
    public boolean setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
        return true;
    }

    /**
     * Setter - Number of turns to fix
     * @param turnsToFix
     * Int value turns to fix
     * @return
     * Boolean True after set statement
     */
    public boolean setTurnsToFix(int turnsToFix) {
        this.turnsToFix = turnsToFix;
        return true;
    }

    //Constructors

    /**
     * Mishap Constructor
     * @param fixable
     * boolean True/False
     * @param turnsToFix
     * Int value Turns to fix
     * @param reductionFactor
     * Double value Reduction Factor
     */
    public Mishap(boolean fixable, int turnsToFix, double reductionFactor){
        setFixable(fixable);
        setReductionFactor(reductionFactor);
        setTurnsToFix(turnsToFix);
    }

    //Methods

    /**
     * After each turn - changes the value of turns to fix to (turnsToFix - 1)
     */
    public void nextTurn(){
        if((turnsToFix != 0) && ((turnsToFix - 1) >= 0)) {
            setTurnsToFix(turnsToFix - 1);
        }
    }

    /**
     * Override for object to print the values of Mishap
     * @return
     * String (fixable value , turnsToFix value, (0.00) reduction Factor value )
     */
    @Override
    public String toString() {
        return "(" + fixable + "," + turnsToFix + "," + new DecimalFormat("0.00").format(reductionFactor) + ")";
    }
}
