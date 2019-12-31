package filesprocessing.errors.typetwo;

/**
 * The basic type for the type two exception, all exceptions that prevent the programs 
 * work flow, and must be handled
 */
public class TypeTwoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * A constant for the message text of any exception class derived
	 * from this class
	 */
	public static final String PRINT_ERROR_MESSAGE = "Type II error: ";
	
	/**
	 * Every TypeTwoException print should start with this message
	 */
	public static final String ERROR_MESSAGE = "ERROR: ";
	
	/**
	 * Default error message used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = 
			"A Type II error has occured that prevents the program from continuing it's workflow";
	
	/**
	 * Default constructor, used the DEFAULT_ERROR_MESSAGE field as it's message
	 */
	public TypeTwoException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The message displayed when the exception is handled (or not)
	 */
	public TypeTwoException(String message) {
		super(message);
	}

}
