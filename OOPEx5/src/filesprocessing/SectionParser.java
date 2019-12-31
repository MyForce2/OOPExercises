package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import filesprocessing.comparators.ComparatorFactory;
import filesprocessing.comparators.FileSorter;
import filesprocessing.errors.typeone.FilterOrderNameException;
import filesprocessing.errors.typeone.ParametersException;
import filesprocessing.filters.FilterFactory;

public class SectionParser {
	
	/**
	 * The list of sections contained in the command file
	 */
	private ArrayList<Section> sections;
	
	/**
	 * List of files in the source directory
	 */
	private ArrayList<File> files;
	
	/**
	 * Textual representation of the all filter
	 */
	private static final String ALL_FILTER = "all";
	
	/**
	 * Textual representation of the abs order
	 */
	private static final String ABS_ORDER = "abs";
	
	/**
	 * Textual representation of the type order
	 */
	private static final String TYPE_ORDER = "type";
	
	/**
	 * Textual representation of the size order
	 */
	private static final String SIZE_ORDER = "size";
	
	/**
	 * A Regular Expression used to check if a given string param to any filter is valid
	 * str.match(REGEX_FILTER_STRING) should return true if the string is valid
	 */
	private static final String REGEX_FILTER_STRING = "[\\w\\s/.-]*";
	
	/**
	 * A hash symbol separates parameters and expression in the commands file
	 */
	private static final String HASH_SYMBOL = "#";
	
	/**
	 * Textual representation of the not modifier
	 */
	private static final String NOT_FILTER = "#NOT";
	
	/**
	 * Textual representation of the reverse modifier
	 */
	private static final String REVERSE_ORDER = "#REVERSE";
	
	/**
	 * Textual representation of the YES parameter, 
	 * true token for the YES / NO filters
	 */
	public static final String TRUE_TOKEN_IN_FILTER = "YES";
	
	/**
	 * Textual representation of the NO parameter,
	 * false token for the YES / NO filters
	 */
	public static final String FALSE_TOKEN_IN_FILTER = "NO";
	
	/**
	 * A SectionParser constructor
	 * @param sections An ArrayList of section objects, containing the sections 
	 * of the commands file
	 * @param files An ArrayList of the files in the source directory
	 */
	public SectionParser(ArrayList<Section> sections, ArrayList<File> files) {
		this.sections = sections;
		this.files = files;
	}
	
	/**
	 * Parses this commands file, section by section
	 */
	public void parse() {
		for(int i = 0; i < sections.size(); i++) {
			Section section = sections.get(i);
			String filter = "";
			ArrayList<String> params = null;
			boolean notFilter = section.getFilterText().endsWith(NOT_FILTER);
			boolean reverseOrder = section.getOrderText().endsWith(REVERSE_ORDER);
			String order = "";
			try {
				filter = parseFilter(section.getFilterText());
			} catch (FilterOrderNameException e) {
				filter = ALL_FILTER;
				//System.err.println(e.getMessage());
				System.err.println("Warning in line: " + (i * 4 + 2));
			} 
			if(!filter.equals(ALL_FILTER)) {
				try {
					params = parseFilterParams(section.getFilterText(), Filters.getFilter(filter));
				} catch (ParametersException e) {
					//System.err.println(e.getMessage());
					System.err.println("Warning in line " + (i * 4 + 2));
					filter = ALL_FILTER;
				}
			}
			try {
				order = parseOrder(section.getOrderText());
			} catch (FilterOrderNameException e) {
				//System.err.println(e.getMessage());
				System.err.println("Warning in line " + (i * 4 + 4));
				order = ABS_ORDER;
			}
			Predicate<File> filterFunc = FilterFactory.getFilter(Filters.getFilter(filter), params);
			filterFunc = notFilter ? filterFunc.negate() : filterFunc;
			Comparator<File> cmp = ComparatorFactory.getComparator(Orders.getOrder(order));
			cmp = reverseOrder ? cmp.reversed() : cmp;
			
			FileSorter sorter = new FileSorter(files.stream().filter(
					filterFunc).collect(Collectors.toList()), cmp);
			sorter.sort();
			File[] f = sorter.getFiles();
			System.err.flush();
			for(File k : f)
				System.out.println(k.getName());
			System.out.flush();
		}
		
		
	}
	
