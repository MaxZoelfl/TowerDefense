import greenfoot.*;
/**
 * Write a description of class Weg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Path extends Tile {

    public Path(int size, int rotation) {
        super(size);

        GreenfootImage img = new GreenfootImage("Path.png");
        img.scale(64, 64);
        setImage(img);
        setRotation(rotation);
        
        setzeBebaubar(false);
    }

}
