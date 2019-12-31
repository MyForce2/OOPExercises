package filesprocessing.filters;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

import filesprocessing.Filters;
import filesprocessing.SectionParser;

/**
 * A FilterFactory class, generates filters objects for a given filter from
 * the Filters enum and an ArrayList<Strings> of parameters
 */
public class FilterFactory {
	
	/**
	 * @return An instance of the AllFilter
	 */
	private static Predicate<File> getAllFilter() {
		return new AllFilter();
	}
	
	/**
	 * 
	 * @param filter Filter type 
	 * @param value The value used in the filters object constructor
	 * @return The filter object initialized with the given value
	 */
	private static Predicate<File> getOneDoubleFilter(Filters filter, double value) {
		if(filter == Filters.GREATER_THAN)
			return new GreaterThanFilter(value);
		if(filter == Filters.SMALLER_THAN)
			return new SmallerThanFilter(value);
		return null;
	}
	/**
	 * @param filter Filter type
	 * @param v1 The value used in the filters object constructor
	 * @param v2 The second value used in the filters object constructor
	 * @return The filter object initialized with the given value
	 */
	private static Predicate<File> getTwoDoubleFilter(Filters filter, double v1, double v2) {
		if(filter == Filters.BETWEEN)
			return new BetweenFilter(v1, v2);
		return null;
	}
	
	/**
	 * @param filter Filter type 
	 * @param value the value used in the filters object constructor
	 * @return The filter object initialized with the given value
	 */
	private static Predicate<File> getOneStringFilter(Filters filter, String value) {
		if(filter == Filters.FILE)
			return new FileFilter(value);
		if(filter == Filters.CONTAINS)
			return new ContainsFilter(value);
		if(filter == Filters.PREFIX)
			return new PrefixFilter(value);
		if(filter == Filters.SUFFIX)
			return new SuffixFilter(value);
		return null;
	}
	
	/**
	 * @param filter Filter type 
	 * @param value the value used in the filters object constructor
	 * @return The filter object initialized with the given value
	 */
	private static Predicate<File> getOneBooleanFilter(Filters filter, boolean value) {
		if(filter == Filters.WRITABLE)
			return new WritableFilter(value);
		if(filter == Filters.EXECUTABLE)
			return new ExecutableFilter(value);
		if(filter == Filters.HIDDEN)
			return new HiddenFilter(value);
		return null;
	}
	
	/**
	 * @param filter Filter type
	 * @param params An ArrayList of parameters in their string format
	 * @return The filter object initialized with the given values
	 */
	public static Predicate<File> getFilter(Filters filter, ArrayList<String> params) {
		if(filter == Filters.WRITABLE || 
				filter == Filters.EXECUTABLE || filter == Filters.HIDDEN)
			return getOneBooleanFilter(filter, 
					params.get(0).equals(SectionParser.TRUE_TOKEN_IN_FILTER));
		if(filter == Filters.FILE || filter == Filters.CONTAINS ||
				filter == Filters.PREFIX || filter == Filters.SUFFIX) 
			return getOneStringFilter(filter, params.get(0));
		if(filter == Filters.SMALLER_THAN || filter == Filters.GREATER_THAN) 
			return getOneDoubleFilter(filter, Double.valueOf(params.get(0)));
		if(filter == Filters.BETWEEN)
			return getTwoDoubleFilter(filter, 
					Double.valueOf(params.get(0)), Double.valueOf(params.get(1)));
		return getAllFilter();
	}

}
