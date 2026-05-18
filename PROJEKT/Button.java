import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Button extends Actor
{
    
    protected int buttonType;

    public Button (int buttonType) {
        this.buttonType = buttonType;
        
        switch(buttonType) {
            
            case GameConstants.PLAY:
                setImage("PlayButton.png");
                break;
            
            case GameConstants.BOW:
                setImage("BowTower.png");
                break;
        }
    }
    
    public void act () {
        if (Greenfoot.mouseClicked(this)) {
            
            GameWorld world = (GameWorld) getWorld();
            world.setSelectedButton(buttonType);
        }
    }

}