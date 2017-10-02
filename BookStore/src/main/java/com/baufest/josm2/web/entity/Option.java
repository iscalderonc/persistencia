package com.baufest.josm2.web.entity;

public class Option {
	
	private Integer number;
	private String description;
	
	public Option(Integer number,String description){
		this.number = number;
		this.description = description;
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
}
