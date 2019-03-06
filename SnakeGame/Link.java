package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;

public class Link {

	protected int xLoc, yLoc, rad;
	protected Color colorFill, frame, color;

	public Link(int xLoc, int yLoc, int rad) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.rad = rad;
		this.colorFill = Color.blue;
		this.frame = Color.black;
	}

	public void draw(Graphics g) {
		Color reserve = g.getColor();
		g.setColor(this.colorFill);
		g.fillOval(xLoc, yLoc, rad * 2, rad * 2);
		g.setColor(reserve);
	}

}
