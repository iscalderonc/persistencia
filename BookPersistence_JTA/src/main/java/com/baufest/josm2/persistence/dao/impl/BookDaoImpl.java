package com.baufest.josm2.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baufest.josm2.persistence.dao.BookDao;
import com.baufest.josm2.model.Book;


@Component
public class BookDaoImpl implements BookDao{
	
	@Autowired
	private DataSource dataSource;
	
	public Book save(Book book) throws SQLException {
		
		String sql = "INSERT INTO BOOK (ID, NAME, AUTHOR, CATEGORY,PAGE, PRICE) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, book.getId());
			ps.setString(2, book.getName());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getCategoy());
			ps.setInt(5,book.getPage());
			ps.setDouble(6,book.getPrice());
			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		return book;
	}
}
