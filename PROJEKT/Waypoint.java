import greenfoot.*;
/**
 * Write a description of class Gras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Waypoint
{
    // instance variables - replace the example below with your own
    private int xPos;
    private int yPos;
    private Waypoint next;
    
    /**
     * Constructor for objects of class Gras
     */
    public Waypoint(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public int getX() {
        return xPos;
    }
    
    public int getY() {
        return yPos;
    }
    
    public Waypoint getNext() { 
        return next; 
    }
    
    public void setNext(Waypoint next) { 
        this.next = next; 
    }
}
