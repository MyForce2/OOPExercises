package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A GreaterThan filter class, returns true when a file's size in KB
 * is greater than the given value
 */
public class GreaterThanFilter implements Predicate<File> {

	/**
	 * Value used in test method
	 */
	private double value;
	
	/**
	 * GreaterThanFilter constructor
	 * @param value This filter returns true when a file's size is greater than
	 * this value in KB
	 */
	public GreaterThanFilter(double value) {
		this.value = value;
	}
	
	public boolean test(File f1) {
		return f1.length() > value * SmallerThanFilter.KB_SIZE;
	}

}
