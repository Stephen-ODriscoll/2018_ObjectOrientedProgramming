package com.cit.MavenGames;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FillMemory {

	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<WinnerList> winnersListCollection = new ArrayList<>();
		TimeUnit.SECONDS.sleep(30);

		while(true)
			winnersListCollection.add(new WinnerList());

	}

}
