public enum TowerType {
    // TOWER(cost, damage, range, cooldown,ProjectileType, image),
    BOW(100, 10, 128, 10, ProjectileType.BOW, "Bow.png"),
    MAGIC(100, 10, 128, 25, ProjectileType.MAGIC, "Magic.png"),
    CANON(100, 10, 128, 10, ProjectileType.CANON, "Canon.png"),
    SNIPER(100, 10, 128, 10, ProjectileType.SNIPER, "Sniper.png");

    public final int cost;
    public final int damage;
    public final int range;
    public final int cooldown;
    public final ProjectileType projectileType;
    public final String image;

    TowerType(int cost, int damage, int range, int cooldown, ProjectileType projectileType, String image) {
        this.cost = cost;
        this.damage = damage;
        this.range = range;
        this.cooldown = cooldown;
        this.projectileType = projectileType;
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}