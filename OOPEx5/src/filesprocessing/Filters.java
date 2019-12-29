package filesprocessing;

public enum Filters {
	GREATER_THAN, BETWEEN, SMALLER_THAN, FILE, 
	CONTAINS, PREFIX, SUFFIX, WRITABLE, EXECUTABLE,
	HIDDEN, ALL, NO_FILTER;
	
	public static final String BAD_FILTER = "BAD_FILTER";
	
	/**
	 * 
	 * @param name The name of the filter as it should appear in the command file
	 * @return The corresponding filter in the enum, NO_FILTER is returned if the given
	 * filter name isn't valid
	 */
	public static Filters getFilter(String name) {
		switch(name) {
		case "greater_than":
			return Filters.GREATER_THAN;
		case "between":
			return Filters.BETWEEN;
		case "smaller_than":
			return Filters.SMALLER_THAN;
		case "file":
			return Filters.FILE;
		case "contains":
			return Filters.CONTAINS;
		case "prefix":
			return Filters.PREFIX;
		case "suffix":
			return Filters.SUFFIX;
		case "writable":
			return Filters.WRITABLE;
		case "executable":
			return Filters.EXECUTABLE;
		case "hidden":
			return Filters.HIDDEN;
		case "all":
			return Filters.ALL;
		default:
			return NO_FILTER;
		}
	}
}
