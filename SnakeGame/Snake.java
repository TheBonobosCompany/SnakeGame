package SnakeGame;

import java.awt.Graphics2D;

public class Snake {

	private Link[] links;
	private int nLinks;
	private int yDir;
	private int xDir;
	private String moveDir;

	public Snake(int x, int y, int r) { // Start of constructor
		this.links = new Link[100];
		this.nLinks = 4; // Initial Number
		for (int i = 0; i < nLinks; i++) {
			links[i] = new Link(x - i * 2 * r, y, r);
		}
		xDir = 2 * r;
		yDir = 0;
		moveDir = "right";
	} // End of constructor

	public int getHeadx() {
		return links[0].xLoc;
	}

	public int getHeady() {
		return links[0].yLoc;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getLinkRad() {
		return this.links[0].rad;
	}

	public String getMoveDir() {
		return moveDir;
	}

	public void setMoveDir(String moveDir) {
		this.moveDir = moveDir;
	}

	public boolean hasTailBeenBitten() {
		for (int i = 1; i < nLinks; i++) {
			if (links[0].xLoc == links[i].xLoc && links[0].yLoc == links[i].yLoc)
				return true;
		}
		return false;
	}

	public void draw(Graphics2D g) {

		for (int i = 0; i < nLinks; i++) {
			links[i].draw(g);
		}
	}

	public void addLink() {
		boolean b = true;
		for (int i = 0; i < nLinks; i++) {
			if (links[i].xLoc == links[nLinks - 1].xLoc - Math.abs(xDir)
					&& links[i].yLoc == links[nLinks - 1].yLoc - yDir)
				b = false;
		}
		if (b) {
			links[nLinks] = new Link(links[nLinks - 1].xLoc, links[nLinks - 1].yLoc, getLinkRad());
			nLinks++;
		}
		System.out.println("nLinks=" + nLinks);
	}

	public void move() {
		for (int i = nLinks - 1; i >= 1; i--) {
			links[i].xLoc = links[i - 1].xLoc;
			links[i].yLoc = links[i - 1].yLoc;
		}
		links[0].xLoc += xDir;
		links[0].yLoc += yDir;
//		addLink();
	}
}
