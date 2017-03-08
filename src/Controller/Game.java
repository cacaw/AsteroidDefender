package Controller;

import Model.Asteroid;
import Model.Player;
import View.MainMenu;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Patricki on 2/24/2017.
 */
public class Game {
    
    private Player player;
    private Asteroid asteroid;
    private ArrayList<Asteroid> asteroids =new ArrayList<>();
    private Asteroid[] asteroidRay;
    
    private MainMenu ui;
//    private ViewController ui;
    private Timer timer;
    private int levelTime;
    
    
    
    //============================================================================
    
    public Game() throws IOException {
        
        ui = new MainMenu();
        player = new Player();
        asteroidRay = new Asteroid[3];
        
        //set ship position
        //should start ship in middle of frame
        player.getShip().setX((ui.getFrame().getWidth() / 2));
        player.getShip().setY((ui.getFrame().getHeight() / 2));
        
        //set shield position
        //should start shield relative to ship
        player.getShield().setX((ui.getFrame().getWidth()));
        player.getShield().setY(ui.getFrame().getHeight());
        
        //set player controls
        player.setInputUP(player.VK_W);
        player.setInputLEFT(player.VK_A);
        player.setInputDOWN(player.VK_S);
        player.setInputRIGHT(player.VK_D);
        player.setInputCOUNTERCLOCKWISE(player.VK_LEFT);
        player.setInputCLOCKWISE(player.VK_RIGHT);
        
        //set positions for asteroids
        //TODO randominzing method for asteroid starting placement
        int x = 100;
        int y = 100;
        for(int i = 0; i < 5; i++) {
            asteroids.add(new Asteroid(80,x,y, 20, 25, asteroids, ui.getFirstLevel(), player));
            x+=100;
            y+=100;
        }
        
        //TODO timer for movement
        //the way its set up right now it will only have a timer on the first level
        timer = new Timer(1000/60, (ActionListener) e -> {
            mainLoop();
//            ui.getFirstLevel().repaint();
        });
    
    }
    
    public void mainLoop() {
        //TODO create main loop method for playing the game
        ui.RunActions();
        asteroidMotion();
    
        boolean LevelBeat = checkLevelBeat();
        
        //stops timer if level beat
        if (LevelBeat != false) {
            timer.stop();
        }
    }
    
    
    public void GameStart(){
        timer.start();
    }
    
    public void togglePause() {
        if (timer.isRunning()) {
            timer.stop();
        }else {
            timer.start();
        }
    }
    
    private boolean checkLevelBeat() {
        
        // checks to see if the player survived the timeer or if the ships health reaches 0
        if (levelTime == 0 || player.getShip().getHealth() == 0) {
            return true;
        }
        return false;
    }
    
    private boolean checkLevelLost() {
        if (player.getShip().getHealth() == 0) {
            return true;
        }
        return false;
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
    
    public MainMenu getUi() {
        return ui;
    }
    
    public void setUi(MainMenu ui) {
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
    
    public Asteroid getAsteroid() {
        return asteroid;
    }
    
    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }
    
    public int getLevelTime() {
        return levelTime;
    }
    
    public void setLevelTime(int levelTime) {
        this.levelTime = levelTime;
    }
}
