import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse PathCorner.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PathCorner extends Tile
{
    public PathCorner(int size, int rotation) {
        super(size, "PathCorner_" + rotation + ".png");

        setBuildable(false);
    }
}