package yah_du_ot;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JFrame;

public class ScoreCard {
	private int grandTotal;
	private Dictionary<Line, Integer> lineTallies;
	
	public ScoreCard(String playerName){
		grandTotal = 0;
		lineTallies = new Hashtable<Line, Integer>();
		lineTallies.put(Line.FullHouse, 0);
		lineTallies.put(Line.ThreeOK, 0);
		lineTallies.put(Line.FourOK, 0);
		lineTallies.put(Line.FiveOK, 0);
		lineTallies.put(Line.SixOK, 0);
		lineTallies.put(Line.SevenOK, 0);
		lineTallies.put(Line.EightOK, 0);
		lineTallies.put(Line.NineOK, 0);
		lineTallies.put(Line.SStraight, 0);
		lineTallies.put(Line.LStraight, 0);
		lineTallies.put(Line.FStraight, 0);
	}
	
	public void addTallies(int numberOfTallies, Line lineEnum){
		lineTallies.put(lineEnum, numberOfTallies);
	}
	
	public JFrame displayCard(){
		//Object[][] tableData = new Object[students.keySet().size()][numberOfColumns];
		ScoreCardFrame scoreFrame = new ScoreCardFrame();
		scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreFrame.setVisible(true);
		return scoreFrame;
	}
	
	class ScoreCardFrame extends JFrame{
		
		public ScoreCardFrame(){
			this.setTitle("ScoreCard");
		}
	}
}
