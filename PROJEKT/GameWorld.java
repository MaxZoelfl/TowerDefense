import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class GameWorld extends World
{   
    private static final int TILE_SIZE = 64;
    private int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,1,0,0},
            {1,1,1,1,0,0,1,0,0,1,0,0},
            {0,0,0,1,0,0,1,0,0,1,0,0},
            {0,0,0,1,0,0,1,0,0,1,0,0},
            {0,0,0,1,1,1,1,0,0,1,0,0},
            {0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,0,0,1,0,0},
        };
    private Waypoint start;

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Tower> towers = new ArrayList<>();
    ArrayList<Waypoint> waypoints = new ArrayList<>();

    //Game Dynamics

    protected boolean paused;
    protected int selectedButton;
    protected int speed;

    public GameWorld()
    {
        super(16 * TILE_SIZE, 9 * TILE_SIZE, 1);
        createMap();
        createWaypoints();
        
        createButtons();

        paused = true;
        selectedButton = 0;
        speed = 1;
        
        
        Enemy e = new Enemy(start);
        addObject(e, start.getX(), start.getY());
        enemies.add(e);
    }

    public void createMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int tileType = map[y][x];
                Tile tile;
                if (tileType == 0) {
                    tile = new Gras(TILE_SIZE);
                } else {
                    tile = new Weg(TILE_SIZE);
                }
                addObject(tile, x * TILE_SIZE + TILE_SIZE/2, y * TILE_SIZE + TILE_SIZE/2);
            }
        }
    }

    public void createWaypoints() {
        Waypoint w1 = new Waypoint(0 * TILE_SIZE + TILE_SIZE/2, 3 * TILE_SIZE + TILE_SIZE/2);
        Waypoint w2 = new Waypoint(3 * TILE_SIZE + TILE_SIZE/2, 3 * TILE_SIZE + TILE_SIZE/2);
        Waypoint w3 = new Waypoint(3 * TILE_SIZE + TILE_SIZE/2, 6 * TILE_SIZE + TILE_SIZE/2);
        Waypoint w4 = new Waypoint(3 * TILE_SIZE + TILE_SIZE/2, 4 * TILE_SIZE + TILE_SIZE/2);
        
        // Kette bauen
        w1.setNext(w2);
        w2.setNext(w3);
        w3.setNext(w4);
        start = w1;
    }

    public void createButtons() {
        addObject(new Button(1), 900, 150);
    }

    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton;
        System.out.println(selectedButton);
    }

    public void act() {
        for (Enemy e : enemies) {
            e.move();
        }
    }
}

