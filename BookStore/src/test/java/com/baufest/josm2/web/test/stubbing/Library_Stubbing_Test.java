package com.baufest.josm2.web.test.stubbing;


import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baufest.josm2.web.entity.Book;
import com.baufest.josm2.web.entity.Operation;
import com.baufest.josm2.web.service.Library;
import com.baufest.josm2.web.utils.Constant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appServlet/servlet-context-test.xml"})
public class Library_Stubbing_Test {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Library_Stubbing_Test.class);
	
	// Create Object whit @Spy	
	@Spy private Library libraryService;

	// Create Object with @Mock
	@Mock private Library libraryServiceMock;
	
	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private Book book5;
	private Book book6;
	
	@Before
	public void setUp(){
		
		// Initial Mock - Allows the creation of the mock
		MockitoAnnotations.initMocks(this);

		// Create Object with Mockito.mock
		book1 = mock(Book.class);
		book2 = mock(Book.class);
		book3 = mock(Book.class);
		book4 = mock(Book.class);
		book5 = mock(Book.class);
		book6 = mock(Book.class);
	}
	
	@Test
	public void validateRuleAdd_SuccessfulTest(){

		// Stubbing
		when(book1.getName()).thenReturn("Java");
		when(book1.getAuthor()).thenReturn("Downey");
		when(book1.getCategory()).thenReturn(Constant.CATEGORY.ADVENTURE.getCategory());
		when(book1.toString()).thenReturn("Algo");
		
		when(book2.getName()).thenReturn("Net");
		when(book2.getAuthor()).thenReturn("Liberty");
		when(book2.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		// Execute Method
		System.out.println("");
		System.out.println(book1.toString());
		System.out.println("[validateRuleAdd_SuccessfulTest] Add book1:" + libraryService.validateRuleAdd(book1).getStatus());
		System.out.println("[validateRuleAdd_SuccessfulTest] Add book2:" + libraryService.validateRuleAdd(book2).getStatus());
		
		// Verification Method
		verify(libraryService,times(1)).validateRuleAdd(book1);
		verify(libraryService,times(1)).validateRuleAdd(book2);
		
		verify(libraryService,times(2)).validateRuleAdd((Book) anyObject());
		verify(libraryService,times(2)).validateMaxByCategory(anyString());
		verify(libraryService,times(2)).getNumberBookByCategory(anyString());
		verify(libraryService,times(2)).add((Book) anyObject());
	}
	
	@Test
	public void validateRuleAdd_FailTest(){

		// Stubbing
		when(book1.getName()).thenReturn("Java");
		when(book1.getAuthor()).thenReturn("Downey");
		when(book1.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		when(book2.getName()).thenReturn("Java1");
		when(book2.getAuthor()).thenReturn("Downey");
		when(book2.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		when(book3.getName()).thenReturn("Java2");
		when(book3.getAuthor()).thenReturn("Downey");
		when(book3.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		when(book4.getName()).thenReturn("Java3");
		when(book4.getAuthor()).thenReturn("Downey");
		when(book4.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		when(book5.getName()).thenReturn("Java4");
		when(book5.getAuthor()).thenReturn("Downey");
		when(book5.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		when(book6.getName()).thenReturn("Java5");
		when(book6.getAuthor()).thenReturn("Downey");
		when(book6.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		// Execute Method
		System.out.println("");
		System.out.println("[validateRuleAdd_FailTest] Add book1:" + libraryService.validateRuleAdd(book1).getStatus());
		System.out.println("[validateRuleAdd_FailTest] Add book2:" + libraryService.validateRuleAdd(book2).getStatus());
		System.out.println("[validateRuleAdd_FailTest] Add book3:" + libraryService.validateRuleAdd(book3).getStatus());
		System.out.println("[validateRuleAdd_FailTest] Add book4:" + libraryService.validateRuleAdd(book4).getStatus());
		System.out.println("[validateRuleAdd_FailTest] Add book5:" + libraryService.validateRuleAdd(book5).getStatus());
		System.out.println("[validateRuleAdd_FailTest] Add book6:" + libraryService.validateRuleAdd(book6).getStatus());
		
		// Verification Method
		verify(libraryService,times(6)).validateRuleAdd((Book) anyObject());
		verify(libraryService,times(6)).validateMaxByCategory(anyString());
		verify(libraryService,times(6)).getNumberBookByCategory(anyString());
		verify(libraryService,times(5)).add((Book) anyObject());
	}
	
	@Test
	public void isDuplicatedBookTest(){
		
		// Stubbing
		when(book1.getName()).thenReturn("Java");
		when(book1.getAuthor()).thenReturn("Downey");
		when(book1.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		// Execute Method
		System.out.println("");
		System.out.println("[isDuplicatedBookTest] Add Book1:" + libraryService.add(book1).getStatus());
		System.out.println("[isDuplicatedBookTest] Add Book2:" + libraryService.add(book1).getStatus());
		
		// Verification Method
		verify(libraryService,times(2)).add(book1);
		verify(libraryService,times(2)).add((Book) anyObject());
	}
	
	@Test
	public void deleteBookTest(){
		// Create Mock
		Operation oper1 = mock(Operation.class);
		Operation oper2 = mock(Operation.class);
		
		// Stubbing
		when(oper1.getStatus()).thenReturn(Constant.STATUS.SUCCESSFUL);
		when(oper2.getStatus()).thenReturn(Constant.STATUS.FAIL);
		
		when(book1.getId()).thenReturn(new Integer(1));
		when(book2.getId()).thenReturn(new Integer(2));
		when(book3.getId()).thenReturn(new Integer(3));
		
		when(libraryServiceMock.delete(new Integer("1"))).thenReturn(oper1);
		when(libraryServiceMock.delete(new Integer("2"))).thenReturn(oper1);
		when(libraryServiceMock.delete(new Integer("3"))).thenReturn(oper2);

		// Execute Method
		System.out.println("");
		System.out.println("[deleteBookTest] Delete Book1: " + libraryServiceMock.delete(new Integer(1)).getStatus());
		System.out.println("[deleteBookTest] Delete Book2: " + libraryServiceMock.delete(new Integer(2)).getStatus());
		System.out.println("[deleteBookTest] Delete Book3: " + libraryServiceMock.delete(new Integer(3)).getStatus());
		
		// Verification Method
		verify(libraryServiceMock,times(1)).delete(new Integer(1));
		verify(libraryServiceMock).delete(new Integer(1));
		verify(libraryServiceMock).delete(new Integer(2));
		verify(libraryServiceMock).delete(new Integer(3));
		verify(libraryServiceMock,times(3)).delete(anyInt());
	}

	@Test
	public void getAllBookTest(){
		// Set Data
		List<Book> books = new ArrayList<Book>(Arrays.asList(book1,book2));

		// Stubbing
		when(libraryServiceMock.getAll()).thenReturn(books);

		// Execute Method
		System.out.println("");
		System.out.println("[getAllBookTest] Size:" + libraryServiceMock.getAll().size());
		
		books.add(book3);
		books.add(book4);
		
		System.out.println("[getAllBookTest] (+2) - Size:" + libraryServiceMock.getAll().size());
		
		// Verification Method
		verify(libraryServiceMock,times(2)).getAll();
	}

	@Test
	public void getBookTest(){
		
		// Set Data
		List<Book> books = new ArrayList<Book>(Arrays.asList(book1,book2,book3));
		
		// Stubbing
		when(book1.getId()).thenReturn(1);
		when(book1.getName()).thenReturn("Java");
		
		when(book2.getId()).thenReturn(2);
		when(book2.getName()).thenReturn("Net");
		
		when(book3.getId()).thenReturn(3);
		when(book3.getName()).thenReturn("C++");
		
		when(libraryServiceMock.getAll()).thenReturn(books);
		when(libraryServiceMock.get(1, "algo")).thenReturn(book1);
		when(libraryServiceMock.get(2, null)).thenReturn(book2);
		when(libraryServiceMock.get(3, "algo")).thenReturn(book3);
		
		// Execute Method
		System.out.println("");
		System.out.println("[getBookTest] getBook(1)- Name:" + libraryServiceMock.get(1,"algo").getName());
		System.out.println("[getBookTest] getBook(2)- Name:" + libraryServiceMock.get(2,null).getName());
		System.out.println("[getBookTest] getBook(3)- Name:" + libraryServiceMock.get(3,"algo").getName());
		
		// Verification Method
		verify(libraryServiceMock).get(1, "algo");
		verify(libraryServiceMock).get(2, null);
		verify(libraryServiceMock).get(3, "algo");
		verify(libraryServiceMock,times(3)).get(anyInt(), anyString());
	}
	
	@Test
	public void validateMaxBookByCategory(){
		
		// Stubbing
		when(book1.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		when(book2.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		when(book3.getCategory()).thenReturn(Constant.CATEGORY.SCIENCE.getCategory());
		when(book4.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		when(book5.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		when(book6.getId()).thenReturn(6);
		when(book6.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
		
		// Execute Method
		libraryService.add(book1);
		libraryService.add(book2);
		libraryService.add(book3);
		libraryService.add(book4);
		libraryService.add(book5);
		libraryService.add(book6);
		
		System.out.println("");
		System.out.println("[validateMaxBookByCategory] Category 'Other' Acepted: " + libraryService.validateMaxByCategory("category"));
		System.out.println("[validateMaxBookByCategory] Category '" + Constant.CATEGORY.COMPUTATION.getCategory() + "' Acepted: " + libraryService.validateMaxByCategory(Constant.CATEGORY.COMPUTATION.getCategory()));
		
		libraryService.delete(6);
		
		System.out.println("[validateMaxBookByCategory] Category [After] '" + Constant.CATEGORY.COMPUTATION.getCategory() + "' Acepted: " + libraryService.validateMaxByCategory(Constant.CATEGORY.COMPUTATION.getCategory()));
		
		// Verification Method
		verify(libraryService,times(3)).validateMaxByCategory(anyString());
		
		// Verify Order Executions
		//		InOrder inOrder = inOrder(bookStoreService);
		//		inOrder.verify(bookStoreService).validateMaxRepCategory(anyString());
		//		inOrder.verify(bookStoreService).add((Book) eq(null));
	}
	
	@Test(expected = NullPointerException.class)
	public void getAllBookNullPointerExceptionTest(){

		// Stubbing
		when(libraryServiceMock.getAll()).thenThrow(new NullPointerException());

		// Execute Method
		System.out.println("");
		System.out.println("[getAllBookNullPointerExceptionTest] Aquí se generara un NullPointerException al hacer <<libraryServiceMock.getAll()>>");
		libraryServiceMock.getAll();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getBookIndexOutOfBoundsExceptionTest(){
		// Set Data
		List<Book> books = new ArrayList<Book>(Arrays.asList(book1,book2));
		
		// Stubbing
		when(book1.getId()).thenReturn(1);
		when(book1.getName()).thenReturn("Java");
		
		when(book2.getId()).thenReturn(2);
		when(book2.getName()).thenReturn("Net");
		
		when(libraryServiceMock.getAll()).thenReturn(books);
		when(libraryServiceMock.get(1, null)).thenReturn(book1);
		when(libraryServiceMock.get(2, null)).thenReturn(book2);
		when(libraryServiceMock.get(3, null)).thenThrow(new IndexOutOfBoundsException());
		
		// Execute Method
		System.out.println("");
		System.out.println("[getBookIndexOutOfBoundsExceptionTest] getBook(1)- Name:" + libraryServiceMock.get(1,null).getName());
		System.out.println("[getBookIndexOutOfBoundsExceptionTest] getBook(2)- Name:" + libraryServiceMock.get(2,null).getName());
		System.out.println("[getBookIndexOutOfBoundsExceptionTest] getBook(3)- Aquí se genera un IndexOutOfBoundsException al hacer <<libraryServiceMock.get(3,null)>>");
		libraryServiceMock.get(3,null);
	}

}
