package yah_du_ot;

import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest {

	@Test
	public void test() {
		Die test = new Die();
		for(int i = 0; i < 20; i++){
			int dieNumber = test.roll();
			if(dieNumber < 1 || dieNumber > 6){
				fail("Die rolled number out of range");
			}
		}
	}

}
