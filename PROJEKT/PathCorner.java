import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class PathCorner extends Tile
{
    public PathCorner(int size, int rotation) {
        super(size, "PathCorner_" + rotation + ".png");

        setBuildable(false);
    }
}