package filesprocessing.errors.typetwo;

/**
 *	A InvalidUsageException, thrown whenever the program arguments are anything
 *  other than two program arguments, where the first it the Source Directory
 *  and the second is the Commands File
 */
public class InvalidUsageException extends TypeTwoException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = 
			"Invalid usage regarding the program input: Source directory | Commands file";
	
	/**
	 * The default constructor uses the DEFAULT_ERROR_MESSAGE field as it's message
	 */
	public InvalidUsageException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The message displayed when this exception is handled (or not)
	 */
	public InvalidUsageException(String message) {
		super(message);
	}

}
