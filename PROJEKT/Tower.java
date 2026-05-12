import greenfoot.*;
import java.util.List;
/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower extends Actor
{
    // instance variables - replace the example below with your own
    protected int size;

    protected int xPos;
    protected int yPos;

    protected int range;
    protected int damage;
    protected int cooldown;

    protected Enemy target;

    /**
     * Constructor for objects of class Tower
     */
    public Tower(int range, int damage, int cooldown, int size){
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.size = size;
    }

    public int gibRange() {
        return range;
    }

    public void attack() {
        List<Enemy> enemies = getObjectsInRange(range, Enemy.class);
        target = null;
        int maxProgress = -1;

        for (Enemy e : enemies) {
            if (e.getPathIndex() > maxProgress) {
                maxProgress = e.getPathIndex();
                target = e;
            }
        }

        if (target != null) {
            shoot(target);
        }
        
    }

    public void shoot(Enemy target) {
        Bullet b = new Bullet(target);
        getWorld().addObject(b, getX(), getY());
        
        Greenfoot.delay(128);
    }

}
