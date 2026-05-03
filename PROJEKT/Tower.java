import greenfoot.*;
/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower extends Actor
{
    // instance variables - replace the example below with your own
    protected int range;
    protected int damage;
    protected int cooldown;

    /**
     * Constructor for objects of class Tower
     */
    public Tower(int range, int damage, int cooldown){
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
    }
    
}
