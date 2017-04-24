//import statements
package yah_du_ot;

/*
 * @author Max McKee, Erik Fox, Will Fraisl
 * Line.java
 */
public enum Line {
	FullHouse, 
	ThreeOK, 
	FourOK, 
	FiveOK, 
	SixOK, 
	SevenOK, 
	EightOK, 
	NineOK, 
	SStraight, 
	LStraight,
	FStraight;
	
	public String toString() {
		switch(this) {
		case EightOK:
			return "Eight of a Kind (60 points)";
		case FStraight:
			return "Full Straight (40 points)";
		case FiveOK:
			return "Five of a Kind (30 points)";
		case FourOK:
			return "Four of a Kind (20 points)";
		case FullHouse:
			return "Full House (70 points)";
		case LStraight:
			return "Large Straight (30 points)";
		case NineOK:
			return "Nine of a Kind (100 points)";
		case SStraight:
			return "Small Straight (20 points)";
		case SevenOK:
			return "Seven of a Kind (50 points)";
		case SixOK:
			return "Six of a Kind (40 points)";
		case ThreeOK:
			return "Three of a Kind (10 points)";
		default:
			throw new IllegalArgumentException();	
		}
	}
}

