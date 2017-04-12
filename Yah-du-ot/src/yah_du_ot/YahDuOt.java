package yah_du_ot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

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
	
	public Menu(YahduotUX game) {
		this.setSize(new Dimension(500, 500));
		
		JButton newGame = new JButton("New Game");
		newGame.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
		newGame.addActionListener(event -> game.setVisible(true));
		newGame.addActionListener(event -> this.setVisible(false));
		getContentPane().add(newGame, BorderLayout.CENTER);
		
	}
}
