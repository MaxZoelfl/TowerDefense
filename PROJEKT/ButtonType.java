public enum ButtonType {
    PLAY("PlayButton.png"),
    BOW("Bow.png"),
    MAGIC("Magic.png"),
    CANON("Canon.png"),
    SNIPER("Sniper.png");

    private final String image;

    ButtonType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}