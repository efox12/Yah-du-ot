package yah_du_ot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;



public class YahDuOt {
	private static Die myDie = new Die();
	private static ScoreCard Player1 = new ScoreCard("Player 1");
	private static ScoreCard Player2 = new ScoreCard("Player 2");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() ->
        {
        	YahduotUX game = new YahduotUX(myDie, Player1, Player2);
        	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
  }
}

