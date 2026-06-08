/*
// ================================================================
// INTEGRATION — GameWorld.java
// Nur die markierten Stellen ändern, Rest bleibt identisch.
// ================================================================

// ── 1. startX / startY auf protected setzen (für WaveManager) ──
//    ALT:  private int startX = 0;
//    NEU:
protected int startX = 0;
protected int startY = 224;


// ── 2. WaveManager-Feld hinzufügen ──
//    (zusammen mit den anderen Feldern wie paused, speed, ...)
private WaveManager waveManager;


// ── 3. Im Konstruktor, NACH createButtons() ──
//    ALT:  paused = true;
//    NEU:
waveManager = new WaveManager(this);
paused = true;

//    Außerdem: Test-Enemy aus dem Konstruktor entfernen:
//    LÖSCHEN: Enemy e = new Enemy(...); addObject(e, ...); enemies.add(e);


// ── 4. In act() ──
//    ALT:
//        if (paused == false) {
//            for (Enemy e : getObjects(Enemy.class)) {
//                e.move(map);
//            }
//        }
//
//    NEU:
public void act() {
    if (!paused) {
        waveManager.update();                    // Wellen-Takt

        for (Enemy e : getObjects(Enemy.class)) {
            e.move(map);
        }
    }
}


// ── 5. Bug-Fix: map-Wert 13 → 14 für Ziel (Enemy.move prüft auf 14) ──
//    In der map[][]-Definition:
//    ALT:  { 0, 0, 0, 0, 0, 0, 0, 0, 0,13, 0, 0},
//    NEU:
        { 0, 0, 0, 0, 0, 0, 0, 0, 0,14, 0, 0},
//    (Zeile 8 in deiner Map, das Ziel-Tile)
*/
