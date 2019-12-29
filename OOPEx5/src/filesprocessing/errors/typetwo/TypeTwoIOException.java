package filesprocessing.errors.typetwo;

/**
 * A TypeTwoIOException type, thrown whenever an 
 * IO exception regarding the commands file has occurred
 */
public class TypeTwoIOException extends TypeTwoException {


	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = 
			"A TypeTwoIO Exception has occured, an IO error occured while accessing the commands file";
	
	/**
	 * Default constructor, uses the DEFAULT_ERROR_MESSAGE field as it's message
	 */
	public TypeTwoIOException() {
		super(DEFAULT_ERROR_MESSAGE);
	}

	/**
	 * @param message The message displayed when this exception is handled (or not)
	 */
	public TypeTwoIOException(String message) {
		super(message);
	}

}
