package filesprocessing.errors.typetwo;

/**
 * A SubSectionException type, thrown when a sub section uses an invalid
 * sub section name (Not ORDER / FILTER).
 */
public class SubSectionException extends TypeTwoException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The default error message used in the default constructor
	 */
	private static final String DEFAULT_ERROR_MESSAGE = 
			"A SubSectionException has occured, valid sub section names are ORDER / FILTER";
	
	/**
	 * Default constructor, uses the DEFAULT_ERROR_MESSAGE field
	 * as it's message
	 */
	public SubSectionException() {
		super(DEFAULT_ERROR_MESSAGE);
	}
	
	/**
	 * @param message The message displayed when this exception is handled (or not)
	 */
	public SubSectionException(String message) {
		super(message);
	}

}
