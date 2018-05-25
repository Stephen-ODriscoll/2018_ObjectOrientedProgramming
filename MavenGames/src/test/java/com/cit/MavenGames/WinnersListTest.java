package com.cit.MavenGames;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class WinnersListTest {

	@Test
	public void testRemove() {
		
		WinnerList winners = new WinnerList();

		boolean result = winners.remove(0);

		assertFalse("Test Removing Winner with 0 Entries", result);
		
		winners.add("Name", 4);
		result = winners.remove(0);
		
		assertTrue("Test Removing Winner with 1 Entry", result);
	}
	
	@Test
	public void testList() {
		
		WinnerList winners = new WinnerList();
		
		winners.add("Player", 4);
		winners.add("Player2", 4);
		winners.add("Player10", 5);
		winners.add("Player123456", 4);
		winners.add("Player800", 6);
		
		String list = winners.list();
		int length = 0;
		boolean result = true;
		
		for(int i = 0; i < 5; i++) {
			
			int x = 0;
			
			while(list.charAt((length*i)+x) != '\n')
				x++;
			
			x++;
			
			if(length == 0)
				length = x;
			
			else if(x != length)
				result = false;
		}
		
		assertTrue("Test Format of list", result);
	}
	
	@Test
	public void testListByName() throws FileNotFoundException {
		
		WinnerList winners = new WinnerList();
		
		winners.add("Player", 4);
		winners.add("Player2", 4);
		winners.add("Player10", 5);
		winners.add("Player123456", 4);
		winners.add("Player800", 6);
		
		String list = winners.listByName();
		int length = 0;
		boolean result = true;
		
		for(int i = 0; i < 5; i++) {
			
			int x = 0;
			
			while(list.charAt((length*i)+x) != '\n')
				x++;
			
			x++;
			
			if(length == 0)
				length = x;
			
			else if(x != length)
				result = false;
		}
		
		assertTrue("Test Format of list", result);
	}

	@Test
	public void testListByPrize() throws FileNotFoundException {
		
		WinnerList winners = new WinnerList();
		
		winners.add("Player", 4);
		winners.add("Player2", 4);
		winners.add("Player10", 5);
		winners.add("Player123456", 4);
		winners.add("Player800", 6);
		
		String list = winners.listByPrize();
		int length = 0;
		boolean result = true;
		
		for(int i = 0; i < 5; i++) {
			
			int x = 0;
			
			while(list.charAt((length*i)+x) != '\n')
				x++;
			
			x++;
			
			if(length == 0)
				length = x;
			
			else if(x != length)
				result = false;
		}
		
		assertTrue("Test Format of list", result);
	}
}
