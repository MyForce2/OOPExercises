package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A Writable filter class, returns true if a given file
 * file.canWrite() == writable value given
 * @author Nadav
 *
 */
public class WritableFilter implements Predicate<File> {

	/**
	 * Writable value used in filter
	 */
	private boolean writeable;
	
	/**
	 * WriteableFIlter constructor
	 * @param writable Value used in test method
	 */
	public WritableFilter(boolean writable) {
		this.writeable = writable;
	}

	@Override
	public boolean test(File f1) {
		return f1.canWrite() == writeable;
	}
	
	
}
