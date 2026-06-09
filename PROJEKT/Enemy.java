import greenfoot.*;

public class Enemy extends Actor {

    protected EnemyType enemyType;

    public int health;
    private final int maxHealth;

    private int pathIndex  = 0;
    private int tileSize;
    private int direction  = 1;    // Startwert: rechts (korrekt für Tile [3][0])

    private int lastTileX  = -1;
    private int lastTileY  = -1;
    private int lastHealth = -1;   // für effiziente Bild-Updates

    // ---------------------------------------------------------------
    public Enemy(int size, EnemyType enemyType) {
        this.enemyType = enemyType;
        this.health    = enemyType.health;
        this.maxHealth = enemyType.health;
        this.tileSize  = size;
        updateImage();
    }

    // ---------------------------------------------------------------
    // act(): Bewegung wird von GameWorld.act() getriggert
    // ---------------------------------------------------------------
    public void act() {
        if (health != lastHealth) {
            lastHealth = health;
            updateImage();
        }
    }

    // ---------------------------------------------------------------
    // Schaden nehmen + Bounty auszahlen (FIX: Gold wurde nie vergeben)
    // ---------------------------------------------------------------
    public void takeDamage(int damage, GameWorld world) {
        health -= damage;
        updateImage();
        if (health <= 0 && getWorld() != null) {
            world.adjustBalance(enemyType.bounty);   // Bounty auszahlen
            world.removeObject(this);
        }
    }

    public int getPathIndex() { return pathIndex; }
    public int getBounty()    { return enemyType.bounty; }

    // ---------------------------------------------------------------
    // Gesundheitsbalken ins Sprite zeichnen
    // ---------------------------------------------------------------
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(enemyType.image);
        img.scale(tileSize, tileSize);

        int barX  = 3, barY  = 3;
        int barW  = tileSize - 6, barH = 6;
        float pct = Math.max(0f, health / (float) maxHealth);
        int   fillW = (int)(barW * pct);

        // Hintergrund (leer = rot)
        img.setColor(new Color(170, 30, 30));
        img.fillRect(barX, barY, barW, barH);

        // Füllung: Farbe nach Prozentsatz
        Color fillColor;
        if      (pct > 0.60f) fillColor = new Color(40,  200,  40);
        else if (pct > 0.30f) fillColor = new Color(220, 180,  20);
        else                  fillColor = new Color(220,  50,  20);
        img.setColor(fillColor);
        img.fillRect(barX, barY, fillW, barH);

        // Rahmen
        img.setColor(new Color(0, 0, 0, 160));
        img.drawRect(barX, barY, barW, barH);

        setImage(img);
    }

    // ---------------------------------------------------------------
    // Bewegungslogik (wird von GameWorld.act() aufgerufen)
    // ---------------------------------------------------------------
    public void move(int[][] map) {
        if (getWorld() == null) return;

        int tileX = getX() / tileSize;
        int tileY = getY() / tileSize;

        // Tile-Wechsel → pathIndex erhöhen (für Tower-Targeting)
        if (tileX != lastTileX || tileY != lastTileY) {
            pathIndex++;
            lastTileX = tileX;
            lastTileY = tileY;
        }

        int centerX = tileX * tileSize + tileSize / 2;
        int centerY = tileY * tileSize + tileSize / 2;

        // Am Tile-Mittelpunkt einrasten + neue Richtung lesen
        if (Math.abs(getX() - centerX) < enemyType.speed &&
            Math.abs(getY() - centerY) < enemyType.speed) {
            setLocation(centerX, centerY);
            direction = map[tileY][tileX];
        }

        // Richtung anwenden
        int dx = 0, dy = 0;
        switch (direction) {
            case 1: case 9: case 11:
                dx = enemyType.speed;  setRotation(0);   break;
            case 2: case 10: case 12:
                dx = -enemyType.speed; setRotation(180); break;
            case 3: case 6: case 8:
                dy = -enemyType.speed; setRotation(270); break;
            case 4: case 5: case 7:
                dy = enemyType.speed;  setRotation(90);  break;
            case GameConstants.TILE_GOAL:   // FIX: Spieler nimmt Schaden + Enemy entfernen
                GameWorld gw = (GameWorld) getWorld();
                if (gw != null) {
                    gw.adjustPlayerHealth(-1);
                    gw.removeObject(this);
                }
                return;
        }

        setLocation(getX() + dx, getY() + dy);
    }
}