import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class MyWorld extends World
{   
    private static final int TILE_SIZE = 64;
    private int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,2,1,1,2,0,0},
            {1,1,1,2,0,0,1,0,0,1,0,0},
            {0,0,0,1,0,0,1,0,0,1,0,0},
            {0,0,0,1,0,0,1,0,0,1,0,0},
            {0,0,0,2,1,1,2,0,0,1,0,0},
            {0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,0,0,2,0,0},
        };
    
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Tower> towers = new ArrayList<>();
    ArrayList<Waypoint> waypoints = new ArrayList<>();
    
    //Game Dynamics
    
    protected boolean paused;
    protected int selectedButton;
    protected int speed;
    

    public MyWorld()
    {
        super(16 * TILE_SIZE, 9 * TILE_SIZE, 1);
        createMap();
        
        paused = true;
        selectedButton = 0;
        speed = 1;
    }

    public void createMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int tileType = map[y][x];
                Tile tile;
                if (tileType == 0) {
                    tile = new Gras(TILE_SIZE);
                } else if (tileType == 3){
                    waypoints.add(new Waypoint());
                    tile = new Weg(TILE_SIZE);
                } else {
                    tile = new Weg(TILE_SIZE);
                }
                addObject(tile, x * TILE_SIZE + TILE_SIZE/2, y * TILE_SIZE + TILE_SIZE/2);
            }
        }
    }
    
}

