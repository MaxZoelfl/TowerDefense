public enum EnemyType {
    // TOWER(Health, bounty, speed, Image),
    RAT(100, 10, 1, "Rat.png"),
    ENEMY2(100, 10, 1, ""),
    ENEMY3(100, 10, 1, ""),
    ENEMY4(100, 10, 1, "");

    public final int health;
    public final int speed;
    public final int bounty;
    public final String image;

    EnemyType(int health, int bounty, int speed, String image) {
        this.health = health;
        this.bounty = bounty;
        this.speed = speed;
        this.image = image;
    }

    public String getImage() {
        return image;
    }
    
    public boolean isReady() {
        return image != null && !image.isEmpty();
    }
}