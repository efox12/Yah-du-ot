package yah_du_ot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;



public class YahDuOt {
	private static Die myDie = new Die();
	private static ScoreCard Player1 = new ScoreCard("Player 1");
	private static ScoreCard Player2 = new ScoreCard("Player 2");
	private static GameBoard board = new GameBoard();
	
	public static void main(String[] args) {
		UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.PLAIN, 45));
		UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.PLAIN, 45)); 
		
		EventQueue.invokeLater(() ->
        {
        	try {
    	        UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
    		} 
    		catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	YahduotUX game = new YahduotUX(myDie, Player1, Player2, board);
        	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	//JOptionPane.showMessageDialog(game, "Player 1 roll for turn");
        	
        });
  }
}

