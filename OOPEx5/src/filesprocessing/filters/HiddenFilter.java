package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * 	A HiddenFilter class, returns true when a file's
 * file.isHidden() == hidden value
 */
public class HiddenFilter implements Predicate<File> {
	
	/**
	 * Value used in the test function
	 */
	private boolean hidden;
	
	/**
	 * HiddenFilter constructor
	 * @param hidden Value used in the test function
	 */
	public HiddenFilter(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean test(File f1) {
		return f1.isHidden() == hidden;
	}
	
	

}
