import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

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
            
            if (target.getWorld() != null) {
                target.health -= damage;
                if (target.health <= 0) {
                    world.removeObject(target);
                }
            }

            if (getWorld() != null) {
                world.removeObject(this);
            }
        }
    }
}
