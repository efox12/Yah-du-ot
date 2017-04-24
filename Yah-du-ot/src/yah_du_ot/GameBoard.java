//import statements
package yah_du_ot;

import java.util.ArrayList;

/*
 * @author Max McKee, Erik Fox, Will Fraisl
 * Gameboard.java
 */

public class GameBoard {
	/*
	 * A list of grouping objects that are the rows of the board
	 */
	private Grouping[] rows;
	/*
	 * A list of grouping objects that are the columns of the board
	 */
	private Grouping[] columns;
	/*
	 * A list of grouping objects that are the clusters of the board
	 */
	private Grouping[] clusters;
	/*
	 * The user experience of the board, the graphics of the game.
	 */
	private YahduotUX UX;
	
	/*
	 * GameBoard constructor
	 * <p>
	 * Creates the game board, by groupings and then initializing the groupings.
	 */
	public GameBoard() {
		rows = new Grouping[9];
		columns = new Grouping[9];
		clusters = new Grouping[9];
		initializeGroupings();
	}
	
	/*
	 * Creates the Yah-du-ot UX
	 * <p>
	 * Creates the user experience and links it to be used in this class.
	 * 
	 * @param UX The user experience
	 */
	public void addUX(YahduotUX UX) {
		this.UX = UX;
	}
	
	/*
	 * Creates the groupings
	 * <p>
	 * Creates all rows, columns, and clusters that are grouped together.
	 */
	public void initializeGroupings() {
		for (int i = 0; i < 9; i++) {
			rows[i] = new Grouping(9);
			columns[i] = new Grouping(9);
			clusters[i] = new Grouping(9);
		}
	}
	
	/*
	 * Checks if a space is open
	 * <p>
	 * Given an x and y coordinate, checks to see if the space is unoccupied.
	 * 
	 * @param x the x coordinate of the space.
	 * @param y the y coordinate of the space.
	 * @return true if the space is unoccupied, false if occupied.
	 */
	public boolean spaceOpen(int x, int y){
		return columns[x].isOpen(y);
	}
	
	/*
	 * Adds random values to the board
	 * <p>
	 * To start the game there are random numbers added to the board.
	 */
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
	
	/*
	 * Adds roll to board
	 * <p>
	 * Given a value of roll, and x and y coordinates, adds the value to the space.
	 * 
	 * @param value the value of the die to be added
	 * @param x the x coordinate of the space
	 * @param y the y coordinate of the space
	 */
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
			UX.addLine(0, y, 8, y);
			scorable = true;
		}
		
		if (columns[x].isComplete()) {
			System.out.println("Column " + (char)(x+65) + " complete");
			groups.add(columns[x]);
			types.add("Column " + (char)(x+65));
			UX.addLine(x, 0, x, 8);
			scorable = true;
		}
		
		if (clusters[(x/3) + (y/3) * 3].isComplete()) {
			System.out.println("Cluster " + ((x/3) + (y/3) * 3) + " complete");
			groups.add((clusters[(x/3) + (y/3) * 3])); 
			types.add("Cluster (" + (x/3 + 1) + ", "  + (3 - (y/3)) + ")");
			UX.addBox(x/3, y/3);
			scorable = true;
		}
		
		if (scorable) {
			UX.scoreGrouping(groups, types);
		}
	}
	
	/*
	 * Checks if the board is full
	 * <p>
	 * Checks each set of groupings- rows, columns, and clusters- to see if all
	 * are complete.
	 * 
	 * @return true if the entire board is complete, otherwise false.
	 */
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
