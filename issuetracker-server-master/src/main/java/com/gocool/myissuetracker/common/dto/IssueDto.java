package com.gocool.myissuetracker.common.dto;

/**
 * Issue info sent through issue dto.
 * 
 * @author GO-COOL
 *
 */
public class IssueDto {

	private Integer id;
	private String projectName;
	private String issueName;
	private String createdBy;
	private Long createdDate;
	private String assignTo;
	private Long assignDate;
	private Long lastModifyDate;
	private String status;
	private String issueType;
	private String severity;
	private String priority;
	private String description;

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

	@Override
	public String toString() {
		return "IssueDto [id=" + id + ", projectName=" + projectName + ", issueName=" + issueName + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", assignTo=" + assignTo + ", assignDate=" + assignDate
				+ ", lastModifyDate=" + lastModifyDate + ", status=" + status + ", issueType=" + issueType
				+ ", severity=" + severity + ", priority=" + priority + ", description=" + description + "]";
	}

	
}
