import greenfoot.*;
/**
 * Write a description of class Gras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gras extends Tile
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class Gras
     */
    public Gras(int size) {
        super(size);
        drawTile();
    }
    
    private void drawTile() {
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(new Color(80, 180, 90));
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, size - 1, size - 1);
        setImage(img);
    }
}
