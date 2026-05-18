import greenfoot.*;
/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor {
    
    protected int size;
    protected boolean buildable;
    protected boolean occupied;
    
    public Tile(int size, String image) {
        this.size = size;
        this.occupied = false;
        
        GreenfootImage img = new GreenfootImage(image);
        img.scale(size, size);
        setImage(img);
    }
    
    public boolean isBuildable() {
        return buildable;
    }
    
    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }
    
    public boolean isOccupied() {
        return occupied;
    }
    
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

/*
 *     public void drawTile(Color fillColor) {
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(fillColor);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, size - 1, size - 1);
        setImage(img);
    }
 */