import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class GameWorld extends World {

    /*
     * Karten-Tile-Werte:
     *  0  = Gras (bebaubar)
     *  1  = Pfad rechts      2  = Pfad links
     *  3  = Pfad hoch        4  = Pfad runter
     *  5  = Ecke links→runter     6  = Ecke links→hoch
     *  7  = Ecke rechts→runter    8  = Ecke rechts→hoch
     *  9  = Ecke unten→rechts    10  = Ecke unten→links
     * 11  = Ecke oben→rechts     12  = Ecke oben→links
     * 13  = ZIEL (Enemy-Entfernung + Spielerschaden)
     */
    private final int[][] map = {
        {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
        {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
        {  0,  0,  0,  0,  0,  0,  9,  1,  1,  5,  0,  0 },
        {  1,  1,  1,  5,  0,  0,  3,  0,  0,  4,  0,  0 },
        {  0,  0,  0,  4,  0,  0,  3,  0,  0,  4,  0,  0 },
        {  0,  0,  0,  4,  0,  0,  3,  0,  0,  4,  0,  0 },
        {  0,  0,  0, 11,  1,  1,  6,  0,  0,  4,  0,  0 },
        {  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  0,  0 },
        {  0,  0,  0,  0,  0,  0,  0,  0,  0, 13,  0,  0 },  // 13 = Ziel
    };

    // Spawn-Punkt: Mittelpunkt von Tile (0, 3) — FIX: war 0, korrekt ist 32
    public final int startX = GameConstants.SPAWN_X;
    public final int startY = GameConstants.SPAWN_Y;

    // Spielzustand
    private int     gameState    = GameConstants.STATE_PLAYING;
    private boolean paused       = true;
    private int     playerHealth = GameConstants.START_HEALTH;
    private int     balance      = GameConstants.START_BALANCE;

    private TowerType   selectedTower = null;
    private WaveManager waveManager;
    private HUD         hud;

    // ---------------------------------------------------------------
    public GameWorld() {
        super(GameConstants.WORLD_COLS * GameConstants.TILE_SIZE,
              GameConstants.WORLD_ROWS * GameConstants.TILE_SIZE, 1);

        drawSidebarBackground();
        createMap();
        createButtons();

        waveManager = new WaveManager(this);

        hud = new HUD();
        addObject(hud, GameConstants.SIDEBAR_CENTER_X, 85);
    }

    // ---------------------------------------------------------------
    // Sidebar-Hintergrund
    // ---------------------------------------------------------------
    private void drawSidebarBackground() {
        GreenfootImage bg = getBackground();
        bg.setColor(new Color(22, 22, 38));
        bg.fillRect(GameConstants.SIDEBAR_X, 0,
                    GameConstants.SIDEBAR_WIDTH,
                    GameConstants.WORLD_ROWS * GameConstants.TILE_SIZE);
        // Trennlinie
        bg.setColor(new Color(55, 55, 95));
        bg.fillRect(GameConstants.SIDEBAR_X, 0, 2,
                    GameConstants.WORLD_ROWS * GameConstants.TILE_SIZE);
    }

    // ---------------------------------------------------------------
    // Karte aufbauen
    // ---------------------------------------------------------------
    public void createMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                int type = map[y][x];
                Tile tile;
                switch (type) {
                    case 0:
                        tile = new Grass(GameConstants.TILE_SIZE);
                        break;
                    case 1: case 2:
                        tile = new Path(GameConstants.TILE_SIZE, 0);
                        break;
                    case 3: case 4: case GameConstants.TILE_GOAL:   // FIX: case 13 = Ziel-Tile
                        tile = new Path(GameConstants.TILE_SIZE, 90);
                        break;
                    case 5: case 10:
                        tile = new PathCorner(GameConstants.TILE_SIZE, 0);
                        break;
                    case 6: case 12:
                        tile = new PathCorner(GameConstants.TILE_SIZE, 90);
                        break;
                    case 7: case 9:
                        tile = new PathCorner(GameConstants.TILE_SIZE, 270);
                        break;
                    case 8: case 11:
                        tile = new PathCorner(GameConstants.TILE_SIZE, 180);
                        break;
                    default:
                        tile = new Grass(GameConstants.TILE_SIZE);
                        break;
                }
                addObject(tile,
                    x * GameConstants.TILE_SIZE + GameConstants.TILE_SIZE / 2,
                    y * GameConstants.TILE_SIZE + GameConstants.TILE_SIZE / 2);
            }
        }
    }

    // ---------------------------------------------------------------
    // Buttons — alle vier Tower + Play/Pause
    // ---------------------------------------------------------------
    public void createButtons() {
        int bx = GameConstants.SIDEBAR_CENTER_X;
        addObject(new Button(ButtonType.BOW,    TowerType.BOW),    bx, 215);
        addObject(new Button(ButtonType.MAGIC,  TowerType.MAGIC),  bx, 290);
        addObject(new Button(ButtonType.CANON,  TowerType.CANON),  bx, 365);
        addObject(new Button(ButtonType.SNIPER, TowerType.SNIPER), bx, 440);
        addObject(new Button(ButtonType.PLAY,   null),             bx, 530);
    }

    // ---------------------------------------------------------------
    // Getter / Setter
    // ---------------------------------------------------------------
    public boolean     getPaused()          { return paused; }
    public TowerType   getSelectedTower()   { return selectedTower; }
    public int         getPlayerHealth()    { return playerHealth; }
    public int         getBalance()         { return balance; }
    public WaveManager getWaveManager()     { return waveManager; }
    public int         getGameState()       { return gameState; }
    public int[][]     getMap()             { return map; }

    public void setSelectedTower(TowerType t) { selectedTower = t; }

    public void setPaused() {
        if (gameState == GameConstants.STATE_PLAYING) paused = !paused;
    }

    public void adjustBalance(int delta) {
        balance += delta;
    }

    public void adjustPlayerHealth(int delta) {
        playerHealth = Math.max(0, playerHealth + delta);
        if (playerHealth == 0) triggerGameOver();
    }

    // ---------------------------------------------------------------
    // Spielzustand
    // ---------------------------------------------------------------
    private void triggerGameOver() {
        if (gameState != GameConstants.STATE_PLAYING) return;
        gameState = GameConstants.STATE_GAME_OVER;
        paused    = true;
        addObject(new GameOverScreen(false), getWidth() / 2, getHeight() / 2);
    }

    private void triggerVictory() {
        if (gameState != GameConstants.STATE_PLAYING) return;
        gameState = GameConstants.STATE_VICTORY;
        paused    = true;
        addObject(new GameOverScreen(true), getWidth() / 2, getHeight() / 2);
    }

    // ---------------------------------------------------------------
    // Haupt-Schleife
    // ---------------------------------------------------------------
    @Override
    public void act() {
        // ESC: Tower-Selektion aufheben
        if (Greenfoot.isKeyDown("escape")) selectedTower = null;

        if (gameState != GameConstants.STATE_PLAYING || paused) return;

        waveManager.update();

        // Victory: alle Wellen abgeschlossen UND keine Gegner mehr auf dem Feld
        if (waveManager.isAllWavesDone() && getObjects(Enemy.class).isEmpty()) {
            triggerVictory();
            return;
        }

        // Alle Enemies bewegen (Kopie der Liste wegen möglicher Entfernungen)
        List<Enemy> enemies = new ArrayList<>(getObjects(Enemy.class));
        for (Enemy e : enemies) {
            if (e.getWorld() != null) e.move(map);
        }
    }
}