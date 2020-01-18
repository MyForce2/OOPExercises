package oop.ex6.main.errors;

/**
 * A SJavaAssigmentException, occurs whenever a variable is assigned to an invalid
 * variable / literal, or whenever an invalid declaration occurs
 */
public class SJavaAssigmentException extends SJavaException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Default error message
	 */
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaAssigmentException has occured";
	
	/**
	 * Default constructor that uses the default error message field as it's
	 * message
	 */
	public SJavaAssigmentException() {
		super(SJavaAssigmentException.DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The displayed message when this is exception is handled
	 */
	public SJavaAssigmentException(String message) {
		super(message);
	}

}
