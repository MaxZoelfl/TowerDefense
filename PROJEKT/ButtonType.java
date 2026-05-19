public enum ButtonType {
    NONE(""),
    PLAY("PlayButton.png"),
    BOW("Bow.png"),
    CANON("Canon.png"),
    ELECTRO("Electro.png"),
    SNIPER("Sniper.png");

    private final String image;

    ButtonType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}