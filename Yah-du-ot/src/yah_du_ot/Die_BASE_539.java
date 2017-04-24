package yah_du_ot;

import java.util.Random;

import org.junit.Test;

public class Die {
	int lastRoll;
	Random r;
	 
	public Die() {
		r = new Random(System.currentTimeMillis());
		lastRoll = r.nextInt(6) + 1;
	}

	public int roll() {
		lastRoll = r.nextInt(6) + 1;
		System.out.println(lastRoll);
		return lastRoll;
	}
	 
	public int getLastRoll() {
		return lastRoll;
	}
}
