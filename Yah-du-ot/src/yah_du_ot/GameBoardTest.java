package yah_du_ot;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameBoardTest {

	@Test
	public void test() {
		GameBoard tester = new GameBoard();
		//assertEquals(true, tester.spaceOpen(0,0));
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 0);
		}
		//tester.addRoll(2, 8, 0);
		
		//for(int i = 0; i < 8; i++){
		//	tester.addRoll(2, i, 1);
		//}
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 2);
		}
		/*
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 3);
		}
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 4);
		}
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 5);
		}
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 6);
		}
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 7);
		}
		for(int i = 0; i < 8; i++){
			tester.addRoll(2, i, 8);
		}
		*/
		//assertEquals(true, tester.isComplete());
		//Arrays.asList("Customer1", "Customer2", "Customer3"), myArray());
	}

}
