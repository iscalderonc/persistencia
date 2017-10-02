package com.baufest.josm2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", precision = 38, scale = 0)
	private Long id;

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "AUTHOR", length = 50)
	private String author;
	
	@Column(name = "CATEGORY", length = 20)
	private String categoy;

	@Column(name = "PAGE")
	private Integer page;
	
	@Column(name = "PRICE")
	private Double price;

	public Book() {
	}

	public Book(String name, String author, String category, Integer page, Double price) {
		super();
		this.name = name;
		this.author = author;
		this.categoy = category;
		this.page = page;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getCategoy() {
		return categoy;
	}

	public void setCategoy(String categoy) {
		this.categoy = categoy;
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
}