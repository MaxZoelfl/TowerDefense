public class GameConstants {

    // Tile-Größe in Pixeln
    public static final int TILE_SIZE = 64;

    // Karten-Abmessungen (Tiles)
    public static final int MAP_COLS   = 12;
    public static final int MAP_ROWS   = 9;

    // Welt-Abmessungen inkl. Sidebar
    public static final int WORLD_COLS = 16;
    public static final int WORLD_ROWS = 9;

    // Sidebar-Layout
    public static final int SIDEBAR_X        = MAP_COLS * TILE_SIZE;                        // 768 px
    public static final int SIDEBAR_WIDTH    = (WORLD_COLS - MAP_COLS) * TILE_SIZE;         // 256 px
    public static final int SIDEBAR_CENTER_X = SIDEBAR_X + SIDEBAR_WIDTH / 2;               // 896 px

    // Spezielle Karten-Tile-Werte
    public static final int TILE_GRASS = 0;
    public static final int TILE_GOAL  = 13;   // Ziel: Enemy wird entfernt, Spieler verliert Leben

    // Spielzustände
    public static final int STATE_PLAYING   = 0;
    public static final int STATE_GAME_OVER = 1;
    public static final int STATE_VICTORY   = 2;

    // Startwerte
    public static final int START_HEALTH  = 20;
    public static final int START_BALANCE = 650;

    // Spawn-Position: Mittelpunkt von Tile (Spalte 0, Zeile 3)
    public static final int SPAWN_X = TILE_SIZE / 2;
    public static final int SPAWN_Y = 3 * TILE_SIZE + TILE_SIZE / 2;
}