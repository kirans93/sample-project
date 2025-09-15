package com.nimblix.driverdashboard.exception;

public class SaveFailedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaveFailedException(String message) {
        super(message);
    }
}
