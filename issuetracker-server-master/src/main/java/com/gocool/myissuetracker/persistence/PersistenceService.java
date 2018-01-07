package com.gocool.myissuetracker.persistence;

import java.util.List;
import java.util.Map;

/**
 * This class represents a service class for interacting with database. The
 * implementing class should provide implementation for all its functionalities.
 * 
 * @author GoCool
 *
 */

public interface PersistenceService {
	
	/**
	 * Get the list of objects based on the criteria passed as a parameter
	 * 
	 * @param clazz
	 *            Entity class
	 * @param mapFilters
	 *            Filters in map object
	 * @return List of objects returned from database based on filters passed
	 */
	public <T> List<T> getListByFilters(Class<T> clazz, Map<String, Object> mapFilters) throws PersistenceException;

	/**
	 * Get the object based on ID
	 * 
	 * @param clazz
	 *            Entity class
	 * @param id
	 * @return null if not object found with specified ID otherwise an object
	 * @throws PersistenceException
	 */
	public <T> T getObjectById(Class<T> clazz, Integer id) throws PersistenceException;

	/**
	 * Save the object in Database
	 * @param <T>
	 * 
	 * @param object
	 * @return void
	 * @throws PersistenceException
	 */
	
	public <T> void update(T object) throws PersistenceException;

	/**
	 * 
	 * @param clazz
	 * @param mapFilters
	 * @return Object
	 */
	public <T> T getObjectByFilters(Class<T> clazz, Map<String, Object> mapFilters) throws PersistenceException;

	/**
	 * 
	 * @param <T>
	 * @param object
	 * @return
	 * @throws PersistenceException
	 */
	public <T> int saveAndReturnId(T object) throws PersistenceException;

	/**
	 * 
	 * @param clazz
	 * @param start
	 * @param end
	 * @param column
	 * @return
	 * @throws PersistenceException
	 */
	public <T> List<T> getListByInBetween(Class<T> clazz, Object start, Object end, String column)
			throws PersistenceException;

	/**
	 * 
	 * @param <T>
	 * @param object
	 * @return
	 */
	public <T> void delete(Class<T> clazz,Integer id) throws PersistenceException;


}
