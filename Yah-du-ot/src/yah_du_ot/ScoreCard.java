package yah_du_ot;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ScoreCard {
	private int grandTotal;
	private Dictionary<Line, Integer> lineTallies;
	private String player;
	final Toolkit kit = Toolkit.getDefaultToolkit();
	final int GAME_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
	final int GAME_WIDTH = GAME_HEIGHT / 17 + GAME_HEIGHT / 3 + GAME_HEIGHT;
	Font BOARD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, (int) (GAME_WIDTH / 50));
	
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
	
	public void addTallies(int numberTallies, Line lineEnum){
		lineTallies.put(lineEnum, lineTallies.get(lineEnum) + numberTallies);
	}
	
	public int getTotal() {
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
		return grandTotal;
	}
	

	
	public JFrame displayCard(){
		getTotal();
		JPanel scoreCard = new JPanel();
		
		JPanel scoreTable = new JPanel();
		scoreTable.setLayout(new BoxLayout(scoreTable, BoxLayout.X_AXIS));
		scoreCard.add(scoreTable);
		
		JPanel type = new JPanel();
		type.setLayout(new BoxLayout(type, BoxLayout.Y_AXIS));
		scoreTable.add(type);
		
		JPanel pointsWorth = new JPanel();
		pointsWorth.setLayout(new BoxLayout(pointsWorth, BoxLayout.Y_AXIS));
		scoreTable.add(pointsWorth);
		
		JPanel timesScored = new JPanel();
		timesScored.setLayout(new BoxLayout(timesScored, BoxLayout.Y_AXIS));
		scoreTable.add(timesScored);
		
		JPanel score = new JPanel();
		score.setLayout(new BoxLayout(score, BoxLayout.Y_AXIS));
		scoreTable.add(score);
		
		type.add(new JLabel("Type"));
		type.add(new JLabel("Full House"));

		Object[][] tableData = {
				{"Type", "Points worth", "Times Scored", "Score"},
				{"Full House","25 Points", lineTallies.get(Line.FullHouse), lineTallies.get(Line.FullHouse)*25},
				{"3 of a Kind", "10 Points", lineTallies.get(Line.ThreeOK), lineTallies.get(Line.ThreeOK)*10},
				{"4 of a Kind", "20 Points", lineTallies.get(Line.FourOK), lineTallies.get(Line.FourOK)*20},
				{"5 of a Kind", "30 Points", lineTallies.get(Line.FiveOK), lineTallies.get(Line.FiveOK)*30},
				{"6 of a Kind", "40 Points", lineTallies.get(Line.SixOK), lineTallies.get(Line.SixOK)*40},
				{"7 of a Kind", "50 Points", lineTallies.get(Line.SevenOK), lineTallies.get(Line.SevenOK)*50},
				{"8 of a Kind", "60 Points", lineTallies.get(Line.EightOK), lineTallies.get(Line.EightOK)*60},
				{"9 of a Kind", "100 Points", lineTallies.get(Line.NineOK), lineTallies.get(Line.NineOK)*100},
				{"Small Straight", "20 Points", lineTallies.get(Line.SStraight), lineTallies.get(Line.SStraight)*20},
				{"Large Straight", "30 Points", lineTallies.get(Line.LStraight), lineTallies.get(Line.LStraight)*30},
				{"Full Straight", "40 Points", lineTallies.get(Line.FStraight), lineTallies.get(Line.FStraight)*40}
				//{"Grand Total", "-", "-", grandTotal} 
		};
		String[] columnNames = {"Type", "Points worth", "Times scored", "Score"};
		
		JTable table = new JTable(tableData, columnNames);
		scoreCard.setLayout(new BoxLayout(scoreCard, BoxLayout.Y_AXIS));
		scoreCard.add(table);
		JPanel totalScore = new JPanel();
		totalScore.setLayout(new BoxLayout(totalScore, BoxLayout.X_AXIS));
		JLabel total = new JLabel("Total Score: " + grandTotal);
		total.setFont(BOARD_FONT);
		totalScore.add(total);
		scoreCard.add(Box.createRigidArea(new Dimension(0, GAME_HEIGHT / 20)));
		scoreCard.add(totalScore);
		
		
		//JScrollPane scrollPane = new JScrollPane(table);
		table.setFont(BOARD_FONT);
		//table.setSize(new Dimension(300, 700));
		table.setFillsViewportHeight(true);
		table.setRowHeight(GAME_HEIGHT/17);
		table.getColumnModel().getColumn(0).setPreferredWidth(GAME_HEIGHT/7);
		DefaultTableCellRenderer middleRenderer = new DefaultTableCellRenderer();
		middleRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(middleRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(middleRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(middleRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(middleRenderer);
		table.getColumnModel().getColumn(3).setPreferredWidth(GAME_HEIGHT/20);
		ScoreCardFrame scoreFrame = new ScoreCardFrame(scoreCard);
		//scoreFrame.add(scoreCard);
		return scoreFrame;
	}
	
	@SuppressWarnings("serial")
	class ScoreCardFrame extends JFrame{
		public ScoreCardFrame(JPanel table){
			setSize(new Dimension(GAME_WIDTH / 3 + GAME_WIDTH / 5, GAME_HEIGHT/2 + GAME_HEIGHT/3 + GAME_HEIGHT/20));
			setLocation(GAME_WIDTH / 3, GAME_HEIGHT / 3);
			setTitle(player + "'s ScoreCard");
			add(table);
			setResizable(false);
			setVisible(true);
		}
	}
}
