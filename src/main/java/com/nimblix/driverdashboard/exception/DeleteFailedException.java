package com.nimblix.driverdashboard.exception;

public class DeleteFailedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteFailedException(String message) {
        super(message);
    }
}
