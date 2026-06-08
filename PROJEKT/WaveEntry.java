/**
 * Beschreibt einen einzelnen Spawn-Eintrag innerhalb einer Welle.
 * Beispiel: 5x RAT mit je 30 Ticks Abstand
 */
public class WaveEntry {

    public final EnemyType enemyType;
    public final int count;
    public final int spawnDelay; // Ticks zwischen jedem Spawn dieser Gruppe

    public WaveEntry(EnemyType enemyType, int count, int spawnDelay) {
        this.enemyType = enemyType;
        this.count = count;
        this.spawnDelay = spawnDelay;
    }
}
