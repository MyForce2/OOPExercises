package filesprocessing;

import java.util.ArrayList;

import filesprocessing.errors.typeone.FilterOrderNameException;
import filesprocessing.errors.typeone.ParametersException;

public class SectionParser {
	
	private ArrayList<Section> sections;
	
	private static final String GREATER_THAN_FILTER = "greater_than";
	private static final String BETWEEN_FILTER = "between";
	private static final String SMALLER_THAN_FILTER = "smaller_than";
	private static final String FILE_FILTER = "file";
	private static final String CONTAINS_FILTER = "contains";
	private static final String PREFIX_FILTER = "prefix";
	private static final String SUFFIX_FILTER = "suffix";
	private static final String WRITABLE_FILTER = "writable";
	private static final String EXECUTABLE_FILTER = "executable";
	private static final String HIDDEN_FILTER = "hidden";
	private static final String ALL_FILTER = "all";
	
	public SectionParser(ArrayList<Section> sections) {
		this.sections = sections;
	}
	
	public static void parseSection(Section section) throws FilterOrderNameException, ParametersException {
		String filter = section.getFilterText();
		String order = section.getOrderText();
	}
	
	public static String parseFilter(String filter) {
		
		return "";
	}
}
