import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class GameWorld extends World
{   
    protected static final int TILE_SIZE = 64;
    
    /* Map Mechanic:
     *  0 -> Gras
     *  1 -> rechts
     *  2 -> links
     *  3 -> hoch
     *  4 -> runter
     *  5 -> von links runter
     *  6 -> von links hoch
     *  7 -> von rechts runter
     *  8 -> von rechts hoch
     *  9 -> von unten nach rechts
     * 10 -> von unten nach links
     * 11 -> von oben nach rechts
     * 12 -> von oben nach links
     * 13 -> Ziel
     */
    private int[][] map = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 9, 1, 1, 5, 0, 0},
            { 1, 1, 1, 5, 0, 0, 3, 0, 0, 4, 0, 0},
            { 0, 0, 0, 4, 0, 0, 3, 0, 0, 4, 0, 0},
            { 0, 0, 0, 4, 0, 0, 3, 0, 0, 4, 0, 0},
            { 0, 0, 0,11, 1, 1, 6, 0, 0, 4, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0,13, 0, 0},
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
        towers.add(t);
        
        // Beispiel Enemy + Lauftest
        Enemy e = new Rat(TILE_SIZE, 1);
        addObject(e, startX, startY);
        enemies.add(e);
    }

    public void createMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int tileType = map[y][x];
                Tile tile;
                
                switch (tileType) {
                    case 0: //Grass
                        tile = new Grass(TILE_SIZE);
                        break;
                        
                    case 1:  //rechts
                    case 2:  //links
                        tile = new Path(TILE_SIZE +1, 0);
                        break;
                        
                    case 3:  //hoch
                    case 4:  //runter
                    case 13: //Ziel
                        tile = new Path(TILE_SIZE +1, 90);
                        break;
                        
                    case 5:  //links>runter
                    case 10: //unten>links
                        tile = new PathCorner(TILE_SIZE +1, 0);
                        break;
                        
                    case 6:  //links>hoch
                    case 12: //oben>links
                        tile = new PathCorner(TILE_SIZE +1, 90);
                        break;
                        
                    case 7:  //rechts>runter
                    case 9:  //unten>rechts
                        tile = new PathCorner(TILE_SIZE +1, 270);
                        break;
                        
                    case 8:  //rechts>hoch
                    case 11: //oben>rechts
                        tile = new PathCorner(TILE_SIZE +1, 180);
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
        addObject(new PlayButton(1), 900, 150);
    }

    public void setSelectedButton(int selectedButton) {
        this.selectedButton = selectedButton;
        System.out.println(selectedButton);
    }

    public void act() {
        for (Enemy e : enemies) {
            e.move(map);
        }
        
        /*
        for (Tower t : towers) {
            t.attack();
        }   
        */
    }
}

