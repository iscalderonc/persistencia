package com.baufest.josm2.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baufest.josm2.web.entity.Book;
import com.baufest.josm2.web.entity.Item;
import com.baufest.josm2.web.entity.Operation;
import com.baufest.josm2.web.entity.Option;
import com.baufest.josm2.web.service.GenericService;
import com.baufest.josm2.web.service.LibraryService;

@Controller
public class BookStoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreController.class);
	private static String pathPages = "redirect:/pages/html/";
	
	@Autowired
	private LibraryService libraryService;
	
	@Autowired
	private GenericService<Book> genericService;
	
	public GenericService<Book> getGenericService() {
		return genericService;
	}

	public void setGenericService(GenericService<Book> genericService) {
		this.genericService = genericService;
	} 
	
	public LibraryService getLibraryService() {
		return libraryService;
	}

	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}
	 
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(@RequestParam(value = "name", required = false, defaultValue = "josm2") String name) {
		System.out.println("in controller init (name: " + name + ")");
		return this.pathPages + "bookStore/init.html";
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public ResponseEntity<Operation> add(@RequestBody Book book) {
		return new ResponseEntity<Operation>(libraryService.validateRuleAdd(book),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/inventory", method = RequestMethod.POST)
	public ResponseEntity<List<Book>> getAll(){
		return new ResponseEntity<List<Book>>(genericService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getBook", method = RequestMethod.GET)
	public ResponseEntity<Book> getBook(@RequestParam("id") Integer id) {
		return  new ResponseEntity<Book>(genericService.get(id,"D"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "deleteBook", method = RequestMethod.GET)
	public ResponseEntity<Operation> deleteB(@RequestParam("id") Integer id) {
		return new ResponseEntity<Operation>(genericService.delete(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	public ResponseEntity<Operation> update(@RequestBody Book book) {
		return new ResponseEntity<Operation>(genericService.update(book),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCategory", method = RequestMethod.POST)
	public ResponseEntity<List<Option>> getCategory() {
		return new ResponseEntity<List<Option>>(libraryService.getCaterory(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCategoryByLabel", method = RequestMethod.GET)
	public ResponseEntity<Option> getCategoryByLabel(@RequestParam("label") String label) {
		return new ResponseEntity<Option>(libraryService.getCateroryByLabel(label),HttpStatus.OK);
	}
	
//	@ResponseBody
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public ResponseEntity<Operation> addItem(@RequestParam("id") Integer id) {
		Operation oper = libraryService.addItem(id);
//		oper = libraryService.getGift(oper);
		return  new ResponseEntity<Operation>(oper, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllItem", method = RequestMethod.POST)
	public ResponseEntity<List<Item>> getAllItem() {
		return new ResponseEntity<List<Item>>(libraryService.getAllItem(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getGift", method = RequestMethod.POST)
	public ResponseEntity<Operation> getGift() {
		Operation oper = libraryService.getGift();
		return  new ResponseEntity<Operation>(oper, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getNumberBookByCategory", method = RequestMethod.POST)
	public ResponseEntity<List<Item>> getNumberBookByCategory() {
		List<Item> list = libraryService.getNumberBookByCategory();
		return  new ResponseEntity<List<Item>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAmountTotal", method = RequestMethod.POST)
	public ResponseEntity<Double> getAmountTotal() {
		Double amountTotal = libraryService.getAmountTotal();
		return  new ResponseEntity<Double>(amountTotal, HttpStatus.OK);
	}
	
	
	
}
