package com.cit.MavenGames;

import java.io.FileNotFoundException;
import java.util.Random;

public class GuessingController {

	private Random random = new Random();
	private final int range = 100;
	private int answer = random.nextInt(range);
	private int attempt = 0;
	private String hint = "";
	private String result = "";

	public boolean placeGuess(String input) throws FileNotFoundException {

		int guess;

		try {
			guess = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			hint = "Enter a number 1-100";
			return false;
		}

		if (guess >= range || guess < 0) {

			hint = "Enter a number 1-100";
			return false;
		}

		attempt++;

		if (guess < answer)
			hint = "Higher";

		else if (guess > answer)
			hint = "Lower";

		else {

			hint = "You won a 4* Prize";
			result = "Correct!";
			return true;
		}

		if (attempt == 6) {

			hint = "Answer was " + answer;
			result = "Game Over!";
		}

		return true;

	}

	public void resetGame() {

		answer = random.nextInt(range);
		attempt = 0;
		result = "";
		hint = "";

		System.out.println("Guessing: " + answer);

		hint = "";
	}

	public int getAttempt() {

		return attempt;
	}

	public String getHint() {

		return hint;
	}

	public String getResult() {

		return result;
	}
}
