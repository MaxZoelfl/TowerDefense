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
            
            if (world.getSelectedButton() == ButtonType.BOW && !isOccupied()) {
                world.addObject(new Tower(GameConstants.TILE_SIZE, TowerType.BOW), getX(), getY());
                setOccupied(true);
                //world.setSelectedButton(GameConstants.NONE);
                world.setSelectedButton(ButtonType.NONE);
            }
        }
    }
}
