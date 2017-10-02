package com.baufest.josm2.persistence.dao.impl;

import org.springframework.stereotype.Component;

import com.baufest.josm2.persistence.GenericDaoImpl;
import com.baufest.josm2.persistence.dao.BookDao;
import com.baufest.josm2.model.Book;


@Component
public class BookDaoImpl extends GenericDaoImpl<Book, Long> 
implements BookDao{

}
