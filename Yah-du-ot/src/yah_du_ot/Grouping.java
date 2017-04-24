//import statements
package yah_du_ot;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * @author Max McKee, Erik Fox, Will Fraisl
 * Grouping.java
 */
public class Grouping {
	/*
	 * A list of the numbers in the grouping.
	 */
	int[] groupingList;
	/*
	 * True if the grouping has been scored.
	 */
	boolean scored;
	/*
	 * Counts how many dice have been added to the grouping.
	 */
	private int counter;
	
	/*
	 * Grouping constructor
	 * <p>
	 * Creates the grouping object based on a given size, and initializes variables.
	 * 
	 * @param listSize the size of the grouping
	 */
	public Grouping(int listSize) {
		groupingList = new int[listSize];
		scored = false;
		counter = 0;
	}
	
	/*
	 * Finds the value that appears the most times.
	 * <p>
	 * Returns the value that appears the most times in the grouping.
	 * 
	 * @return the value that appears the most times
	 */
	public int maxOfAKindFound(){
		int valueOfAKind = 0;
		int temp = 0;
		for (int i = 1; i <= 6; i++){
			temp = 0;
			for(int j = 0; j < 9; j++){
				if(groupingList[j] == i){
					temp++;
				}
			}
			valueOfAKind = Math.max(valueOfAKind, temp);
		}
		return valueOfAKind;
	}
	
	/*
	 * Finds how many times a value occurs
	 * <p>
	 * Given a die value, returns how many times that value occurs in the grouping.
	 * 
	 * @param value the value to search for
	 * @return the number of times the value occurs in the grouping
	 */
	public int maxOfAKindFound(int value) {
		int valueOfAKind = 0;
		for(int i = 0; i < 9; i++){
			if(groupingList[i] == value){
				valueOfAKind++;
			}
		}

		return valueOfAKind;
	}
	
	/*
	 * Checks if a full house if present
	 * <p>
	 * Looks for 5 of one kind and 4 of a different kind for a full house.
	 * 
	 * @return true if the full house if present
	 */
	public boolean isFullHouse(){
		boolean fourOfAKind = false;
		boolean fiveOfAKind = false;
		for(int i = 1; i<= 6; i++){
			if(maxOfAKindFound(i) == 4)
				fourOfAKind = true;
			if(maxOfAKindFound(i) == 5)
				fiveOfAKind = true;
		}
		if(fourOfAKind && fiveOfAKind)
			return true;
		else
			return false;
	}
	
	/*
	 * Finds the longest straight that occurs
	 * <p>
	 * Searches through the grouping to find that longest possible straight.
	 * 
	 * @return the length of the longest straight
	 */
	public int maxStraightFound(){
		Arrays.sort(groupingList);
		int maxLength = 1;
		int currentLength = 1;
		for(int i=0; i<8; i++){
			if(groupingList[i] + 1 == groupingList[i + 1])
	            currentLength++;
	        else if(groupingList[i] + 1 < groupingList[i + 1])
	            currentLength = 1;
	        if(currentLength > maxLength)
	            maxLength = currentLength;
		}
		System.out.println("Max straight: " + maxLength);
		return maxLength;
	}
	
	/*
	 * Creates a list of possible values to be scored
	 * <p>
	 * Finds all possible scores when the grouping is complete and returns a
	 * list of all of the possible lines that can be scored.
	 * 
	 * @return a list of all the lines that can be scored
	 */
	public ArrayList<Line> score(){
		ArrayList<Line> possibleValues = new ArrayList<Line>();
		scored = true;
		if(isFullHouse())
			possibleValues.add(Line.FullHouse);
		if(maxOfAKindFound() == 3)
			possibleValues.add(Line.ThreeOK);
		if(maxOfAKindFound() == 4)
			possibleValues.add(Line.FourOK);
		if(maxOfAKindFound() == 5)
			possibleValues.add(Line.FiveOK);
		if(maxOfAKindFound() == 6)
			possibleValues.add(Line.SixOK);
		if(maxOfAKindFound() == 7)
			possibleValues.add(Line.SevenOK);
		if(maxOfAKindFound() == 8)
			possibleValues.add(Line.EightOK);
		if(maxOfAKindFound() == 9)
			possibleValues.add(Line.NineOK);		
		if(maxStraightFound() == 4)
			possibleValues.add(Line.SStraight);
		else if(maxStraightFound() == 5)
			possibleValues.add(Line.LStraight);
		else if(maxStraightFound() == 6)
			possibleValues.add(Line.FStraight);
			
		return possibleValues;
	}
	
	/*
	 * Checks if the grouping has been scored
	 * 
	 * @return true if the grouping has been scored
	 */
	public boolean isScored(){
		return scored;
	}
	
	/*
	 * Checks if a space in the grouping is open.
	 * 
	 * @param space the space to check if open
	 * @return true if the space is open
	 */
	public boolean isOpen(int space){
		if(groupingList[space] == 0)
			return true;
		else
			return false;
	}
	
	/*
	 * Adds a roll to the board
	 * <p>
	 * Given a value of the roll and the space to place it, places the roll
	 * in the space.
	 * 
	 * @param value the value to place
	 * @param space the space to place the value
	 */
	public void addRoll(int value, int space){
		groupingList[space] = value;
		counter++;
	}
	
	/*
	 * Checks if the grouping is complete
	 * 
	 * @return true if the grouping is complete
	 */
	public boolean isComplete() {
		return counter == 9;
	}
	
	/*
	 * Returns the list of values in the grouping
	 * 
	 * @return the list of values in the grouping
	 */
	public int[] getGroupingList(){
		return groupingList;
	}
}