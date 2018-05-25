package com.cit.MavenGames;

import com.cit.PrizeTree.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PrizesController {

	private PrizeTree tree = new PrizeTree();
	private ArrayList<Integer> category = new ArrayList<Integer>();
	private ArrayList<String> key = new ArrayList<String>();
	private int fourStarTokens = 0, fiveStarTokens = 0, sixStarTokens = 0;

	public PrizeTree getTree() {

		return tree;
	}

	public int getCategory(int position) {

		return category.get(position);
	}

	public String getKey(int position) {

		return key.get(position);
	}

	public void addFourStar() {

		fourStarTokens++;
	}

	public void subtractFourStar() {

		fourStarTokens--;
	}

	public void addFiveStar() {

		fiveStarTokens++;
	}

	public void subtractFiveStar() {

		fiveStarTokens--;
	}

	public void addSixStar() {

		sixStarTokens++;
	}

	public void subtractSixStar() {

		sixStarTokens--;
	}

	public int getFourStar() {

		return fourStarTokens;
	}

	public int getFiveStar() {

		return fiveStarTokens;
	}

	public int getSixStar() {

		return sixStarTokens;
	}

	public boolean loadTokens() throws FileNotFoundException {

		File tokensFile = new File("Tokens.txt");

		if (!tokensFile.exists())
			return false;

		Scanner tokens = new Scanner(tokensFile);
		try {
			fourStarTokens = Integer.parseInt(tokens.next());
			fiveStarTokens = Integer.parseInt(tokens.next());
			sixStarTokens = Integer.parseInt(tokens.next());
		} catch (NumberFormatException e) {
			System.out.println("Error Reading Tokens. Resetting File!");
			fourStarTokens = 0;
			fiveStarTokens = 0;
			sixStarTokens = 0;
			tokens.close();
			return false;
		}
		tokens.close();
		return true;
	}

	public void saveTokens() throws FileNotFoundException {

		PrintWriter outputFile = new PrintWriter("Tokens.txt");
		outputFile.print(fourStarTokens + " " + fiveStarTokens + " " + sixStarTokens);
		outputFile.close();

	}

	public boolean loadTree() throws FileNotFoundException {

		File prizeTreeFile = new File("PrizesData.txt");

		if (!prizeTreeFile.exists()) {

			System.out.println("No Prizes Without the PrizesData File.");
			return false;
		}

		Scanner prizeTree = new Scanner(prizeTreeFile);

		while (prizeTree.hasNext()) {

			String prizeDetails = prizeTree.nextLine();
			String[] section = prizeDetails.split("\t");

			if (section.length != 3) {

				System.out.println("Error Reading PrizesData. Prize did not load");
				prizeTree.next();
			} else {

				try {
					category.add(Integer.parseInt(section[1]));
					tree.insert(section[2], section[0]);
					key.add(section[2]);
				} catch (NumberFormatException e) {
					System.out.println("Error Reading PrizesData. Prize did not load!");
					category.add(0);
				}
			}
		}
		prizeTree.close();
		return true;
	}

}