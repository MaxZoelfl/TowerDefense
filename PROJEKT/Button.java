import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Button extends Actor
{
    
    protected ButtonType buttonType;

    public Button(ButtonType buttonType) {
        this.buttonType = buttonType;
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
            world.setSelectedButton(buttonType);
        }
    }

}