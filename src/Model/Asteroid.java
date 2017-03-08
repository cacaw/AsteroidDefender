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
	private Color myC = Color.WHITE;
	private Player myPlayer;
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
	public Asteroid(int size,int x, int y, int minVel, int maxVel, ArrayList<Asteroid> myField,JPanel game,Player isPlayer)
	{
		myPlayer = isPlayer;
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
		
			if(rndm.nextBoolean())
			{
				this.setXvel(rndm.nextInt(maxVel-minVel)+minVel);
			}
			else
			{
				this.setXvel(-1*rndm.nextInt(maxVel-minVel)+minVel);
			}
			
			if(rndm.nextBoolean())
			{
				this.setYvel(rndm.nextInt(maxVel-minVel)+minVel);
			}
			else
			{
				this.setYvel(-1*rndm.nextInt(maxVel-minVel)+minVel);
			}
		//this.setYvel(minVel);
		
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
	 * moves the asteroid according to the xvel and yvel values and changes those values based on collision
	 * @throws InterruptedException 
	 */

	public void move() throws InterruptedException {
		if (x + xvel > game.getWidth() - astSize|| x+xvel<0)
		{
			//bounce x of the sides
			xvel*=-1;
		}
		if (y + yvel > game.getHeight() - astSize||y + yvel < 0)
		{
			yvel *=-1;
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
	/**
	 * 
	 * @param colliding
	 * @return a boolean if the collision happens on the left or right sides
	 */
	private boolean hCollide(Collidable colliding)
	{
		return this.getBounds().intersects(colliding.getBounds());
	}
	/**
	 * 
	 * @param colliding
	 * @return a boolean if the collision happens on the left or right sides
	 */
	private boolean vCollide(Collidable colliding)
	{
		if(colliding instanceof Asteroid)
		{
			Asteroid col = (Asteroid)colliding;
			return this.getBounds(astSize + astSize/16).intersects(col.getBounds(astSize + astSize/16));
		}
		return this.getBounds().intersects(colliding.getBounds());
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
				if(hCollide(colliding))
				{	
					return 1;
				}
				if(vCollide(colliding))
				{
					return 2;
				}
			}
			//colliding with the ship
			if(shipCollision((Collidable) myPlayer.getShip().getBounds()))
			{
				if(hCollide(colliding))
				{	
					return 1;
				}
				if(vCollide(colliding))
				{
					return 2;
				}
			}
			//colliding with the shield
			if(shieldCollision((Collidable) myPlayer.getShield().getBounds()))
			{
				if(hCollide(colliding))
				{	
					return 1;
				}
				if(vCollide(colliding))
				{
					return 2;
				}
			}
		}
		
		return 0;
	}
	private boolean shipCollision(Collidable colliding)
	{
		boolean hit = false;
		if(this.getBounds().intersects(colliding.getBounds()) && colliding instanceof Ship)
		{
			Ship myShip= (Ship)colliding;
			myShip.setHealth(myShip.getHealth()-this.getHealth());
			field.remove(this);
		}
		return hit;
	}
	
	private boolean shieldCollision(Collidable colliding)
	{
		boolean hit = false;
		if(this.getBounds().intersects(colliding.getBounds())&& colliding instanceof Shield)
		{
			hit = true;
			health-=1;
		}
		return hit;
	}
	
	/**
	 * 
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, astSize, astSize);
	}
	public Rectangle getBounds(int size) {
		return new Rectangle(x, y, size, size);
	}
}
