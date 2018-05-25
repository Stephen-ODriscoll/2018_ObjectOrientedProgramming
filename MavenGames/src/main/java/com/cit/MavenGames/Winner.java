package com.cit.MavenGames;

import java.io.Serializable;
import java.util.Comparator;

public class Winner implements Serializable, Comparator<String>{

	private static final long serialVersionUID = 1;
	private int position;
	private String name;
	private int category;
	
	public Winner(int position, String name, int category) {
		
		this.position = position;
		this.name = name;
		this.category = category;
	}
	
	public int getPosition() {
		
		return position;
	}
	
	public String getName() {
		
		return name;
	}
	
	public int getCategory() {
		
		return category;
	}
	
	public int getLength() {
		
		return name.length();
	}
	
	public int compare(String one, String two) {
		 
		return one.compareTo(two);
	}
}
