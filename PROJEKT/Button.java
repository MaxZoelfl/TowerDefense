import greenfoot.*;

public class Button extends Actor {

    protected ButtonType buttonType;
    protected TowerType  towerType;

    private boolean lastHighlighted = false;

    private static final int ICON_SIZE = 48;
    private static final int PAD_X     = 7;
    private static final int PAD_Y     = 6;

    public Button(ButtonType buttonType, TowerType towerType) {
        this.buttonType = buttonType;
        this.towerType  = towerType;
        drawButton(false);
    }

    public void act() {
        GameWorld world = (GameWorld) getWorld();
        if (world == null) return;

        // Klick-Verarbeitung
        if (Greenfoot.mouseClicked(this)) {
            if (buttonType == ButtonType.PLAY) {
                world.setPaused();
            } else {
                // Toggle: Klick auf bereits selektierten Tower → abwählen
                TowerType current = world.getSelectedTower();
                world.setSelectedTower(current == towerType ? null : towerType);
            }
        }

        // Visuellen Zustand aktualisieren (nur wenn sich etwas geändert hat)
        boolean highlighted = (buttonType == ButtonType.PLAY)
            ? !world.getPaused()
            : (world.getSelectedTower() == towerType);

        if (highlighted != lastHighlighted) {
            lastHighlighted = highlighted;
            drawButton(highlighted);
        }
    }

    private void drawButton(boolean highlighted) {
        int textH  = (towerType != null) ? 18 : 0;
        int totalW = ICON_SIZE + PAD_X * 2;          // 62 px
        int totalH = ICON_SIZE + PAD_Y * 2 + textH;  // 78 px (Tower) / 60 px (Play)

        GreenfootImage img = new GreenfootImage(totalW, totalH);

        // Hintergrund
        img.setColor(highlighted
            ? new Color(55, 95, 185, 220)
            : new Color(32, 32, 58, 200));
        DrawUtil.fillRoundRect(
            img,
            0,
            0,
            totalW - 1,
            ICON_SIZE + PAD_Y * 2 - 1,
            10
        );

        // Rahmen
        img.setColor(highlighted
            ? new Color(110, 160, 255)
            : new Color(65, 65, 105));
        DrawUtil.drawRoundRect(
            img,
            0,
            0,
            totalW - 1,
            ICON_SIZE + PAD_Y * 2 - 1,
            10
        );
        if (highlighted)
            DrawUtil.drawRoundRect(
                img,
                1,
                1,
                totalW - 3,
                ICON_SIZE + PAD_Y * 2 - 3,
                8
            );

        // Icon
        GreenfootImage icon = new GreenfootImage(buttonType.getImage());
        icon.scale(ICON_SIZE, ICON_SIZE);
        img.drawImage(icon, PAD_X, PAD_Y);

        // Kosten-Text für Tower-Buttons
        if (towerType != null) {
            img.setFont(new Font("Arial", true, false, 12));
            img.setColor(new Color(255, 205, 50));
            String costStr = towerType.getCost() + " Gold";
            img.drawString(costStr, PAD_X, totalH - 3);
        }

        setImage(img);
    }
}