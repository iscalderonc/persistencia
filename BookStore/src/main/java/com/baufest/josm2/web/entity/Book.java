package com.baufest.josm2.web.entity;

public class Book{

	private Integer id;
	private String name;
	private String author;
	private String category;
	private Integer page;
	private Double price;
	
	public Book(){}
	
	public Book(Integer id, String name, String author, String category, Integer page, Double price){
		this.id = id ;
		this.name = name ;
		this.author = author ;
		this.category = category ;
		this.page = page ;
		this.price = price ;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String genre) {
		this.category = genre;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString(){
		return "{ \"name\":" + this.name + ",\"author\": " + this.author + ", \"category\": " + this.category + ", \"page\": " + this.page + "}";
	}
	@Override
	public boolean equals(Object obj) {
		Book book = (Book) obj;  
		boolean result = false;
		//		if(this.id != null && this.id.equals(book.id))
		if(this.name.equals(book.name) && this.author.equals(book.author)){
			result = true;
		}
		
		return result;
	}

	
//	public int compareTo(Book o) {
//		return category.compareTo(b.category);
//		return 0;
//	}
}
