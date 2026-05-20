public enum ProjectileType {
    // PTOWER(damage von tower, image)
    BOW("BowProjectile.png"),
    MAGIC("MagicProjectile.png"),
    CANON("CanonProjectile.png"),
    SNIPER("SniperProjectile.png");

    private final String image;

    ProjectileType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}