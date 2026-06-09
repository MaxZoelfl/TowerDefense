import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class Projectile extends Actor {

    protected ProjectileType projectileType;
    private Enemy target;
    private int   damage;
    private int   speed = 5;

    public Projectile(ProjectileType projectileType, Enemy target, int damage) {
        this.projectileType = projectileType;
        this.target         = target;
        this.damage         = damage;
        setImage(projectileType.getImage());
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

    private void moveToTarget() {
        turnTowards(target.getX(), target.getY());
        move(speed);
    }

    private void checkCollision() {
        if (!intersects(target)) return;

        World world = getWorld();
        if (world == null) return;
        GameWorld gw = (GameWorld) world;

        int splash = projectileType.getSplashRadius();

        if (splash > 0) {
            // FIX: Kopie der Liste erstellen, um ConcurrentModificationException zu vermeiden
            List<Enemy> nearby = new ArrayList<>(world.getObjects(Enemy.class));
            for (Enemy e : nearby) {
                if (e.getWorld() == null) continue;
                double dx   = e.getX() - getX();
                double dy   = e.getY() - getY();
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist <= splash) {
                    e.takeDamage(damage, gw);   // FIX: takeDamage zahlt Bounty automatisch aus
                }
            }
        } else {
            if (target.getWorld() != null) {
                target.takeDamage(damage, gw);
            }
        }

        if (getWorld() != null) world.removeObject(this);
    }
}