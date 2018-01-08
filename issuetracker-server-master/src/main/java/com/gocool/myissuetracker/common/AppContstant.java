package com.gocool.myissuetracker.common;

/**
 * Constants used in whole application declare here.
 * 
 * @author GO-COOL
 *
 */
public interface AppContstant {

	public enum CustomResponse {
		SUCCESS(0, "Success"), ERROR(-1, "Error"), INVALID_FORMAT(-1, "Invalid request format"), UNAUTHORIZED(401,
				"Unauthorized"), FORBIDDEN(403, "Forbidden"), INTERNAL_SERVER_ERROR(500, "Internal Server Error");

		private Integer code;
		private String message;

		private CustomResponse(Integer code, String message) {
			this.code = code;
			this.message = message;
		}

		public Integer getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}

	public interface IssueType {
		String BUG = "BUG";
		String QUESTION = "QUESTION";
		String ENHANCEMENT = "ENHANCEMENT";
	}

	public interface Severity {
		String CRITICAL = "CRITICAL";
		String MAJOR = "MAJOR";
		String MODERATE = "MODERATE";
		String MINOR = "MINOR";
		String WISHLIST = "WISHLIST";
	}

	public interface Priority {
		String LOW = "LOW";
		String NORMAL = "NORMAL";
		String HIGH = "HIGH";
	}

	public interface Status {
		String NEW = "NEW";
		String IN_PROGRESS = "IN-PROGRESS";
		String READY_TO_TEST = "READY-TO-TEST";
		String CLOSED = "CLOSED";
		String NEED_INFO = "NEED-INFO";
		String REJECTED = "REJECTED";
		String POSTPONED = "POSTPONED";

	}
}