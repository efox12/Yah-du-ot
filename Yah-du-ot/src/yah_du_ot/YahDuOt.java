//import statements
package yah_du_ot;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/*
 * @author Max McKee, Erik Fox, Will Fraisl
 * YahDuOt.java
 */
public class YahDuOt {
	private static Die myDie = new Die();
	private static ScoreCard Player1 = new ScoreCard("Player 1");
	private static ScoreCard Player2 = new ScoreCard("Player 2");
	private static GameBoard board = new GameBoard();
	private static YahduotUX game; 
	
	public static void main(String[] args) {
		UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.PLAIN, 45));
		UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.PLAIN, 45)); 
		
		EventQueue.invokeLater(() ->
        {
        	try {
    	        UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
    		} 
    		catch (Exception e) {
    			e.printStackTrace();
    		}
        	UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.BLUE));
        	UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(Color.WHITE));
        	UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.BOLD, 11));
        	game = new YahduotUX(myDie, Player1, Player2, board);
        	board.addUX(game);
        	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        	
        });
  }
}

