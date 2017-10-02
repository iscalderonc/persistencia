package com.baufest.josm2.persistence.dao;

import java.sql.SQLException;

import com.baufest.josm2.model.Book;

public interface BookDao {
	
	Book save(Book book) throws SQLException;
	
}
