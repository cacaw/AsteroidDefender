package Controller;

import Model.Asteroid;
import Model.Player;
import View.GUI;
import View.ViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by Patricki on 2/24/2017.
 */
public class Game {
    
    private Player player;
    private ArrayList<Asteroid> asteroids =new ArrayList<>();
    private Asteroid[] asteroidRay;
    
    private GUI ui;
//    private ViewController ui;
    private Timer timer;
    private int levelTime;
    
    
    
    //============================================================================
    
    public Game() throws IOException {
        
        ui = new GUI();
        player = new Player();
        asteroidRay = new Asteroid[3];
        
        //create and show ship
        //numbers are pong and will will probably need to be changed at some point
        player.getShip().setX((ui.getDisplay().getWidth() - 100);
        player.getShip().setY((ui.getDisplay().getHight() / 2) -150);
        
        //create and show shield
        //might need some math here like we have in the above ship
        player.getShield().setX(ui.getDisplay());
        player.getShield().setY(ui.getDisplay());
        
        //set player controls
        player.setInputUP(player.VK_W);
        player.setInputLEFT(player.VK_A);
        player.setInputDOWN(player.VK_S);
        player.setInputRIGHT(player.VK_D);
        player.setInputCOUNTERCLOCKWISE(player.VK_LEFT);
        player.setInputCLOCKWISE(player.VK_RIGHT);
        
        //TODO arraylist for asteroids
        //create and show asteroids
        for(int i = 0; i < asteroidRay.length; i++) {
            asteroidRay[i] = new Asteroid();
            asteroidRay[i].setX(ui.getDisplay);
            asteroidRay[i].setY(ui.getDisplay);
        }
        
        //TODO timer for movement
        timer = new Timer(1000/60, (ActionListener) e -> {
            mainLoop();
            ui.getPanel().repaint();
        });
    
    }
    
    public void mainLoop() {
        //TODO create main loop method for playing the game
        ui.RunActions();
        asteroidMotion();
        
    }
    
    private boolean checkLevelBeat() {
        //TODO create method for checking if level is over
        return true;
    }
    
    private void asteroidMotion() {
        //TODO create method for asteroid motion
    }
    
    
    
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }
    
    public void setAsteroids(ArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }
    
    public GUI getUi() {
        return ui;
    }
    
    public void setUi(GUI ui) {
        this.ui = ui;
    }
    
    public Timer getTimer() {
        return timer;
    }
    
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    public Asteroid[] getAsteroidRay() {
        return asteroidRay;
    }
    
    public void setAsteroidRay(Asteroid[] asteroidRay) {
        this.asteroidRay = asteroidRay;
    }
    
    public int getLevelTime() {
        return levelTime;
    }
    
    public void setLevelTime(int levelTime) {
        this.levelTime = levelTime;
    }
}
