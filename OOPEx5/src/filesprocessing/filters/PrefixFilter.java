package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A Prefix filter class, returns true when a file's name starts
 * with the given prefix
 */
public class PrefixFilter implements Predicate<File> {
	
	/**
	 * Prefix value used in the test method
	 */
	private String value;
	
	/**
	 * PrefixFilter constructor
	 * @param value Prefix value used in the test method
	 */
	public PrefixFilter(String value) {
		this.value = value;
	}

	@Override
	public boolean test(File f1) {
		return f1.getName().startsWith(value);
	}
	
	

}
