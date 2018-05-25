package com.cit.MavenGames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class LottoController {

	private int numbers = 46;
	private final int numberOfNumbers = 6; // If numberOfNumbers < numbers program will loop forever
	private final int numbersPerRow = 13;
	private int rows;
	private int answer;
	private int[] number = new int[numberOfNumbers];
	private int[] input = new int[numberOfNumbers];
	private String result = "";

	public LottoController() {

		rows = (numbers / numbersPerRow) + 1;
	}

	public void clearBox(int num) {

		for (int i = 0; i < numberOfNumbers; i++)
			if (num == input[i])
				input[i] = 0;
	}

	public void input(int answer) {

		this.answer = answer;

		for (int i = 0; i < numberOfNumbers; i++)
			if (input[i] == 0) {

				input[i] = answer;
				break;
			}
	}

	public void loadNumbers() throws FileNotFoundException {

		File lottoNumbersFile = new File("LottoNumbers.txt");

		if (!lottoNumbersFile.exists()) {

			newNumbers();
			return;
		}

		Scanner lottoNumbers = new Scanner(lottoNumbersFile);

		for (int i = 0; i < numberOfNumbers; i++) {

			if (lottoNumbers.hasNext()) {

				try {
					number[i] = Integer.parseInt(lottoNumbers.next());
				} catch (NumberFormatException e) {
					System.out.println("Error Reading LottoNumbers. Overwriting!");
					newNumbers();
					lottoNumbers.close();
					return;
				}
			}

			else {

				newNumbers();
				lottoNumbers.close();
				return;
			}
		}
		try {
			if (lottoNumbers.hasNext() && Integer.parseInt(lottoNumbers.next()) == -1) {
				
				newNumbers();
				lottoNumbers.close();
				return;
			}
		} catch (NumberFormatException e) {
		}
		lottoNumbers.close();

		for (int i = 0; i < numberOfNumbers; i++)
			if (numbers < number[i]) {
				newNumbers();
				return;
			}

		System.out.print("Lottery: ");

		for (int i = 0; i < numberOfNumbers; i++)
			System.out.print(number[i] + " ");

		System.out.println();
	}

	public void newNumbers() throws FileNotFoundException {

		Random random = new Random();

		for (int i = 0; i < numberOfNumbers; i++) {

			number[i] = random.nextInt(numbers) + 1;

			for (int x = (i - 1); x >= 0; x--)
				if (number[i] == number[x]) {

					number[i] = random.nextInt(numbers) + 1;
					x = i;
				}
		}
		saveNumbers(false);

		System.out.print("Lottery: ");

		for (int i = 0; i < numberOfNumbers; i++)
			System.out.print(number[i] + " ");

		System.out.println();
	}

	public void saveNumbers(boolean finished) throws FileNotFoundException {

		PrintWriter outputFile = new PrintWriter("LottoNumbers.txt");

		for (int i = 0; i < numberOfNumbers; i++)
			outputFile.print(number[i] + " ");

		if (finished == true)
			outputFile.print(-1);

		outputFile.close();
	}

	public boolean submit(Prizes prizes) throws FileNotFoundException {

		int correct = 0;
		boolean win = false;

		for (int i = 0; i < numberOfNumbers; i++)
			if (input[i] == 0) {

				result = "Please enter all numbers";
				return false;
			}

		for (int i = 0; i < numberOfNumbers; i++)
			for (int x = 0; x < numberOfNumbers; x++)
				if (input[i] == number[x])
					correct++;

		if (correct == 6) {

			win = true;
			prizes.addSixStar();
			result = "Congrats, you won a 6* prize";
		}

		else if (correct == 5) {

			win = true;
			prizes.addFiveStar();
			result = "Congrats, you won a 5* prize";
		}

		else if (correct == 4) {

			win = true;
			prizes.addFourStar();
			result = "Congrats, you won a 4* prize";
		}

		else
			result = "Try again, better luck next time";

		return win;

	}

	public void clear() {

		for (int i = 0; i < numberOfNumbers; i++)
			input[i] = 0;
	}

	public int getNumbers() {

		return numbers;
	}

	public int getNumberOfNumbers() {

		return numberOfNumbers;
	}

	public int getNumbersPerRow() {

		return numbersPerRow;
	}

	public int getRows() {

		return rows;
	}

	public int getAnswer() {

		return answer;
	}

	public int[] getNumber() {

		return number;
	}

	public int[] getInput() {

		return input;
	}

	public String getResult() {

		return result;
	}
}
