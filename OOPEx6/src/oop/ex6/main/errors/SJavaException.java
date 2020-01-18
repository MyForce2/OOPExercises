package oop.ex6.main.errors;

/**
 * The base class for all SJavaExceptions, thrown whenever there is
 * an error with the parsed code
 */
public class SJavaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message
	 */
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaException has occured";
	
	/**
	 * The default constructor uses the default error message field as it's message
	 */
	public SJavaException() {
		super(SJavaException.DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * 
	 * @param message The displayed message whenever this exception is handled
	 */
	public SJavaException(String message) {
		super(message);
	}

}
