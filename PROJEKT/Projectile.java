import greenfoot.*;
/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{
    // instance variables - replace the example below with your own
    private Enemy target;
    private int speed = 5;

    /**
     * Constructor for objects of class Projectile
     */
    public Projectile(Enemy enemy) {
        target = enemy;
    }

    public void act() {
        if (getWorld() == null) return;
        if (target == null || target.getWorld() == null) {
            getWorld().removeObject(this);
            return;
        }
        
        moveToTarget();
        checkCollision();
    }

    public void moveToTarget()
    {
        turnTowards(target.getX(), target.getY());
        move(speed);
    }
    
    private void checkCollision() {
        if (intersects(target)) {
            World world = getWorld();
            
            if (target.getWorld() != null) {
                world.removeObject(target);
            }

            if (getWorld() != null) {
                world.removeObject(this);
            }
        }
    }
}
