import greenfoot.*;
/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor {
    protected int size;

    /**
     * Constructor for objects of class Tile
     */
    public Tile(int size) {
        this.size = size;
        getImage().scale(size, size);
    }

}
