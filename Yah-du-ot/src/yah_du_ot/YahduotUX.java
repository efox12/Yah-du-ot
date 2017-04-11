package yah_du_ot;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class YahduotUX extends JFrame{
	private static Toolkit kit = Toolkit.getDefaultToolkit();
	private static final int MAX_HEIGHT = (int) (0.75 * kit.getScreenSize().height);
	private static final int MAX_WIDTH = (int) (0.75 * kit.getScreenSize().width);
	
	public YahduotUX() {
		this.setTitle("Yah-du-ot");
		this.setResizable(false);
		this.setSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
		this.setLocation(MAX_WIDTH / 6, MAX_HEIGHT / 6);
	}
}
