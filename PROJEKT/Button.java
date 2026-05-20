import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Button extends Actor
{
    
    protected ButtonType buttonType;
    protected TowerType towerType;

    public Button(ButtonType buttonType, TowerType towerType) {
        this.buttonType = buttonType;
        this.towerType = towerType;
        setImage(buttonType.getImage());
    }
    
    /*
    public Button (int buttonType) {
        this.buttonType = buttonType;
        
        switch(buttonType) {
            
            case GameConstants.PLAY:
                setImage("PlayButton.png");
                break;
            
            case GameConstants.BOW:
                setImage("Bow.png");
                break;
        }
    }
    */
    
    public void act () {
        if (Greenfoot.mouseClicked(this)) {
            
            GameWorld world = (GameWorld) getWorld();
            
            if (buttonType == ButtonType.PLAY) {
                world.setPaused();
            }
            
            else {
                world.setSelectedTower(towerType);
            }
        }
    }

}