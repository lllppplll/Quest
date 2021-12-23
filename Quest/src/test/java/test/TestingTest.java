package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestingTest {

	@Test
	void test() {
		Testing test = new Testing();
		// arrange
		int output = test.square(5);
		// act
		// assert
		assertEquals(25, output);
		
		
		/* fail("Not yet implemented"); */
	}
}
