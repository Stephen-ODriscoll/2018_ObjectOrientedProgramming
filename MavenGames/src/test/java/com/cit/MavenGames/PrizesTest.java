package com.cit.MavenGames;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.FileNotFoundException;

public class PrizesTest {

	@Test
	public void testAddition() {

		PrizesController prizes = new PrizesController();

		int four = prizes.getFourStar();
		int five = prizes.getFiveStar();
		int six = prizes.getSixStar();

		prizes.addFourStar();
		prizes.addFiveStar();
		prizes.addSixStar();

		assertEquals("Test Adding a Four Star Token", prizes.getFourStar(), four + 1);
		assertEquals("Test Adding a Five Star Token", prizes.getFiveStar(), five + 1);
		assertEquals("Test Adding a Six Star Token", prizes.getSixStar(), six + 1);
	}

	@Test
	public void testSubtraction() {

		PrizesController prizes = new PrizesController();

		int four = prizes.getFourStar();
		int five = prizes.getFiveStar();
		int six = prizes.getSixStar();

		prizes.subtractFourStar();
		prizes.subtractFiveStar();
		prizes.subtractSixStar();

		assertEquals("Test Subtracting a Four Star Token", prizes.getFourStar(), four - 1);
		assertEquals("Test Subtracting a Five Star Token", prizes.getFiveStar(), five - 1);
		assertEquals("Test Subtracting a Six Star Token", prizes.getSixStar(), six - 1);
	}

	@Test
	public void testLoadTokens() throws FileNotFoundException {

		PrizesController prizes = new PrizesController();

		boolean result = prizes.loadTokens();

		assertTrue("Test Loading Tokens", result);
	}

	@Test
	public void testLoadTree() throws FileNotFoundException {

		PrizesController prizes = new PrizesController();

		boolean result = prizes.loadTree();

		assertTrue("Test Loading HashMap", result);
	}
}
