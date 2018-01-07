package com.gocool.myissuetracker.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Issue model with all details info.
 * 
 * @author GO-COOL
 *
 */
@Entity
@Table(name = "issues", catalog = "myissuetracker_db")
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "issue_name", nullable = false)
	private String issueName;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "created_date", nullable = false)
	private Long createdDate;

	@Column(name = "assign_to", nullable = false)
	private String assignTo;

	@Column(name = "assgin_date", nullable = false)
	private Long assignDate;

	@Column(name = "last_modify_date", nullable = false)
	private Long lastModifyDate;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "issue_type", nullable = false)
	private String issueType;

	@Column(name = "severity", nullable = false)
	private String severity;

	@Column(name = "priority", nullable = false)
	private String priority;

	@Column(name = "description", nullable = false)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
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

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public Long getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Long assignDate) {
		this.assignDate = assignDate;
	}

	public Long getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Long lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", issueName=" + issueName + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", assignTo=" + assignTo + ", assignDate=" + assignDate + ", lastModifyDate="
				+ lastModifyDate + ", status=" + status + ", issueType=" + issueType + ", severity=" + severity
				+ ", priority=" + priority + ", description=" + description + "]";
	}

}
