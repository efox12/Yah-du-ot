package yah_du_ot;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class Grouping {
	int[] groupingList;
	boolean scored;
	private int counter;
	
	//public Grouping(){	
	//	scored = false;
	//	counter = 0;
	//}
	
	public Grouping(int listSize) {
		groupingList = new int[listSize];
		scored = false;
		counter = 0;
	}
	
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
	
	public int maxOfAKindFound(int value) {
		int valueOfAKind = 0;
		for(int i = 0; i < 9; i++){
			if(groupingList[i] == value){
				valueOfAKind++;
			}
		}

		return valueOfAKind;
	}
	
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
	
	public boolean isScored(){
		return scored;
	}
	
	public boolean isOpen(int space){
		if(groupingList[space] == 0)
			return true;
		else
			return false;
	}
	
	public void addRoll(int value, int space){
		groupingList[space] = value;
		counter++;
	}
	
	public boolean isComplete() {
		return counter == 9;
	}
	
	public int[] getGroupingList(){
		return groupingList;
	}
}