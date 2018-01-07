package com.goccol.myissuetracker.persistence.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gocool.myissuetracker.common.model.Issue;
import com.gocool.myissuetracker.common.model.Project;
import com.gocool.myissuetracker.common.model.User;
import com.gocool.myissuetracker.config.HibernateConfiguration;
import com.gocool.myissuetracker.persistence.PersistenceException;
import com.gocool.myissuetracker.persistence.PersistenceService;

/***
 *  Unit test cases for database related operations.
 * @author GO-COOL
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfiguration.class })
public class MysqlPersistenceTest {

	@Autowired
	PersistenceService persistenceService;

	/**
	 * User related test cases.
	 * @throws PersistenceException
	 */
	//@Test
	public void userTest() throws PersistenceException {
		User user = persistenceService.getObjectById(User.class, 1);
		System.out.println(user);
	}

	/**
	 * Project related test case.
	 * @throws PersistenceException
	 */
	//@Test
	public void projectTest() throws PersistenceException {
		Project project = persistenceService.getObjectById(Project.class, 1);
		System.out.println(project);
		
		for (Issue issue : project.getIssues()) {
			System.out.println(issue.getIssueName());
		}
	}

	/**
	 * Issue related test case.
	 * @throws PersistenceException
	 */
	//@Test
	public void issueTest() throws PersistenceException {
		Map<String, Object> mapFilters=new HashMap<>();
		mapFilters.put("project.id", 2);
		List<Issue> issues = persistenceService.getListByFilters(Issue.class, mapFilters);
		for (Issue issue : issues) {
			System.out.println(issue);
		}
	}
	
	@Test
	public void myTest() throws PersistenceException{
		com.gocool.myissuetracker.common.model.Test test = new com.gocool.myissuetracker.common.model.Test();
		test.setName("gocool");
		persistenceService.saveAndReturnId(test);
		test.setName("gggg");
	}
}
