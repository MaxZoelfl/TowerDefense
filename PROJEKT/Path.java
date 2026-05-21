import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Path extends Tile {
    
    public Path(int size, int rotation) {
        super(size, "Path_" + rotation + ".png");
        
        setBuildable(false);
    }

}
