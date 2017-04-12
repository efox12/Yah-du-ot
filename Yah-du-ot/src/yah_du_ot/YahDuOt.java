package yah_du_ot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class YahDuOt {

	public static void main(String[] args) {
		EventQueue.invokeLater(() ->
        {
        	YahduotUX game = new YahduotUX();
        	Menu mainMenu = new Menu(game);
        	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	mainMenu.setVisible(true);
        	game.setVisible(false);
        });
  }
}

class Menu extends JFrame {
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static Dimension screen = kit.getScreenSize();
	private static final int MAX_HEIGHT = (int) (0.4 * screen.height);
	private static final int MAX_WIDTH = (int) (0.4 * screen.width);
	private static final Font STANDARD_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
	
	public Menu(YahduotUX game) {
		this.setSize(new Dimension(MAX_WIDTH, MAX_WIDTH));
		this.setLocation(((screen.width - MAX_WIDTH) / 2), ((screen.height - MAX_WIDTH) / 2));
		
		//this.setLayout(new BoxLayout());
		JButton newGame = new JButton("New Game");
		newGame.setFont(STANDARD_FONT);
		newGame.addActionListener(event -> game.setVisible(true));
		newGame.addActionListener(event -> this.setVisible(false));
		getContentPane().add(newGame, BorderLayout.CENTER);
		
	}
}
