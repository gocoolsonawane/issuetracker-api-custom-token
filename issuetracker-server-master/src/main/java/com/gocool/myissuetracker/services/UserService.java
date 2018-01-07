package com.gocool.myissuetracker.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gocool.myissuetracker.common.dto.UserDto;
import com.gocool.myissuetracker.common.model.User;
import com.gocool.myissuetracker.persistence.PersistenceException;
import com.gocool.myissuetracker.persistence.PersistenceService;

/**
 * AuthenticationService handle user authentication,password encryption.
 * 
 * @author GO-COOL
 *
 */
@Service
@org.springframework.transaction.annotation.Transactional
public class UserService {

	@Autowired
	PersistenceService persistenceService;

	private static Logger logger = Logger.getLogger(UserService.class);

	public User authenticate(String username, String password) throws Exception {
		try {
			Map<String, Object> filter = new HashMap<>();
			filter.put("email", username);
			filter.put("password", md5(password));
			List<User> userList = persistenceService.getListByFilters(User.class, filter);
			if (userList == null || userList.isEmpty()) {
				return null;
			} else {
				User user=userList.get(0);
				user=generateCustomeToken(user);
				return user;
			}
		} catch (Exception e) {
			logger.error("Authentication failed for username:" + username, e);
			throw new Exception(e);
		}
	}

	/**
	 * encrypt user password with md5 encryption algorithm.
	 * 
	 * @param string
	 *            password
	 * @return encrypt password
	 */
	public String md5(String input) {
		String md5 = null;

		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());
			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	/**
	 * get user all user list
	 * 
	 * @return
	 */
	public List<User> getUserList() {
		List<User> userList = new ArrayList<>();
		try {
			userList = persistenceService.getListByFilters(User.class, null);
			return userList;
		} catch (Exception e) {
			logger.error("failed to get user list");
		}
		return userList;
	}
	
	private User generateCustomeToken(User user) {
		user.setToken(UUID.randomUUID().toString());
		try {
			persistenceService.update(user);
		} catch (PersistenceException e) {
			logger.error("error while setting user token"+e);
		}
		return user;
	}
	
	public UserDto transformUserToUserDto(User loginUser) {
		UserDto userDto = new UserDto();
		userDto.setId(loginUser.getId());
		userDto.setName(loginUser.getName());
		userDto.setEmail(loginUser.getEmail());
		userDto.setMobile(loginUser.getMobile());
		userDto.setRole(loginUser.getRole());
		userDto.setToken(loginUser.getToken());
		return userDto;
	}
	
	
}
