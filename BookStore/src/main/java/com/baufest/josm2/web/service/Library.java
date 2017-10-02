package com.baufest.josm2.web
.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baufest.josm2.web.entity.Book;
import com.baufest.josm2.web.entity.BookComparator;
import com.baufest.josm2.web.entity.Item;
import com.baufest.josm2.web.entity.ItemComparator;
import com.baufest.josm2.web.entity.Operation;
import com.baufest.josm2.web.entity.Option;
import com.baufest.josm2.web.utils.Constant;
import com.baufest.josm2.web.utils.Constant.CATEGORY;
import com.baufest.josm2.web.utils.Constant.CATEGORY_DISCOUNT;
import com.baufest.josm2.web.utils.Constant.OPERATIONS;
import com.baufest.josm2.web.utils.Constant.STATUS;
import com.baufest.josm2.model.Category;
import com.baufest.josm2.persistence.dao.BookDao;
import com.baufest.josm2.persistence.dao.CategoryDao;

@Service
public class Library implements GenericService<Book>, LibraryService {
	
	private List<Book> books;
	private List<Item> items;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public Operation add(Book book) {
		Operation oper = new Operation(Constant.OPERATIONS.ADD,"Add Book.",Constant.STATUS.FAIL);
		
		if(books==null){
			books = getBooks(bookDao.listAll());
		}
		
		oper = isDuplicatedBook(oper, book);
		
		return oper;	
	}
	
	private List<Book> getBooks(List<com.baufest.josm2.model.Book> list){
		List<Book> listB = new ArrayList<Book>();

		Iterator i = list.iterator();
		while(i.hasNext()){
			com.baufest.josm2.model.Book b = (com.baufest.josm2.model.Book) i.next();
			Book bo = new Book();
			bo.setAuthor(b.getAuthor());
			bo.setCategory(b.getCategoy());
			bo.setId(b.getId().intValue());
			bo.setName(b.getName());
			bo.setPage(b.getPage());
			bo.setPrice(b.getPrice());
			
			listB.add(bo);
		}
			
		return listB;
		
	}
	
	public Operation update(Book book) {
		Operation oper = new Operation(Constant.OPERATIONS.UPDATE,"Update Book.",Constant.STATUS.FAIL);
		oper = isDuplicatedBook(oper, book);
		return oper;
	}
	
	public Operation delete(Integer id){
		Operation oper = new Operation(Constant.OPERATIONS.DELETE,"Delete Book.",Constant.STATUS.FAIL);
//		Arrays.sort(books.toArray());
		Iterator<Book> iter = books.iterator();
		while(iter.hasNext()){
			Book b = iter.next();
			if(b.getId().equals(id)){
				books.remove(b);
				oper.setStatus(Constant.STATUS.SUCCESSFUL);
				break;
			}
		}
		
		return oper;
	}
	
	public Book get(Integer id, String option) {
//		Arrays.sort(books.toArray());
		Iterator<Book> iter = books.iterator();
		while(iter.hasNext()){
			Book b = iter.next();
			if(b.getId().equals(id)){
				if(option != null && option.equals("D")){
					books.remove(b);
				}
				return b;
			}
		}
		
		return null;
	}
	
	public List<Book> getAll(){
		return books;
	}

	private Operation isDuplicatedBook(Operation oper, Book book){
		if(!books.contains(book)){
			if(oper.getTypeOperation().ADD.equals(Constant.OPERATIONS.ADD)){
				book.setId(new Integer(books.size()+1));
			}
			books.add(book);
			com.baufest.josm2.model.Book b = new com.baufest.josm2.model.Book();
			b.setId(book.getId().longValue());
			b.setName(book.getName());
			b.setCategoy(book.getCategory());
			b.setAuthor(book.getAuthor());
			b.setPage(book.getPage());
			b.setPrice(book.getPrice());
			
			try{
				bookDao.save(b);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Hubo un error: " + e.getMessage());
			}
			
			oper.setStatus(Constant.STATUS.SUCCESSFUL);
		}else{
			oper.setDescription("Duplicate Book.");
		}
		
		return oper;
	}
	
	@Transactional(readOnly = true) 
	public List<Option> getCaterory(){
		List<Option> categorys = new ArrayList<Option>();
		
		for(Category cat: categoryDao.listAll()){
			categorys.add(new Option(cat.getId().intValue(), cat.getName()));
		}
		
		/*
		for(CATEGORY cat: Constant.CATEGORY.values()){
			categorys.add(new Option(cat.ordinal(), cat.getCategory()));
		}
		*/
		return categorys; 
	}
	
	public Option getCateroryByLabel(String label){
		Option option = null;
		for(CATEGORY cat: Constant.CATEGORY.values()){
			if(cat.getCategory().equals(label)){
				option = new Option(cat.ordinal(), cat.getCategory());
			}
		}
		return option; 
	}
	
