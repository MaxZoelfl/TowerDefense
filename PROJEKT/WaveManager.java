import java.util.ArrayList;
import java.util.List;

/**
 * WaveManager — verwaltet alle Wellen und spawnt Enemies getaktet.
 *
 * Integration in GameWorld:
 *   1. Feld:        private WaveManager waveManager;
 *   2. Konstruktor: waveManager = new WaveManager(this);
 *   3. In act():    if (!paused) waveManager.update();
 */
public class WaveManager {

    // ---------------------------------------------------------------
    // Wellen-Definition
    // ---------------------------------------------------------------
    private static List<Wave> buildWaves() {
        List<Wave> waves = new ArrayList<>();

        waves.add(new Wave(300)
            .add(EnemyType.RAT, 5, 40)
        );
        waves.add(new Wave(300)
            .add(EnemyType.RAT, 8, 25)
        );
        waves.add(new Wave(360)
            .add(EnemyType.RAT,    5, 25)
            .add(EnemyType.ENEMY2, 3, 50)
        );
        waves.add(new Wave(360)
            .add(EnemyType.ENEMY2, 6, 30)
            .add(EnemyType.ENEMY3, 4, 40)
        );
        waves.add(new Wave(480)
            .add(EnemyType.RAT,    10, 20)
            .add(EnemyType.ENEMY4,  1, 0)
        );

        return waves;
    }

    // ---------------------------------------------------------------
    // Zustand
    // ---------------------------------------------------------------
    private final GameWorld  world;
    private final List<Wave> waves;

    private int currentWaveIndex  = 0;
    private int currentEntryIndex = 0;
    private int spawnedInEntry    = 0;
    private int spawnTimer        = 0;
    private int breakTimer        = 0;

    private boolean waveActive  = false;
    private boolean allWavesDone = false;

    // ---------------------------------------------------------------
    public WaveManager(GameWorld world) {
        this.world = world;
        this.waves = buildWaves();
    }

    // ---------------------------------------------------------------
    // Update — einmal pro act()-Tick
    // ---------------------------------------------------------------
    public void update() {
        if (allWavesDone) return;

        if (breakTimer > 0) {
            breakTimer--;
            if (breakTimer == 0) startNextWave();
            return;
        }

        if (!waveActive) {
            startNextWave();
            return;
        }

        if (spawnTimer > 0) { spawnTimer--; return; }

        Wave wave = waves.get(currentWaveIndex);
        List<WaveEntry> entries = wave.getEntries();

        if (currentEntryIndex >= entries.size()) {
            finishWave();
            return;
        }

        WaveEntry entry = entries.get(currentEntryIndex);

        if (spawnedInEntry < entry.count) {
            spawnEnemy(entry.enemyType);
            spawnedInEntry++;
            spawnTimer = entry.spawnDelay;
        }

        if (spawnedInEntry >= entry.count) {
            currentEntryIndex++;
            spawnedInEntry = 0;
            spawnTimer     = 0;
        }
    }

    // ---------------------------------------------------------------
    // Hilfsmethoden
    // ---------------------------------------------------------------
    private void startNextWave() {
        if (currentWaveIndex >= waves.size()) {
            allWavesDone = true;
            System.out.println("[WaveManager] Alle Wellen abgeschlossen!");
            return;
        }
        System.out.println("[WaveManager] Starte Welle " + (currentWaveIndex + 1));
        currentEntryIndex = 0;
        spawnedInEntry    = 0;
        spawnTimer        = 0;
        waveActive        = true;
    }

    private void finishWave() {
        System.out.println("[WaveManager] Welle " + (currentWaveIndex + 1) + " abgeschlossen.");
        breakTimer = waves.get(currentWaveIndex).breakDuration;
        currentWaveIndex++;
        waveActive = false;
    }

    private void spawnEnemy(EnemyType type) {
        if (!type.isReady()) {
            System.out.println("[WaveManager] Überspringe " + type.name() + " (kein Bild)");
            return;
        }
        Enemy e = new Enemy(GameConstants.TILE_SIZE, type);
        world.addObject(e, world.startX, world.startY);
    }

    // ---------------------------------------------------------------
    // Getter für HUD / GameWorld
    // ---------------------------------------------------------------
    public int     getCurrentWaveNumber() { return currentWaveIndex + 1; }
    public int     getTotalWaves()        { return waves.size(); }
    public boolean isInBreak()            { return breakTimer > 0; }
    public int     getBreakTimer()        { return breakTimer; }
    public boolean isAllWavesDone()       { return allWavesDone; }
}