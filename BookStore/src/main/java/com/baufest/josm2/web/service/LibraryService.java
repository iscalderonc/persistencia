package com.baufest.josm2.web.service;

import java.util.List;

import com.baufest.josm2.web.entity.Book;
import com.baufest.josm2.web.entity.Item;
import com.baufest.josm2.web.entity.Operation;
import com.baufest.josm2.web.entity.Option;

public interface LibraryService  {
	public List<Option> getCaterory();
	public Option getCateroryByLabel(String label);
	public Operation getGift();
	public Operation validateRuleAdd(Book item);
	public Operation addItem(Integer id);
	public List<Item> getAllItem();
	public List<Item> getNumberBookByCategory();
	public Double getAmountTotal();
}
