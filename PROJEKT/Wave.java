import java.util.ArrayList;
import java.util.List;

/**
 * Eine Welle besteht aus mehreren WaveEntry-Gruppen.
 * Beispiel: Welle 3 = [5x RAT @ delay 30] + [2x ENEMY2 @ delay 60]
 */
public class Wave {

    private final List<WaveEntry> entries;
    public final int breakDuration; // Ticks Pause nach dieser Welle

    public Wave(int breakDuration) {
        this.entries = new ArrayList<>();
        this.breakDuration = breakDuration;
    }

    /**
     * Fügt eine Spawn-Gruppe zur Welle hinzu.
     * Rückgabe: this (Builder-Pattern für lesbare Verkettung)
     */
    public Wave add(EnemyType enemyType, int count, int spawnDelay) {
        entries.add(new WaveEntry(enemyType, count, spawnDelay));
        return this;
    }

    public List<WaveEntry> getEntries() {
        return entries;
    }
}
