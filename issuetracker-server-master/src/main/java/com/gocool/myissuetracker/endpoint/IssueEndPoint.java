package com.gocool.myissuetracker.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gocool.myissuetracker.common.AppContstant.CustomResponse;
import com.gocool.myissuetracker.common.dto.IssueDto;
import com.gocool.myissuetracker.common.dto.Response;
import com.gocool.myissuetracker.common.model.Issue;
import com.gocool.myissuetracker.services.IssueService;

/**
 * Handle issue related requests.
 * @author GO-COOL
 *
 */
@RestController
@RequestMapping("/api")
public class IssueEndPoint {

	@Autowired
	IssueService issueService;

	private static Logger logger = Logger.getLogger(IssueEndPoint.class);

	/**
	 *  get issue by project id.
	 * @param projectId
	 * @return custom response object
	 */
	
	@RequestMapping(value = "/{projectId}/issue", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('USER')")
	public Response getIssueByProjectId(@PathVariable Integer projectId) {
		Response response = new Response();
		try {
			List<Issue> issues = issueService.getAllIssueByProjectId(projectId);
			if (!issues.isEmpty()) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
				response.setData(transformIssueListToIssueDtoList(issues));
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage(CustomResponse.ERROR.getMessage());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("get all issue by Project Id Failed ", e);
		}
		return response;
	}

	/**
	 * get issue by issue id
	 * @param issueId
	 * @return custom response object
	 */
	@RequestMapping(value = "/issue/{issueId}", method = RequestMethod.GET)
	public Response getIssueById(@PathVariable Integer issueId) {
		Response response = new Response();
		try {
			Issue issue = issueService.getIssueById(issueId);
			if (issue != null) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
				response.setData(transformIssueToIssueDto(issue));
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage(CustomResponse.ERROR.getMessage());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("get issue by issue id failed ", e);
		}
		return response;
	}

	/**
	 * add new issue
	 * @param issue
	 * @param projectId
	 * @return custom response object
	 */
	@RequestMapping(value = "/{projectId}/issue", method = RequestMethod.POST)
	public Response saveIssue(@RequestBody Issue issue, @PathVariable Integer projectId) {
		Response response = new Response();
		try {
			Boolean isSave = issueService.saveIssueObject(issue, projectId);
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
			logger.error("error while saving issue", e);
		}
		return response;
	}
	
	
	@RequestMapping(value = "/issue/{issueId}", method = RequestMethod.DELETE)
	public Response deleteIssue(@PathVariable Integer issueId) {
		Response response = new Response();
		try {
			Boolean isDelete= issueService.deleteIssueById(issueId);
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
			logger.error("delete issue by issue id failed ", e);
		}
		return response;
	}
	
	/**
	 * update issue
	 * @param issue
	 * @return custom response object
	 */
	@RequestMapping(value = "/issue", method = RequestMethod.PUT)
	public Response updateIssue(@RequestBody IssueDto issueDto) {
		Response response = new Response();
		try {
			Boolean isUpdate = issueService.updateIssueObject(issueDto);
			if (isUpdate) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage(CustomResponse.ERROR.getMessage());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("error while updating issue", e);
		}
		return response;
	}
	

	/**
	 * 
	 * @param issue
	 * @return issue dto.
	 */
	private IssueDto transformIssueToIssueDto(Issue issue) {
		IssueDto issueDto=new IssueDto();
		issueDto.setId(issue.getId());
		issueDto.setProjectName(issue.getProject().getProjectName());
		issueDto.setIssueName(issue.getIssueName());
		issueDto.setAssignDate(issue.getAssignDate());
		issueDto.setAssignTo(issue.getAssignTo());
		issueDto.setCreatedBy(issue.getCreatedBy());
		issueDto.setCreatedDate(issue.getCreatedDate());
		issueDto.setDescription(issue.getDescription());
		issueDto.setIssueType(issue.getIssueType());
		issueDto.setLastModifyDate(issue.getLastModifyDate());
		issueDto.setPriority(issue.getPriority());
		issueDto.setSeverity(issue.getSeverity());
		issueDto.setStatus(issue.getStatus());
		return issueDto;
	}

	/**
	 * 
	 * @param issues
	 * @return list of issue dto.
	 */
	private List<IssueDto> transformIssueListToIssueDtoList(List<Issue> issues) {
		List<IssueDto> issueList=new ArrayList<>();
		for (Issue issue : issues) {
			issueList.add(transformIssueToIssueDto(issue));
		}
		return issueList;
	}

}
