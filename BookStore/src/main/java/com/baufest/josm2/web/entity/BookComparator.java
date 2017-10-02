package com.baufest.josm2.web.entity;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
	
	public int compare(Book b1, Book b2) {
		return b1.getCategory().compareTo(b2.getCategory());
	}

}
