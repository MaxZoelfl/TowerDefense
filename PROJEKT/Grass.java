import greenfoot.*;
/**
 * Write a description of class Gras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grass extends Tile {
    
    public Grass(int size) {
        super(size, "Grass.png");
        
        setBuildable(true);
    }
    
    public void act() {
        
        if (Greenfoot.mouseClicked(this)) {
            
            GameWorld world = (GameWorld) getWorld();
            
            if (world.getSelectedButton() == GameConstants.BOW && !isOccupied()) {
                
                world.addObject(new Bow(GameConstants.TILE_SIZE), getX(), getY());
                setOccupied(true);
                world.setSelectedButton(GameConstants.NONE);
            }
        }
    }
}
