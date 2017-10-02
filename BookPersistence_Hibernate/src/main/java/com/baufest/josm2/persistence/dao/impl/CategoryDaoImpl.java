package com.baufest.josm2.persistence.dao.impl;

import org.springframework.stereotype.Component;

import com.baufest.josm2.persistence.GenericDaoImpl;
import com.baufest.josm2.persistence.dao.BookDao;
import com.baufest.josm2.persistence.dao.CategoryDao;
import com.baufest.josm2.model.Book;
import com.baufest.josm2.model.Category;


@Component
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> 
implements CategoryDao{

}
