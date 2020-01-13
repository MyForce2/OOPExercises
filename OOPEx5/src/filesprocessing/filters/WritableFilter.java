package filesprocessing.filters;

import java.io.File;
import java.nio.file.Files;
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
	private boolean writable;
	
	/**
	 * WriteableFilter constructor
	 * @param writable Value used in test method
	 */
	public WritableFilter(boolean writable) {
		this.writable = writable;
	}

	@Override
	public boolean test(File f1) {
		return Files.isWritable(f1.toPath()) == writable;
	}
	
	
}
