package oop.ex6.main.errors;

/**
 * A SJavaMethodException class, thrown whenever an error involving 
 * a method occurs, calling a method with invalid params for any reason
 * or any error involving the method declaration
 * @author nadav
 *
 */
public class SJavaMethodException extends SJavaException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message
	 */
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaMethodException has occured";
	
	/**
	 * The default constructor, uses the default error message field as it's message
	 */
	public SJavaMethodException() {
		super(SJavaMethodException.DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The message displayed when this exception is handled
	 */
	public SJavaMethodException(String message) {
		super(message);
	}

}
