package oop.ex6.main.errors;

/**
 * A SJavaFormatException, thrown whenever there is an error with the format of the
 * file or the code, no logical errors
 */
public class SJavaFormatException extends SJavaException {


	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message
	 */
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaFormatException has occured";
	
	/**
	 * A default constructor that uses the default error message field as it's message
	 */
	public SJavaFormatException() {
		super(SJavaFormatException.DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The displayed message whenever this exception is handled
	 */
	public SJavaFormatException(String message) {
		super(message);
	}

}
