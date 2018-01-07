package com.gocool.myissuetracker.common.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Project Model with basic info.
 * 
 * @author GO-COOL
 *
 */
@Entity
@Table(name = "projects", catalog = "myissuetracker_db")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "project_name", nullable = false)
	private String projectName;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	private Long createdDate;

	@OneToMany(mappedBy = "project")
	private Set<Issue> issues;

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

	public Set<Issue> getIssues() {
		return issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", description=" + description + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + "]";
	}
}
