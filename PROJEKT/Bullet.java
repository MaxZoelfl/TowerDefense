import greenfoot.*;
/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    // instance variables - replace the example below with your own
    private Enemy target;

    /**
     * Constructor for objects of class Projectile
     */
    public Bullet(Enemy enemy) {
        target = enemy;
    }

    public void act() {
        moveToTarget();
    }

    public void moveToTarget()
    {
        if (target == null)
        {
            getWorld().removeObject(this);
            return;
        }

        turnTowards(target.getX(), target.getY());
        move(5);

        if (intersects(target))
        {
            getWorld().removeObject(target);

            getWorld().removeObject(this);
        }
    }
}
