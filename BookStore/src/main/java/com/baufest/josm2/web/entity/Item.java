package com.baufest.josm2.web.entity;

import com.baufest.josm2.web.utils.Constant;

public class Item{
	
//	private Integer id;
	private Book book;
	private Integer number;
	private String description;
	private Double amount;
	
	public Item(Integer number,String description, Double amount){
		this.number = number;
		this.description = description;
		this.amount = amount;
	}
	/*
	public Item(Integer id, Integer number,String description, Double amount){
		this.id = id;
		this.number = number;
		this.description = description;
		this.amount = amount;
	}
	*/
	public Item(Book book, Integer number,String description, Double amount){
		this.book = book;
		this.number = number;
		this.description = description;
		this.amount = amount;
	}
	public Double calcDicountAmount(){
		return this.book.getPrice() - ((this.book.getPrice() * Constant.DISCOUNT_PERCENT_AMOUNT)/100);
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/*
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	*/
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public boolean equals(Object obj) {
		Item item = (Item) obj;  
		boolean result = false;

		if(this.book.getId().equals(item.book.getId())){
			result = true;
		}
		
		return result;
	}
	
//	public int compareTo(Item b) {
//        return id.compareTo(b.id);
//    }
}
