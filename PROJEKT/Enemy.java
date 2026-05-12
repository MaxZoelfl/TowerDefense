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

    public void move(int [][] map) {
        int tileX = getX() / tileSize;
        int tileY = getY() / tileSize;

        int centerX = tileX * tileSize + tileSize / 2;
        int centerY = tileY * tileSize + tileSize / 2;

        //int direction = map[tileY][tileX];

        if (Math.abs(getX() - centerX) < speed &&
        Math.abs(getY() - centerY) < speed)
        {
            setLocation(centerX, centerY);

            pathIndex++;

            direction = map[tileY][tileX];

            switch (direction)
            {
                case 1: // rechts
                    setLocation(getX() + speed, getY());
                    break;

                case 2: // links
                    setLocation(getX() - speed, getY());
                    break;

                case 3: // hoch
                    setLocation(getX(), getY() - speed);
                    break;

                case 4: // runter
                    setLocation(getX(), getY() + speed);
                    break;

                case 5: // Ziel erreicht
                    getWorld().removeObject(this);
                    return;
            }
        }
        else
        {
            // weiter in aktueller Richtung bewegen (kein abruptes Umsteuern)
            // optional: verhindert Zittern
            int dx = 0;
            int dy = 0;

            if (direction == 1) dx = speed;
            else if (direction == 2) dx = -speed;
            else if (direction == 3) dy = -speed;
            else if (direction == 4) dy = speed;

            setLocation(getX() + dx, getY() + dy);
        }
        // Greenfoot.delay(2);
    }
}
