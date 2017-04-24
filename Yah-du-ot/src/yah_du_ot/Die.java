//import statements
package yah_du_ot;

import java.util.Random;

/*
 * @author Max McKee, Erik Fox, Will Fraisl
 * Die.java
 */
public class Die {
	/*
	 * Holds the value of the last roll
	 */
	int lastRoll;
	/*
	 * Random generator for die roll
	 */
	Random r;
	
	/*
	 * Die constructor
	 * <p>
	 * Creates a die object that has a lastRoll value from 1-6 inclusive
	 * and initializes the object with a roll.
	 */
	public Die() {
		r = new Random(System.currentTimeMillis());
		lastRoll = r.nextInt(6) + 1;
	}

	/*
	 * Rolls the die
	 * <p>
	 * Uses the random generator to change the last roll value of the die.
	 * This value is an int from 1-6.
	 * 
	 * @return lastRoll the value of the last roll of the die.
	 */
	public int roll() {
		lastRoll = r.nextInt(6) + 1;
		System.out.println(lastRoll);
		return lastRoll;
	}
	
	/*
	 * Gets the last roll.
	 * <p>
	 * Returns an int that hold the value of the last roll of the die.
	 * 
	 * @return lastRoll the value of the last roll of the die.
	 */
	public int getLastRoll() {
		return lastRoll;
	}
}
