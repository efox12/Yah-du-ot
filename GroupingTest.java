package yah_du_ot;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class GroupingTest {

	@Test
	public void test() {
		Grouping tester  = new Grouping(9);
		
		assertEquals(true, tester.isOpen(1));
		assertEquals(false, tester.isComplete());
		
		tester.addRoll(1, 0);
		tester.addRoll(2, 1);
		tester.addRoll(3, 2);
		tester.addRoll(4, 3);
		tester.addRoll(6, 4);
		tester.addRoll(6, 5);
		tester.addRoll(6, 6);
		tester.addRoll(6, 7);
		tester.addRoll(6, 8);
		
		assertEquals(4, tester.maxStraightFound());
		assertEquals(true, tester.isComplete());
		assertEquals(false, tester.isOpen(8));
		assertEquals(5, tester.maxOfAKindFound());
		assertEquals(6, tester.groupingList[8]);
		assertEquals(Arrays.asList(Line.FiveOK, Line.SStraight), tester.score());
		
		tester.addRoll(1, 0);
		tester.addRoll(1, 1);
		tester.addRoll(1, 2);
		tester.addRoll(1, 3);
		
		assertEquals(true, tester.isFullHouse());
	}

}
