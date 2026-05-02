import greenfoot.*;
/**
 * Write a description of class Weg here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weg extends Tile  
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class Weg
     */
    public Weg(int size) {
        super(size);
        drawTile();
    }
    
    private void drawTile() {
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(new Color(139, 69, 19));
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, size - 1, size - 1);
        setImage(img);
    }
}
