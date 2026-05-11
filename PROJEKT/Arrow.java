import greenfoot.*;
/**
 * Basic starter Tower
 * Medium Range
 * Medium Damage
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Tower
{
    
    public Arrow(int size) {
        super(128, 10, 32, size);
        
        GreenfootImage img = new GreenfootImage("BowTower.png");
        img.scale(64, 64);
        setImage(img);
    }
    
    /**
     * Act - do whatever the Arrow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
