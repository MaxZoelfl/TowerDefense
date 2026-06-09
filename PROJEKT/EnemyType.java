public enum EnemyType {
    //            health  bounty  speed  image
    RAT(             100,    10,      1, "Rat.png"),
    ENEMY2(          220,    25,      2, ""),   // Gepanzert: mehr HP, schneller
    ENEMY3(           70,    15,      3, ""),   // Schnell: wenig HP, hohe Geschwindigkeit
    ENEMY4(          600,   100,      1, "");   // Boss: sehr hohe HP

    public final int    health;
    public final int    bounty;
    public final int    speed;
    public final String image;

    EnemyType(int health, int bounty, int speed, String image) {
        this.health = health;
        this.bounty = bounty;
        this.speed  = speed;
        this.image  = image;
    }

    public String getImage() { return image; }

    /** Nur spawnbar wenn ein Bild hinterlegt ist */
    public boolean isReady() {
        return image != null && !image.isEmpty();
    }
}