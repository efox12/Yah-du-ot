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
		return columns[x].isOpen(y);
	}
	public void addRandoms(){
		for(int i = 0; i < 12; i++){
			int randomSpotX = (int )(Math.random() * 9 + 1);
			int randomSpotY = (int )(Math.random() * 9 + 1);
			int randomDie = (int )(Math.random() * 6 + 1);
			if(randomSpotX < 9){	
				addRoll(randomDie, randomSpotX, randomSpotY);
			}
		}
	}
	public void addRoll(int value, int x, int y){
		rows[y].addRoll(value, x);
		columns[x].addRoll(value, y);
		clusters[(x/3) + (y/3)].addRoll(value, (x%3) + (y%3) * 3);
		
		if (rows[y].isComplete()) {
			scoreGrouping(rows[y]);
		}
		
		if (columns[x].isComplete()) {
			scoreGrouping(columns[x]);
		}
		
		if (clusters[(x/3) + (y/3)].isComplete()) {
			scoreGrouping(clusters[(x/3) + (y/3)]);
		}
	}
	
	public void scoreGrouping(Grouping toScore) {
		System.out.println("Scoring");
	}
}
