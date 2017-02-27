package edu.neumont.oop.finalproject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShieldSprite extends JPanel {

	private BufferedImage img;
	
	public void shieldImg() {
		try {
			img = ImageIO.read(new File("Images/shield.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintShield(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
}
