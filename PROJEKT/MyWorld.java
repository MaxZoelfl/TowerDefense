import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
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

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Tower> towers = new ArrayList<>();

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(16 * TILE_SIZE, 9 * TILE_SIZE, 1);
        createMap();
    }

    private void createMap() {
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

    public void spawn(Enemy enemy, int x, int y) {
        enemy.setzePos(x, y);
        enemies.add(enemy);
        addObject(enemy, x, y);
    }
    
    public Enemy nächsterEnemy() {
        Enemy nächsterGegner = null;
        double minAbstand = Double.MAX_VALUE;
        double abstand;
        int dX;
        int dY;
        for (Tower t : towers) {
            for (Enemy e : enemies) {
                dX = e.gibX() - t.gibX();
                dY = e.gibY() - t.gibY();
                abstand = dX*dX + dY*dY;
                if ( abstand < minAbstand) {
                    minAbstand = abstand;
                    nächsterGegner = e;
                }
            }
        }  
        return nächsterGegner;
    }
    
}

