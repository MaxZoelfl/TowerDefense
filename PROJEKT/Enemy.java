import greenfoot.*;
import java.util.ArrayList;
/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    // instance variables - replace the example below with your own
    private int tick;
    private int step;

    protected int distanceWaypoint;
    private Waypoint current;
    protected int waypointIndex;

    private int health;
    private int speed;
    private int bounty;

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy(Waypoint start) {
        current = start;
    }

    public void move() {
        if (current == null) {
            return;
        }

        moveTowards(current);

        if (reached(current)) {
            current = current.getNext();
        }
    }

    private boolean reached(Waypoint w) {
        return getX() == w.getX() && getY() == w.getY();
    }

    public void moveTowards(Waypoint w) {
        int dx = w.getX() - getX();
        int dy = w.getY() - getY();

        double distance = Math.sqrt(dx*dx + dy*dy);

        if (distance < speed) {
            setLocation(w.getX(), w.getY());
            return;
        }

        double dirX = dx / distance;
        double dirY = dy / distance;

        setLocation(getX() + (int)(dirX * speed), getY() + (int)(dirY * speed)
        );
    }

    public int gibDistanzWaypoint() {
        return distanceWaypoint;
    }

    public int gibWaypointIndex() {
        return waypointIndex;
    }
}
