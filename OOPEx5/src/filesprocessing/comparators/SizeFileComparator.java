package filesprocessing.comparators;

import java.io.File;
import java.util.Comparator;

/**
 * A SizeFileComparator, compares files by their size in bytes, if the two files
 * have the same size, compares them using AbsFileComparator
 * Used when the order specified in the ORDER section is size
 */
public class SizeFileComparator implements Comparator<File>{

	@Override
	public int compare(File f1, File f2) {
		long size1 = f1.length();
		long size2 = f2.length();
		int result = Long.compare(size1, size2);
		if(result == 0) {
			// In the case file sizes are equal, compare the files using the AbsFileComparator 
			Comparator<File> cmp = new AbsFileComparator();
			return cmp.compare(f1, f2);
		}
		return result;
	}
	
}
