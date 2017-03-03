package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Asteroid implements Collidable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int astSize;
	private int health;
	private int x;
	private int y;
	private int yvel;
	private int xvel;
	//private Vector velocity;
	private JPanel game;
	private ArrayList<Asteroid> field;
	private Ship ship;
	private Color myC = Color.WHITE;
	public Asteroid()
	{
		
	}
	/**
	 * overloaded constructor to intake 
	 * @param size
	 * @param x
	 * @param y
	 * @param minVel
	 * @param maxVel
	 * @param myField
	 */
	public Asteroid(int size,int x, int y, int minVel, int maxVel, ArrayList<Asteroid> myField,JPanel game)
	{
		this.setX(x);
		this.setY(y);
		this.setAstSize(size);
		Random rndm = new Random();
		this.setVel(rndm,minVel,maxVel);
		this.setGame(game);
		this.setField(myField);
		health = size/10;
		//this.setVisible(true);
	}
	
	/**
	 * Sets a pseudo random velocity for the xvel and yvel 
	 */
	private void setVel(Random rndm, int minVel, int maxVel) {
		/*for(int i=0; i<2; i++)
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
				
		}*/
		this.setXvel(minVel);
		this.setY(0);
		
	}
	/**
	 * paints a circle that represents an asteroid
	 * @param g
	 */
	public void paint(Graphics g) {
		//super.paint(g);
		g.setColor(myC);
		g.fillOval(this.getX(),this.getY(), astSize, astSize);
		
		
	}
	/**
	 * @return the game
	 */
	public JPanel getGame() {
		return game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(JPanel game) {
		this.game = game;
	}
	/**
	 * @return the field
	 */
	public ArrayList<Asteroid> getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(ArrayList<Asteroid> field) {
		this.field = field;
	}
	/**
	 * @return the size
	 */
	public int getAstSize() {
		return astSize;
	}

	/**
	 * @param size the size to set
	 */
	public void setAstSize(int size) {
		this.astSize = size;
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
	/**
	 * moves the asteroid according to the xvel and yvel values and changes those values based on collision
	 */

	public void move() {
		if (x + xvel > game.getWidth() - astSize|| x+xvel<0)
		{
			//bounce x of the sides
			xvel*=-1;
		}
		if (y + yvel > game.getHeight() - astSize||y + yvel < 0)
		{
			yvel *=-1;;
		}
		// the cases of collision for an asteroid from the vertical or horizontal
		if (collision() == 1){
			xvel *= -1;
			myC = Color.RED;
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
	/**
	 * 
	 * @param colliding
	 * @return a boolean if the collision happens on the left or right sides
	 */
	private boolean hCollide(Asteroid colliding)
	{
		return (this.getY()> (colliding.getY()-(this.getAstSize()+this.getY())) &&
				this.getY() < (colliding.getAstSize()+colliding.getY()+this.getAstSize())&&
				(this.getX()>= colliding.getX()+colliding.getAstSize()||
				this.getX()+this.getAstSize()<=colliding.getX()));
	}
	/**
	 * 
	 * @param colliding
	 * @return a boolean if the collision happens on the top or bottom
	 */
	private boolean vCollide(Asteroid colliding)
	{
		return (this.getX()> (colliding.getX()-(this.getAstSize()+this.getX())) &&
				this.getX() < (colliding.getAstSize()+colliding.getX()+this.getAstSize())&&
				(this.getY()>= colliding.getY()+colliding.getAstSize()||
				this.getY()+this.getAstSize()<=colliding.getY()));
	}
	/**
	 * 
	 * @return an int that represents the type of collision that has occurred
	 * 1 - horizontal collision between asteroids
	 * 2 - vertical collision between asteroids
	 * 3 - collision with the shield of the ship
	 * 4 - collision with the ship
	 */
	private int collision() {

		for(Asteroid colliding : field)
		{
			if(colliding != this)
			{
				if(this.getBounds().intersects(colliding.getBounds())&& hCollide(colliding))
				{
					myC=Color.RED;
					return 1;
				}
				if(this.getBounds().intersects(colliding.getBounds())&& vCollide(colliding))
				{
					return 2;
				}
			}
			//colliding with the ship
			if(shipCollision(ship))
			{
				return 3;
			}
			//colliding with the shield
			if(shieldCollision(ship))
			{
				return 4;
			}
		}
		return 0;
	}
	private boolean shipCollision(Collidable colliding)
	{
		boolean hit = false;
		/*if(this.getBounds().intersects(colliding.getBounds()) && colliding instanceof Ship)
		{
			Ship myShip= (Ship)colliding;
			myShip.setHealth(myShip.getHealth()-this.getHealth());
			field.remove(this);
			
		}*/
		return hit;
	}
	
	private boolean shieldCollision(Collidable colliding)
	{
		boolean hit = false;
		
		return hit;
	}
	
	/**
	 * 
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, astSize, astSize);
	}
	
}
