package Model;

/**
 * Created by Patricki on 2/25/2017.
 */
public class Player {
    
    Ship ship;
    Shield shield;
    
    
    //keycodes for ship movement
    public final int VK_W = 87;
    public final int VK_A = 65;
    public final int VK_S = 83;
    public final int VK_D = 68;
    
    private int inputUP;
    private int inputLEFT;
    private int inputDOWN;
    private int inputRIGHT;
    
    //just using for reference
    private boolean inputUP;
    private boolean inputLEFT;
    private boolean inputDOWN;
    private boolean inputRIGHT;
    private boolean inputCOUNTERCLOCKWISE;
    private boolean inputCLOCKWISE;
    
    //keycodes for shield movement
    public final int VK_LEFT = 37;
    public final int VK_RIGHT = 39;
    
    private int inputCOUNTERCLOCKWISE;
    private int inputCLOCKWISE;
    
    //logic for ship movement
    public void moveShip(int input, int x, int y){
        if (inputUP) {
            this.ship.getY() = this.ship.getY() + this.ship.getySpeed();
        }else if (inputDOWN) {
            this.ship.getY() = this.ship.getY() - this.ship.getySpeed();
        }else if (inputLEFT) {
            this.ship.getX() = this.ship.getX() - this.ship.getxSpeed();
        }else if (inputRIGHT) {
            this.ship.getX() = this.ship.getX() + this.ship.getxSpeed();
        }
        
        
        // wraping logic
        int maxX = 500;//maximum width of screen/panel
        int maxY = 500;//maximum length of screen/panel
        int xx;
        int yy;
        
        if (xx < 0) {
            xx = maxX;
        }
        if (xx > maxX){
            xx = 0;
        }
        if (yy < 0){
            yy = maxY;
        }
        if (yy > maxY) {
            yy = 0;
        }
     
     
    }
    
    
    //logic for shield movement
    public void moveShield() {
        
        //outside edge of ship/object position
        double oX = ship.getX() + ship.getHeight() + 5;
        double oY = ship.getY() + ship.getHeight() + 5;
        
        //center of ship/object position
        double cX = ship.getX() + ship.getWidth() / 2;
        double cY = ship.getY() + ship.getHeight() / 2;
        
        //relative vector from current center position
        double vectorX = oX - cX;
        double vectorY = oY - cY;
        
        //find radius
        double radius = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        
        //find angle the line from cX to oX is at
        double curTheta = Math.atan2(vectorX, vectorY);
        
        //speed at which the shield moves
        int speed = 5;
        //inputCOUNTERCLOCKWISE = speed;
        //inputCLOCKWISE = speed* -1;
        
        //find angular position where speed is distance around around
        double deltaTheta = speed / radius;
        
        //find angular position where speed is a linear distance
        double deltaTheta2 = 2 * Math.asin(speed / radius);
        
        //find new angle
        double counterTheta = curTheta + deltaTheta; //for counter clockwise movement
        double clockwiseTheta = curTheta - deltaTheta; //for clockwise movement
        
        //updated relative vector
        double newVectorX = radius * Math.cos(counterTheta);
        double newVectorY = radius * Math.sin(counterTheta);
        
        //target point in relation to ship/object center
        double xPosition = cX + newVectorX;
        double yPosition = cY + newVectorY;
        
        /* another formula which might work
        deltaX = oX-cX;
        deltaY = oY-cY;
        radius = sqrt(deltaX*deltaX+deltaY*deltaY);
        orthoX = -deltaY*d/radius;
        orthoY = deltaX*d/radius;
        newDeltaX = deltaX+orthoX;
        newDeltaY = deltaY+orthoY;
        newLength = sqrt(newDeltaX*newDeltaX+newDeltaY*newDeltaY);
        aX = cX+newDeltaX*radius/newLength;
        aY = cY+newDeltaY*radius/newLength;
         */
    }
    
    
}
