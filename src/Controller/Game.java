package Controller;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Created by Patricki on 2/24/2017.
 */
public class Game extends JPanel {

	private Player player;
	private Asteroid asteroid;
	private ArrayList<Asteroid> asteroids = new ArrayList<>();
	private Asteroid[] asteroidRay;
	private JPanel mainPanel;
	private MainMenu ui;
	// private ViewController ui;
	private Timer timer;
	private int levelTime;
	private int astRem;
	private int astCount;

	// ============================================================================

	public Game(JPanel isOn, int Diff, int PlayTime, MainMenu myMenu) throws IOException {

		ui = myMenu;
		player = new Player();
		this.setSize(isOn.getWidth(), isOn.getHeight());
		// set ship position
		// should start ship in middle of frame
		player.getShip().setX((ui.getFrame().getWidth() / 2));
		player.getShip().setY((ui.getFrame().getHeight() / 2));

		// set shield position
		// should start shield relative to ship
		player.getShield().setX((ui.getFrame().getWidth()));
		player.getShield().setY(ui.getFrame().getHeight());

		// set player controls
		player.setInputUP(player.VK_W);
		player.setInputLEFT(player.VK_A);
		player.setInputDOWN(player.VK_S);
		player.setInputRIGHT(player.VK_D);
		player.setInputCOUNTERCLOCKWISE(player.VK_LEFT);
		player.setInputCLOCKWISE(player.VK_RIGHT);

		// set positions for asteroids
		// TODO randominzing method for asteroid starting placement
		int x = 100;
		int y = 100;
		for (int i = 0; i < Diff; i++) {
			asteroids.add(new Asteroid(80, x, y, 20, 25, asteroids, this, player));
			x += 90;
			y += 90;
		}

		// TODO timer for movement
		// the way its set up right now it will only have a timer on the first
		// level
		timer = new Timer(1000 / 60, (ActionListener) e -> {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ui.getFirstLevel().repaint();
		});

		astRem = PlayTime / Diff;
	}

	public void mainLoop() throws InterruptedException {
		// TODO create main loop method for playing the game
		ui.RunActions();
		asteroidMotion();

		boolean LevelBeat = checkLevelBeat();

		// stops timer if level beat
		if (LevelBeat) {
			timer.stop();
		}
	}

	public void GameStart() throws InterruptedException {
		// timer.start();
		// Thread.sleep(50);

		asteroidMotion();

	}

	public void togglePause() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	private boolean checkLevelBeat() {

		// checks to see if the player survived the timer or if the ships health
		// reaches 0 or if all asteroids are gone
		if (player.getShip().getHealth() == 0 || asteroids.size() == 0) {
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

	private void asteroidMotion() throws InterruptedException {
		// TODO create method for asteroid motion

		if (asteroids.size() > 0) {

			for (int i = 0; i < asteroids.size(); i++) {
				asteroids.get(i).move();
				this.repaint();
				Thread.sleep(50);
			}
		}
	}

	public void paint(Graphics g) {
		for (Asteroid rock : asteroids) {
			rock.paint(g);
		}
		try {
			player.paint(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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