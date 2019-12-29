package filesprocessing.errors.typetwo;

/**
 * A BadFormatException type, thrown whenever the format of the commands file isn't valid
 * (e.g no ORDEER sub section)
 */
public class FormatException extends TypeTwoException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message, used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = 
			"A BadFormatException has occured, the commands file has a bad format"
			+ "(e.g no ORDER sub section)";
	
	/**
	 * The default constructor uses the DEFAULT_ERROR_MESSAGE field as it's message
	 */
	public FormatException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	
	/**
	 * @param message The message displayed when this exception is handled (or not)
	 */
	public FormatException(String message) {
		super(message);
	}

}
