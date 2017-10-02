package com.baufest.josm2.web.service;

import java.util.List;

import com.baufest.josm2.web.entity.Operation;

public interface GenericService<T>  {
	public Operation add(T item);
	public Operation update(T item);
	public Operation delete(Integer id);
	public T get(Integer id, String option);
	public List<T> getAll();
}
