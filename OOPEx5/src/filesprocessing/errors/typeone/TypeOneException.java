package filesprocessing.errors.typeone;

/**
 * A TypeOneException base class, all type one exceptions are derived from this class
 * meaning all derived classes should be handled the same way:
 * Printing a warning message (The exception message) and continuing normally
 */
public class TypeOneException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = 
			"A TypeOneException has occured, the "
			+ "program should print a warning message and continue";
	
	/**
	 * The default constructor uses the DEFAULT_ERROR_MESSAGE field as it's message
	 */
	public TypeOneException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The message displayed when this exception is caught & handled
	 */
	public TypeOneException(String message) {
		super(message);
	}
	
	
	
}
