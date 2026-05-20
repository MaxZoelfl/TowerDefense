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
    protected TowerType towerType;
    private int cooldownTimer;

    protected Enemy target;

    /**
     * Constructor for objects of class Tower
     */
    public Tower(int size, TowerType towerType) {
        this.towerType = towerType;
        this.cooldownTimer = 0;

        GreenfootImage img = new GreenfootImage(towerType.getImage());
        img.scale(size, size);
        setImage(img);
    }

    public void act() {
        if (cooldownTimer > 0) {
            cooldownTimer--;
        }

        if (cooldownTimer == 0) {
            attack();
        }
    }

    public void attack() {
        List<Enemy> enemies = getObjectsInRange(towerType.range, Enemy.class);

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
            cooldownTimer = towerType.cooldown;
        }
    }

    public void shoot(Enemy target) {
        Projectile p = new Projectile(towerType.projectileType, target, towerType.damage);
        getWorld().addObject(p, getX(), getY());
    }
}
