package SnakeGame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame J = new JFrame("Snake Game");
		J.setSize(new Dimension(500, 500));
		J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardPanel bp = new boardPanel(J.getWidth(), J.getHeight());
		J.add(bp);
		J.setVisible(true);
		if (boardPanel.error.equals("one"))
			System.out.close();
		System.out.println(bp.getWidth());
		System.out.println(bp.getHeight());

	}

}
