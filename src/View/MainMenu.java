package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements KeyListener {

	/**
	 *
	 */
	private Game game;
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Times New Roman", Font.PLAIN, 55);
	private JFrame frame;
	private JPanel mainMenuPanel, mainMenuButtons, firstLevelScreen, firstLevelButtons, controlsScreen,
			controlsScreenLabels, levelSelectScreen, levelSelectScreenButtons;
	private JButton start, quit, viewControls, selectLevel;
	private int userConfirmationInput;
	private GridBagConstraints c;

	public static void main(String[] args) throws IOException {
		new MainMenu();
	}

	public MainMenu() throws IOException {
		initComponents();
	}

	private void initComponents() throws IOException {
		buildFrame();
		buildLevel();
		buildControlsScreen();
		buildSelectLevelScreen();
		initJButtons();
		createLayout();
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(this.frame, "Welcome to the Asteroid Dodger CSC150 application!");

	}

	private void createLayout() throws IOException {
		c = new GridBagConstraints();
		BufferedImage image = ImageIO.read(new File("MainMenu.jpg"));

		mainMenuPanel = new JPanel(new GridBagLayout()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		mainMenuButtons = new JPanel();

		mainMenuButtons.setOpaque(false);
		mainMenuButtons.setLayout(new BoxLayout(mainMenuButtons, BoxLayout.Y_AXIS));

		mainMenuButtons.add(start);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);

		mainMenuButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		mainMenuButtons.add(selectLevel);
		selectLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		mainMenuButtons.add(viewControls);
		viewControls.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		mainMenuButtons.add(quit);
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuPanel.add(mainMenuButtons, c);
		frame.getContentPane().add(mainMenuPanel);
		mainMenuPanel.repaint();
	}

	private void initJButtons() {
		start = new JButton("Start");
		start.setFont(font);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				startButtonEvent(evt);
			}
		});
		selectLevel = new JButton("Select Level");
		selectLevel.setFont(font);
		selectLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				selectLevelButtonEvent(evt);
			}

		});
		viewControls = new JButton("View Controls");
		viewControls.setFont(font);
		viewControls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				viewControlsButtonEvent(evt);
			}

		});
		quit = new JButton("Quit");
		quit.setFont(font);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				quitButtonEvent(evt);
			}

		});

	}

	private void buildFrame() {
		frame = new JFrame("Asteroid Dodger");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void buildLevel() {
		Image img = Toolkit.getDefaultToolkit().getImage("SpaceBackground.gif");

		firstLevelScreen = new JPanel(new GridBagLayout()) {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}

		};

		c = new GridBagConstraints();

		firstLevelButtons = new JPanel();
		JButton mainMenuReturn = new JButton("Main Menu");
		JButton quitLevel = new JButton("Quit Level");
		// utilize one panel - rebuilding / have class for it

		// firstLevelScreen.setBackground(Color.darkGray);

		firstLevelButtons.setOpaque(false);
		firstLevelButtons.setLayout(new BoxLayout(firstLevelButtons, BoxLayout.Y_AXIS));

		mainMenuReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuReturn.setFont(font);

		quitLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitLevel.setFont(font);

		firstLevelButtons.add(mainMenuReturn, c);
		firstLevelButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		firstLevelButtons.add(quitLevel, c);

		firstLevelScreen.add(firstLevelButtons);
		firstLevelScreen.requestFocus();
		firstLevelScreen.setFocusable(true);
		firstLevelScreen.addKeyListener(this);

		// implement keyPress + key release action listeners
		// give quitLevel an option to return to level select screen
		mainMenuReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userConfirmationInput = JOptionPane.showConfirmDialog(null, "Do you want to return to main menu?",
						"Select desired option?", JOptionPane.YES_NO_CANCEL_OPTION);
				if (userConfirmationInput == 0) {
					mainMenuReturnFromInsideLevel(evt);
				}

			}
		});

		quitLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userConfirmationInput = JOptionPane.showConfirmDialog(null,
						"Do you want to return to level select screen?", "Select desired option",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (userConfirmationInput == 0) {
					quitLevelReturnToLevelSelect(e);
				}

			}

		});

		firstLevelButtons.setVisible(false);

	}

	private void buildControlsScreen() {

		controlsScreen = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		controlsScreenLabels = new JPanel();
		JLabel mainMenuControls = new JLabel("Controls");
		JLabel moveUpwards = new JLabel("To move Up, use the W key.");
		JLabel moveLeft = new JLabel("To move Left, use the A key.");
		JLabel moveDownwards = new JLabel("To move Down, use the S key.");
		JLabel moveRight = new JLabel("To move Right, use the D key.");
		JLabel pause = new JLabel("To Pause and UnPause the Game, use the P key.");
		JButton mainMenuReturn = new JButton("Main Menu");

		controlsScreen.setBackground(Color.darkGray);
		controlsScreenLabels.setOpaque(false);
		controlsScreenLabels.setLayout(new BoxLayout(controlsScreenLabels, BoxLayout.Y_AXIS));

		mainMenuControls.setFont(font);
		mainMenuControls.setForeground(Color.RED);

		moveUpwards.setFont(font);
		moveUpwards.setForeground(Color.RED);

		moveDownwards.setFont(font);
		moveDownwards.setForeground(Color.RED);

		moveLeft.setFont(font);
		moveLeft.setForeground(Color.RED);

		moveRight.setFont(font);
		moveRight.setForeground(Color.RED);

		pause.setFont(font);
		pause.setForeground(Color.RED);

		mainMenuReturn.setFont(font);
		mainMenuReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userConfirmationInput = JOptionPane.showConfirmDialog(null, "Do you want to return to main menu?",
						"Select desired option?", JOptionPane.YES_NO_CANCEL_OPTION);
				if (userConfirmationInput == 0) {
					mainMenuReturnFromControls(evt);
				}

			}
		});

		controlsScreenLabels.add(mainMenuControls);
		controlsScreenLabels.add(moveUpwards);
		controlsScreenLabels.add(moveLeft);
		controlsScreenLabels.add(moveDownwards);
		controlsScreenLabels.add(moveRight);
		controlsScreenLabels.add(pause);
		controlsScreenLabels.add(Box.createRigidArea(new Dimension(0, 50)));
		c.gridx = 0;
		c.gridy = 0;
		controlsScreen.add(controlsScreenLabels, c);
		c.gridx = 0;
		c.gridy = 1;
		controlsScreen.add(mainMenuReturn, c);

	}

	private void buildSelectLevelScreen() {
		levelSelectScreen = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		levelSelectScreenButtons = new JPanel();
		JButton mainMenuReturn = new JButton("Main Menu");
		JButton firstLevel = new JButton("First Level");
		JButton secondLevel = new JButton("Second Level");
		JButton thirdLevel = new JButton("Third Level");

		levelSelectScreen.setBackground(Color.darkGray);
		levelSelectScreenButtons.setOpaque(false);
		levelSelectScreenButtons.setLayout(new BoxLayout(levelSelectScreenButtons, BoxLayout.Y_AXIS));
		// Use Start Button for Level One
		// Use Return to Main Menu Button
		// need logic to find if a level has been completed or not
		// need a way to state that a level has been completed in order to
		// implement
		// setEnable(true) when the previous level has been cleared.
		// second/third level need actionListeners

		firstLevel.setFont(font);
		secondLevel.setFont(font);
		thirdLevel.setFont(font);
		mainMenuReturn.setFont(font);

		levelSelectScreen.requestFocus();
		levelSelectScreen.setFocusable(true);
		levelSelectScreen.addKeyListener(this);

		firstLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonEvent(e);

			}
		});

		mainMenuReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userConfirmationInput = JOptionPane.showConfirmDialog(null, "Do you want to return to main menu?",
						"Select desired option?", JOptionPane.YES_NO_CANCEL_OPTION);
				if (userConfirmationInput == 0) {
					mainMenuReturnFromLevelSelect(evt);
				}

			}
		});

		secondLevel.setEnabled(false);
		thirdLevel.setEnabled(false);

		levelSelectScreenButtons.add(firstLevel);
		levelSelectScreenButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		levelSelectScreenButtons.add(secondLevel);
		levelSelectScreenButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		levelSelectScreenButtons.add(thirdLevel);
		levelSelectScreenButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		levelSelectScreenButtons.add(mainMenuReturn);
		levelSelectScreen.add(levelSelectScreenButtons);

	}

	private void startButtonEvent(ActionEvent evt) {
		userConfirmationInput = JOptionPane.showConfirmDialog(null, "Select desired option", "Are you certain?",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (userConfirmationInput == 0) {
			mainMenuPanel.setVisible(false);
			levelSelectScreen.setVisible(false);
			firstLevelScreen.setVisible(true);
			frame.getContentPane().add(firstLevelScreen);

		}

	}

	private void mainMenuReturnFromInsideLevel(ActionEvent evt) {
		firstLevelButtons.setVisible(false);
		firstLevelScreen.setVisible(false);
		mainMenuPanel.setVisible(true);
	}

	private void quitLevelReturnToLevelSelect(ActionEvent e) {
		firstLevelButtons.setVisible(false);
		firstLevelScreen.setVisible(false);
		levelSelectScreen.setVisible(true);
		frame.getContentPane().add(levelSelectScreen);
	}

	private void selectLevelButtonEvent(ActionEvent evt) {
		userConfirmationInput = JOptionPane.showConfirmDialog(null, "Select desired option", "Are you certain?",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (userConfirmationInput == 0) {
			levelSelectScreen.setVisible(true);
			frame.getContentPane().add(levelSelectScreen);
			mainMenuPanel.setVisible(false);
		}

	}

	private void mainMenuReturnFromControls(ActionEvent evt) {
		controlsScreen.setVisible(false);
		mainMenuPanel.setVisible(true);
	}

	private void mainMenuReturnFromLevelSelect(ActionEvent evt) {
		levelSelectScreen.setVisible(false);
		mainMenuPanel.setVisible(true);

	}

	private void viewControlsButtonEvent(ActionEvent evt) {
		userConfirmationInput = JOptionPane.showConfirmDialog(null, "Select desired option", "Are you certain?",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (userConfirmationInput == 0) {
			controlsScreen.setVisible(true);
			frame.getContentPane().add(controlsScreen);
			mainMenuPanel.setVisible(false);
		}
	}

	private void quitButtonEvent(ActionEvent evt) {

		userConfirmationInput = JOptionPane.showConfirmDialog(null, "You will be exited.", "Exit?",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (userConfirmationInput == 0) {
			System.exit(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (firstLevelButtons.isVisible() == false && e.getKeyCode() == KeyEvent.VK_P) {
			firstLevelButtons.setVisible(true);
			// timer.start (continue)
			// game.togglePause();

		} else if (firstLevelButtons.isVisible() == true && e.getKeyCode() == KeyEvent.VK_P) {
			firstLevelButtons.setVisible(false);
			// timer.stop (pause)
			// game.togglePause();
			// actions.remove((Integer) e.getKeyCode());

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	private ArrayList<Integer> actions;

	// public class ViewController implements Serializable,KeyListener{
	// private int moveAmount = game.getPlayer().getShip().getxSpeed();
	private int moveAmount = 5;
	private int shieldSpeed = 5;

	public void RunActions() {

		int shipMove; // ship speed
		actions = new ArrayList<>(); // i think this goes in the main layout
		// method

		for (int i = 0; i < actions.size(); i++) {
			if (actions.get(i).intValue() == game.getPlayer().getInputUP()) {
				game.getPlayer().moveShipY(moveAmount, 0, 1000);
			} else if (actions.get(i).intValue() == game.getPlayer().getInputLEFT()) {
				game.getPlayer().moveShipX(moveAmount * -1, 0, 1000);
			} else if (actions.get(i).intValue() == game.getPlayer().getInputDOWN()) {
				game.getPlayer().moveShipY(moveAmount * -1, 0, 1000);
			} else if (actions.get(i).intValue() == game.getPlayer().getInputRIGHT()) {
				game.getPlayer().moveShipX(moveAmount, 0, 1000);
			} else if (actions.get(i).intValue() == game.getPlayer().getInputCOUNTERCLOCKWISE()) {
				game.getPlayer().moveShield(shieldSpeed);
			} else if (actions.get(i).intValue() == game.getPlayer().getInputCLOCKWISE()) {
				game.getPlayer().moveShield(shieldSpeed * -1);
			}
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getMainMenu() {
		return mainMenuPanel;
	}

	public void setMainMenu(JPanel mainMenu) {
		this.mainMenuPanel = mainMenu;
	}

	public JPanel getMainMenuButtons() {
		return mainMenuButtons;
	}

	public void setMainMenuButtons(JPanel mainMenuButtons) {
		this.mainMenuButtons = mainMenuButtons;
	}

	public JPanel getFirstLevel() {
		return firstLevelScreen;
	}

	public void setFirstLevel(JPanel firstLevel) {
		this.firstLevelScreen = firstLevel;
	}

	public JPanel getFirstLevelButtons() {
		return firstLevelButtons;
	}

	public void setFirstLevelButtons(JPanel firstLevelButtons) {
		this.firstLevelButtons = firstLevelButtons;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}