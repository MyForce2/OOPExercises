package filesprocessing.errors.typeone;

/**
 * A ParmetersException type, thrown whenever 
 * bad parameters are given to any filters
 */
public class ParametersException extends TypeOneException{

	private static final long serialVersionUID = 1L;

	/**
	 * The default error message used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = "";
	
	/**
	 * The default constructor uses the DEFAULT_ERROR_MESSAGE field as it's message
	 */
	public ParametersException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The message displayed when this exception is caught & handled
	 */
	public ParametersException(String message) {
		super(message);
	}
}
