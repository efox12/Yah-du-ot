//import statements
package yah_du_ot;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 * @author Max McKee, Erik Fox, Will Fraisl
 * YahduotUX.java
 */
@SuppressWarnings("serial")
public class YahduotUX extends JFrame {
	
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static final int GAME_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
	private static final int GAME_WIDTH = GAME_HEIGHT / 7 + GAME_HEIGHT / 3 + GAME_HEIGHT;
	private static final double BOX_INSET_X = GAME_WIDTH * 0.04255;
	private static final double BOX_INSET_Y = GAME_HEIGHT * 0.0391;
	private static final double BOX_WIDTH = GAME_HEIGHT - GAME_HEIGHT / 6;
	private static final double BOX_HEIGHT = GAME_HEIGHT - GAME_HEIGHT / 6;
	
	private final Font BOARD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, (int) (GAME_WIDTH / 35));
	private static Drawing myDrawing;
	private boolean playerTurn; //True for player 1, false for player 2
	private JLabel P1;
	private JLabel P2;
	private JLabel turn;
	private Container rollSpace;
	private Die gameDie;
	private JButton myDie = new JButton();
	private ScoreCard player1;
	private ScoreCard player2;
	private GameBoard board;
	private YButton [][] buttonBox = new YButton[9][9];
	private Line selectedLine;
	private ArrayList<Color> lineColors = new ArrayList<Color>();
	private ArrayList<Line2D.Double> straightLines = new ArrayList<Line2D.Double>();	
	
	/*
	 * YahduotUX constructor
	 * <p>
	 * Creates the main User Experience of the game.
	 * 
	 * @param thisdie die to use
	 * @param Player1 the ScoreCard for first player
	 * @param Player2 the ScoreCard for the second player
	 * @param board the GameBoard to hold the groupings
	 */
	public YahduotUX(Die thisDie, ScoreCard Player1, ScoreCard Player2, GameBoard board) {
		
		this.setTitle("Yah-du-ot");		
		this.setResizable(false);
		this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setLocation(GAME_WIDTH / 6, GAME_HEIGHT / 6);
		this.setIconImage(new ImageIcon(this.getClass().getResource("Icon.jpg")).getImage());
		this.player1 = Player1;
		this.player2 = Player2;
		this.board = board;
		playerTurn = true;
		setLayout(new BorderLayout());
		gameDie = thisDie;
		myDie.addActionListener(event -> updateDieButton());
		myDie.addActionListener(event -> revalidate());
		myDie.addActionListener(event -> gameDie.roll());
		myDie.addActionListener(event -> myDie.setEnabled(false));
		myDie.addActionListener(event -> turn.setText("Player " + (playerTurn ? 1 : 2) + " place die"));
		myDie.addActionListener(event -> togglePlayspace());
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.decode("#CEE3F6"));
		JLabel Players = new JLabel("Players");
		Players.setAlignmentX(CENTER_ALIGNMENT);
		Players.setFont(BOARD_FONT);
		P1 = new JLabel("Player 1: 0   ");
		P1.setFont(BOARD_FONT);
		P1.setForeground(Color.BLUE);
		
		P2 = new JLabel("Player 2: 0   ");
		P2.setFont(BOARD_FONT);
		P2.setForeground(Color.RED);
		
		JButton P1Score = new JButton("Score Card");
		P1Score.setBackground(Color.decode("#0F54C1"));
		P1Score.setOpaque(true);
		P1Score.setFont(BOARD_FONT);
		P1Score.setForeground(Color.decode("#D6DBE3"));
		P1Score.setBorder(BorderFactory.createLineBorder(Color.decode("#4D72AD")));
		P1Score.setPreferredSize(new Dimension(GAME_WIDTH/6,GAME_HEIGHT/10));
		P1Score.setMinimumSize(new Dimension(GAME_WIDTH/6,GAME_HEIGHT/10));
		P1Score.setMaximumSize(new Dimension(GAME_WIDTH/6,GAME_HEIGHT/10));
		P1Score.addActionListener(event -> displayScore(player1));
		
		JButton P2Score = new JButton("Score Card");
		P2Score.setBackground(Color.decode("#0F54C1"));
		P2Score.setOpaque(true);
		P2Score.setFont(BOARD_FONT);
		P2Score.setForeground(Color.decode("#D6DBE3"));
		P2Score.setBorder(BorderFactory.createLineBorder(Color.decode("#4D72AD")));
		P2Score.setPreferredSize(new Dimension(GAME_WIDTH/6,GAME_HEIGHT/10));
		P2Score.setMinimumSize(new Dimension(GAME_WIDTH/6,GAME_HEIGHT/10));
		P2Score.setMaximumSize(new Dimension(GAME_WIDTH/6,GAME_HEIGHT/10));
		P2Score.addActionListener(event -> displayScore(player2));
		
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.setBackground(Color.decode("#94AACE"));
		leftSide.setPreferredSize(new Dimension(GAME_WIDTH/23,390));
		leftSide.setMinimumSize(new Dimension(GAME_WIDTH/23,390));
		leftSide.setMaximumSize(new Dimension(GAME_WIDTH/23,390));
		JPanel middleBox = new JPanel();
		middleBox.setLayout(new BoxLayout(middleBox, BoxLayout.Y_AXIS));
		middleBox.setBackground(Color.decode("#94AACE"));
		JPanel rightSide = new JPanel();
		rightSide.setPreferredSize(new Dimension(GAME_WIDTH / 4 + GAME_WIDTH / 7,200));
		rightSide.setMinimumSize(new Dimension(GAME_WIDTH / 4 + GAME_WIDTH / 7,200));
		rightSide.setMaximumSize(new Dimension(GAME_WIDTH / 4 + GAME_WIDTH / 7,200));
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setBackground(Color.decode("#94AACE"));
		JPanel rightSide1 = new JPanel();
		rightSide1.setBackground(Color.decode("#94AACE"));
		JPanel rightSide2 = new JPanel();
		rightSide2.setBackground(Color.decode("#94AACE"));
		rightSide.add(Box.createRigidArea(new Dimension(0, GAME_HEIGHT / 10)));
		rightSide.add(Players);
		rightSide.add(Box.createRigidArea(new Dimension(0,GAME_HEIGHT / 25)));
		rightSide.add(rightSide1);
		rightSide.add(rightSide2);
		rightSide1.add(P1);

		rightSide1.add(P1Score);
		
		rightSide2.add(P2);
		
		rightSide2.add(P2Score);
				
		rightSide.add(createRoll(thisDie));

		add(rightSide, BorderLayout.LINE_END);
		middleBox.add(createColumnLabels());
		leftSide.add(Box.createRigidArea(new Dimension(0,GAME_HEIGHT / 25)));
		leftSide.add(createRowLabels());
		add(leftSide, BorderLayout.LINE_START);
		middleBox.add(createButtonMap());
		add(middleBox, BorderLayout.CENTER);
    	
    	this.setVisible(true);
    	myDrawing = new Drawing();
    	setGlassPane(myDrawing);
    	myDrawing.setOpaque(false);
    	myDrawing.setVisible(true);
    	togglePlayspace();
	}	
	
	/*
	 * Displays the ScoreCard for a given player
	 * 
	 * @param player the player to display the ScoreCard of
	 */
	private void displayScore(ScoreCard player) {
		JFrame card = player.displayCard();
		card.setVisible(true);
	}

	/*
	 * Updates the image of the die button
	 */
	private void updateDieButton() {
		myDie.setIcon(new ImageIcon(getDieImage().getImage().getScaledInstance(GAME_WIDTH / 8, GAME_WIDTH / 8, Image.SCALE_DEFAULT)));
	}

	/*
	 * Creates the column labels for the board
	 * 
	 * @return the container with column labels
	 */
	private Container createColumnLabels() {
		Container columns = new Container();
		columns.setLayout(new GridLayout(1, 9));
		
		int gridSize = GAME_HEIGHT - GAME_HEIGHT / 6;
		
		columns.setPreferredSize(new Dimension(gridSize,GAME_HEIGHT / 25));
		columns.setMinimumSize(new Dimension(gridSize,GAME_HEIGHT /25));
		columns.setMaximumSize(new Dimension(gridSize,GAME_HEIGHT /25));
		
		JLabel temp = new JLabel();
		for(char s = 'A'; s <= 'I'; s++) {
			temp = new JLabel(Character.toString(s));
			temp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, GAME_HEIGHT / 40));
			temp.setHorizontalAlignment(JLabel.CENTER);
			temp.setVerticalAlignment(JLabel.CENTER);
			columns.add(temp);
		}
		
		return columns;
	}
	
	/*
	 * Creates the row labels for the board
	 * 
	 * @return the container with row labels
	 */
	private Container createRowLabels() {
		Container rows = new Container();
		rows.setLayout(new GridLayout(9, 1));
		
		int gridSize = GAME_HEIGHT - GAME_HEIGHT / 6;
		
		rows.setPreferredSize(new Dimension(GAME_WIDTH/40,gridSize));
		rows.setMinimumSize(new Dimension(GAME_WIDTH/40,gridSize));
		rows.setMaximumSize(new Dimension(GAME_WIDTH/40,gridSize));
		
		JLabel temp = new JLabel();
		for(int i = 1; i <= 9; i++) {
			temp = new JLabel(Integer.toString(i));
			temp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, GAME_HEIGHT / 40));
			temp.setHorizontalAlignment(JLabel.RIGHT);
			temp.setVerticalAlignment(JLabel.CENTER);
			rows.add(temp);
		}
		
		return rows;
	}
	
	/*
	 * Creates the grid that the buttons are in
	 * 
	 * @return the panel that the grid is in
	 */
	private JPanel createButtonMap() {
		int gridSize = GAME_HEIGHT - GAME_HEIGHT / 6;
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(9, 9));
		buttons.setPreferredSize(new Dimension(gridSize,gridSize));
		buttons.setMinimumSize(new Dimension(gridSize,gridSize));
		buttons.setMaximumSize(new Dimension(gridSize,gridSize));
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String label = Character.toString((char) (j + 65)) + (i + 1) + "";
				buttonBox[i][j] = new YButton(j, i);
				buttonBox[i][j].setSize(new Dimension(20,20));
				buttonBox[i][j].setBackground(Color.decode("#F9F9F9"));
				buttonBox[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				buttonBox[i][j].setOpaque(true);
				buttonBox[i][j].setFont(BOARD_FONT);
				buttonBox[i][j].setForeground(Color.BLACK);
				buttonBox[i][j].addActionListener(event ->
													System.out.println(label + " pressed"));
				buttonBox[i][j].addActionListener(buttonBox[i][j]);
				buttons.add(buttonBox[i][j]);
			}
		}
		
		return buttons;
	}
	
	/*
	 * Creates the space for the die to be rolled
	 * 
	 * @param thisDie the die to be rolled
	 * @return the container the the rollspace is in
	 */
	private Container createRoll(Die thisDie) {
		rollSpace = new Container();
		BoxLayout rollLayout = new BoxLayout(rollSpace, BoxLayout.PAGE_AXIS);
		rollSpace.setLayout(rollLayout);
		
		turn = new JLabel("Player 1 roll die");
		turn.setFont(BOARD_FONT);
		turn.setAlignmentX(CENTER_ALIGNMENT);
		rollSpace.add(turn);
		rollSpace.add(Box.createRigidArea(new Dimension(0, GAME_HEIGHT / 15)));
		updateDieButton();
		myDie.setPreferredSize(new Dimension(GAME_WIDTH / 8,GAME_WIDTH / 8));
		myDie.setMaximumSize(new Dimension(GAME_WIDTH / 8,GAME_WIDTH / 8));
		myDie.setMinimumSize(new Dimension(GAME_WIDTH / 8,GAME_WIDTH / 8));
		myDie.setBackground(Color.decode("#0F54C1"));
		myDie.setIcon(new ImageIcon(getDieImage().getImage().getScaledInstance(GAME_WIDTH / 8, GAME_WIDTH / 8, Image.SCALE_DEFAULT)));
		myDie.setAlignmentX(Component.CENTER_ALIGNMENT);
		rollSpace.add(myDie);
		rollSpace.add(Box.createRigidArea(new Dimension(0, GAME_HEIGHT / 7)));
		revalidate();
		return rollSpace;
	}
	
	/*
	 * Toggles whether the playspace is active
	 */
	private void togglePlayspace()  {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!buttonBox[i][j].isSet()) {
					buttonBox[i][j].setEnabled(!buttonBox[i][j].isEnabled());
				}
			}
		}
	}
	
	/*
	 * Gets the correct image for the die value
	 * 
	 * @return the die image
	 */
	private ImageIcon getDieImage() {
		ImageIcon diePic;
		
		switch (gameDie.getLastRoll()) {
		case 1: diePic = new ImageIcon(this.getClass().getResource("Die1.png"));
				break;
		case 2: diePic = new ImageIcon(this.getClass().getResource("Die2.png"));
				break;
		case 3: diePic = new ImageIcon(this.getClass().getResource("Die3.png"));
				break;
		case 4: diePic = new ImageIcon(this.getClass().getResource("Die4.png"));
				break;
		case 5: diePic = new ImageIcon(this.getClass().getResource("Die5.png"));
				break;
		case 6: diePic = new ImageIcon(this.getClass().getResource("Die6.png"));
				break;
		default: diePic = new ImageIcon(this.getClass().getResource("Die0.png"));
				break;
		}
		
		return diePic;
	}
	
	/*
	 * Presents the scoring option for a given grouping
	 * 
	 * @param groups a list of the groups to be scored
	 * @param types the types of scoring possible
	 */
	public void scoreGrouping(ArrayList<Grouping> groups, ArrayList<String> types) {
		Grouping g = groups.get(0);
		String type = types.get(0);
		JPanel scoringOptions = new JPanel(new GridLayout(0,1));
		ButtonGroup group = new ButtonGroup();
		JDialog score = new JDialog();
		score.setTitle("Pick a score");
		ArrayList<Line> options = g.score();

		JButton confirm = new JButton("Confirm");
		confirm.setFont(BOARD_FONT);
		
		JLabel header = new JLabel();
		header.setFont(BOARD_FONT);
		header.setText("Player " + (!playerTurn ? 1 : 2) + ": " + type); 
		scoringOptions.add(header);
		if (options.size() == 0) {
			JLabel sorry = new JLabel("No scores available");
			sorry.setFont(BOARD_FONT);
			scoringOptions.add(sorry);
			if (groups.size() == 1) {
				confirm.addActionListener(event -> determineGameOver());
			}
			confirm.addActionListener(event -> setEnabled(true));
			confirm.addActionListener(event -> score.setVisible(false));
			confirm.addActionListener(event -> score.dispose());
		} else {
			
			for (int i = 0; i < options.size(); i++) {
				Line l = options.get(i);
				JRadioButton b = new JRadioButton(l.toString());
				b.setFont(BOARD_FONT);
				b.addActionListener(event -> selectedLine = l);
				b.setSelected(true);
				selectedLine = l;
				group.add(b);
				scoringOptions.add(b);
			}
			
			if (groups.size() == 1) {
				confirm.addActionListener(event -> determineGameOver());
			}
			confirm.addActionListener(event -> 
										{if (groups.size() == 1) {
											return;
										} else {
											groups.remove(0);
											types.remove(0);
											scoreGrouping(groups, types);
										}});
			confirm.addActionListener(event -> setEnabled(true));
			confirm.addActionListener(event -> score.setVisible(false));
			confirm.addActionListener(event -> score.dispose());
			confirm.addActionListener(event -> 
										{if (!playerTurn) {
											player1.addTallies(1, selectedLine);
											P1.setText("Player 1: " + player1.getTotal());
										} else {
											player2.addTallies(1, selectedLine);
											P2.setText("Player 2: " + player2.getTotal());}});
		}	
		scoringOptions.add(confirm);
			
		score.setSize(new Dimension(GAME_WIDTH / 2, GAME_HEIGHT / 2));
		score.setLocation(GAME_WIDTH / 3, GAME_HEIGHT / 3);
		score.setResizable(false);
		score.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		score.getContentPane().add(scoringOptions);
		this.setEnabled(false);
		score.setVisible(true);
	}
	
	/*
	 * Adds a box to the playspace
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void addBox(int x, int y) {
		for (int i = x*3; i < x*3+3; i++) {
			for (int j = y*3; j < y*3+3; j++){
				if (!playerTurn) {
					buttonBox[j][i].setBackground(new Color(179, 217, 255));
				} else {
					buttonBox[j][i].setBackground(new Color(255, 187, 187));
				}
			}
		}
	}
	
	/*
	 * Adds a line to a row or column that's full
	 * 
	 * @param x1 first x coordinate
	 * @param y1 first y coordinate
	 * @param x2 second x coordinate
	 * @param y2 second y coordinate
	 */
	public void addLine(int x1, int y1, int x2, int y2) {
		if (!playerTurn) {
			lineColors.add(new Color(55, 130, 255));
		} else {
			lineColors.add(new Color(255, 87, 87));
		}
		
		
		double lx1 = (0.5 * (BOX_WIDTH/9)) + ((x1) * BOX_WIDTH/9) + BOX_INSET_X;
		double lx2 = ((x2) * BOX_WIDTH/9) + BOX_INSET_X + (0.5 * (BOX_WIDTH/9));
		double ly1 = (0.5 * (BOX_WIDTH/9)) + ((y1) * BOX_WIDTH/9) + BOX_INSET_Y;
		double ly2 = ((y2) * BOX_WIDTH/9) + BOX_INSET_Y + (0.5 * (BOX_WIDTH/9));
		
		System.out.println("Adding Line (" + lx1 + ", " + ly1 + " -> (" + lx2 + ", " + ly2 + ")");
		straightLines.add(new Line2D.Double(lx1, ly1, lx2, ly2));
		getGlassPane().repaint();
	}
	
	/*
	 * Displays the gameover message if the game is over
	 */
	private void determineGameOver() {
		if (board.isComplete()) {
			myDie.setEnabled(false);
			JLabel message = new JLabel();
			message.setFont(BOARD_FONT);
			message.setHorizontalAlignment(JLabel.CENTER);
			if (player1.getTotal() > player2.getTotal()){
				message.setText("Player 1 wins!");
			} else if (player1.getTotal() < player2.getTotal()) {
				message.setText("Player 2 wins!");
			} else {
				message.setText("Draw!");
			}
			
			JFrame gameOver = new JFrame();
			gameOver.setLayout(new GridLayout(0,1));
			gameOver.setTitle("Game Over");
			gameOver.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			gameOver.setResizable(false);
			gameOver.add(message);
			gameOver.setLocation(GAME_WIDTH/2 + GAME_WIDTH/6, GAME_HEIGHT/2 + GAME_HEIGHT/6);
			gameOver.setSize(GAME_WIDTH/3, GAME_HEIGHT/3);
			gameOver.setVisible(true);
		}
	}
	
	class Drawing extends JPanel {
		
		public Drawing() {
			this.setPreferredSize(super.getPreferredSize());
		}
		
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			Rectangle2D box = new Rectangle2D.Double(BOX_INSET_X, BOX_INSET_Y, BOX_WIDTH, BOX_HEIGHT);
			Rectangle2D horizontal = new Rectangle2D.Double(BOX_INSET_X, BOX_INSET_Y + (BOX_HEIGHT / 3), BOX_WIDTH, BOX_HEIGHT / 3);
			Rectangle2D vertical = new Rectangle2D.Double(BOX_INSET_X + (BOX_WIDTH / 3), BOX_INSET_Y, BOX_WIDTH / 3, BOX_HEIGHT);
			g2.setStroke(new BasicStroke(5));
			g2.draw(box);
			g2.draw(horizontal);
			g2.draw(vertical);
			
			for (int i = 0; i < straightLines.size(); i++) {
				g2.setPaint(lineColors.get(i));
				g2.draw(straightLines.get(i));
			}
		}
		
		
	}
	
	class Menu extends JFrame {
		private final Dimension screen = kit.getScreenSize();
		private final int MAX_HEIGHT = (int) (0.4 * screen.height);
		private final int MAX_WIDTH = (int) (0.4 * screen.width);
		private final Font MENU_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, MAX_WIDTH / 20);
		
		public Menu() {
			this.setSize(new Dimension(MAX_WIDTH, MAX_WIDTH));
			this.setTitle("Yah-du-ot");
			this.setLocation(((screen.width - MAX_WIDTH) / 2), ((screen.height - MAX_WIDTH) / 2));
			this.setIconImage(new ImageIcon(this.getClass().getResource("Icon.jpg")).getImage());
			this.setResizable(false);
			Background bg = new Background();
			setContentPane(bg);
			
			
			BoxLayout menuLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
			Dimension buttonSize = new Dimension(2 * MAX_WIDTH / 5, MAX_HEIGHT / 6);
			
			getContentPane().setLayout(menuLayout);
			JButton keep = new JButton("Keep Playing");
			keep.setFont(MENU_FONT);
			keep.setPreferredSize(buttonSize);
			keep.setMaximumSize(buttonSize);
			keep.setAlignmentX(CENTER_ALIGNMENT);
			keep.setAlignmentY(CENTER_ALIGNMENT);
			keep.addActionListener(event ->	YahduotUX.this.setVisible(true));
			keep.addActionListener(event -> this.setVisible(false));
			
			JButton newGame = new JButton("New Game");
			newGame.setFont(MENU_FONT);
			newGame.setPreferredSize(buttonSize);
			newGame.setMaximumSize(buttonSize);
			newGame.setAlignmentX(CENTER_ALIGNMENT);
			newGame.setAlignmentY(CENTER_ALIGNMENT);
			
			JButton rules = new JButton("How to Play");
			rules.setFont(MENU_FONT);
			rules.setPreferredSize(buttonSize);
			rules.setMaximumSize(buttonSize);
			rules.setAlignmentX(CENTER_ALIGNMENT);
			rules.setAlignmentY(CENTER_ALIGNMENT);
			
			getContentPane().add(Box.createVerticalGlue());
			getContentPane().add(keep);
			getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
			getContentPane().add(newGame);
			getContentPane().add(Box.createRigidArea(new Dimension(0, 50)));
			getContentPane().add(rules);
			getContentPane().add(Box.createVerticalGlue());	
			//getContentPane().setOpaque()
		}
	}
	
	class Background extends JPanel {
		  private Image bg;

		  public Background() {
		    bg = new ImageIcon(this.getClass().getResource("bg.jpg")).getImage();
		  }

		  public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    g.drawImage(bg, -70, -100, this);
		  }
	}
	
	class YButton extends JButton implements ActionListener{
		private int x;
		private int y;
		private boolean set;
		
		public YButton(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public boolean isSet() {
			return set;
		}
		
		public int getXCoord() {
			return x;
		}
		
		public int getYCoord() {
			return y;
		}
		
		public void actionPerformed(ActionEvent e) {
			this.set = true;
			this.setEnabled(false);
			this.setText(Integer.toString(gameDie.getLastRoll()));
			playerTurn = !playerTurn;
			board.addRoll(gameDie.getLastRoll(), this.x, this.y);
			turn.setText("Player " + (playerTurn ? 1 : 2) + " roll die");
			togglePlayspace();
			myDie.setEnabled(true);
		}
		
	}

}

