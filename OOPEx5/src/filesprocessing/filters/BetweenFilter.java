package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A filter class for the between filter, for two given doubles
 * returns true if the file size in kb is between those two values
 * (inclusive)
 */
public class BetweenFilter implements Predicate<File> {

	/**
	 * Minimal value for the filter
	 */
	private double min;
	
	/**
	 * Maximal value for the filter
	 */
	private double max;
	
	/**
	 * BetweenFilter constructor
	 * @param min Minimal file size in KB
	 * @param max Maximal file size in KB
	 */
	public BetweenFilter(double min, double max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean test(File f1) {
		return min * SmallerThanFilter.KB_SIZE <= f1.length() 
				&& f1.length() <= max * SmallerThanFilter.KB_SIZE;
	}
	
	
}
