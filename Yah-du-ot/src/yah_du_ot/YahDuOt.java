package yah_du_ot;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

public class YahDuOt {

	public static void main(String[] args) {
		EventQueue.invokeLater(() ->
        {
        	Menu mainMenu = new Menu();
        	YahduotUX game = new YahduotUX();
        	mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	mainMenu.setVisible(true);
        	game.setVisible(false);
        });
  }
}

class Menu extends JFrame {
	
	
	public Menu(YahduotUX game) {
		JButton newGame = new JButton("New Game");
		
	}
}
