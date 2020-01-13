package filesprocessing.comparators;

import java.io.File;
import java.util.Comparator;

/**
 * A comparator used to sort the files by absolute file path (alphabetically)
 * when using the ORDER abs, also the default used when no other
 * order is specified in the ORDER section
 */
public class AbsFileComparator implements Comparator<File> {
	
	@Override
	public int compare(File f1, File f2) {
		String path1 = f1.getAbsolutePath();
		String path2 = f2.getAbsolutePath();
		return path1.compareTo(path2);
	}
	
}