	/**
	 * Parses a given order text
	 * @param orderText The whole order line 
	 * @return The order command
	 * @throws FilterOrderNameException in the case there is a problem with the order name
	 */
	public static String parseOrder(String orderText) throws FilterOrderNameException {
		String[] content = orderText.split(HASH_SYMBOL);
		String order = content[0];
		int hashCount = content.length - 1;
		if(hashCount > 1)
			throw new FilterOrderNameException();
		if(hashCount == 1) {
			if(!orderText.endsWith(REVERSE_ORDER))
				throw new FilterOrderNameException();
		}
		if(order.equals(ABS_ORDER))
			return ABS_ORDER;
		if(order.equals(TYPE_ORDER))
			return TYPE_ORDER;
		if(order.equals(SIZE_ORDER))
			return SIZE_ORDER;
		throw new FilterOrderNameException();
	}
	
	/**
	 * Parses the parameters of the different filters
	 * @param filterText The text line of a filter sub section
	 * @param filter The filter contained in that line, that way
	 * the method knows what parameters to except
	 * @return An ArrayList of String containing the parameters for the given filter
	 * @throws ParametersException If a problem occurs, e.g Non valid parameters, not enough parameters
	 * or any other problem with the parameters a ParametersException is thrown
	 */
	public static ArrayList<String> parseFilterParams(String filterText, Filters filter) throws ParametersException {
		String[] content = filterText.split(HASH_SYMBOL);
		if(filter == Filters.BETWEEN) {
			try {
				Double d1 = Double.valueOf(content[1]);
				Double d2 = Double.valueOf(content[2]);
				if(d1 > d2)
					throw new ParametersException("Problem with the between parameters");
			} catch (NumberFormatException e) {
				throw new ParametersException("Problem with the between parameters");
			}
			return new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(content, 1, content.length)));
		}
		if(filter == Filters.GREATER_THAN || filter == Filters.SMALLER_THAN) {
			String param = filterText.split(HASH_SYMBOL)[1];
			try {
				Double d1 = Double.valueOf(param);
			} catch (NumberFormatException e) {
				throw new ParametersException("Problem with the " + content[0] + " parameter");
			}
			return new ArrayList<String>(Arrays.asList(param));
		}
		if(filter == Filters.WRITABLE || filter == Filters.HIDDEN || filter == Filters.EXECUTABLE) {
			String param = filterText.split(HASH_SYMBOL)[1];
			if(param.equals(TRUE_TOKEN_IN_FILTER) || 
					param.equals(FALSE_TOKEN_IN_FILTER)) 
				return new ArrayList<String>(Arrays.asList(param));
			throw new ParametersException("Problem with the " + content[0] + " parameter");
		}
		String param = filterText.split(HASH_SYMBOL)[1];
		if(param.matches(REGEX_FILTER_STRING) && !param.equals("")) 
			return new ArrayList<String>(Arrays.asList(param));
		throw new ParametersException("Problem with the " + content[0] + " parameter");
	}
	
	/**
	 * Parses a given filter text 
	 * @param filterText The whole filter line 
	 * @return Only the filter itself out of the whole line
	 * @throws FilterOrderNameException in the case there is a problem with the filter name
	 */
	public static String parseFilter(String filterText) throws FilterOrderNameException {
		if(filterText.equals(ALL_FILTER))
			return ALL_FILTER;
		String[] content = filterText.split(HASH_SYMBOL);
		int hashCount = content.length - 1;
		if(hashCount == 0) 
			throw new FilterOrderNameException();
		if(hashCount == 1) {
			String filter = content[0];
			if(filterText.endsWith(NOT_FILTER)) 
				return ALL_FILTER;
			return parseOneHashFilters(filterText);
		}
		if(hashCount == 2) {
			String filter = content[0];
			if(filterText.endsWith(NOT_FILTER))
				return parseOneHashFilters(filterText);
			return parseTwoHashFilters(filterText);
		}
		if(hashCount == 3) {
			String filter = content[0];
			if(filterText.endsWith(NOT_FILTER))
				return parseTwoHashFilters(filterText);
			throw new FilterOrderNameException();
		}
		throw new FilterOrderNameException();
	}
	
	private static String parseOneHashFilters(String filterText) throws FilterOrderNameException {
		String filter = filterText.split(HASH_SYMBOL)[0];
		Filters filterVal = Filters.getFilter(filter);
		if(filterVal == Filters.NO_FILTER || 
				filterVal == Filters.BETWEEN || filterVal == Filters.ALL)
			throw new FilterOrderNameException();
		return filter;
	}
	
	private static String parseTwoHashFilters(String filterText) throws FilterOrderNameException {
		String filter = filterText.split(HASH_SYMBOL)[0];
		Filters filterVal = Filters.getFilter(filter);
		if(filterVal != Filters.BETWEEN)
			throw new FilterOrderNameException();
		return filter;
	}

	

}
