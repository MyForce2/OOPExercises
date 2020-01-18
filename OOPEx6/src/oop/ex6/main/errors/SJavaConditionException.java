package oop.ex6.main.errors;

/**
 * A SJavaConditionException class, occurs whenever a an error involving
 * conditions inside if / while loops occur
 */
public class SJavaConditionException extends SJavaException {


	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message
	 */
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaConditionException has occured";
	
	/**
	 * The default constructor that uses the default error message field as it's message
	 */
	public SJavaConditionException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The displayed message when this is exception is handled
	 */
	public SJavaConditionException(String message) {
		super(message);
	}

}
