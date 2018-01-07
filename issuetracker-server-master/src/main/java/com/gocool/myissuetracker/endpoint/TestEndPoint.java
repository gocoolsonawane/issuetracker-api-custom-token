package com.gocool.myissuetracker.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gocool.myissuetracker.common.AppContstant.CustomResponse;
import com.gocool.myissuetracker.common.dto.Response;
import com.gocool.myissuetracker.common.model.Issue;
import com.gocool.myissuetracker.common.model.Test;
import com.gocool.myissuetracker.services.TestService;

/**
 *  Test API Rest Controller.
 * @author GO-COOL
 *
 */
@RestController
@RequestMapping("/api")
public class TestEndPoint {

	@Autowired
	TestService testService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Response getTest(){
		Test test=testService.getTestObjectById();
		Response response=new Response();
		response.setCode(CustomResponse.SUCCESS.getCode());
		response.setMessage(CustomResponse.SUCCESS.getMessage());
		response.setData(test.getName());
		return response;
	}
	
	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public Response getDBTest(@PathVariable Integer id){
		Issue test=testService.getTestDBObjectById(id);
		Response response=new Response();
		response.setCode(CustomResponse.SUCCESS.getCode());
		response.setMessage(CustomResponse.SUCCESS.getMessage());
		response.setData(test.getProject().getProjectName());
		return response;
	}
}