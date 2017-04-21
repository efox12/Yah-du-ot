package yah_du_ot;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JTable;

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
		Object[][] tableData = {
				{"FullHouse","25 Points", lineTallies.get(Line.FullHouse)},
				{"ThreeOK", "10 Points", lineTallies.get(Line.ThreeOK)},
				{"FourOK", "20 Points", lineTallies.get(Line.FourOK)},
				{"FiveOK", "30 Points", lineTallies.get(Line.FiveOK)},
				{"SixOK", "40 Points", lineTallies.get(Line.SixOK)},
				{"SevenOK", "50 Points", lineTallies.get(Line.SevenOK)},
				{"EightOK", "60 Points", lineTallies.get(Line.EightOK)},
				{"NineOK", "100 Points", lineTallies.get(Line.NineOK)},
				{"SStraight", "20 Points", lineTallies.get(Line.SStraight)},
				{"LStraight", "30 Points", lineTallies.get(Line.LStraight)},
				{"FStraight", "40 Points", lineTallies.get(Line.FStraight)},
		};
		String[] columnNames = {"Type", "Points worth", "Times scored"};
		JTable table = new JTable(tableData, columnNames);
		ScoreCardFrame scoreFrame = new ScoreCardFrame(table);
		return scoreFrame;
	}
	
	class ScoreCardFrame extends JFrame{
		
		public ScoreCardFrame(JTable table){
			setTitle("ScoreCard");
			add(table);
			setVisible(true);
		}
	}
}
