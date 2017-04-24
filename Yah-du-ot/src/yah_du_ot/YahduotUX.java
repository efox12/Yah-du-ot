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

@SuppressWarnings("serial")
public class YahduotUX extends JFrame {
	
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static final int GAME_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
	private static final int GAME_WIDTH = GAME_HEIGHT / 17 + GAME_HEIGHT / 3 + GAME_HEIGHT;
	private static final double BOX_INSET_X = GAME_WIDTH * 0.04555;
	private static final double BOX_INSET_Y = GAME_HEIGHT * 0.0391;
	private static final double BOX_WIDTH = GAME_HEIGHT - GAME_HEIGHT / 10;
	private static final double BOX_HEIGHT = GAME_HEIGHT - GAME_HEIGHT / 10;
	
	private final Font BOARD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, (int) (GAME_WIDTH / 50));
	private static Drawing myDrawing;
	private boolean turn;
	private JLabel P1;
	private JLabel P2;
	private Container rollSpace;
	private Die gameDie;
	private JButton myDie = new JButton();
	private ScoreCard player1;
	private ScoreCard player2;
	private GameBoard board;
	private YButton [][] buttonBox = new YButton[9][9];
	private Line selectedLine;
	
	public YahduotUX(Die thisDie, ScoreCard Player1, ScoreCard Player2, GameBoard board) {
		
		this.setTitle("Yah-du-ot");
		
		//setSize(1000,600);
		this.setResizable(false);
		this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setLocation(GAME_WIDTH / 6, GAME_HEIGHT / 6);
		this.setIconImage(new ImageIcon(this.getClass().getResource("Icon.jpg")).getImage());
		this.player1 = Player1;
		this.player2 = Player2;
		this.board = board;
		turn = true;
		setLayout(new BorderLayout());
		gameDie = thisDie;
		myDie.addActionListener(event -> updateDieButton());
		myDie.addActionListener(event -> revalidate());
		myDie.addActionListener(event -> gameDie.roll());
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.decode("#CEE3F6"));
		JLabel Players = new JLabel("Players");
		Players.setAlignmentX(CENTER_ALIGNMENT);
		Players.setFont(BOARD_FONT);
		
		P1 = new JLabel("Player 1: 0");
		P1.setFont(BOARD_FONT);
		
		P2 = new JLabel("Player 2: 0");
		P2.setFont(BOARD_FONT);
		
		JButton P1Score = new JButton("Score Card");
		P1Score.setBackground(Color.decode("#2E64FE"));
		P1Score.setOpaque(true);
		P1Score.setFont(BOARD_FONT);
		P1Score.setPreferredSize(new Dimension(GAME_WIDTH/6,50));
		P1Score.setMinimumSize(new Dimension(GAME_WIDTH/6,50));
		P1Score.setMaximumSize(new Dimension(GAME_WIDTH/6,50));
		P1Score.addActionListener(event -> displayScore(player1));
		
		JButton P2Score = new JButton("Score Card");
		P2Score.setBackground(Color.decode("#2E64FE"));
		P2Score.setOpaque(true);
		P2Score.setFont(BOARD_FONT);
		P2Score.setPreferredSize(new Dimension(GAME_WIDTH/6,50));
		P2Score.setMinimumSize(new Dimension(GAME_WIDTH/6,50));
		P2Score.setMaximumSize(new Dimension(GAME_WIDTH/6,50));
		P2Score.addActionListener(event -> displayScore(player2));
		
		JPanel leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		leftSide.setBackground(Color.decode("#CEE3F6"));
		leftSide.setPreferredSize(new Dimension(GAME_WIDTH/22,390));
		leftSide.setMinimumSize(new Dimension(GAME_WIDTH/22,390));
		leftSide.setMaximumSize(new Dimension(GAME_WIDTH/22,390));
		//GAME_HEIGHT - GAME_HEIGHT / 10, GAME_HEIGHT - GAME_HEIGHT / 10 + GAME_WIDTH/22
		JPanel middleBox = new JPanel();
		middleBox.setLayout(new BoxLayout(middleBox, BoxLayout.Y_AXIS));
		middleBox.setBackground(Color.decode("#CEE3F6"));
		JPanel rightSide = new JPanel();
		rightSide.setPreferredSize(new Dimension(GAME_WIDTH / 4 + GAME_WIDTH / 18,200));
		rightSide.setMinimumSize(new Dimension(GAME_WIDTH / 4 + GAME_WIDTH / 18,200));
		rightSide.setMaximumSize(new Dimension(GAME_WIDTH / 4 + GAME_WIDTH / 18,200));
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		rightSide.setBackground(Color.decode("#CEE3F6"));
		JPanel rightSide1 = new JPanel();
		rightSide1.setBackground(Color.decode("#CEE3F6"));
		JPanel rightSide2 = new JPanel();
		rightSide2.setBackground(Color.decode("#CEE3F6"));
		rightSide.add(Box.createRigidArea(new Dimension(0, GAME_HEIGHT / 10)));
		rightSide.add(Players);
		rightSide.add(Box.createRigidArea(new Dimension(0,GAME_HEIGHT / 25)));
		rightSide.add(rightSide1);
		//rightSide.add(Box.createRigidArea(new Dimension(0,GAME_HEIGHT / 10)));
		rightSide.add(rightSide2);
		rightSide1.add(P1);

		rightSide1.add(P1Score);
		
		rightSide2.add(P2);
		
		rightSide2.add(P2Score);
		
		//rightSide.add(P1Score);
		
		rightSide.add(createRoll(thisDie));
		
		add(rightSide, BorderLayout.LINE_END);
		middleBox.add(createColumnLabels());
		leftSide.add(Box.createRigidArea(new Dimension(0,GAME_HEIGHT / 25)));
		leftSide.add(createRowLabels());
		add(leftSide, BorderLayout.LINE_START);
		middleBox.add(createButtonMap());
		add(middleBox, BorderLayout.CENTER);
		/*
		Menu mainMenu = new Menu();
    	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainMenu.setVisible(true);
    	*/
    	
    	this.setVisible(true);
    	myDrawing = new Drawing();
    	setGlassPane(myDrawing);
    	myDrawing.setOpaque(false);
    	myDrawing.setVisible(true);
	}	
	
	private void displayScore(ScoreCard player) {
		JFrame card = player.displayCard();
		card.setVisible(true);
	}

	private void updateDieButton() {
		myDie.setIcon(new ImageIcon(getDieImage().getImage().getScaledInstance(GAME_WIDTH / 8, GAME_WIDTH / 8, Image.SCALE_DEFAULT)));
	}

	private Container createColumnLabels() {
		Container columns = new Container();
		columns.setLayout(new GridLayout(1, 9));
		
		int gridSize = GAME_HEIGHT - GAME_HEIGHT / 10;
		
		columns.setPreferredSize(new Dimension(gridSize,GAME_HEIGHT / 25));
		columns.setMinimumSize(new Dimension(gridSize,GAME_HEIGHT /25));
		columns.setMaximumSize(new Dimension(gridSize,GAME_HEIGHT /25));
		
		JLabel temp = new JLabel();
		for(char s = 'A'; s <= 'I'; s++) {
			temp = new JLabel(Character.toString(s));
			temp.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, GAME_HEIGHT / 30));
			temp.setHorizontalAlignment(JLabel.CENTER);
			temp.setVerticalAlignment(JLabel.CENTER);
			columns.add(temp);
		}
		
		return columns;
	}
	
	private Container createRowLabels() {
		Container rows = new Container();
		rows.setLayout(new GridLayout(9, 1));
		
		int gridSize = GAME_HEIGHT - GAME_HEIGHT / 10;
		
		rows.setPreferredSize(new Dimension(GAME_WIDTH/40,gridSize));
		rows.setMinimumSize(new Dimension(GAME_WIDTH/40,gridSize));
		rows.setMaximumSize(new Dimension(GAME_WIDTH/40,gridSize));
		
		JLabel temp = new JLabel();
		for(int i = 1; i <= 9; i++) {
			temp = new JLabel(Integer.toString(i));
			temp.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, GAME_HEIGHT / 30));
			temp.setHorizontalAlignment(JLabel.RIGHT);
			temp.setVerticalAlignment(JLabel.CENTER);
			rows.add(temp);
		}
		
		return rows;
	}
	
	private JPanel createButtonMap() {
		int gridSize = GAME_HEIGHT - GAME_HEIGHT / 10;
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(9, 9));
		buttons.setPreferredSize(new Dimension(gridSize,gridSize));
		buttons.setMinimumSize(new Dimension(gridSize,gridSize));
		buttons.setMaximumSize(new Dimension(gridSize,gridSize));
		//buttons.setBackground(Color.BLUE);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String label = Character.toString((char) (j + 65)) + (i + 1) + "";
				buttonBox[i][j] = new YButton(j, i);
				buttonBox[i][j].setSize(new Dimension(20,20));
				buttonBox[i][j].setBackground(Color.WHITE);
				buttonBox[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				buttonBox[i][j].setOpaque(true);
				buttonBox[i][j].setFont(BOARD_FONT);
				buttonBox[i][j].setForeground(Color.BLACK);
				buttonBox[i][j].addActionListener(event ->
													System.out.println(label + " pressed"));
				buttonBox[i][j].addActionListener(event ->
													setButtonText(event));
				buttonBox[i][j].addActionListener(buttonBox[i][j]);
				buttons.add(buttonBox[i][j]);
			}
		}
		
		return buttons;
	}
	
	private void setButtonText(ActionEvent e) {
		((YButton) e.getSource()).setText(Integer.toString(gameDie.getLastRoll()));
		((YButton) e.getSource()).setEnabled(false);
	}
	
	private Container createRoll(Die thisDie) {
		rollSpace = new Container();
		BoxLayout rollLayout = new BoxLayout(rollSpace, BoxLayout.PAGE_AXIS);
		rollSpace.setLayout(rollLayout);
		
		
		JLabel turn = new JLabel("First turn not decided");
		turn.setFont(BOARD_FONT);
		turn.setAlignmentX(CENTER_ALIGNMENT);
		//rollSpace.add(Box.createRigidArea(new Dimension(0, 50)));
		rollSpace.add(turn);
		rollSpace.add(Box.createRigidArea(new Dimension(0, GAME_HEIGHT / 15)));
		updateDieButton();
		myDie.setSize(new Dimension(100,100));
		myDie.setBackground(Color.decode("#045FB4"));
		myDie.setIcon(new ImageIcon(getDieImage().getImage().getScaledInstance(GAME_WIDTH / 8, GAME_WIDTH / 8, Image.SCALE_DEFAULT)));
		myDie.setAlignmentX(Component.CENTER_ALIGNMENT);
		rollSpace.add(myDie);
		rollSpace.add(Box.createRigidArea(new Dimension(0, GAME_HEIGHT / 7)));
		revalidate();
		return rollSpace;
	}

	
	private ImageIcon getDieImage() {
		ImageIcon diePic;
		
		switch (gameDie.getLastRoll()) {
		case 1: diePic = new ImageIcon(this.getClass().getResource("Die 1.jpg"));
				break;
		case 2: diePic = new ImageIcon(this.getClass().getResource("Die 2.jpg"));
				break;
		case 3: diePic = new ImageIcon(this.getClass().getResource("Die 3.jpg"));
				break;
		case 4: diePic = new ImageIcon(this.getClass().getResource("Die 4.jpg"));
				break;
		case 5: diePic = new ImageIcon(this.getClass().getResource("Die 5.jpg"));
				break;
		case 6: diePic = new ImageIcon(this.getClass().getResource("Die 6.jpg"));
				break;
		default: diePic = new ImageIcon(this.getClass().getResource("Die 0.jpg"));
				break;
		}
		
		return diePic;
	}
	
	public void scoreGrouping(ArrayList<Grouping> groups, ArrayList<String> types) {
		Grouping g = groups.get(0);
		String type = types.get(0);
		JPanel scoringOptions = new JPanel(new GridLayout(0,1));
		ButtonGroup group = new ButtonGroup();
		JDialog score = new JDialog();
		score.setTitle("Pick a score");
		ArrayList<Line> options = g.score();

		JLabel header = new JLabel();
		header.setFont(BOARD_FONT);
		header.setText("Player " + (!turn ? 1 : 2) + ": " + type); 
		scoringOptions.add(header);
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
		
		JButton confirm = new JButton("Confirm");
		confirm.setFont(BOARD_FONT);
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
									{if (turn) {
										player1.addTallies(1, selectedLine);
										P1.setText("Player 1: " + player1.getTotal());
									} else {
										player2.addTallies(1, selectedLine);
										P2.setText("Player 2: " + player2.getTotal());}});
		scoringOptions.add(confirm);
		
		score.setSize(new Dimension(GAME_WIDTH / 2, GAME_HEIGHT / 2));
		score.setLocation(GAME_WIDTH / 3, GAME_HEIGHT / 3);
		score.setResizable(false);
		score.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		score.getContentPane().add(scoringOptions);
		this.setEnabled(false);
		score.setVisible(true);
	}
	
	class Drawing extends JPanel {
		
		public Drawing() {
			//this.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
			this.setPreferredSize(super.getPreferredSize());
		}
		
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			Rectangle2D box = new Rectangle2D.Double(BOX_INSET_X, BOX_INSET_Y, BOX_WIDTH, BOX_HEIGHT);
			Rectangle2D horizontal = new Rectangle2D.Double(BOX_INSET_X, BOX_INSET_Y + (BOX_HEIGHT / 3), BOX_WIDTH, BOX_HEIGHT / 3);
			Rectangle2D vertical = new Rectangle2D.Double(BOX_INSET_X + (BOX_WIDTH / 3), BOX_INSET_Y, BOX_WIDTH / 3, BOX_HEIGHT);
			g2.setStroke(new BasicStroke(5));
			//g2.fillRect(10, 10, 100, 100);
			g2.draw(box);
			g2.draw(horizontal);
			g2.draw(vertical);
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
		
		public YButton(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getXCoord() {
			return x;
		}
		
		public int getYCoord() {
			return y;
		}
		
		public void actionPerformed(ActionEvent e) {
			board.addRoll(gameDie.getLastRoll(), this.x, this.y);
		}
		
	}

}

