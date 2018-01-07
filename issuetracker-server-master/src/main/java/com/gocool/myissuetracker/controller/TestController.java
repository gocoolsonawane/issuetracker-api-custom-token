package com.gocool.myissuetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gocool.myissuetracker.common.model.Test;
import com.gocool.myissuetracker.services.TestService;

/**
 * Test Controller
 * @author GO-COOL
 *
 */
@Controller
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return new String("index");
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model) {
		Test test=testService.getTestObjectById();
		System.out.println(test);
		model.addAttribute("name", test.getName() );
		return new String("index");
	}
}