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
        
        super(size);
        
        GreenfootImage img = new GreenfootImage("PathCorner.png");
        img.scale(64, 64);
        setImage(img);
        setRotation(rotation);
        
        setzeBebaubar(false);
    }
}