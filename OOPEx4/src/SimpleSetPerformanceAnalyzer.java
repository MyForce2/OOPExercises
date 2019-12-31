import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * A class containing the main method, all of the different tests are executed here
 */
public class SimpleSetPerformanceAnalyzer {
	
	/**
	 * File path for data1.txt
	 */
	private static final String PATH_DATA_1 = "src//data1.txt";
	
	/**
	 * File path for data2.txt
	 */
	private static final String PATH_DATA_2 = "src//data2.txt";
	
	/**
	 * The amount of data structures tested
	 */
	private static final int DATA_STRUCTURES_AMOUNT = 5;
	
	/**
	 * OpenHashSet index in the data structures array
	 */
	private static final int OPEN_HASH = 0;

	/**
	 * ClosedHashSet index in the data structures array
	 */
	private static final int CLOSED_HASH = 1;
	
	/**
	 * TreeSet index in the data structures array
	 */
	private static final int TREE_SET = 2;
	
	/**
	 * LinkedList index in the data structures array
	 */
	private static final int LINKED_LIST = 3;
	
	/**
	 * HashSet index in the data structures array
	 */
	private static final int HASH_SET = 4;
	
	/**
	 * A string used in the tests
	 */
	private static final String CONTAIN_TEST_ONE = "hi";
	
	/**
	 * A string used in the tests
	 */
	private static final String CONTAIN_TEST_TWO  = "-13170890158";
	
	/**
	 * A string used in the tests
	 */
	private static final String CONTAIN_TEST_THREE = "23";
	
	/**
	 * The amount of "warm up" and regular iterations executed before run time is measured during
	 * tests
	 */
	private static final int WARM_UP_CAP = 70000;
	
	/**
	 * The amount of "warm up" and regular iterations executed when testing the LinkedList data
	 * structure
	 */
	private static final int LINKED_LIST_CAP = 7000;

	
	
	public static void main(String[] args) {
		String[] data1 = Ex4Utils.file2array(PATH_DATA_1);
		String[] data2 = Ex4Utils.file2array(PATH_DATA_2);
		System.out.println(new String("") == new String(""));
		System.out.println("Results for data1: (If enabled)");
		test(data1, CONTAIN_TEST_ONE, CONTAIN_TEST_TWO, new boolean[]{false, false, false});
		System.out.println("Results for data2: (If enabled)");
		test(data2, CONTAIN_TEST_ONE, CONTAIN_TEST_THREE, new boolean[]{false, false, false});	
	}
	
	/**
	 * Initializes a SimpleSet array according to the constants
	 * @return An initialized SimpleSet array
	 */
	public static SimpleSet[] initDataStructures() {
		SimpleSet[] ds = new SimpleSet[DATA_STRUCTURES_AMOUNT];
		ds[OPEN_HASH] = new OpenHashSet();
		ds[CLOSED_HASH] = new ClosedHashSet();
		ds[TREE_SET] = new CollectionFacadeSet(new TreeSet<String>());
		ds[LINKED_LIST] = new CollectionFacadeSet(new LinkedList<String>());
		ds[HASH_SET] = new CollectionFacadeSet(new HashSet<String>());
		return ds;
	}
	
	/**
	 * Returns the name of data structure in the given index in the SimpleSet array
	 * @param index The given index
	 * @return The name of the data structure in the given index 
	 */
	public static String getDSName(int index) {
		switch(index) {
		case OPEN_HASH:
			return OpenHashSet.class.getName();
		case CLOSED_HASH:
			return ClosedHashSet.class.getName();
		case TREE_SET:
			return TreeSet.class.getName();
		case LINKED_LIST:
			return LinkedList.class.getName();
		case HASH_SET:
			return HashSet.class.getName();
		default:
			return "";
		}
	}
	
	/**
	 * Measures the amount of time it takes to add all of the data in the given String array
	 * to each of the data structures in the SimpleSet array
	 * @param dataStructures Given data structures array
	 * @param data Given data array (Strings array)
	 */
	public static void addDataTest(SimpleSet[] dataStructures, String[] data) {
		for(int i = 0; i < dataStructures.length; i++) {
			SimpleSet set = dataStructures[i];
			long start = System.nanoTime();
			for(int j = 0; j < data.length; j++) 
				set.add(data[j]);	
			long end = System.nanoTime();
			System.out.println("DS : " + getDSName(i) + "\tTime: " + ((end - start) / 1000000) + " ms");
		}
	}
	
	/**
	 * Measures the amount of time it takes to check if the
	 *  given string is in each of the given
	 * data structures in the SimpleSet array
	 * @param dataStructures Given data structures array
	 * @param contain Tested string, tested if contained in data structures
	 */
	public static void containsTest(SimpleSet[] dataStructures, String contain) {
		for(int i = 0; i < dataStructures.length; i++) {
			if(i == LINKED_LIST)
				continue;
			SimpleSet set = dataStructures[i];
			for(int j = 0; j < WARM_UP_CAP; j++) 
				set.contains(contain);
			long start = System.nanoTime();
			for(int j = 0; j < WARM_UP_CAP; j++)
				set.contains(contain);
			long end = System.nanoTime();
			System.out.println("DS : " + getDSName(i) + "\tContains: " + contain 
					+ "\tTime: " + ((end - start) / WARM_UP_CAP) + " ns");
		}
		SimpleSet lst = dataStructures[LINKED_LIST];
		long start = System.nanoTime();
		for(int i = 0; i < LINKED_LIST_CAP; i++)
			lst.contains(contain);
		long end = System.nanoTime();
		System.out.println("DS : " + getDSName(LINKED_LIST) + "\tContains: " + contain
				+ "\tTime: " + ((end - start) / LINKED_LIST_CAP) + " ns");
		
	}
	
	/**
	 * Test function according to the requested parameters
	 * @param data Data set added to each of the data structures, used with addDataTest function
	 * as the data parameter
	 * @param containOne String used in the containsTest function as the contain parameter
	 * @param containTwo String used in the containsTest function as the contain parameter
	 * @param tests An array specifying which tests to run e.g
	 * {[0] = true,}
	 * {[1] = false,}
	 * {[2] = false} -----> results in running addDataTest with data, and containsTest with containTwo
	 * containsTest with containOne won't be executed
	 * If the addDataTest is disabled, results shouldn't be valid since all of the data structures are empty,
	 * since they are always initialized empty
	 */
	public static void test(String[] data, String containOne, String  containTwo, boolean[] tests) {
		SimpleSet[] dataStructures = initDataStructures();
		if(tests[0])
			addDataTest(dataStructures, data);
		if(tests[1])
			containsTest(dataStructures, containOne);
		if(tests[2])
			containsTest(dataStructures, containTwo);
	}
}