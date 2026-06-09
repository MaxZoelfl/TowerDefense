import greenfoot.*;
import java.util.List;

public class Tower extends Actor {

    protected TowerType towerType;
    private int cooldownTimer = 0;
    protected Enemy target;

    public Tower(int size, TowerType towerType) {
        this.towerType = towerType;
        GreenfootImage img = new GreenfootImage(towerType.getImage());
        img.scale(size, size);
        setImage(img);
    }

    public void act() {
        GameWorld world = (GameWorld) getWorld();
        if (world == null || world.getPaused()) return;

        if (cooldownTimer > 0) { cooldownTimer--; return; }
        attack();
    }

    public void attack() {
        List<Enemy> inRange = getObjectsInRange(towerType.range, Enemy.class);
        target = null;
        int maxProgress = -1;

        // Gegner mit höchstem Pfad-Fortschritt wird priorisiert
        for (Enemy e : inRange) {
            if (e.getPathIndex() > maxProgress) {
                maxProgress = e.getPathIndex();
                target = e;
            }
        }

        if (target != null) {
            turnTowards(target.getX(), target.getY());
            shoot(target);
            cooldownTimer = towerType.cooldown;
        }
    }

    public void shoot(Enemy t) {
        Projectile p = new Projectile(towerType.projectileType, t, towerType.damage);
        getWorld().addObject(p, getX(), getY());
    }
}