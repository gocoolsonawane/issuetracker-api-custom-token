package com.gocool.myissuetracker.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gocool.myissuetracker.common.AppContstant.CustomResponse;
import com.gocool.myissuetracker.common.dto.Response;
import com.gocool.myissuetracker.common.dto.UserDto;
import com.gocool.myissuetracker.common.model.User;
import com.gocool.myissuetracker.services.UserService;

/**
 * Handle user Authentication requests.
 * 
 * @author GO-COOL
 *
 */

@RestController
@RequestMapping("/api")
public class UserEndPoint {

	@Autowired
	UserService userService;

	private static Logger logger = Logger.getLogger(UserEndPoint.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(@RequestBody User user) {
		Response response = new Response();
		try {
			// Authenticate the user using the credentials provided
			User loginUser = userService.authenticate(user.getEmail(), user.getPassword());
			if (loginUser != null) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
				response.setData(transformUserToUserDto(loginUser));
				logger.info("Login success for user : " + user.getEmail());
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage("Invalid credentials");
				logger.info("Login fail for user : " + user.getEmail());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("Login Failed ", e);
		}
		return response;
	}

	/**
	 * Contract user dto response object from user.
	 * 
	 * @param loginUser
	 * @return
	 */
	private UserDto transformUserToUserDto(User loginUser) {
		UserDto userDto = new UserDto();
		userDto.setId(loginUser.getId());
		userDto.setName(loginUser.getName());
		userDto.setEmail(loginUser.getEmail());
		userDto.setMobile(loginUser.getMobile());
		userDto.setRole(loginUser.getRole());
		userDto.setToken(loginUser.getToken());
		return userDto;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Response getAllUser() {
		Response response = new Response();
		try {
			// get all user list 
			List<User> users = userService.getUserList();
			if (users.size()>0) {
				response.setCode(CustomResponse.SUCCESS.getCode());
				response.setMessage(CustomResponse.SUCCESS.getMessage());
				response.setData(transformUserListToUserDtoList(users));
			} else {
				response.setCode(CustomResponse.ERROR.getCode());
				response.setMessage(CustomResponse.ERROR.getMessage());
			}
		} catch (Exception e) {
			response.setCode(CustomResponse.ERROR.getCode());
			response.setMessage(CustomResponse.ERROR.getMessage());
			logger.error("Failed  to get user list", e);
		}
		return response;
	}

	/**
	 * convert user list to user dto list 
	 * @param users
	 * @return
	 */
	private List<UserDto> transformUserListToUserDtoList(List<User> users) {
		List<UserDto> userDtolist=new ArrayList<>();
		//call transformUserToUserDto function 
		for (User user : users) {
			userDtolist.add(transformUserToUserDto(user));
		}
		return userDtolist;
	}
}
