package com.gocool.myissuetracker.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocool.myissuetracker.common.dto.IssueDto;
import com.gocool.myissuetracker.common.model.Issue;
import com.gocool.myissuetracker.common.model.Project;
import com.gocool.myissuetracker.common.model.User;
import com.gocool.myissuetracker.persistence.PersistenceException;
import com.gocool.myissuetracker.persistence.PersistenceService;

/**
 * IssueService handle add,view,update issue calls .
 * 
 * @author GO-COOL
 *
 */

@Service
public class IssueService {

	@Autowired
	PersistenceService persistenceService;

	private static Logger logger = Logger.getLogger(ProjectService.class);

	/**
	 * save issue object.
	 * 
	 * @param issue
	 * @param projectId
	 * @return
	 */
	public Boolean saveIssueObject(Issue issue, Integer projectId) {
		try {
			Project project = persistenceService.getObjectById(Project.class, projectId);
			issue.setProject(project);
			// convert user id to integer get user name from id.
			issue.setCreatedBy(getUserNameUserId(Integer.parseInt(issue.getCreatedBy())));
			Integer id = persistenceService.saveAndReturnId(issue);
			if (id > 0) {
				return true;
			}
		} catch (PersistenceException e) {
			logger.error("Error while Saving Project Data" + e);
		}
		return false;
	}

	/**
	 * get user name from id.
	 * @param userId
	 * @return
	 */
	private String getUserNameUserId(int userId) {
		String name = "";
		try {
			User user = persistenceService.getObjectById(User.class, userId);
			return user.getName();
		} catch (PersistenceException e) {
			logger.error("Error while getting user data Data" + e);
		}
		return name;
	}

	/**
	 * @param projectId
	 * @return list of issue with requested project id.
	 */
	public List<Issue> getAllIssueByProjectId(Integer projectId) {
		List<Issue> issues = new ArrayList<>();
		Map<String, Object> mapFilters = new HashMap<>();
		mapFilters.put("project.id", projectId);
		try {
			issues = persistenceService.getListByFilters(Issue.class, mapFilters);
		} catch (PersistenceException e) {
			logger.error("error while getting issue list by project " + e);
		}
		return issues;
	}

	/**
	 * @param issueId
	 * @return issue object with requested issueId.
	 */
	public Issue getIssueById(Integer issueId) {
		try {
			Issue issue = persistenceService.getObjectById(Issue.class, issueId);
			return issue;
		} catch (PersistenceException e) {
			logger.error("error while getting issue object " + e);
		}
		return null;
	}

	/**
	 * delete issue object by its id.
	 * 
	 * @param issueId
	 * @return delete flag
	 */
	public Boolean deleteIssueById(Integer issueId) {
		try {
			persistenceService.delete(Issue.class, issueId);
			return true;
		} catch (PersistenceException e) {
			logger.error("error while deleting issue object " + e);
		}
		return false;
	}

	/**
	 * update issue object
	 * 
	 * @param issueDto
	 * @return
	 */
	public Boolean updateIssueObject(IssueDto issueDto) {
		Issue issue = getIssueById(issueDto.getId());// get issue object by id.
		// set dto property to issue object
		issue.setStatus(issueDto.getStatus());
		issue.setLastModifyDate(System.currentTimeMillis());
		issue.setAssignTo(issueDto.getAssignTo());
		issue.setIssueType(issueDto.getIssueType());
		issue.setSeverity(issueDto.getSeverity());
		issue.setPriority(issueDto.getPriority());
		try {
			//update issue
			persistenceService.update(issue);
			return true;
		} catch (PersistenceException e) {
			logger.error("error while updating issue object " + e);
		}
		return false;
	}
}
