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
	private static final String REGEX_SPLIT = ".";
	
	/**
	 * The type of a file "without" a type, a basic file
	 */
	private static final String NO_TYPE = "";


	@Override
	public int compare(File f1, File f2) {
		String type1 = fileType(f1), type2 = fileType(f2);
		int result = type1.compareTo(type2);
		if(result == 0) {
			// In the case file types are equal, compare the files using the AbsFileComparator
			Comparator<File> cmp = new AbsFileComparator();
			return cmp.compare(f1, f2);
		}
		return result;
	}
	
	/**
	 * @param file The file whose type is returned
	 * @return The file type of the given file, the NO_TYPE field if no type is specified
	 */
	private static String fileType(File file) {
		String name = file.getName();
		if(name.contains("."))
			return NO_TYPE;
		if(name.startsWith(".")) {
			String nonHiddenName = name.substring(1);
			if(!nonHiddenName.contains("."))
				return NO_TYPE;
		}
		String[] arr = name.split(REGEX_SPLIT);
		return arr[arr.length - 1];
	}
		
	
}
