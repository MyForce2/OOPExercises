package filesprocessing.comparators;

import java.io.File;
import java.util.Comparator;

/**
 * A TypeFileComparator used to compare files by their type (alphabetically)
 * if the two files have the same file type, compares them using the AbsFileComparator
 * Used when the order in the ORDER section is type
 * @author nadav
 *
 */
public class TypeFileComparator implements Comparator<File> {
	
	/**
	 * A regular expression used to split an array of its name and type
	 * e.g:
	 * Name: File.txt
	 * Result: array[0] = "File", array[1] = "txt"
	 */
	private static final String REGEX_SPLIT = "[.]";
	
	/**
	 * The index of the file type after splitting the file path using
	 * the REGEX_SPLIT constant
	 */
	private static final byte FILE_TYPE_INDEX = 1;

	@Override
	public int compare(File f1, File f2) {
		String name1 = f1.getName();
		String name2 = f2.getName();
		String type1 = name1.split(REGEX_SPLIT)[FILE_TYPE_INDEX];
		String type2 = name2.split(REGEX_SPLIT)[FILE_TYPE_INDEX];
		int result = type1.compareTo(type2);
		if(result == 0) {
			// In the case file types are equal, compare the files using the AbsFileComparator
			Comparator<File> cmp = new AbsFileComparator();
			return cmp.compare(f1, f2);
		}
		return result;
	}
		
	
}
