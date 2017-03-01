package Model;

/**
 * Created by Patricki on 2/25/2017.
 */
public class Ship {
    
    //declare size of ship
    private int height = 2;
    private int width = 1;
    
    //2d location variables
    private int X;
    private int Y;
    
    //ship health
    private int health = 2;
    
    //ship speed
    private int xSpeed = 2;
    private int ySpeed = 2;
    
    public Ship() {}
    
    public Ship(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
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
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public int getxSpeed() {
        return xSpeed;
    }
    
    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    
    public int getySpeed() {
        return ySpeed;
    }
    
    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
}
