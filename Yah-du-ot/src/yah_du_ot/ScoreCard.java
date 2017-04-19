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
	
	/*
	public JFrame displayCard(){
		return new ScoreCardFrame();
	}
	
	class ScoreCardFrame extends JFrame{
		private final Dimension screen = kit.getScreenSize();
		private final int MAX_HEIGHT = (int) (0.4 * screen.height);
		private final int MAX_WIDTH = (int) (0.4 * screen.width);
		private final Font STANDARD_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, MAX_WIDTH / 20);
		
		public ScoreCardFrame(){
			this.setSize(new Dimension(MAX_WIDTH, MAX_WIDTH));
			this.setTitle("ScoreCard");
			this.setLocation(((screen.width - MAX_WIDTH) / 2), ((screen.height - MAX_WIDTH) / 2));
		}
	}*/
}
