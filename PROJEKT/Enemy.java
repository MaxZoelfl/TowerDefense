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
    private int health;
    private int speed; // WICHTIG: muss ein Teiler von TILE_SIZE sein. (z.B. 2, 4, 8, 12, etc.)
    private int bounty;

    private int pathIndex = 0;
    private int tileSize;
    private int direction = 1;

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy(int tileSize, int speed) {
        this.tileSize = tileSize;
        this.speed = speed;
    }

    public void act() {

    }

    public int getPathIndex()
    {
        return pathIndex;
    }

    public void move(int[][] map)
    {
        int tileX = getX() / tileSize;
        int tileY = getY() / tileSize;

        int centerX = tileX * tileSize + tileSize / 2;
        int centerY = tileY * tileSize + tileSize / 2;

        // Prüfen ob Gegner nahe genug am Mittelpunkt des aktuellen Tiles ist
        if (Math.abs(getX() - centerX) < speed &&
        Math.abs(getY() - centerY) < speed)
        {
            // Exakt auf Mittelpunkt setzen
            setLocation(centerX, centerY);

            // Richtung des aktuellen Tiles lesen
            direction = map[tileY][tileX];
        }

        // Bewegung anhand der aktuellen Richtung
        int dx = 0;
        int dy = 0;

        switch (direction)
        {
                // RECHTS
            case 1:
            case 9:
            case 11:
                dx = speed;
                break;

                // LINKS
            case 2:
            case 10:
            case 12:
                dx = -speed;
                break;

                // HOCH
            case 3:
            case 6:
            case 8:
                dy = -speed;
                break;

                // RUNTER
            case 4:
            case 5:
            case 7:
                dy = speed;
                break;

                // ZIEL ERREICHT
            case 13:
                getWorld().removeObject(this);
                return;
        }

        setLocation(getX() + dx, getY() + dy);
    }
}
