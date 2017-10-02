package com.baufest.josm2.persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Data Access Object interface used for a single specified domain object.
 *  A single instance implementing this interface can be used only for the type of domain object specified in the type parameters.
 * 
 * @param <T>
 *            The type of the domain object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the domain object for which this instance is
 *            to be used.
 */
public interface GenericDao<T, ID extends Serializable> {

	/**
	 * Add the specified object as a new entry
	 * @param object
	 */
	public ID save(T object);
	
	/**
	 * creates an object if the id is null or zero otherwise updates the object
	 * @param object
	 * @return
	 */
	public void saveOrUpdate(T object);
	
	/**
	 * Remove the specified object from the datastore
	 * @param object
	 * @return
	 */
	public void delete(T object);
	
	/**
	 * Get the object with the specified id
	 * @param id
	 * @return
	 */
	public T getById(ID id);
	
	/**
	 * Retrieves all the objects of this type
	 * @return
	 */
	public List<T> listAll();
	
	/**
	 * Updates the object in the datastore with the properties of the specified object.
	 * The corresponding object is determined by id
	 * @param object
	 */
	public void update(T object);
	
	/**
	 * Executes a detached criteria
	 * @param criteria
	 * @return
	 */
	public List<T> findByCriteria(DetachedCriteria criteria);
	
	
	public void flush();
	
	public void merge(T object);
	
	public List<T> listAll(String orderColumn);	
	
	public String getTableName();
	
	public void evict(T object);
	
}
