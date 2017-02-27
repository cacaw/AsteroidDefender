package edu.neumont.oop.finalproject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AsteroidSprite extends JPanel {

	private BufferedImage img;

	public void asteroidImg() {
		try {
			img = ImageIO.read(new File("Images/asteroid.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintRock(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	
}
