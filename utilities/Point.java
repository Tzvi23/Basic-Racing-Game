package utilities;


/**
 * Basic Point Class: has moving double x,y and final MIN_X,MAX_X,MIN_Y,MAX_Y
 * @author : Tzvi Puchinsky 
 */
public class Point {
    private double x;
    private double y;
    private final double MIN_X;
    private final double MAX_X;
    private final double MIN_Y;
    private final double MAX_Y;

    /**
     * Constructor
     * @param _x
     * Coordinates of x
     * @param _y
     * Coordinates of y
     */
    public Point(double _x,double _y){
        MIN_X = 0;
        MAX_X = 10000000;
        MIN_Y = 0;
        MAX_Y = 800;
        setX(_x);
        setY(_y);
    }

    /**
     * Copy Constructor
     * @param other
     * Other Point object
     */
    public Point(Point other){
        MIN_X = 0;
        MAX_X = 10000000;
        MIN_Y = 0;
        MAX_Y = 800;
        setX(other.getX());
        setY(other.getY());
    }

    /**
     * Return String
     * @return String "(X,Y)"
     */
    public String toString(){
        return "(" + getX() + "," + getY() + ")";
    }


    /**
     * Sets the coordinate if its in the range between max and min else sets min max if needed
     * @param x
     * X coordinate
     * @return True if set successful else false
     */
    public boolean setX(double x) {
        if (MIN_X <= x && x <= MAX_X){
            this.x = x;
            return true;
        }
        else return false;
    }

    /**
     * Sets the coordinate if its in the range between max and min else sets min max if needed
     * @param y
     * X coordinate
     */
    public void setY(double y) {
        if (MIN_Y <= y && y <= MAX_Y)this.y = y;
        else if (y < MIN_Y)this.y = MIN_Y;
        else this.y = MAX_X;
    }

    /**
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }
}
