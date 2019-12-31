/**
 * An implementation of the HashSet data structure using a HashTable
 * using quadratic probing
 */
public class ClosedHashSet extends SimpleHashSet {

	/**
	 * The underlying array in this ClosedHashSet instance
	 * the table itself
	 */
	private String[] arr;
	
	/**
	 * A constant string with a unique place in memory, used to identify deleted
	 * indices in the table
	 */
	private final String DELETED_VAL;
	
	/**
	 * The size of this set (Number of elements)
	 */
	private int size;
	
	

	/**
	 * A default constructor. Constructs a new, 
	 * empty table with default initial capacity (16), 
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public ClosedHashSet() {
		super();
		arr = new String[SimpleHashSet.INITIAL_CAPACITY];
		DELETED_VAL = new String();
		size = 0;
	}

	/**
	 * Constructs a new, empty table with the specified 
	 * load factors, and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		arr = new String[SimpleHashSet.INITIAL_CAPACITY];
		DELETED_VAL = new String();
		size = 0;
	}

	/**
	 * Data constructor - builds the hash set by 
	 * adding the elements one by one. Duplicate values should be ignored. 
	 * The new table has the default values of initial capacity (16), 
	 * upper load factor (0.75), and lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public ClosedHashSet(String[] data) {
		this();
		for(String str : data)
			add(str);
	}

	@Override
	public boolean add(String newValue) {
		if(newValue == null)
			return false;
		int hash = newValue.hashCode();
		for(int i = 0; i < capacity; i++) {
			int quad = (i + i * i) / 2;
			int index = clamp(hash + quad);
			if(arr[index] == null) {
				// Prevents the case the upperLoadFactor is 1,
				// Which will never be surpassed
				if(size + 1 > capacity * upperLoadFactor) {
					resize(true);
					return add(newValue);
				}
				arr[index] = newValue;
				size++;
				return true;
			}
			if(arr[index].hashCode() != hash)
				continue;
			if(arr[index].equals(newValue))
				return false;
		}
		return false;
	}
	


	@Override
	public boolean contains(String searchVal) {
		if(searchVal == null)
			return false;
		int hash = searchVal.hashCode();
		for(int i = 0; i < capacity; i++) {
			int quad = (i + i * i) / 2;
			int index = clamp(hash + quad);
			if(arr[index] == null) 
				return false;
			if(arr[index] == DELETED_VAL)
				continue;
			if(arr[index].hashCode() == hash)
				if(arr[index].equals(searchVal))
					return true;
		}
		return false;
	}


	@Override
	public boolean delete(String toDelete) {
		if(toDelete == null)
			return false;
		int hash = toDelete.hashCode();
		for(int i = 0; i < capacity; i++) {
			int quad = (i + i * i) / 2;
			int index = clamp(hash + quad);
			if(arr[index] == null) 
					return false;
			if(arr[index] == DELETED_VAL)
				continue;
			if(arr[index].hashCode() != hash)
				continue;
			if(arr[index].equals(toDelete)) {
				arr[index] = DELETED_VAL;
				size--;
				if(size < DEFAULT_LOWER_CAPACITY * capacity)
					resize(false);
				return true;
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
	public int clamp(int index) {
		return index & (capacity - 1);
	}

	/**
	 * Resizes the underlying array in this ClosedHashSet instance, doubles the size
	 * if growing upwards, halves the size if growing downwards, not less than 1
	 * @param up True if growing upwards, false downwards
	 */
	private void resize(boolean up) {
		capacity = Math.max(up ? capacity * 2 : capacity / 2, 1);
		String[] old = arr;
		arr = new String[capacity];
		size = 0;
		for(String str : old) {
			if(str == DELETED_VAL)
				continue;
			add(str);
		}
	}

}
