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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu implements KeyListener {
	
	private Game game;
	private Font font = new Font("Times New Roman", Font.PLAIN, 55);
	private JFrame frame;
	private JPanel mainMenu, mainMenuButtons, firstLevel, firstLevelButtons;
	private JButton start, quit, viewControls, selectLevel;
	private int userConfirmationInput;
	private GridBagConstraints c;

	public MainMenu() throws IOException {
		initComponents();
	}

	private void initComponents() throws IOException {
		buildFrame();
		buildLevel();
		initJButtons();
		createLayout();
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(this.frame, "Welcome to the Asteroid Dodger CSC150 application!");

	}

	private void createLayout() throws IOException {
		c = new GridBagConstraints();
		BufferedImage image = ImageIO.read(new File("MainMenu.jpg"));

		mainMenu = new JPanel(new GridBagLayout()) {

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
		mainMenu.add(mainMenuButtons, c);
		frame.getContentPane().add(mainMenu);
		mainMenu.repaint();
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
		//sets size of the frame for everything to work in
		frame.setSize(new Dimension(1000, 1000));
	}

	private void buildLevel() {
		firstLevel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();

		firstLevelButtons = new JPanel();
		JButton mainMenu = new JButton("Main Menu");
		JButton quitLevel = new JButton("Quit Level");
		// utilize one panel - rebuilding / have class for it

		firstLevel.setBackground(Color.darkGray);

		firstLevelButtons.setOpaque(false);
		firstLevelButtons.setLayout(new BoxLayout(firstLevelButtons, BoxLayout.Y_AXIS));

		mainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenu.setFont(font);

		quitLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitLevel.setFont(font);

		firstLevelButtons.add(mainMenu, c);
		firstLevelButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		firstLevelButtons.add(quitLevel, c);

		firstLevel.add(firstLevelButtons);
		firstLevel.requestFocus();
		firstLevel.setFocusable(true);
		firstLevel.addKeyListener(this);

		// implement keyPress + key release action listeners
		// give quitLevel an option to return to level select screen
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userConfirmationInput = JOptionPane.showConfirmDialog(null, "Do you want to return to main menu?",
						"Select desired option?", JOptionPane.YES_NO_CANCEL_OPTION);
				if (userConfirmationInput == 0) {
					mainMenuReturn(evt);
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

	private void startButtonEvent(ActionEvent evt) {
		userConfirmationInput = JOptionPane.showConfirmDialog(null, "Select desired option", "Are you certain?",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (userConfirmationInput == 0) {
			firstLevel.setVisible(true);
			frame.getContentPane().add(firstLevel);
			mainMenu.setVisible(false);
		}

	}

	private void mainMenuReturn(ActionEvent evt) {
		firstLevel.setVisible(false);
		mainMenu.setVisible(true);
	}

	private void levelSelectReturn(ActionEvent e) {
		// firstLevel.setVisible(false);
		// level select screen visible true

	}

	private void selectLevelButtonEvent(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void viewControlsButtonEvent(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void quitButtonEvent(ActionEvent evt) {
		System.exit(0);

	}

	public static void main(String[] args) throws IOException {
		new MainMenu();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (firstLevelButtons.isVisible() == false) {
			firstLevelButtons.setVisible(true);
		} else if (firstLevelButtons.isVisible() == true) {
			firstLevelButtons.setVisible(false);
		}

	}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
    

    private ArrayList<Integer> actions;
	
    
    //    public class ViewController implements Serializable,KeyListener{
//        private final int PANEL_MOVE_AMOUNT = 10;
    private int moveAmount = game.getPlayer().getShip().getxSpeed();
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
		return mainMenu;
	}
	
	public void setMainMenu(JPanel mainMenu) {
		this.mainMenu = mainMenu;
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