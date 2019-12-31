package filesprocessing.comparators;

import java.io.File;
import java.util.Comparator;

import filesprocessing.Orders;

/**
 * A Factory class that generates comparators corresponding
 * to the a given Order
 */
public class ComparatorFactory {

	/**
	 * @param order Order in the commands file
	 * @return The corresponding comparator used in the File Sorter class
	 */
	public static Comparator<File> getComparator(Orders order) {
		if(order == Orders.ABS)
			return new AbsFileComparator();
		if(order == Orders.TYPE)
			return new TypeFileComparator();
		if(order == Orders.SIZE)
			return new SizeFileComparator();
		return null;
	}
}
