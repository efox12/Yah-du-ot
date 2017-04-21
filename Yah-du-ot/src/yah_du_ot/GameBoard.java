package yah_du_ot;

public class GameBoard {
	private Grouping[] rows;
	private Grouping[] columns;
	private Grouping[] clusters;
	
	public GameBoard() {
		rows = new Grouping[9];
		columns = new Grouping[9];
		clusters = new Grouping[9];
	}
	
	public boolean spaceOpen(int x, int y){
		return true;
	}
	
	public void addRoll(int x, int y){
		
	}
}
