package Model;

/**
 * Created by Patricki on 2/25/2017.
 */
public class Player {
    
    private Ship ship;
    private Shield shield;
    
    public Player(){
        shield = new Shield();
        ship = new Ship();
    }
    
    //keycodes for ship movement
    public final int VK_W = 87;
    public final int VK_A = 65;
    public final int VK_S = 83;
    public final int VK_D = 68;
    
    private int inputUP;
    private int inputLEFT;
    private int inputDOWN;
    private int inputRIGHT;
    
    //keycodes for shield movement
    public final int VK_LEFT = 37;
    public final int VK_RIGHT = 39;
    
    private int inputCOUNTERCLOCKWISE;
    private int inputCLOCKWISE;
    
    private int XX = 1000;      //should be max x length in GUI
    private int YY = 1000;      //should be may y for gui
    
    public void moveShipX(int speed, int xx, int XX) {
        //handles ship move logic across x axis
        if (this.ship.getX() + speed + (this.ship.getWidth() / 2) > XX) {
            this.ship.setX(this.ship.getX() + speed);
            this.shield.setX(this.ship.getX() + speed);
        }
        else if (this.ship.getX() + speed + (this.ship.getWidth() / 2) == XX) {
            this.ship.setX(this.ship.getX() - (XX - 1));
            this.shield.setX(this.ship.getX() + speed);
        }
        else if (this.ship.getX() + speed + (this.ship.getWidth() / 2) == 0) {
            this.ship.setX(this.ship.getX() + XX - 1);
            this.shield.setX(this.ship.getX() + speed);
        }
    }
    
    public void moveShipY(int speed, int yy, int YY){
        //handles ship move logic across y axis
        if (this.ship.getY() + speed + (this.ship.getHeight() / 2) > YY) {
            this.ship.setY(this.ship.getY() + speed);
            this.shield.setY(this.shield.getY() + speed);
        }
        else if (this.ship.getY() + speed + (this.ship.getHeight() / 2) == YY) {
            this.ship.setY(this.ship.getY() - (YY - 1));
            this.shield.setY(this.shield.getY() + speed);
        }
        else if (this.ship.getY() + speed + (this.ship.getHeight() / 2) == 0) {
            this.ship.setY(this.ship.getY() + YY - 1);
            this.shield.setY(this.shield.getY() + speed);
        }
    }
    
    
    //logic for shield movement
    public void moveShield(int speed) {
        
        speed = 5;
        
        int buffer = 5;     //holds variable for distance from center of ship to shield
        
        //center of ship/object position
        double cX = ship.getX() + ship.getWidth() / 2;
        double cY = ship.getY() + ship.getHeight() / 2;
    
        //outside edge of ship/object position
        double oX = cX + buffer;
        double oY = cY + buffer;
        
        //relative vector from current center position
        double vectorX = oX - cX;
        double vectorY = oY - cY;
        
        //find radius
        double radius = Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        
        //find angle the line from cX to oX is at
        double curTheta = Math.atan2(vectorX, vectorY);
        
        //speed at which the shield moves
//        int speed = 5;
        //inputCOUNTERCLOCKWISE = speed;
        //inputCLOCKWISE = speed* -1;
        
        //find angular position where speed is distance around around
        double deltaTheta = speed / radius;
        
        //find angular position where speed is a linear distance
//        double deltaTheta2 = 2 * Math.asin(speed / radius);
        
        //find new angle
        double counterTheta = curTheta + deltaTheta; //for counter clockwise movement
//        double clockwiseTheta = curTheta - deltaTheta; //for clockwise movement
        
        //updated relative vector
        double newVectorX = radius * Math.cos(counterTheta);
        double newVectorY = radius * Math.sin(counterTheta);
        
        //target point in relation to ship/object center
        double xPosition = cX + newVectorX;
        double yPosition = cY + newVectorY;
    
        
//        if (this.shield.getY() + speed + (this.ship.getWidth() / 2) > YY) {
//            this.shield.setY(this.shield.getY() + speed);
//        }
        
        //handles ship move logic across x axis
        if (this.shield.getX() + xPosition + (this.shield.getWidth() / 2) > XX) {
            this.shield.setX((int) (this.shield.getX() + xPosition));
        }
        else if (this.shield.getX() + xPosition + (this.shield.getWidth() / 2) == XX) {
            this.shield.setX(this.shield.getX() - (XX - 1));
        }
        else if (this.shield.getX() + xPosition + (this.shield.getWidth() / 2) == 0) {
            this.shield.setX(this.shield.getX() + XX - 1);
        }
    
        //handles shield move logic across y axis
        if (this.shield.getY() + yPosition + (this.ship.getWidth() / 2) > YY) {
            this.shield.setY((int) (this.shield.getY() + yPosition));
        }
        else if (this.shield.getY() + yPosition + (this.shield.getHeight() / 2) == YY) {
            this.shield.setY(this.shield.getY() - (YY - 1));
        }
        else if (this.ship.getY() + yPosition + (this.shield.getHeight() / 2) == 0) {
            this.shield.setY(this.shield.getY() + YY - 1);
        }
        
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
    
    public Ship getShip() {
        return ship;
    }
    
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    
    public Shield getShield() {
        return shield;
    }
    
    public void setShield(Shield shield) {
        this.shield = shield;
    }
    
    public int getInputUP() {
        return inputUP;
    }
    
    public void setInputUP(int inputUP) {
        this.inputUP = inputUP;
    }
    
    public int getInputLEFT() {
        return inputLEFT;
    }
    
    public void setInputLEFT(int inputLEFT) {
        this.inputLEFT = inputLEFT;
    }
    
    public int getInputDOWN() {
        return inputDOWN;
    }
    
    public void setInputDOWN(int inputDOWN) {
        this.inputDOWN = inputDOWN;
    }
    
    public int getInputRIGHT() {
        return inputRIGHT;
    }
    
    public void setInputRIGHT(int inputRIGHT) {
        this.inputRIGHT = inputRIGHT;
    }
    
    public int getInputCOUNTERCLOCKWISE() {
        return inputCOUNTERCLOCKWISE;
    }
    
    public void setInputCOUNTERCLOCKWISE(int inputCOUNTERCLOCKWISE) {
        this.inputCOUNTERCLOCKWISE = inputCOUNTERCLOCKWISE;
    }
    
    public int getInputCLOCKWISE() {
        return inputCLOCKWISE;
    }
    
    public void setInputCLOCKWISE(int inputCLOCKWISE) {
        this.inputCLOCKWISE = inputCLOCKWISE;
    }
}
