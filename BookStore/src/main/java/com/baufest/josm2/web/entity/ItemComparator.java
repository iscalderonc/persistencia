package com.baufest.josm2.web.entity;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
	
	public int compare(Item i1, Item i2) {
		return i1.getBook().getId().compareTo(i2.getBook().getId());
	}

}
