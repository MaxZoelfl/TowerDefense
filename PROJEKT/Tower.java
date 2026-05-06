import greenfoot.*;
import java.util.ArrayList;
/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower extends Actor
{
    // instance variables - replace the example below with your own
    protected int size;

    protected int xPos;
    protected int yPos;
    
    protected int range;
    protected int damage;
    protected int cooldown;

    /**
     * Constructor for objects of class Tower
     */
    public Tower(int range, int damage, int cooldown, int size){
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.size = size;
    }

    public void setzePos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public int gibX() {
        return xPos;
    }
    
    public int gibY() {
        return yPos;
    }    
    
    public int gibRange() {
        return range;
    }
    
    public void drawTower(String image) {
        GreenfootImage img = new GreenfootImage(image);
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, img.getWidth() - 1, img.getHeight() - 1);
        setImage(img);
    }
    
    public void schiesse() {
        this.damage = damage;
        Projectile projectile = new Projectile(damage);
    }
    
        public Enemy nächsterEnemy(ArrayList<Enemy> enemies, ArrayList<Tower> towers) {
        Enemy nächsterGegner = null;
        double minAbstand = Double.MAX_VALUE;
        double abstand;
        int dX;
        int dY;
        for (Tower t : towers) {
            for (Enemy e : enemies) {
                dX = e.gibX() - t.gibX();
                dY = e.gibY() - t.gibY();
                abstand = dX*dX + dY*dY;
                if ( abstand < minAbstand) {
                    minAbstand = abstand;
                    nächsterGegner = e;
                }
            }
        }  
        return nächsterGegner;
    }
    
}
