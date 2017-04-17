package yah_du_ot;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class YahduotUX extends JFrame{
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static final int GAME_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
	private static final int GAME_WIDTH = (int) (0.75 * kit.getScreenSize().width);
	
	public YahduotUX() {
		this.setTitle("Yah-du-ot");
		this.setResizable(false);
		this.setSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		this.setLocation(GAME_WIDTH / 6, GAME_HEIGHT / 6);
		this.setIconImage(new ImageIcon("Icon.jpg").getImage());
		
		Menu mainMenu = new Menu();
    	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainMenu.setVisible(true);
    	this.setVisible(false);
	}	
	
	class Menu extends JFrame {
		private final Dimension screen = kit.getScreenSize();
		private final int MAX_HEIGHT = (int) (0.4 * screen.height);
		private final int MAX_WIDTH = (int) (0.4 * screen.width);
		private final Font STANDARD_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, MAX_WIDTH / 20);
		
		public Menu() {
			this.setSize(new Dimension(MAX_WIDTH, MAX_WIDTH));
			this.setTitle("Yah-du-ot");
			this.setLocation(((screen.width - MAX_WIDTH) / 2), ((screen.height - MAX_WIDTH) / 2));
			this.setIconImage(new ImageIcon("Icon.jpg").getImage());
			BoxLayout menuLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
			Dimension buttonSize = new Dimension(2 * MAX_WIDTH / 5, MAX_HEIGHT / 6);
			
			getContentPane().setLayout(menuLayout);
			JButton keep = new JButton("Keep Playing");
			keep.setFont(STANDARD_FONT);
			keep.setPreferredSize(buttonSize);
			keep.setMaximumSize(buttonSize);
			keep.setAlignmentX(CENTER_ALIGNMENT);
			keep.setAlignmentY(CENTER_ALIGNMENT);
			keep.addActionListener(event ->	YahduotUX.this.setVisible(true));
			keep.addActionListener(event -> this.setVisible(false));
			
			JButton newGame = new JButton("New Game");
			newGame.setFont(STANDARD_FONT);
			newGame.setPreferredSize(buttonSize);
			newGame.setMaximumSize(buttonSize);
			newGame.setAlignmentX(CENTER_ALIGNMENT);
			newGame.setAlignmentY(CENTER_ALIGNMENT);
			
			JButton rules = new JButton("How to Play");
			rules.setFont(STANDARD_FONT);
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
		}
	}
}

