import greenfoot.*;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    // instance variables - replace the example below with your own
    protected EnemyType enemyType;
    
    public int health;

    private int pathIndex = 0;
    private int tileSize;
    private int direction = 1;

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy(int size, EnemyType enemyType) {
        this.enemyType = enemyType;
        this.health = enemyType.health;
        
        this.tileSize = size;
        
        GreenfootImage img = new GreenfootImage(enemyType.image);
        img.scale(size, size);
        setImage(img);
    }

    public void act() {

    }

    public int getPathIndex()
    {
        return pathIndex;
    }

    public void move(int[][] map) {
        if (getWorld() == null) return;
        
        int tileX = getX() / tileSize;
        int tileY = getY() / tileSize;

        int centerX = tileX * tileSize + tileSize / 2;
        int centerY = tileY * tileSize + tileSize / 2;

        // Prüfen ob Gegner nahe genug am Mittelpunkt des aktuellen Tiles ist
        if (Math.abs(getX() - centerX) < enemyType.speed && Math.abs(getY() - centerY) < enemyType.speed) {
            // Exakt auf Mittelpunkt setzen
            setLocation(centerX, centerY);

            // Richtung des aktuellen Tiles lesen
            direction = map[tileY][tileX];
        }

        // Bewegung anhand der aktuellen Richtung
        int dx = 0;
        int dy = 0;

        switch (direction) {
                // RECHTS
            case 1:
            case 9:
            case 11:
                dx = enemyType.speed;
                setRotation(0);
                break;

                // LINKS
            case 2:
            case 10:
            case 12:
                dx = -enemyType.speed;
                setRotation(180);
                break;

                // HOCH
            case 3:
            case 6:
            case 8:
                dy = -enemyType.speed;
                setRotation(270);
                break;

                // RUNTER
            case 4:
            case 5:
            case 7:
                dy = enemyType.speed;
                setRotation(90);
                break;

                // ZIEL ERREICHT
            case 14:
                getWorld().removeObject(this);
                return;
        }

        setLocation(getX() + dx, getY() + dy);
    }
}
