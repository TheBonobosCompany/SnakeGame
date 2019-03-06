package SnakeGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class Apple {
	private int xApple;
	private int yApple;
	private int rad;
	private int xBound;
	private int yBound;
	private Color c;

	public Apple(int xBound, int yBound, int rad) {
		this.xBound = Math.abs(xBound);
		this.yBound = Math.abs(yBound);
		xApple = (int) (Math.random() * (this.xBound - rad * 2)) * 10;
		yApple = (int) (Math.random() * (this.yBound - rad * 2)) * 10;
		this.rad = rad;
		this.c = Color.red;
	}

	public int getxBound() {
		return xBound;
	}

	public void setxBound(int xBound) {
		this.xBound = xBound;
	}

	public int getyBound() {
		return yBound;
	}

	public void setyBound(int yBound) {
		this.yBound = yBound;
	}

	public int getxApple() {
		return xApple;
	}

	public int getyApple() {
		return yApple;
	}

	public int getRad() {
		return rad;
	}

	public void setRad(int rad) {
		this.rad = rad;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public void RandomizeAppleLocation() {
		xApple = (int) (Math.random() * (this.xBound - rad * 2)) * 10;
		yApple = (int) (Math.random() * (this.yBound - rad * 2)) * 10;
	}

	public void drawApple(Graphics2D g) {
		Color reserve = g.getColor();
		g.setColor(c);
		g.fillOval(xApple, yApple, rad * 2, rad * 2);
		g.setColor(reserve);
	}

}
