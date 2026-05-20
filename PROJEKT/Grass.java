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
        
        GameWorld world = (GameWorld) getWorld();
        
        if (Greenfoot.mouseClicked(this) && world.getSelectedTower() != null) {
            
            placeTower(world.getSelectedTower());
        }
    }
    
    private void placeTower(TowerType towerType) {
        
        GameWorld world = (GameWorld) getWorld();
        
        if (!isOccupied() && world.getBalance() >= towerType.getCost()) {
            
            world.addObject(new Tower(GameConstants.TILE_SIZE, towerType), getX(), getY());
            setOccupied(true);
            world.adjustBalance(-towerType.getCost());
            //world.setSelectedButton(GameConstants.NONE);
            world.setSelectedTower(null);
        }
    }
}
