package filesprocessing.errors.typeone;

/**
 * A FilterOrderNameException type, thrown whenever a 
 * bad FILTER / ORDER name is given
 * in any of the commands file sections
 */
public class FilterOrderNameException extends TypeOneException {


	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = 
			"A FilterOrderException has occured, meaning a "
			+ "bad (invalid) filter / order name was specified";
	
	/**
	 * The default constructor uses the DEFAULT_ERROR_MESSAGE field as it's message
	 */
	public FilterOrderNameException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The message displayed when this exception is caught & handled
	 */
	public FilterOrderNameException(String message) {
		super(message);
	}

}
