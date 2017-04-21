package yah_du_ot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YahduotUX extends JFrame{
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static final int GAME_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
	private static final int GAME_WIDTH = (int) (0.75 * kit.getScreenSize().width);
	private static final double BOX_INSET_X = GAME_WIDTH * 0.02395;
	private static final double BOX_INSET_Y = GAME_HEIGHT * 0.04784;
	private static final double BOX_WIDTH = GAME_WIDTH * 0.647;
	private static final double BOX_HEIGHT = GAME_HEIGHT * 0.91;
	
	private final Font BOARD_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, GAME_WIDTH / 30);
	private static Drawing myDrawing;
	
	public YahduotUX() {
		this.setTitle("Yah-du-ot");
		this.setResizable(false);
		this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setLocation(GAME_WIDTH / 6, GAME_HEIGHT / 6);
		this.setIconImage(new ImageIcon(this.getClass().getResource("Icon.jpg")).getImage());
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel Players = new JLabel("Players");
		Players.setFont(BOARD_FONT);
		
		JLabel P1 = new JLabel("Player 1: 0");
		P1.setFont(BOARD_FONT);
		
		JLabel P2 = new JLabel("Player 2: 0");
		P2.setFont(BOARD_FONT);
		
		JButton P1Score = new JButton("Score");
		P1Score.setFont(BOARD_FONT);
		
		JButton P2Score = new JButton("Score");
		P2Score.setFont(BOARD_FONT);
		
		JButton filler = new JButton("Filler");
		filler.setFont(BOARD_FONT);
		
		Container playerInfo = new Container();
		playerInfo.setLayout(new GridBagLayout());
		
		Container roll = new Container();
		BoxLayout rollLayout = new BoxLayout(roll, BoxLayout.Y_AXIS);
		roll.setLayout(rollLayout);
		
		JLabel turn = new JLabel("First turn not decided");
		turn.setFont(BOARD_FONT);
		turn.setAlignmentX(CENTER_ALIGNMENT);
		roll.add(turn);
		
		c.weighty = 1.0;
		c.weightx = 1.0;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		playerInfo.add(Players, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		playerInfo.add(P1, c);
		
		c.gridx = 2;
		c.gridwidth = 1;
		playerInfo.add(P1Score, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		playerInfo.add(P2, c);
		
		c.gridx = 2;
		c.gridwidth = 1;
		playerInfo.add(P2Score, c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 3;
		playerInfo.add(new JLabel(), c);
			
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.gridx = 2;
		c.gridheight = 1;
		c.weighty = 0.1;
		c.weightx = 0.1;
		playerInfo.validate();
		getContentPane().add(playerInfo, c);
		
		c.gridy = 2;
		getContentPane().add(roll, c);
		
		c.gridy = 0;
		c.gridx = 1;
		c.weighty = 0;
		getContentPane().add(createColumnLabels(), c);
		
		c.gridy = 1;
		c.gridx = 0;
		c.gridheight = 2;
		getContentPane().add(createRowLabels(), c);
		
		c.gridy = 1;
		c.gridx = 1;
		c.gridwidth = 0;
		c.gridheight = 2;
		c.gridwidth = 1;
		c.weightx = 0.9;
		c.weighty = 1.0;
		getContentPane().add(createButtonMap(), c);
		
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
	
	private Container createColumnLabels() {
		Container columns = new Container();
		columns.setLayout(new GridLayout(1, 9));
		
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
		
		JLabel temp = new JLabel();
		for(int i = 1; i <= 9; i++) {
			temp = new JLabel(Integer.toString(i));
			temp.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, GAME_HEIGHT / 30));
			temp.setHorizontalAlignment(JLabel.CENTER);
			temp.setVerticalAlignment(JLabel.CENTER);
			rows.add(temp);
		}
		
		return rows;
	}
	
	private Container createButtonMap() {
		Container buttons = new Container();
		buttons.setLayout(new GridLayout(9, 9));
		JButton [][] buttonBox = new JButton[9][9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String label = Character.toString((char) (i + 65)) + (j + 1) + "";
				buttonBox[i][j] = new JButton(label);
				buttonBox[i][j].setFont(BOARD_FONT);
				buttonBox[i][j].setForeground(Color.RED);
				buttonBox[i][j].addActionListener(event ->
													System.out.println(label));
				buttons.add(buttonBox[i][j]);
			}
		}
		
		return buttons;
	}
	
	class Drawing extends JPanel {
		
		public Drawing() {
			this.setBounds(0, 0, GAME_WIDTH, GAME_HEIGHT);
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
}

