package yah_du_ot;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScoreCardTest {

	@Test
	public void test() {
		ScoreCard tester = new ScoreCard("test player");
		
		assertEquals(0, tester.getTotal());
		
		tester.addTallies(1, Line.FullHouse);
		tester.addTallies(1, Line.ThreeOK);
		tester.addTallies(1, Line.FourOK);
		tester.addTallies(1, Line.FiveOK);
		tester.addTallies(1, Line.SixOK);
		tester.addTallies(1, Line.SevenOK);
		tester.addTallies(1, Line.EightOK);
		tester.addTallies(1, Line.NineOK);
		tester.addTallies(1, Line.SStraight);
		tester.addTallies(1, Line.LStraight);
		tester.addTallies(1, Line.FStraight);
		
		assertEquals(470, tester.getTotal());
	}

}
