public enum TowerType {
    //         cost  dmg  range  cooldown  ProjectileType      image
    BOW(        200,  15,   150,       18, ProjectileType.BOW,    "Bow.png"),    // Günstig, schnell, Einzelziel
    MAGIC(      500,  20,   160,       28, ProjectileType.MAGIC,  "Magic.png"),  // Teuer, großes AoE
    CANON(      350,  45,   130,       32, ProjectileType.CANON,  "Canon.png"),  // Viel Schaden, mittlerer Splash
    SNIPER(     600,  90,   280,       55, ProjectileType.SNIPER, "Sniper.png"); // Sehr hohe Reichweite & Schaden

    public final int           cost;
    public final int           damage;
    public final int           range;
    public final int           cooldown;
    public final ProjectileType projectileType;
    public final String         image;

    TowerType(int cost, int damage, int range, int cooldown,
              ProjectileType projectileType, String image) {
        this.cost           = cost;
        this.damage         = damage;
        this.range          = range;
        this.cooldown       = cooldown;
        this.projectileType = projectileType;
        this.image          = image;
    }

    public String getImage() { return image; }
    public int    getCost()  { return cost; }
}