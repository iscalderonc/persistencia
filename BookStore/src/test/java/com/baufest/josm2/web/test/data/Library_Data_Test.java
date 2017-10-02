package com.baufest.josm2.web.test.data;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.baufest.josm2.web.entity.Book;
import com.baufest.josm2.web.entity.Item;
import com.baufest.josm2.web.entity.Operation;
import com.baufest.josm2.web.entity.Option;
import com.baufest.josm2.web.service.Library;
import com.baufest.josm2.web.utils.Constant;

// runner de mockito que detecta las anotaciones
@RunWith(JUnit4ClassRunner.class)
public class Library_Data_Test {
	
	@Autowired
	private Library libraryService;
	
	private static int i;
	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private Book book5;
	private Book book6;
	
	@BeforeClass
	public static void init(){		
		System.out.println("Se ejecuta una sola vez @BeforeClass");
	}
	
	@Before
	public void setUp(){
		System.out.println("Por cada @Test se ejecuta @Before " + i++);
//		MockitoAnnotations.initMocks(this);
		libraryService = new Library();
		book1 = new Book(1,"Java","Downey","Computation",100,250.60);
		book2 = new Book(2,"Net","Liberty","Science",50,300.10);
		book3 = new Book(3,"History","Robles","History",300,400D);
		book4 = new Book(4,"Java","Downey","Computation",100,250.60);
		book5 = new Book(5,"Practical Unit Testing with JUnit and Mockito","Tomek Kaczanowski","Computation",100,600D);
		book6 = new Book(6,"Clean Code","Robert C. Martin","Computation",150,700D);
	}

	@Test
	public void addBookTest(){
		Operation oper = libraryService.add(book5);
		Operation oper2 = libraryService.add(book6);
		
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL,oper.getStatus());
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL,oper2.getStatus());
	}
	
	@Test
	public void duplicateBookTest(){
		Operation oper = libraryService.add(book1);
		Operation oper1 = libraryService.add(book2);
		Operation oper2 = libraryService.add(book3);
		Operation oper3 = libraryService.add(book4);
		
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL,oper.getStatus());
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL,oper1.getStatus());
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL,oper2.getStatus());
		Assert.assertEquals(Constant.STATUS.FAIL,oper3.getStatus());
	}
	
	@Test
	public void deleteBookTest(){
		
		libraryService.add(book1);
		libraryService.add(book3);
		
		Operation oper = libraryService.delete(book1.getId());
		Assert.assertNotNull(oper);
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL,oper.getStatus());
	}
	
	@Test
	public void getAllBookTest(){
		libraryService.add(book1);
		libraryService.add(book3);
		
		Assert.assertNotNull(libraryService.getAll());
		Assert.assertEquals(new Integer(2),new Integer(libraryService.getAll().size()));
		Assert.assertFalse(new Integer(libraryService.getAll().size()).equals(new Integer(10)));
	}
	
	@Test
	public void getCateroryTest(){
		Assert.assertNotNull(libraryService.getCaterory());
		Assert.assertTrue(new Integer(libraryService.getCaterory().size()).equals(new Integer(Constant.CATEGORY.values().length))) ;
	}
	
	@Test
	public void getCateroryByLabelTest(){
		Option option = libraryService.getCateroryByLabel(Constant.CATEGORY.ART.getCategory());
		Assert.assertNotNull(option);
	}
	
	@Test
	public void getCategoryByLavelNUllTest(){
		Option option = libraryService.getCateroryByLabel("OTHER CATEGORY");
		Assert.assertEquals(null,option);
	}
	
	@Test
	public void validateNumberBookByCategory_Data(){
		
		libraryService.add(book1); //Category: Computation
		libraryService.add(book2); //Category: Science
		libraryService.add(book3); //Category: History
		libraryService.add(book5); //Category: Computation
		libraryService.add(book6); //Category: Computation
		
		Assert.assertTrue(libraryService.validateMaxByCategory(Constant.CATEGORY.ADVENTURE.getCategory()));
		Assert.assertTrue(libraryService.validateMaxByCategory(Constant.CATEGORY.COMPUTATION.getCategory()));
	}
	
	@Test
	public void addItemTest(){
	
		// Set Data
		libraryService.add(book1);
		libraryService.add(book3);
		
		Operation oper = libraryService.addItem(book1.getId());
		Operation oper2 =libraryService.addItem(book3.getId());
		
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL, oper.getStatus());
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL, oper2.getStatus());
	}
	
	@Test
	public void getGift_DISCOUNT(){
		
		// Set Data
		libraryService.add(book1);
		libraryService.add(book3);
		
		libraryService.addItem(book1.getId());
		libraryService.addItem(book3.getId());
		
		// Amount Total: 250.60 + 400 = 650.60
		Operation oper = libraryService.getGift();
		
		Assert.assertEquals(Constant.STATUS.SUCCESSFUL, oper.getStatus());

		// Validating Amount less than 1000
		Assert.assertEquals(Constant.GIFT.DISCOUNT.getGift() + "[" + Constant.DISCOUNT_PERCENT_AMOUNT + "%].", oper.getDescription());
	}
	
	
	@Test
	public void getGift_BOOK(){
		
		// Set Data
		libraryService.add(book1);
		libraryService.add(book3);
		libraryService.add(book5);
		
		libraryService.addItem(book1.getId());
		libraryService.addItem(book3.getId());
		libraryService.addItem(book5.getId());
		
		// Amount Total: 250.60 + 400 + 600 = 1250.60
		Operation oper = libraryService.getGift();
		
		// Validating Amount greater than 1000
		Assert.assertEquals(Constant.GIFT.BOOK.getGift() + "[1].", oper.getDescription());
	}
}
