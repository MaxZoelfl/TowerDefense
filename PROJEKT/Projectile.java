import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.List;
public class Projectile extends Actor
{
    protected ProjectileType projectileType;
    private Enemy target;
    private int damage;
    private int speed = 5;

    public Projectile(ProjectileType projectileType, Enemy target, int damage) {
        this.projectileType = projectileType;

        setImage(projectileType.getImage());

        this.target = target;
        this.damage = damage; 
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

    public void moveToTarget() {
        turnTowards(target.getX(), target.getY());
        move(speed);
    }

    private void checkCollision() {
        if (intersects(target)) {
            World world = getWorld();

            if (world == null) return;
            int splash = projectileType.getSplashRadius();
            // Splash Damage
            if (splash > 0) {
                List<Enemy> enemies = world.getObjects(Enemy.class);
                for (Enemy e : enemies) {

                    double dx = e.getX() - getX();
                    double dy = e.getY() - getY();

                    double distance = Math.sqrt(dx * dx + dy * dy);

                    if (distance <= splash) {

                        e.health -= damage;

                        if (e.health <= 0 && e.getWorld() != null) {
                            world.removeObject(e);
                        }
                    }
                }
            } 
            // Single Target Damage
            else {
                if (target.getWorld() != null) {

                    target.health -= damage;

                    if (target.health <= 0) {
                        world.removeObject(target);
                    }
                }
            }

            // Projectile entfernen
            if (getWorld() != null) {
                world.removeObject(this);
            }
        }
    }
}
