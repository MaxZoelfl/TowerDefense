import java.util.ArrayList;
import java.util.List;

/**
 * WaveManager — verwaltet alle Wellen und spawnt Enemies getaktet.
 *
 * Integration in GameWorld:
 *   1. Feld anlegen:        private WaveManager waveManager;
 *   2. Im Konstruktor:      waveManager = new WaveManager(this);
 *   3. In act():            if (!paused) waveManager.update();
 *
 * Spawn-Position: startX / startY aus GameWorld.
 */
public class WaveManager {

    // ---------------------------------------------------------------
    // Wellen-Definition — hier alles konfigurieren
    // ---------------------------------------------------------------
    private static List<Wave> buildWaves() {
        List<Wave> waves = new ArrayList<>();

        // Welle 1 — Einstieg: 5 Rats, gleichmäßig
        waves.add(new Wave(300)
            .add(EnemyType.RAT, 5, 40)
        );

        // Welle 2 — mehr Druck: 8 Rats, dichter
        waves.add(new Wave(300)
            .add(EnemyType.RAT, 8, 25)
        );

        // Welle 3 — gemischt: Rats + ENEMY2
        waves.add(new Wave(360)
            .add(EnemyType.RAT,    5, 25)
            .add(EnemyType.ENEMY2, 3, 50)
        );

        // Welle 4 — Doppelwelle: gleichzeitig zwei Typen im Wechsel
        waves.add(new Wave(360)
            .add(EnemyType.ENEMY2, 6, 30)
            .add(EnemyType.ENEMY3, 4, 40)
        );

        // Welle 5 — Boss-Welle: viele schwache + ein starker
        waves.add(new Wave(480)
            .add(EnemyType.RAT,    10, 20)
            .add(EnemyType.ENEMY4,  1, 0)
        );

        // Weitere Wellen hier einfach mit waves.add(...) anhängen

        return waves;
    }

    // ---------------------------------------------------------------
    // State
    // ---------------------------------------------------------------
    private final GameWorld world;
    private final List<Wave> waves;

    private int currentWaveIndex   = 0;  // welche Welle gerade aktiv
    private int currentEntryIndex  = 0;  // welcher WaveEntry in der Welle
    private int spawnedInEntry     = 0;  // wie viele dieser Gruppe schon gespawnt
    private int spawnTimer         = 0;  // Ticks bis nächster Spawn
    private int breakTimer         = 0;  // Ticks Pause zwischen Wellen

    private boolean waveActive     = false;
    private boolean allWavesDone   = false;

    // ---------------------------------------------------------------
    // Konstruktor
    // ---------------------------------------------------------------
    public WaveManager(GameWorld world) {
        this.world = world;
        this.waves = buildWaves();
    }

    // ---------------------------------------------------------------
    // Haupt-Update — einmal pro act()-Tick aufrufen
    // ---------------------------------------------------------------
    public void update() {
        if (allWavesDone) return;

        // Pause zwischen Wellen läuft
        if (breakTimer > 0) {
            breakTimer--;
            if (breakTimer == 0) {
                startNextWave();
            }
            return;
        }

        // Erste Welle beim ersten Update starten
        if (!waveActive) {
            startNextWave();
            return;
        }

        // Spawn-Timer herunterzählen
        if (spawnTimer > 0) {
            spawnTimer--;
            return;
        }

        // Aktuellen Entry spawnen
        Wave wave = waves.get(currentWaveIndex);
        List<WaveEntry> entries = wave.getEntries();

        if (currentEntryIndex >= entries.size()) {
            // Alle Entries dieser Welle abgearbeitet
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
            // Nächste Gruppe
            currentEntryIndex++;
            spawnedInEntry = 0;
            spawnTimer = 0;
        }
    }

    // ---------------------------------------------------------------
    // Interne Hilfsmethoden
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
        if (!type.isReady()) return;  // überspringen statt crashen
        Enemy e = new Enemy(GameConstants.TILE_SIZE, type);
        world.addObject(e, world.startX, world.startY);

    }

    // ---------------------------------------------------------------
    // Getter für UI / GameWorld
    // ---------------------------------------------------------------

    /** Aktuelle Wellennummer (1-basiert, für Anzeige) */
    public int getCurrentWaveNumber() {
        return currentWaveIndex + 1;
    }

    /** Gesamtanzahl Wellen */
    public int getTotalWaves() {
        return waves.size();
    }

    /** true während der Pause zwischen zwei Wellen */
    public boolean isInBreak() {
        return breakTimer > 0;
    }

    /** Verbleibende Pause-Ticks */
    public int getBreakTimer() {
        return breakTimer;
    }

    /** true wenn alle Wellen abgeschlossen */
    public boolean isAllWavesDone() {
        return allWavesDone;
    }
}
