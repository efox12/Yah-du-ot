package yah_du_ot;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {	
	@Test
	public void test() {
		GameBoard tester = new GameBoard();
		assertEquals(false, tester.isComplete());
		
		tester.addRoll(2, 0, 0);
		tester.addRoll(2, 0, 1);
		tester.addRoll(2, 0, 2);
		tester.addRoll(2, 0, 3);
		tester.addRoll(2, 0, 4);
		tester.addRoll(2, 0, 5);
		tester.addRoll(2, 0, 6);
		tester.addRoll(4, 0, 7);
		
		assertEquals(false, tester.spaceOpen(0, 0));
		assertEquals(true, tester.spaceOpen(8, 8));
	}
}
