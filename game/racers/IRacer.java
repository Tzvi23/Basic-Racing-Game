package game.racers;


import java.util.Hashtable;
//@author : Tzvi Puchinsky 
public interface IRacer {
    void addAtrribute(String value, Object racer);

    Hashtable getDecoration();
}
