package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A Smaller than filter class, returns true when a file's size
 * in KB is smaller than the given size
 */
public class SmallerThanFilter implements Predicate<File> {

	/**
	 * Size in KB used in test method
	 */
	private double value;
	
	/**
	 * The amount of bytes in a single kilo byte
	 */
	public static final short KB_SIZE = 1024;
	
	/**
	 * SmallerThanFilter constructor
	 * @param value Maximal value in KB for a file to return true
	 * using this filter
	 */
	public SmallerThanFilter(double value) {
		this.value = value;
	}
	
	@Override
	public boolean test(File f1) {
		return f1.length() < value * KB_SIZE;
	}
	

}
