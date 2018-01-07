package com.gocool.myissuetracker.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Project info sent through project dto.
 * 
 * @author GO-COOL
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class ProjectDto {

	private Integer id;
	private String projectName;
	private String description;
	private String createdBy;
	private Long createdDate;
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "ProjectDto [id=" + id + ", projectName=" + projectName + ", description=" + description + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + "]";
	}
}
