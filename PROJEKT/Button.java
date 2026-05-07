import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

public class Button extends Actor
{
    protected int buttonType;
    
    public Button (int buttonType) {
        this.buttonType = buttonType;
    }
    
    public void act () 
    {
        if (Greenfoot.mouseClicked(this)) {
            //
        }
    }    
}