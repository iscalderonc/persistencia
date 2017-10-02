package com.baufest.josm2.persistence;

import java.sql.SQLException;

//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.baufest.josm2.model.Book;
import com.baufest.josm2.persistence.dao.BookDao;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/servlet-context-test.xml"} )
public class DataBaseTest {
	
//	@Autowired
//	private BasicDataSource dataSource;
/*	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory; 
	
	@Autowired
	private BookDao bookDao; 
	
	@Before
	public void setUp(){
	}
	
	@Test
	public void dataSourceNotNullTest(){
		Assert.assertNotNull(dataSource);
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bookDaoNotNullTest(){
		Assert.assertNotNull(sessionFactory);
	}
	
	@Transactional
	@Test
	public void sessionFactoryNotNull(){
		Assert.assertNotNull(bookDao);
		
		com.baufest.josm2.model.Book b = new com.baufest.josm2.model.Book();
		b.setId(new Long(1));
		b.setName("NameTest");
		b.setCategoy("CategoryTets");
		b.setAuthor("AuthorTest");
		b.setPage(1);
		b.setPrice(1.0);
		
		try{
			bookDao.save(b);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error (Save): " + e.getMessage());
		}
		
		Book b1 = bookDao.getById(new Long(1));
		System.out.println(b1.getCategoy());
	}
	
*/	
}
