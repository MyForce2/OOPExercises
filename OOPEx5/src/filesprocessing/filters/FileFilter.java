package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A FileFilter class which filters files if their name
 * doesn't correspond to the given value
 * @author Nadav
 *
 */
public class FileFilter implements Predicate<File> {

	/**
	 * Name of the file used in the test method
	 */
	private String name;
	
	/**
	 * FileFilter constructor
	 * @param name Name of the file used in the test method
	 */
	public FileFilter(String name) {
		this.name = name;
	}
	
	@Override
	public boolean test(File f1) {
		return f1.getName().equals(name);
	}

}
