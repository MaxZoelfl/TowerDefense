import greenfoot.*;
/**
 * Write a description of class Gras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grass extends Tile {
    
    public Grass(int size) {
        super(size);
        
        GreenfootImage img = new GreenfootImage("Grass.png");
        img.scale(64, 64);
        setImage(img);
        
        setzeBebaubar(true);
    }
}
