package View;

import Model.Asteroid;
import Model.Shield;
import Model.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Patricki on 3/7/2017.
 */
public class Level1 extends JPanel implements Serializable, ActionListener{
    
    MainMenu mainView;
    
    Graphics2D G2;
    
    private Font font = new Font("Times New Roman", Font.PLAIN, 55);
    
    public Level1(MainMenu theMain) {
        this.mainView = theMain;
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        
        g.setColor(Color.black);
        g.fillRect(0, 0, this.mainView.getFrame().getWidth(), this.mainView.getFrame(),getHeight());
        
        this.G2 = (Graphics2D) g;
    
        ArrayList<Asteroid> field = mainView.getGame().getAsteroids();
            for (Asteroid rock: field){
                rock.paint(g);
//                asteroid<i> = this.mainView.getGame().getAsteroid();
//                asteroid.get(i).paint(g);
            }
    
        Ship ship = this.mainView.getGame().getPlayer().getShip();
        Shield shield = this.mainView.getGame().getPlayer().getShield();
        
        G2.fillRect(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());
        G2.fillRect(shield.getX(), shield.getY(), shield.getWidth(), shield.getHeight());
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
