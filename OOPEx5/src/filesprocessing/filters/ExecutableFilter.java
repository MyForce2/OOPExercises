package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A filter class for the executable filter, returns true
 * if the given file canExecute() == executable value
 * @author Nadav
 *
 */
public class ExecutableFilter implements Predicate<File> {
	
	/**
	 * Executable value used in test, YES = true, NO = false
	 */
	private boolean executable;
	
	/**
	 * Executable filter constructor
	 * @param executable the value used in the test function, compared
	 * to file.canExecute() value
	 */
	public ExecutableFilter(boolean executable) {
		this.executable = executable;
	}

	@Override
	public boolean test(File f1) {
		return f1.canExecute() == executable;
	}
	
	

}
