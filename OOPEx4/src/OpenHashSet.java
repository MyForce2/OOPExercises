import java.util.LinkedList;

/**
 * An implementation of the HashSet data structure using a hash table
 * and chaining.
 */
public class OpenHashSet extends SimpleHashSet {

	/**
	 * The underlying data array of this OpenHashSet instance, the table itself
	 * Type is LinkedListStr, since an array of LinkedList<String> itself can't
	 * be instantiated, LinkedListStr is a wrapper of LinkedList<String>
	 */
	private LinkedListStr[] arr;
	
	/**
	 * The size of this collection, the amount of elements in this set
	 */
	private int size;

	/**
	 * A default constructor. Constructs a new, empty table with default initial
	 * capacity (16), upper load factor (0.75), and lower load factor(0.25)
	 */
	public OpenHashSet() {
		super();
		this.arr = new LinkedListStr[capacity];
		size = 0;
	}

	/**
	 * A default constructor. Constructs a new, 
	 * empty table with default initial capacity (16), 
	 * upper load factor (0.75) and lower load factor (0.25).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		this.arr = new LinkedListStr[capacity];
		size = 0;
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be ignored. 
	 * The new table has the default values of initial capacity (16), 
	 * upper load factor (0.75), and lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public OpenHashSet(String[] data) {
		this();
		size = 0;
		for(String str : data)
			add(str);
	}

	@Override
	public boolean add(String newValue) {
		int hash = newValue.hashCode();
		int i  = clamp(hash);
		if(arr[i] == null) {
			arr[i] = new LinkedListStr(new LinkedList<String>());
			arr[i].get().addLast(newValue);
			size++;
			if(size > upperLoadFactor * capacity)
				resize(true);
			return true;
		}
		for(String str : arr[i].get()) {
			if(str.hashCode() == hash) {
				if(str.equals(newValue))
					return false;
			}
		}
		arr[i].get().addLast(newValue);
		size++;
		if(size > upperLoadFactor * capacity)
			resize(true);
		return true;
	}

	@Override
	public boolean contains(String searchVal) {
		int hash = searchVal.hashCode();
		int i = clamp(hash);
		if(arr[i] == null)
			return false;
		for(String str : arr[i].get()) {
			if(str.hashCode() == hash) {
				if(str.equals(searchVal))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(String toDelete) {
		int hash = toDelete.hashCode();
		int i = clamp(hash);
		if(arr[i] == null)
			return false;
		for(String str : arr[i].get()) {
			if(str.hashCode() == hash) {
				if(str.equals(toDelete)) {
					if(arr[i].get().size() == 1) {
						arr[i] = null;
						size--;
						return true;
					}
					arr[i].get().remove(toDelete);
					size--;
					if(size < lowerLoadFactor * capacity)
						resize(false);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@Override
	protected int clamp(int index) {
		return index & (capacity - 1);
	}

	/**
	 * Resizes the underlying array in this ClosedHashSet instance, doubles the size
	 * if growing upwards, halves the size if growing downwards, not less than 1
	 * @param up True if growing upwards, false downwards
	 */
	private void resize(boolean up) {
		capacity = Math.max(up ? capacity * 2 : capacity / 2, 1);
		LinkedListStr[] old = this.arr;
		this.arr = new LinkedListStr[capacity];
		size = 0;
		for(LinkedListStr lst : old) {
			if(lst == null)
				continue;
			for(String str : lst.get()) {
				add(str);
			}
		}
	}

}
