package com.gocool.myissuetracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocool.myissuetracker.common.model.Issue;
import com.gocool.myissuetracker.common.model.Test;
import com.gocool.myissuetracker.persistence.PersistenceException;
import com.gocool.myissuetracker.persistence.PersistenceService;


/**
 * Test Service
 * @author GO-COOL
 *
 */
@Service
public class TestService {

	@Autowired
	PersistenceService persistenceService;
	
	public Test getTestObjectById(){
		try {
			return persistenceService.getObjectById(Test.class, 2);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Issue getTestDBObjectById(Integer id) {
		try {
			return persistenceService.getObjectById(Issue.class, id);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return null;
	}
}