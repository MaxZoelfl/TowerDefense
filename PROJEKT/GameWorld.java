import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class GameWorld extends World
{   
    private static final int TILE_SIZE = 64;
    
    /* Map Mechanic:
     * 0 -> Gras
     * 1 -> rechts
     * 2 -> links
     * 3 -> hoch
     * 4 -> runter
     * 5 -> Ziel
     * 6 -> Start
     */
    private int[][] map = {
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,4,0,0},
            {1,1,1,4,0,0,3,0,0,4,0,0},
            {0,0,0,4,0,0,3,0,0,4,0,0},
            {0,0,0,4,0,0,3,0,0,4,0,0},
            {0,0,0,1,1,1,3,0,0,4,0,0},
            {0,0,0,0,0,0,0,0,0,4,0,0},
            {0,0,0,0,0,0,0,0,0,6,0,0},
        };
    private int startX = 0;
    private int startY = 224;

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Tower> towers = new ArrayList<>();

    //Game Dynamics

    protected boolean paused;
    protected int selectedButton;
    protected int speed;

    public GameWorld()
    {
        super(16 * TILE_SIZE, 9 * TILE_SIZE, 1);
        createMap();
        
        createButtons();

        paused = true;
        selectedButton = 0;
        speed = 1;
        
        // Beispiel BowTower bzw Arrow
        Tower t = new Arrow(TILE_SIZE);
        addObject(t, 5 * TILE_SIZE/2, 5 * TILE_SIZE/2);
        
        // Beispiel Enemy + Lauftest
        Enemy e = new Enemy(map, TILE_SIZE, 1);
        addObject(e, startX, startY);
        enemies.add(e);
    }

    public void createMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int tileType = map[y][x];
                Tile tile;
                
                switch (tileType) {
                    case 0:
                        tile = new Grass(TILE_SIZE);
                        break;
                    case 1:
                    case 2:
                        tile = new Path(TILE_SIZE, 0);
                        break;
                    case 3:
                    case 4:
                    case 6:
                        tile = new Path(TILE_SIZE, 270);
                        break;
                    default:
                        tile = new Grass(TILE_SIZE);
                        break;
                }
                
                addObject(tile, 
                    x * TILE_SIZE + TILE_SIZE/2, 
                    y * TILE_SIZE + TILE_SIZE/2
                );
            }
        }
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

