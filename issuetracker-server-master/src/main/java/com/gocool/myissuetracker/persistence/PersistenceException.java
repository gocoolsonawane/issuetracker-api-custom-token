package com.gocool.myissuetracker.persistence;

/**
 * Exception class for all database related operations
 * 
 * @author GoCool
 *
 */

public class PersistenceException extends Exception {

	private static final long serialVersionUID = -6955476146157312459L;

	public PersistenceException() {
		super();
	}

	PersistenceException(String message) {
		super(message);
	}

	PersistenceException(Throwable th) {
		super(th);
	}
}
