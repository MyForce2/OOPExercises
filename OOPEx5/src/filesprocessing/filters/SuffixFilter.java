package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A Suffix filter class, returns true when a given file's name has the
 * given String as it's suffix
 * @author Nadav
 *
 */
public class SuffixFilter implements Predicate<File> {

	/**
	 * Value used in test method
	 */
	private String value;
	
	/**
	 * SuffixFilter constructor
	 * @param value 
	 */
	public SuffixFilter(String value) {
		this.value = value;
	}

	@Override
	public boolean test(File f1) {
		return f1.getName().endsWith(value);
	}
	
	
}
