package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A filter class for the contains filter, returns true if the given String
 * is contained in the file's name (excluding path)
 * @author Nadav
 *
 */
public class ContainsFilter implements Predicate<File> {
	
	/**
	 * Contain value tested
	 */
	private String value;
	
	/**
	 * ContainsFilter constructor
	 * @param value The value used in the test method
	 */
	public ContainsFilter(String value) {
		this.value = value;
	}

	@Override
	public boolean test(File f1) {
		return f1.getName().contains(value);
	}

}
