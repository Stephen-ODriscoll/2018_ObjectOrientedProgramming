package com.cit.MavenGames;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WinnersController {

	private WinnerList winners = new WinnerList();
	
	public void addWinner(String winner, int category) throws FileNotFoundException {

		winners.add(winner, category);
		saveWinners();
	}
	
	public void removeWinner(int position) throws FileNotFoundException {
		
		winners.remove(position);
		saveWinners();
	}
	
	public String listWinners() {
		
		return winners.list();
	}
	
	public String listWinnersByName() throws FileNotFoundException {
		
		return winners.listByName();
	}
	
	public String listWinnersByPrize() throws FileNotFoundException {
		
		return winners.listByPrize();
	}
	
	public boolean loadWinners() throws FileNotFoundException {

		try {

			ObjectInputStream in = new ObjectInputStream(new FileInputStream("winners.ser"));
			winners = (WinnerList) in.readObject();
			in.close();

		} catch (IOException i) {

			saveWinners();
			return false;

		} catch (ClassNotFoundException c) {

			System.out.println("Winners class not found");
			return false;

		}
		return true;
	}

	public void saveWinners() throws FileNotFoundException {

		try {

			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("winners.ser"));
			out.writeObject(winners);
			out.close();

		} catch (IOException i) {

		}

	}
}
