package com.gocool.myissuetracker.persistence;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * A class implementing PersistenceService. It acts as a Persistence layer for
 * interacting with MySQL database.
 * 
 * @author GO-COOL
 *
 */

@Transactional
public class MySQLPersistenceService implements PersistenceService {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/***
	 * return list based on provided map filter.
	 */
	@Override
	public <T> List<T> getListByFilters(Class<T> clazz, Map<String, Object> mapFilters) throws PersistenceException {
		try {
			Criteria criteria = getSession().createCriteria(clazz);
			if (mapFilters != null) {
				Set<String> keys = mapFilters.keySet();
				for (String key : keys) {
					criteria.add(Restrictions.eq(key, mapFilters.get(key)));
				}
			}
			@SuppressWarnings("unchecked")
			List<T> list = criteria.list();
			getSession().flush();
			return list;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	/**
	 * get object based on id.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjectById(Class<T> clazz, Integer id) throws PersistenceException {
		try {
			if (id != null)
				return (T) getSession().get(clazz, id);
			getSession().flush();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return null;
	}
	
	/**
	 * update entity object. 
	 */
	@Override
	public <T> void update(T object) throws PersistenceException {
		getSession().update(object);
	}

	@Override
	public <T> T getObjectByFilters(Class<T> clazz, Map<String, Object> mapFilters) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * save entity object. 
	 */
	@Override
	public <T> int saveAndReturnId(T object) throws PersistenceException {
		Integer id = (Integer) getSession().save(object);
		return id;
	}

	@Override
	public <T> List<T> getListByInBetween(Class<T> clazz, Object start, Object end, String column)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  delete given entity by provided id.
	 */
	@Override
	public <T> void delete(Class<T> clazz, Integer id) throws PersistenceException {
		String hql = "delete " + clazz.getName() + " where id = :id";
		Query q = getSession().createQuery(hql).setParameter("id", id);
		q.executeUpdate();

	}

}
