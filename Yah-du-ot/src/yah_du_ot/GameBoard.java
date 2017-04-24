package yah_du_ot;

import java.util.ArrayList;

public class GameBoard {
	private Grouping[] rows;
	private Grouping[] columns;
	private Grouping[] clusters;
	private YahduotUX UX;
	
	public GameBoard() {
		rows = new Grouping[9];
		columns = new Grouping[9];
		clusters = new Grouping[9];
		initializeGroupings();
	}
	
	public void addUX(YahduotUX UX) {
		this.UX = UX;
	}
	
	public void initializeGroupings() {
		for (int i = 0; i < 9; i++) {
			rows[i] = new Grouping(9);
			columns[i] = new Grouping(9);
			clusters[i] = new Grouping(9);
		}
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
		System.out.println("Adding roll to (" + x + ", " + y + ")");
		rows[y].addRoll(value, x);
		columns[x].addRoll(value, y);
		clusters[(x/3) + (y/3) * 3].addRoll(value, (x%3) + (y%3) * 3);
		
		boolean scorable = false;
		ArrayList<Grouping> groups = new ArrayList<Grouping>();
		ArrayList<String> types = new ArrayList<String>();
		
		if (rows[y].isComplete()) {
			System.out.println("Row " + y + " complete");
			groups.add(rows[y]);
			types.add("Row " + (y+1));
			scorable = true;
		}
		
		if (columns[x].isComplete()) {
			System.out.println("Column " + x + " complete");
			groups.add(columns[x]);
			types.add("Column " + (x+1));
			scorable = true;
		}
		
		if (clusters[(x/3) + (y/3) * 3].isComplete()) {
			System.out.println("Cluster " + ((x/3) + (y/3) * 3) + " complete");
			groups.add((clusters[(x/3) + (y/3) * 3])); 
			types.add("Cluster (" + (x/3 + 1) + ", "  + (3 - (y/3) * 3) + ")");
			scorable = true;
		}
		
		if (scorable) {
			UX.scoreGrouping(groups, types);
		}
	}
	
	public boolean isComplete() {
		for (int i = 0; i < 9; i++) {
			if (!(rows[i].isComplete() 
				&& columns[i].isComplete() 
				&& clusters[i].isComplete())) {
				return false;
			} 
		}
		
		return true;
	}

}
