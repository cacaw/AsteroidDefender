package Model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Asteroid implements ActionListener, Collidable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int size;
	private int health;
	private int x;
	private int y;
	private int yvel;
	private int xvel;
	//private Vector velocity;
	private Game game;
	private Timer myTime;
	private ArrayList<Asteroid> field;
	
	public Asteroid()
	{
		
	}
	
	public Asteroid(int size,int x, int y, int minVel, int maxVel, ArrayList<Asteroid> myField)
	{
		this.setX(x);
		this.setY(y);
		this.setSize(size);
		Random rndm = new Random();
		setVel(rndm,minVel,maxVel);
		field = myField;
		health = 3;
		myTime = new Timer(3000,this);
		myTime.start();
	}
	
	/**
	 * Sets a psuedo random velocity for the xvel and yvel 
	 */
	private void setVel(Random rndm, int minVel, int maxVel) {
		for(int i=0; i<2; i++)
		{
			if(rndm.nextBoolean() && i%2 == 0)
			{
				this.setXvel(rndm.nextInt(maxVel-minVel)+minVel);
			}
			else if(i%2==0)
			{
				this.setXvel(-1*rndm.nextInt(maxVel-minVel)+minVel);
			}
			
			if(rndm.nextBoolean() && i%2 == 1)
			{
				this.setYvel(rndm.nextInt(maxVel-minVel)+minVel);
			}
			else if(i%2==1)
			{
				this.setYvel(-1*rndm.nextInt(maxVel-minVel)+minVel);
			}
				
		}
		
	}

	public void paint(Graphics g) {
		g.fillOval(x, y, size, size);
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the velocity
	 */
	/*public Vector getVelocity() {
		return velocity;
	}*/

	/**
	 * @param velocity the velocity to set
	 */
	/*
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (x + xvel > game.getWidth() - size|| x+xvel<0)
		{
			//bounce x of the sides
			xvel*=-1;
		}
		if (y + yvel > game.getHeight() - size||y + yvel < 0)
		{
			yvel *=-1;;
		}
		// the cases of collision for an asteroid from the vertical or horizontal
		if (collision() == 1){
			xvel *= -1;
		}
		if(collision() == 2)
		{
			yvel *= -1;
		}
		
		x = x + xvel;
		y = y + yvel;
	}

	/**
	 * @return the yvel
	 */
	public int getYvel() {
		return yvel;
	}

	/**
	 * @param yvel the yvel to set
	 */
	public void setYvel(int yvel) {
		this.yvel = yvel;
	}

	/**
	 * @return the xvel
	 */
	public int getXvel() {
		return xvel;
	}

	/**
	 * @param xvel the xvel to set
	 */
	public void setXvel(int xvel) {
		this.xvel = xvel;
	}
	
	private boolean hCollide(Asteroid colliding)
	{
		return (this.getY()> (colliding.getY()-(this.getSize()+this.getY())) &&
				this.getY() < (colliding.getSize()+colliding.getY()+this.getSize())&&
				(this.getX()>= colliding.getX()+colliding.getSize()||
				this.getX()+this.getSize()<=colliding.getX()));
	}
	
	private boolean vCollide(Asteroid colliding)
	{
		return (this.getX()> (colliding.getX()-(this.getSize()+this.getX())) &&
				this.getX() < (colliding.getSize()+colliding.getX()+this.getSize())&&
				(this.getY()>= colliding.getY()+colliding.getSize()||
				this.getY()+this.getSize()<=colliding.getY()));
	}
	
	private int collision() {

		for(Asteroid colliding : field)
		{
			if(colliding != this)
			{
				if(this.getBounds().intersects(colliding.getBounds())&& hCollide(colliding))
				{
					return 1;
				}
				if(this.getBounds().intersects(colliding.getBounds())&& vCollide(colliding))
				{
					return 2;
				}
			}
			//colliding with the ship
			//colliding with the shield
		}
		return 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, size, size);
	}
	
}
