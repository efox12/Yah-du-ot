package yah_du_ot;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YahduotUX extends JFrame{
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static final int GAME_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
	private static final int GAME_WIDTH = (int) (0.75 * kit.getScreenSize().width);
	private final Font BOARD_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, GAME_WIDTH / 30);
	
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
		c.gridy = 0;
		c.gridheight = 1;
		c.weighty = 0.1;
		c.weightx = 0.1;
		playerInfo.validate();
		getContentPane().add(playerInfo, c);
		
		c.gridy = 1;
		getContentPane().add(roll, c);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 0;
		c.gridheight = 2;
		c.gridwidth = 2;
		c.weightx = 0.3;
		c.weighty = 1.0;
		getContentPane().add(filler, c);
		
		
		/*
		Menu mainMenu = new Menu();
    	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainMenu.setVisible(true);
    	*/
    	this.setVisible(true);
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

