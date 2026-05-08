import greenfoot.*;
/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor {
    
    protected int size;
    protected boolean bebaubar;
    
    public Tile(int size) {
        this.size = size;
    }
    
    public boolean istBebaubar() {
        return bebaubar;
    }
    
    public void setzeBebaubar(boolean bebaubar) {
        this.bebaubar = bebaubar;
    }
}

/**
 *     public void drawTile(Color fillColor) {
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(fillColor);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, size - 1, size - 1);
        setImage(img);
    }
 */