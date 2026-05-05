import greenfoot.*;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    // instance variables - replace the example below with your own
    private int xPos;
    private int yPos;
    
    private int health;
    private int speed;
    private int bounty;

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy()
    {
    }
    
    public void setzePos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public int gibX() {
        return xPos;
    }
    
    public int gibY() {
        return yPos;
    }
}
