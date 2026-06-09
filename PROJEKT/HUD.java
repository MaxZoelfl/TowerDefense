import greenfoot.*;

/**
 * HUD — Anzeige von Leben, Gold, Wellenstatus in der Sidebar.
 * Platzierung: addObject(hud, GameConstants.SIDEBAR_CENTER_X, 85)
 */
public class HUD extends Actor {

    private static final int W = GameConstants.SIDEBAR_WIDTH - 12;  // 244 px
    private static final int H = 165;

    // Caching: nur neu zeichnen wenn sich etwas geändert hat
    private int     lastHealth    = -1;
    private int     lastBalance   = -1;
    private int     lastWave      = -1;
    private boolean lastPaused    = true;
    private boolean lastInBreak   = false;
    private int     lastBreakSecs = -1;

    public HUD() {
        setImage(new GreenfootImage(W, H));  // leerer Platzhalter bis act() läuft
    }

    public void act() {
        GameWorld world = (GameWorld) getWorld();
        if (world == null) return;

        int     h  = world.getPlayerHealth();
        int     b  = world.getBalance();
        WaveManager wm = world.getWaveManager();
        int     wv = wm.getCurrentWaveNumber();
        boolean p  = world.getPaused();
        boolean ib = wm.isInBreak();
        int     bs = ib ? wm.getBreakTimer() / 60 : 0;   // Sekunden

        boolean changed = (h != lastHealth || b != lastBalance || wv != lastWave
                || p != lastPaused || ib != lastInBreak || bs != lastBreakSecs);
        if (!changed) return;

        lastHealth    = h;
        lastBalance   = b;
        lastWave      = wv;
        lastPaused    = p;
        lastInBreak   = ib;
        lastBreakSecs = bs;

        drawPanel(h, b, wv, wm.getTotalWaves(), p, ib, wm.getBreakTimer());
    }

    private void drawPanel(int health, int balance, int wave, int total,
    boolean paused, boolean inBreak, int breakTimer) {
        GreenfootImage img = new GreenfootImage(W, H);

        // Hintergrund
        img.setColor(new Color(26, 26, 48, 238));
        DrawUtil.fillRoundRect(
            img,
            0,
            0,
            W - 1,
            H - 1,
            10
        );
        img.setColor(new Color(65, 65, 110));
        DrawUtil.fillRoundRect(
            img,
            0,
            0,
            W - 1,
            H - 1,
            10
        );

        // Titel
        new Font("Arial", true, false, 14);
        img.setColor(new Color(175, 175, 255));
        img.drawString("TOWER DEFENSE", 8, 20);
        img.setColor(new Color(60, 60, 100));
        img.fillRect(5, 25, W - 10, 1);

        // --- Lebens-Balken ---
        new Font("Arial", false, false, 13);
        img.setColor(Color.WHITE);
        img.drawString("Leben", 8, 42);

        int bX = 58, bY = 30, bW = W - 68, bH = 13;
        img.setColor(new Color(85, 18, 18));
        img.fillRect(bX, bY, bW, bH);

        float pct  = health / (float) GameConstants.START_HEALTH;
        int   fill = (int)(bW * Math.max(0f, pct));
        img.setColor(pct > 0.6f
            ? new Color(40,  185,  40)
            : pct > 0.3f
            ? new Color(215, 170,  20)
            : new Color(210,  45,  45));
        img.fillRect(bX, bY, fill, bH);
        img.setColor(new Color(100, 100, 145));
        img.drawRect(bX, bY, bW, bH);
        new Font("Arial", true, false, 10);
        img.setColor(Color.WHITE);
        img.drawString(health + "/" + GameConstants.START_HEALTH, bX + 2, bY + 10);

        // --- Gold ---
        new Font("Arial", true, false, 14);
        img.setColor(new Color(255, 205, 50));
        img.drawString("Gold:  " + balance, 8, 70);

        // --- Welle ---
        new Font("Arial", false, false, 13);
        img.setColor(new Color(135, 195, 255));
        img.drawString("Welle:  " + wave + " / " + total, 8, 92);

        // --- Status ---
        new Font("Arial", false, true, 12);
        if (inBreak) {
            int secs = Math.max(1, (breakTimer + 59) / 60);
            img.setColor(new Color(200, 215, 95));
            img.drawString("Nächste Welle in ~" + secs + "s", 8, 113);
        } else if (paused) {
            img.setColor(new Color(255, 165, 45));
            img.drawString("Pausiert – PLAY drücken", 8, 113);
        } else {
            img.setColor(new Color(85, 220, 85));
            img.drawString("Welle läuft …", 8, 113);
        }

        // --- Trennlinie + Hinweise ---
        img.setColor(new Color(60, 60, 100));
        img.fillRect(5, 120, W - 10, 1);

        new Font("Arial", false, false, 13);
        img.setColor(new Color(175, 175, 218));
        img.drawString("TÜRME PLATZIEREN:", 8, 140);

        new Font("Arial", false, false, 13);
        img.setColor(new Color(105, 115, 150));
        img.drawString("ESC = Tower abwählen", 8, 158);

        setImage(img);
    }
}