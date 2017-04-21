package yah_du_ot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;

import javax.swing.JButton;
import javax.swing.JToggleButton;


public class Grouping {
	ArrayList<Integer> groupingList = new ArrayList<Integer>();
	boolean scored;
	public Grouping(){	
		scored = false;
	}
	
	public int maxOfAKindFound(int value){
		int valueOfAKind = 0;
		for(int i = 0; i < 9; i++){
			if(groupingList.get(i) == value){
				valueOfAKind++;
			}
		}
		return valueOfAKind;
	}
	
	public boolean isFullHouse(){
		boolean fourOfAKind = false;
		boolean fiveOfAKind = true;
		for(int i = 1; i<=6; i++){
			if(maxOfAKindFound(i) == 4)
				fourOfAKind = true;
			if(maxOfAKindFound(i) == 4)
				fiveOfAKind = true;
		}
		if(fourOfAKind && fiveOfAKind)
			return true;
		else
			return false;
	}
	
	public int maxStraightFound(){
		Collections.sort(groupingList);
		int maxLength = 1;
		int currentLength = 1;
		for(int i=0; i<9; i++){
			if(groupingList.get(i) + 1 == groupingList.get(i + 1))
	            currentLength++;
	        else if(groupingList.get(i) + 1 < groupingList.get(i + 1))
	            currentLength = 1;
	        if(currentLength > maxLength)
	            maxLength = currentLength;
		}
		return maxLength;
	}
	
	public ArrayList<Line> score(){
		ArrayList<Line> possibleValues = new ArrayList<Line>();
		scored = true;
		if(isFullHouse())
			possibleValues.add(Line.FullHouse);
		for(int i = 1; i<=6; i++){
			if(maxOfAKindFound(i) == 3)
				possibleValues.add(Line.ThreeOK);
			if(maxOfAKindFound(i) == 4)
				possibleValues.add(Line.FourOK);
			if(maxOfAKindFound(i) == 5)
				possibleValues.add(Line.FiveOK);
			if(maxOfAKindFound(i) == 6)
				possibleValues.add(Line.SixOK);
			if(maxOfAKindFound(i) == 7)
				possibleValues.add(Line.SevenOK);
			if(maxOfAKindFound(i) == 8)
				possibleValues.add(Line.EightOK);
			if(maxOfAKindFound(i) == 9)
				possibleValues.add(Line.NineOK);
		}
		if(maxStraightFound() == 6)
			possibleValues.add(Line.SStraight);
		else if(maxStraightFound() == 5)
			possibleValues.add(Line.LStraight);
		else if(maxStraightFound() == 4)
			possibleValues.add(Line.FStraight);
			
		return possibleValues;
	}
	
	public boolean isScored(){
		return scored;
	}
	
	public boolean isOpen(int space){
		if(groupingList.get(space) == null)
			return true;
		else
			return false;
	}
	
	public void addRoll(int value, int space){
		groupingList.set(space, value);
	}
}