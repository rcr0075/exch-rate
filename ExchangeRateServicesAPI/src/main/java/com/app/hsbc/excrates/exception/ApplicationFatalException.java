/**
 * 
 */
package com.app.hsbc.excrates.exception;

/**
 * @author Rejin Chandran R
 *
 */
public class ApplicationFatalException extends Exception {

	private static final long serialVersionUID = 3636001305367375135L;
	


	/**
	 * 
	 */
	public ApplicationFatalException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ApplicationFatalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationFatalException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ApplicationFatalException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ApplicationFatalException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
