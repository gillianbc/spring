package org.gillianbc.messenger.exception;

public class DataNotFoundException extends RuntimeException {
	
	public DataNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
