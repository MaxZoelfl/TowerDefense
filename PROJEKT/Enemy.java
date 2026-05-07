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
    Waypoint currentWaypoint;
    protected int waypointIndex;

    private int health;
    private int speed;
    private int bounty;

    /**
     * Constructor for objects of class Enemy
     */
    public Enemy() {
        waypointIndex = 0;
        step = 2;
        tick = 0;
    }
    
    public void moveTowards(ArrayList<Waypoint> waypoints) {
        System.out.println("Los");
        
        int minDistance = Integer.MAX_VALUE;
        int waypointIndex = 0;
        
        for (Waypoint w : waypoints) {
            int dx = w.getX() - getX();
            int dy = w.getY() - getY();    
            int currentDistance = dx*dx + dy*dy;
            
            if (currentDistance < minDistance) {
                distanceWaypoint = currentDistance;
                currentWaypoint = w;
                break;
            } else {
                waypointIndex++;
            }
        }
        
        Waypoint w = waypoints.get(waypointIndex);

        int dx = w.getX() - getX();
        int dy = w.getY() - getY();

        if (speed == tick) {
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
        return distanceWaypoint;
    }

    public int gibWaypointIndex() {
        return waypointIndex;
    }
}
