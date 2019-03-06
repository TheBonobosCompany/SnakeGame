package SnakeGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class boardPanel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6085247837470150866L;
	protected static String error;
	private Snake s;
	private volatile boolean running = true;
	private int speedup;
	private int level;
	private Apple apple;
	private Thread t;
	private boolean gameOver = false;

	public boardPanel(int width, int height) {
		super();
		this.level = 1;
		this.speedup = 200;
		this.setSize(width, height);
		s = new Snake(this.getWidth() / 2, this.getHeight() / 2, 5);
		apple = new Apple(this.getWidth() / 10, this.getHeight() / 10, 5);
		this.setFocusable(true);
		this.addKeyListener(this);
		startThread();
	}

	public void popupMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Snake Dialog", JOptionPane.WARNING_MESSAGE);
		gameOver = true;
		stopThread();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (isHit()) {
			gameOver = true;
			System.out.println("Game Over");
			return;
		}
		if (s.getHeadx() == apple.getxApple() && s.getHeady() == apple.getyApple()) {
			apple.RandomizeAppleLocation();
			s.addLink();
			speedup = speedup - 5;
			level++;
			System.out.println("Speed: " + speedup);
			System.out.println("Level: " + level);
		}
		apple.drawApple(g2);
		s.draw(g2);
		System.out.println("Snake's Head Location {" + s.getHeadx() + ", " + s.getHeady() + "}");
		System.out.println("Apple's Location {" + apple.getxApple() + ", " + apple.getyApple() + "}\n");

		// counter++;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP && !s.getMoveDir().equals("down")) {
			s.setxDir(0);
			s.setyDir(s.getLinkRad() * -2);
			s.setMoveDir("up");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN && !s.getMoveDir().equals("up")) {
			s.setxDir(0);
			s.setyDir(s.getLinkRad() * 2);
			s.setMoveDir("down");
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT && !s.getMoveDir().equals("left")) {
			s.setxDir(s.getLinkRad() * 2);
			s.setyDir(0);
			s.setMoveDir("right");
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT && !s.getMoveDir().equals("right")) {
			s.setxDir(s.getLinkRad() * -2);
			s.setyDir(0);
			s.setMoveDir("left");
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			try {
				reset();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	public boolean isHit() {
		if (s.getHeadx() < 0 || s.getHeady() < 0 || s.getHeadx() >= this.getWidth()
				|| s.getHeady() >= this.getHeight()) {
			// System.out.println("Out Of Bounds");
			// popupMessage("Out Of Bounds");
			// t = null;
			// gameOver = true;
			stopThread();
			JOptionPane.showMessageDialog(null, "Test", "Snake Dialog", JOptionPane.WARNING_MESSAGE);
			// error = new Throwable("Out Of Bounds");
			error = "one";
			return true;
		}
		if (s.hasTailBeenBitten()) {
			System.out.println("Bitten his tail");
			return true;
		}
		return false;
	}

	public void reset() throws InterruptedException {
		System.out.println("Reseting The Game");
		System.out.println("1...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("2...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("3...");
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Reset Complete! - Enjoy!");
		// counter = 0;
		gameOver = false;
		s.setxDir(s.getLinkRad() * 2);
		s.setyDir(0);
		s.setMoveDir("right");

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void threadLoop() {
		while (running) {
			s.move();
			repaint();
			try {
				Thread.sleep(speedup);
			} catch (InterruptedException e) {
				if (!running) {
					break;
				}
			}
		}
	}

	public void startThread() {
		if (t == null) {
			t = new Thread() {
				@Override
				public void run() {
					threadLoop();
				}
			};
		}
		t.start();
	}

	public void stopThread() {
		running = false;
		Thread.interrupted();
	}

}
