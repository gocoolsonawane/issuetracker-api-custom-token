package com.gocool.myissuetracker.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gocool.myissuetracker.common.AppContstant.CustomResponse;
import com.gocool.myissuetracker.common.dto.ProjectDto;
import com.gocool.myissuetracker.common.dto.Response;
import com.gocool.myissuetracker.common.model.Project;
import com.gocool.myissuetracker.services.ProjectService;

/**
 * Handle Project requests.
 * 
 * @author GO-COOL
 *
 */

@RestController
@RequestMapping("/api")
public class ProjectEndPoint {

	@Autowired
	ProjectService projectService;

	private static Logger logger = Logger.getLogger(ProjectEndPoint.class);

	/**
	 * get all project list.
	 * 
	 * @return custom response object
	 */
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public Response getProjects(Authentication authentication) {
		System.out.println(authentication.getDetails());
		Response response = new Response();
		try {
			List<Project> projects = projectService.getAllProjects();
			if (!projects.isEmpty()) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
				response.setData(transformProjectToPojectDto(projects));
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage(CustomResponse.ERROR.getMessage());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("get all project Failed ", e);
		}
		return response;
	}

	/**
	 * add new project
	 * 
	 * @param projectDto
	 * @return custom response object
	 */
	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public Response saveProject(@RequestBody ProjectDto projectDto) {
		Response response = new Response();
		try {
			Boolean isSave = projectService.saveProjectData(projectDto);
			if (isSave) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage(CustomResponse.ERROR.getMessage());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("saving project Failed ", e);
		}
		return response;
	}

	/**
	 * remove project from database.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
	public Response deleteProject(@PathVariable Integer id) {
		Response response = new Response();
		try {
			Boolean isDelete = projectService.removeProjectData(id);
			if (isDelete) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage(CustomResponse.ERROR.getMessage());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("error while project delete ", e);
		}
		return response;
	}

	/***
	 * transform project model data to dto.
	 * 
	 * @param projects
	 * @return
	 */
	private Object transformProjectToPojectDto(List<Project> projects) {
		List<ProjectDto> projectList = new ArrayList<>();
		for (Project project : projects) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(project.getId());
			projectDto.setProjectName(project.getProjectName());
			projectDto.setCreatedBy(project.getCreatedBy());
			projectDto.setCreatedDate(project.getCreatedDate());
			projectDto.setDescription(project.getDescription());
			projectList.add(projectDto);
		}
		return projectList;
	}

}