	public Item getNumberBookByCategory(String category){
		int count = 0;
		if(books!=null){
			Book[] bks = getBooksArrays(books);
			Arrays.sort(bks, new BookComparator());
			for(Book b: bks){
				if(b.getCategory().equals(category)){
					count+= 1;
				}
			}
		}
		return new Item(count, category, 0D);
	}
	
	public Boolean validateMaxByCategory(String category) {
		Boolean valid = Boolean.TRUE;
		Item item = getNumberBookByCategory(category);
		if(item.getNumber() >= Constant.MAX_NUMBER_BOOK_REPEATED_CATEGORY){
				valid = Boolean.FALSE;
		}
		return valid;
	}
	
	public Item give(Double amount){
		Item item = null;
		
		if(amount >= Constant.AMOUNT_MAX){
			item = new Item(1, Constant.GIFT.BOOK.getGift() + "[1]." , amount);
		}else{
			amount = amount - ((amount * Constant.DISCOUNT_PERCENT_AMOUNT)/100);
			item = new Item(1, Constant.GIFT.DISCOUNT.getGift() + "[" + Constant.DISCOUNT_PERCENT_AMOUNT + "%]." , amount);
		}
		return item;
	}
	@Transactional
	public Operation validateRuleAdd(Book book) {
		Operation oper = new Operation(Constant.OPERATIONS.ADD,"No more than " + Constant.MAX_NUMBER_BOOK_REPEATED_CATEGORY + " books by category",Constant.STATUS.FAIL);
		Boolean add = validateMaxByCategory(book.getCategory());
		
		if(add){
			oper = add(book);
		}
		
		return oper;	
	}
	
	public Operation addItem(Integer id) {
		Operation oper = new Operation(OPERATIONS.GET,"Add Item",STATUS.SUCCESSFUL);
		Book b = get(id,null);
		
		if(items == null){
			items = new ArrayList<Item>();
		}
		
		items.add(new Item(b, 1, "Book [" + b.getName() + " " + b.getAuthor() + "]", b.getPrice()));
		
		return oper;
	}
	
	public Operation getGift() {
		Operation oper = new Operation(OPERATIONS.GET,"Gift",STATUS.SUCCESSFUL);
		Item item = give(getAmountTotal());
		oper.setDescription(item.getDescription());
		oper.setAmount(item.getAmount());
		
		return oper;
	}
	
	public List<Item> getAllItem(){
		List<Item> filterAllItem = new ArrayList<Item>();  
		if(items!=null){
			for(Item i : items){
				if(filterAllItem.contains(i)){
					Item[] its = getItemArrays(filterAllItem);
					Arrays.sort(its,new ItemComparator());
					for(Item j : its){
						if (j.getBook().getId().equals(i.getBook().getId())){
							j.setNumber(j.getNumber()+1);
							j.setAmount(j.getAmount() + i.getAmount());
						}
					}
				}else{
					filterAllItem.add(new Item(i.getBook(),1, i.getDescription(), i.getAmount()));
				}
			}
		}
		return filterAllItem;
	}
	
	public List<Item> getNumberBookByCategory(){
		List<Item> categoryList = new ArrayList<Item>();
		if(books!=null){
			String category = null;
			int count = 1;
			Book[] bks = getBooksArrays(books);
			Arrays.sort(bks,new BookComparator());
			for(Book b: bks){
				if(category == null){
					category = b.getCategory();
				}else if(!b.getCategory().equals(category)){
					categoryList.add(new Item(count, category,0D));
					category = b.getCategory();
					count= 1;
				}else{
					count+= 1;
				}
			}
			categoryList.add(new Item(count, category,0D));
		}
		
		return categoryList;
	}
	
	private Book[] getBooksArrays(List<Book> books){
		int ind = 0;
		Book[] bks = new Book[books.toArray().length];
		for(Object o: books.toArray()){
			bks[ind++] = (Book)o;
		}
		
		return bks;
	}
	
	private Item[] getItemArrays(List<Item> items){
		int ind = 0;
		Item[] bks = new Item[items.toArray().length];
		for(Object o: items.toArray()){
			bks[ind++] = (Item)o;
		}
		
		return bks;
	}
	
	public Double getAmountTotal(){
		Double amount = 0D;
		if(items != null){
			for(Item i: items){
				boolean accumulate = true;
				for(CATEGORY_DISCOUNT cat: Constant.CATEGORY_DISCOUNT.values()){
					if(cat.getCategory().equals(i.getBook().getCategory())){
						amount += i.calcDicountAmount();
						accumulate = false;
					}
				}
				if(accumulate){
					amount += i.getAmount();
				}
			}
		}
		
		return amount;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
