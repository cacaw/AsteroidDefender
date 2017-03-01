import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {

	private Font font = new Font("Times New Roman", Font.PLAIN, 55);
	private JFrame frame;
	private JPanel mainMenu, mainMenuButtons, firstLevel, secondLevel, thirdLevel;
	private JButton start, quit, viewControls, selectLevel;
	private int userConfirmationInput;

	public GUI() throws IOException {
		initComponents();
	}

	private void initComponents() throws IOException {
		buildFrame();
		createLevels();
		initJButtons();
		createLayout();
		buildLevel();
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(this.frame, "Welcome to the Asteroid Dodger CSC150 application!");

	}

	private void createLevels() {
		firstLevel = new JPanel();
		// utilize one panel - rebuilding / have class for it
		secondLevel = new JPanel();
		thirdLevel = new JPanel();
	}

	private void createLayout() throws IOException {
		GridBagConstraints c;
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
		start.setFont(font);
		mainMenuButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		mainMenuButtons.add(selectLevel);
		selectLevel.setFont(font);
		selectLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		mainMenuButtons.add(viewControls);
		viewControls.setFont(font);
		viewControls.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainMenuButtons.add(Box.createRigidArea(new Dimension(0, 50)));
		mainMenuButtons.add(quit);
		quit.setFont(font);
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
	}

	private void buildLevel() {
		firstLevel.setBackground(Color.green);
		JButton test = new JButton("Return to Main Menu");
		firstLevel.add(test);
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				userConfirmationInput = JOptionPane.showConfirmDialog(null, "Select desired option", "Are you certain?",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (userConfirmationInput == 0) {
					test(evt);
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

	private void test(ActionEvent evt) {
		firstLevel.setVisible(false);
		mainMenu.setVisible(true);
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
		new GUI();
	}

}