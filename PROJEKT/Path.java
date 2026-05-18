import greenfoot.*;

/**
 * Write a description of class Weg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Path extends Tile {
    

    public Path(int size, int rotation) {
        super(size, "Path_" + rotation + ".png");
        
        setBuildable(false);
    }

}
