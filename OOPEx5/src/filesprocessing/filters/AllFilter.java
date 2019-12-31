package filesprocessing.filters;

import java.io.File;
import java.util.function.Predicate;

/**
 * A filter class that filters files according to the all filter
 * meaning always returns true for any given file
 * @author Nadav
 *
 */
public class AllFilter implements Predicate<File>{

	@Override
	public boolean test(File f) {
		return true;
	}
	
}
