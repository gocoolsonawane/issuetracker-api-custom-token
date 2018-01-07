package com.gocool.myissuetracker.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocool.myissuetracker.common.dto.ProjectDto;
import com.gocool.myissuetracker.common.model.Project;
import com.gocool.myissuetracker.common.model.User;
import com.gocool.myissuetracker.persistence.PersistenceException;
import com.gocool.myissuetracker.persistence.PersistenceService;

/**
 * ProjectService handle add, remove, view Project calls .
 * 
 * @author GO-COOL
 *
 */

@Service
public class ProjectService {

	@Autowired
	PersistenceService persistenceService;

	private static Logger logger = Logger.getLogger(ProjectService.class);

	/**
	 * @return list of project.
	 */
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<Project>();
		try {
			projects = persistenceService.getListByFilters(Project.class, null);
		} catch (PersistenceException e) {
			logger.error("Error while getting all project list " + e);
		}
		return projects;
	}

	/**
	 * save project object.
	 * 
	 * @param projectDto
	 * @return
	 */
	public Boolean saveProjectData(ProjectDto projectDto) {
		Project project = new Project();// construct project object from user
										// submitted data.
		project.setProjectName(projectDto.getProjectName());
		project.setDescription(projectDto.getDescription());
		project.setCreatedBy(getUserNameUserId(projectDto.getUserId()));
		project.setCreatedDate(System.currentTimeMillis());// current epoch time
		try {
			// save project object.
			Integer id = persistenceService.saveAndReturnId(project);
			if (id > 0) {
				return true;
			}
		} catch (PersistenceException e) {
			logger.error("Error while Saving Project Data" + e);
		}
		return false;
	}

	/**
	 * @param userId
	 * @return user name of particular id.
	 */

	private String getUserNameUserId(Integer userId) {
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
	 * delete project by id.
	 * 
	 * @param id
	 * @return
	 */
	public Boolean removeProjectData(Integer id) {
		try {
			persistenceService.delete(Project.class, id);
			return true;
		} catch (PersistenceException e) {
			logger.error("Error while Saving Project Data" + e);
		}
		return false;
	}

}
