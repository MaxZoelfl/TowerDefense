import greenfoot.*;

/**
 * GameOverScreen — Overlay bei Game Over oder Sieg.
 * Klick auf das Panel startet das Spiel neu.
 */
public class GameOverScreen extends Actor {

    private final boolean victory;

    public GameOverScreen(boolean victory) {
        this.victory = victory;
        buildImage();
    }

    private void buildImage() {
        int w = 520, h = 310;
        GreenfootImage img = new GreenfootImage(w, h);

        // Hintergrund (halbtransparent)
        img.setColor(new Color(0, 0, 0, 210));
        DrawUtil.fillRoundRect(
            img,
            0,
            0,
            w - 1,
            h - 1,
            22
        );

        // Farbiger Rahmen
        Color accent = victory
            ? new Color(70, 215, 70)
            : new Color(215, 55, 55);
        img.setColor(accent);
        DrawUtil.drawRoundRect(
            img,
            2,
            2,
            w - 5,
            h - 5,
            20
        );
        DrawUtil.drawRoundRect(
            img,
            5,
            5,
            w - 11,
            h - 11,
            17
        );

        // Titel
        new Font("Arial", true, false, 50);
        img.setColor(accent);
        String title = victory ? "SIEG!" : "GAME OVER";
        int tx = victory ? 170 : 75;
        img.drawString(title, tx, 100);

        // Untertitel
        new Font("Arial", false, false, 21);
        img.setColor(Color.WHITE);
        String sub = victory
            ? "Alle Wellen erfolgreich abgewehrt!"
            : "Das Lager wurde überrannt …";
        img.drawString(sub, 35, 150);

        // Trennlinie
        img.setColor(new Color(75, 75, 115));
        img.fillRect(40, 168, w - 80, 1);

        // Neustart-Hinweis
        new Font("Arial", false, true, 18);
        img.setColor(new Color(170, 195, 255));
        img.drawString("Hier klicken zum Neustart", 135, 220);

        new Font("Arial", false, false, 13);
        img.setColor(new Color(105, 115, 145));
        img.drawString("(Greenfoot-Fenster neu öffnen falls nötig)", 95, 265);

        setImage(img);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new GameWorld());
        }
    }
}