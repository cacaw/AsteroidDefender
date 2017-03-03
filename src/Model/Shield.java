package Model;

import java.awt.*;

/**
 * Created by Patricki on 2/25/2017.
 */
public class Shield implements Collidable{
    
    private Ship ship;
    
    //declare dimensions of shield
    private int width = 5;
    private int height = 5;
//    private int right;
//    private int left;
    
    //declare location of shield
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
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
