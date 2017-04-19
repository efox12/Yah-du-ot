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
	
	public int score(){
		scored = true;
		int total = 0;
		if(isFullHouse())
			total += 60;
		else{
			for(int i = 1; i<=6; i++){
				if(maxOfAKindFound(i) == 3)
					total =+ 10;
				if(maxOfAKindFound(i) == 4)
					total =+ 20;
				if(maxOfAKindFound(i) == 5)
					total =+ 30;
				if(maxOfAKindFound(i) == 6)
					total =+ 40;
				if(maxOfAKindFound(i) == 7)
					total =+ 50;
				if(maxOfAKindFound(i) == 8)
					total =+ 60;
				if(maxOfAKindFound(i) == 9)
					total =+ 100;
			}
			if(maxStraightFound() == 6)
				total =+ 40;
			else if(maxStraightFound() == 5)
				total =+ 30;
			else if(maxStraightFound() == 4)
				total =+ 20;
		}
		return total;
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