package Model;

import java.awt.*;

/**
 * Created by Patricki on 2/25/2017.
 */
public class Shield implements Collidable{
    
    Ship ship;
    
    //declare dimensions of shield
    private int width;
    private int hight;
    private int right;
    private int left;
    
    //declare location of shield
    private int baseLocationX = ship.getX();
    private int baseLocationY = ship.getY();
    private int X;
    private int Y;
    
    public Shield() {}
    
    public Shield(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    
    public int getX() {
        return X;
    }
    
    public void setX(int x) {
        X = x;
    }
    
    public int getY() {
        return Y;
    }
    
    public void setY(int y) {
        Y = y;
    }
    
    public Ship getShip() {
        return ship;
    }
    
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, hight);
    }
}
