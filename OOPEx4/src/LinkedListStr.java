import java.util.LinkedList;

/**
 * A wrapper class of the Type LinkedList<String>
 */
public class LinkedListStr {
	
	/**
	 * The underlying linked list this instance wraps
	 */
	private LinkedList<String> list;
	
	/**
	 * The only constructor, this should always be initialized using with an existing
	 * reference to an existing LinkedList<String> instance
	 * @param list An existing LinkedList<String>
	 */
	public LinkedListStr(LinkedList<String> list) {
		this.list = list;
	}
	
	/**
	 * 
	 * @return The underlying linked list
	 */
	public LinkedList<String> get() {
		return list;
	}

}
