package yah_du_ot;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScoreCard {
	private int grandTotal;
	private Dictionary<Line, Integer> lineTallies;
	private String player;
	
	public ScoreCard(String playerName){
		grandTotal = 0;
		player = playerName;
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
		grandTotal = lineTallies.get(Line.FullHouse)*25 +
				lineTallies.get(Line.ThreeOK)*10 +
				lineTallies.get(Line.FourOK)*20 +
				lineTallies.get(Line.FiveOK)*30 +
				lineTallies.get(Line.SixOK)*40 +
				lineTallies.get(Line.SevenOK)*50 +
				lineTallies.get(Line.EightOK)*60 +
				lineTallies.get(Line.NineOK)*100 +
				lineTallies.get(Line.SStraight)*20 +
				lineTallies.get(Line.LStraight)*30 +
				lineTallies.get(Line.FStraight)*40;
		Object[][] tableData = {
				{"Type", "Points worth", "Times Scored", "Score"},
				{"Full House","25 Points", lineTallies.get(Line.FullHouse), lineTallies.get(Line.FullHouse)*25},
				{"Three of a Kind", "10 Points", lineTallies.get(Line.ThreeOK), lineTallies.get(Line.ThreeOK)*10},
				{"Four of a Kind", "20 Points", lineTallies.get(Line.FourOK), lineTallies.get(Line.FourOK)*20},
				{"Five of a Kind", "30 Points", lineTallies.get(Line.FiveOK), lineTallies.get(Line.FiveOK)*30},
				{"Six of a Kind", "40 Points", lineTallies.get(Line.SixOK), lineTallies.get(Line.SixOK)*40},
				{"Seven of a Kind", "50 Points", lineTallies.get(Line.SevenOK), lineTallies.get(Line.SevenOK)*50},
				{"Eight of a Kind", "60 Points", lineTallies.get(Line.EightOK), lineTallies.get(Line.EightOK)*60},
				{"Nine of a Kind", "100 Points", lineTallies.get(Line.NineOK), lineTallies.get(Line.NineOK)*100},
				{"Small Straight", "20 Points", lineTallies.get(Line.SStraight), lineTallies.get(Line.SStraight)*20},
				{"Large Straight", "30 Points", lineTallies.get(Line.LStraight), lineTallies.get(Line.LStraight)*30},
				{"Full Straight", "40 Points", lineTallies.get(Line.FStraight), lineTallies.get(Line.FStraight)*40},
				{"Grand Total", "-", "-", grandTotal} 
		};
		String[] columnNames = {"Type", "Points worth", "Times scored", "Score"};
		JTable table = new JTable(tableData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		ScoreCardFrame scoreFrame = new ScoreCardFrame(table);
		return scoreFrame;
	}
	
	class ScoreCardFrame extends JFrame{
		public ScoreCardFrame(JTable table){
			final Toolkit kit = Toolkit.getDefaultToolkit();
			final int GAME_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
			final int GAME_WIDTH = (int) (0.75 * kit.getScreenSize().width);
			setSize(new Dimension(GAME_WIDTH / 2, GAME_HEIGHT / 2));
			setLocation(GAME_WIDTH / 3, GAME_HEIGHT / 3);
			setTitle(player + "'s ScoreCard");
			add(table);
			setResizable(false);
			setVisible(true);
		}
	}
}
