package com.baufest.josm2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", precision = 38, scale = 0)
	private Long id;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NAME", length = 50)
	private String name;

	
}