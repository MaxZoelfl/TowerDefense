import greenfoot.*;

public class DrawUtil
{
    public static void fillRoundRect(
        GreenfootImage img,
        int x, int y,
        int w, int h,
        int radius)
    {
        img.fillRect(x + radius, y, w - radius * 2, h);

        img.fillRect(
            x,
            y + radius,
            radius,
            h - radius * 2
        );

        img.fillRect(
            x + w - radius,
            y + radius,
            radius,
            h - radius * 2
        );

        img.fillOval(
            x,
            y,
            radius * 2,
            radius * 2
        );

        img.fillOval(
            x + w - radius * 2,
            y,
            radius * 2,
            radius * 2
        );

        img.fillOval(
            x,
            y + h - radius * 2,
            radius * 2,
            radius * 2
        );

        img.fillOval(
            x + w - radius * 2,
            y + h - radius * 2,
            radius * 2,
            radius * 2
        );
    }

    public static void drawRoundRect(
        GreenfootImage img,
        int x, int y,
        int w, int h,
        int radius)
    {
        img.drawRect(x, y, w, h);
    }
}