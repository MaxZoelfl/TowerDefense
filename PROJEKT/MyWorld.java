import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        {0,0,0,0,0,0,0,0,0,1,0,0}
    };
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
}
