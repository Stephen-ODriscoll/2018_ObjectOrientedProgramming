package com.cit.MavenGames;

import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WinnerList implements Serializable {

	static final Comparator<Winner> NAME_ORDER = new Comparator<Winner>() {
		public int compare(Winner winner1, Winner winner2) {
			return winner1.getName().compareTo(winner2.getName());
		}
	};

	static final Comparator<Winner> PRIZE_ORDER = new Comparator<Winner>() {
		public int compare(Winner winner1, Winner winner2) {
			return winner2.getCategory() - winner1.getCategory();
		}
	};

	private static final long serialVersionUID = 1;
	private List<Winner> winners = new ArrayList<>();
	private int longestName = 0;

	public void add(String name, int category) {

		winners.add(new Winner(winners.size(), name, category));

		if (name.length() > longestName)
			longestName = name.length();
	}

	public boolean remove(int position) {

		if (position >= winners.size() || position < 0)
			return false;

		winners.remove(position);

		longestName = 0;

		for (int i = 0; i < winners.size(); i++)
			if (winners.get(i).getLength() > longestName)
				longestName = winners.get(i).getLength();

		return true;
	}

	public String list() {

		String listOfWinners = "";
		String format = "Position: %-5d\tName: %-" + longestName + "s\tCategory: %-1d%n";

		for (int i = 0; i < winners.size(); i++)
			listOfWinners += String.format(format, winners.get(i).getPosition(), winners.get(i).getName(),
					winners.get(i).getCategory());

		return listOfWinners;
	}

	public String listByName() throws FileNotFoundException {

		String listOfWinners = "";
		String format = "Position: %-5d\tName: %-" + longestName + "s\tCategory: %-1d%n";
		ArrayList<Winner> winnersCopy = new ArrayList<>();

		for (int i = 0; i < winners.size(); i++)
			winnersCopy.add(winners.get(i));

		Collections.sort(winnersCopy, NAME_ORDER);

		for (int i = 0; i < winnersCopy.size(); i++)
			listOfWinners += String.format(format, winnersCopy.get(i).getPosition(), winnersCopy.get(i).getName(),
					winnersCopy.get(i).getCategory());

		generateReport(listOfWinners);
		return listOfWinners;
	}

	public String listByPrize() throws FileNotFoundException {

		String listOfWinners = "";
		String format = "Position: %-5d\tName: %-" + longestName + "s\tCategory: %-1d%n";
		ArrayList<Winner> winnersCopy = new ArrayList<>();

		for (int i = 0; i < winners.size(); i++)
			winnersCopy.add(winners.get(i));

		Collections.sort(winnersCopy, PRIZE_ORDER);
		
		for (int i = 0; i < winnersCopy.size(); i++)
			listOfWinners += String.format(format, winnersCopy.get(i).getPosition(), winnersCopy.get(i).getName(),
					winnersCopy.get(i).getCategory());

		generateReport(listOfWinners);
		return listOfWinners;
	}

	public void generateReport(String report) throws FileNotFoundException {

		PrintWriter outputFile = new PrintWriter("Report.txt");
		outputFile.print(report);
		outputFile.close();

	}
}
