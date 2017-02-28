package edu.neumont.oop.finalproject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpaceShipSprite extends JPanel {
	
	private BufferedImage img;
	
	public void shipImage () {
		try {
			img = ImageIO.read(new File("Images/orangeship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintShip(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	
}
