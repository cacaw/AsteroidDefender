package View;

import Controller.Game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu implements KeyListener {
	
	private Game game;
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Times New Roman", Font.PLAIN, 55);
	private JFrame frame;
	private JPanel mainMenuPanel, mainMenuButtons, firstLevel, firstLevelButtons, controlsScreen, controlsScreenLabels;
	private JButton start, quit, viewControls, selectLevel;
	private int userConfirmationInput;
	private GridBagConstraints c;

	public MainMenu() throws IOException {
		initComponents();
	}

	private void initComponents() throws IOException {
		buildFrame();
		buildLevel();
		buildControlsScreen();
		initJButtons();
		createLayout();
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(this.frame, "Welcome to the Asteroid Dodger CSC150 application!");

	}

	private void createLayout() throws IOException {
		c = new GridBagConstraints();
		BufferedImage image = ImageIO.read(new File("Images/MainMenu.jpg"));

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
		firstLevel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		firstLevelButtons = new JPanel();
		JButton mainMenuReturn = new JButton("Main Menu");
		JButton quitLevel = new JButton("Quit Level");
		// utilize one panel - rebuilding / have class for it

		firstLevel.setBackground(Color.darkGray);

		firstLevelButtons.setOpaque(false);
		firstLevelButtons.setLayout(new BoxLayout(firstLevelButtons, BoxLayout.Y_AXIS));

		mainMenuReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuReturn.setFont(font);

		quitLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitLevel.setFont(font);

		firstLevelButtons.add(mainMenuReturn, c);
		firstLevelButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		firstLevelButtons.add(quitLevel, c);

		firstLevel.add(firstLevelButtons);
		firstLevel.requestFocus();
		firstLevel.setFocusable(true);
		firstLevel.addKeyListener(this);

		// implement keyPress + key release action listeners
		// give quitLevel an option to return to level select screen
		mainMenuReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userConfirmationInput = JOptionPane.showConfirmDialog(null, "Do you want to return to main menu?",
						"Select desired option?", JOptionPane.YES_NO_CANCEL_OPTION);
				if (userConfirmationInput == 0) {
					mainMenuReturnFromLevel(evt);
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
					levelSelectReturn(e);
				}

			}

		});

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
		controlsScreenLabels.add(Box.createRigidArea(new Dimension(0, 50)));
		c.gridx = 0;
		c.gridy = 0;
		controlsScreen.add(controlsScreenLabels, c);
		c.gridx = 0;
		c.gridy = 1;
		controlsScreen.add(mainMenuReturn, c);

	}

	private void startButtonEvent(ActionEvent evt) {
		userConfirmationInput = JOptionPane.showConfirmDialog(null, "Select desired option", "Are you certain?",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (userConfirmationInput == 0) {
			firstLevel.setVisible(true);
			frame.getContentPane().add(firstLevel);
			mainMenuPanel.setVisible(false);
		}

	}

	private void mainMenuReturnFromLevel(ActionEvent evt) {
		firstLevel.setVisible(false);
		mainMenuPanel.setVisible(true);
	}

	private void levelSelectReturn(ActionEvent e) {
		// firstLevel.setVisible(false);
		// level select screen visible true

	}

	private void selectLevelButtonEvent(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void mainMenuReturnFromControls(ActionEvent evt) {
		controlsScreen.setVisible(false);
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
		System.exit(0);

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

		} else if (firstLevelButtons.isVisible() == true && e.getKeyCode() == KeyEvent.VK_P) {
			firstLevelButtons.setVisible(false);
			// timer.stop (pause)
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
        actions.remove((Integer)e.getKeyCode());
        
        if(e.getKeyCode() == 80){
            game.togglePause();
        }

	}
    

    private ArrayList<Integer> actions;
	
    
    //    public class ViewController implements Serializable,KeyListener{
//    private int moveAmount = game.getPlayer().getShip().getxSpeed();
    private int moveAmount = 5;
    private int shieldSpeed = 5;
    
    public void RunActions() {
        
        int shipMove; // ship speed
        actions = new ArrayList<>(); // i think this goes in the main layout method
        
        for (int i = 0; i < actions.size(); i++) {
            if(actions.get(i).intValue() == game.getPlayer().getInputUP()) {
                game.getPlayer().moveShipY(moveAmount, 0, 1000);
            }else if (actions.get(i).intValue() == game.getPlayer().getInputLEFT()) {
                game.getPlayer().moveShipX(moveAmount * -1, 0, 1000);
            }else if (actions.get(i).intValue() == game.getPlayer().getInputDOWN()) {
                game.getPlayer().moveShipY(moveAmount * -1, 0, 1000);
            }else if (actions.get(i).intValue() == game.getPlayer().getInputRIGHT()) {
                game.getPlayer().moveShipX(moveAmount, 0, 1000);
            }else if (actions.get(i).intValue() == game.getPlayer().getInputCOUNTERCLOCKWISE()) {
                game.getPlayer().moveShield(shieldSpeed);
            }else if (actions.get(i).intValue() == game.getPlayer().getInputCLOCKWISE()) {
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
		return firstLevel;
	}
	
	public void setFirstLevel(JPanel firstLevel) {
		this.firstLevel = firstLevel;
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