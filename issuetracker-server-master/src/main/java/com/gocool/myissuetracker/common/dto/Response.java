package com.gocool.myissuetracker.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Custom Response DTO, this needs to be constructed and sent to client as a
 * response for every request
 * 
 * @author GO-COOL
 *
 */

@JsonInclude(value = Include.NON_NULL)
public class Response {

	private Integer code;
	private String message;
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
