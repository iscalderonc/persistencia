package com.baufest.josm2.web.test.practice;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.runners.MockitoJUnit44Runner;

import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.util.AlternativeJdkIdGenerator;









import com.baufest.josm2.web.entity.*;
import com.baufest.josm2.web.service.Library;
import com.baufest.josm2.web.utils.Constant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/appServlet/servlet-context-test.xml"})
public class Practice_Test {
	
	private Book b1;
	private Book b2;
	private Item item1;
	private Item item2;
	@Spy Library libraryService; 
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		b1 = mock(Book.class);
		b2 = mock(Book.class);
		item1 = mock(Item.class);
		item2 = mock(Item.class);
	}

	/*
	 * Este Test evidencia el error que se tiene al obtener el precio del libro, del objeto "Book" o objeto "Item".
	 * Al momento de validar la categoria de los libros que tenemos en la lista "items".
	 * Pongan atencion en el monto final, es correcto?. Si te queda duda podemos revisarlo con gusto.
	 * 
	 * Ademas si se dan cuenta en el mismo Test, podemos validar Datos y Comportamiento.
	 */
	
    @Test
    public void getAmountTotal_SUCCESSFUL_1(){
       	   // Stubbing
           when(b1.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
           when(b1.getPrice()).thenReturn(300D);
           
           when(b2.getCategory()).thenReturn(Constant.CATEGORY.HISTORY.getCategory());
           when(b2.getPrice()).thenReturn(200D);
           
           when(item1.getBook()).thenReturn(b1);
           when(item1.getAmount()).thenReturn(300d);
           when(item1.calcDicountAmount()).thenReturn(30d);
           
           when(item2.getBook()).thenReturn(b2);
           when(item2.getAmount()).thenReturn(200d);
           when(item2.calcDicountAmount()).thenReturn(20d);    
           
           // Execute Method
           libraryService.setItems(Arrays.asList(item1,item2));
           
           Assert.assertEquals(new Double(230), libraryService.getAmountTotal());
           
           // Verification Method
           verify(item1,times(1)).calcDicountAmount();
           verify(item2,never()).calcDicountAmount();
           verify(item2,times(1)).getAmount();
    }
    
    /*
	 * Este Test evidencia el error que se tiene al obtener el precio del libro, del objeto "Book" o objeto "Item". 
	 * Al momento de validar la categoria de los libros que tenemos en la lista "items".
	 * Pongan atencion en el monto final, es correcto?. 
	 * Es el mismo escenario que el Test anterior? 
	 * Si te queda duda podemos revisarlo con gusto.
	 * 
	 * Ademas si se dan cuenta en el mismo Test, podemos validar Datos y Comportamiento.
	 */
    
    @Test
    public void getAmountTotal_SUCCESSFUL_2(){
       	   // Stubbing
           when(b1.getCategory()).thenReturn(Constant.CATEGORY.COMPUTATION.getCategory());
           when(b1.getPrice()).thenReturn(300D);
           
           when(b2.getCategory()).thenReturn(Constant.CATEGORY.SCIENCE.getCategory());
           when(b2.getPrice()).thenReturn(200D);
           
           when(item1.getBook()).thenReturn(b1);
           when(item1.getAmount()).thenReturn(300d);
           when(item1.calcDicountAmount()).thenReturn(30d);
           
           when(item2.getBook()).thenReturn(b2);
           when(item2.getAmount()).thenReturn(200d);
           when(item2.calcDicountAmount()).thenReturn(20d);    
           
           // Execute Method
           libraryService.setItems(Arrays.asList(item1,item2));
           
           Assert.assertEquals(new Double(50), libraryService.getAmountTotal());
           
           // Verification Method
           verify(item1,times(1)).calcDicountAmount();
           verify(item1,never()).getAmount();
           verify(item2,times(1)).calcDicountAmount();
           verify(item2,never()).getAmount();
    }
}
