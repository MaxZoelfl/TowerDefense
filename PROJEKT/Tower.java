import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.List;

public class Tower extends Actor
{
    protected TowerType towerType;
    private int cooldownTimer;

    protected Enemy target;

    public Tower(int size, TowerType towerType) {
        this.towerType = towerType;
        this.cooldownTimer = 0;

        GreenfootImage img = new GreenfootImage(towerType.getImage());
        img.scale(size, size);
        setImage(img);
    }

    public void act() {
        
        GameWorld world = (GameWorld) getWorld();
        
        if (world.getPaused() == false) {
            
            if (cooldownTimer > 0) {
                cooldownTimer--;
            }
    
            if (cooldownTimer == 0) {
                attack();
            }
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
