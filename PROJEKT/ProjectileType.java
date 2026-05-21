public enum ProjectileType {
    // PTOWER(splashRadius, image)
    BOW(0, "BowProjectile.png"),
    MAGIC(300, "MagicProjectile.png"),
    CANON(32, "CanonProjectile.png"),
    SNIPER(0, "SniperProjectile.png");

    private final int splashRadius;
    private final String image;

    ProjectileType(int splashRadius, String image) {
        this.splashRadius = splashRadius;
        this.image = image;
    }

    public int getSplashRadius() {
        return splashRadius;
    }
    
    public String getImage() {
        return image;
    }
}