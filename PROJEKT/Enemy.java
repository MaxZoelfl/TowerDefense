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
    private int step = 2;

    protected int distanzWaypoint;
    Waypoint aktuellerWaypoint;
    protected int waypointIndex = 0;

    private int health;
    private int speed;
    private int bounty;

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy()
    {
    }

    public void moveTowards(ArrayList<Waypoint> waypoints) {

        if (waypointIndex >= waypoints.size()) {
            return;
        }

        Waypoint w = waypoints.get(waypointIndex);

        int dx = w.getX() - getX();
        int dy = w.getY() - getY();
        
        distanzWaypoint = dx*dx + dy*dy;
        aktuellerWaypoint = w;

        if (tick == speed) {
            if (dx != 0) {
                setLocation(getX() + (dx > 0 ? step : -step), getY());
            } else if (dy != 0) {
                setLocation(getX(), getY() + (dy > 0 ? step : -step));
            }
            if (Math.abs(dx) <= step && Math.abs(dy) <= step) {
                waypointIndex++;
            }
            
            tick = 0;
        } else {
            tick++;
        }
    }

    public int gibDistanzWaypoint() {
        return distanzWaypoint;
    }

    public int gibWaypointIndex() {
        return waypointIndex;
    }
}
