import java.util.LinkedList;

public class OpenHashSet extends SimpleHashSet {

	
	private LinkedListStr[] arr;
	private int size;
	private int numOfLists;

	/**
	 * A default constructor. Constructs a new, empty table with default initial
	 * capacity (16), upper load factor (0.75), and lower load factor(0.25)
	 */
	OpenHashSet() {
		super();
		this.arr = new LinkedListStr[capacity];
		size = 0;
		numOfLists = 0;
	}

	OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		this.arr = new LinkedListStr[capacity];
		size = 0;
		numOfLists = 0;
	}

	OpenHashSet(String[] data) {
		this();
		size = 0;
		numOfLists = 0;
		for(String str : data)
			add(str);
	}

	@Override
	public boolean add(String newValue) {
		// TODO Auto-generated method stub
		int hash = newValue.hashCode();
		int i  = clamp(hash);
		if(arr[i] == null) {
			arr[i] = new LinkedListStr(new LinkedList<String>());
			arr[i].get().addLast(newValue);
			size++;
			if(size + 1 > upperLoadFactor * capacity)
				resize(true);
			numOfLists++;
			return true;
		}
		if(arr[i].get().contains(newValue))
			return false;
		arr[i].get().addLast(newValue);
		size++;
		return true;
	}

	@Override
	public boolean contains(String searchVal) {
		// TODO Auto-generated method stub
		int hash = searchVal.hashCode();
		int i = clamp(hash);
		if(arr[i] == null)
			return false;
		return arr[i].get().contains(searchVal);
	}

	@Override
	public boolean delete(String toDelete) {
		int hash = toDelete.hashCode();
		int i = clamp(hash);
		if(arr[i] == null)
			return false;
		if(!arr[i].get().contains(toDelete))
			return false;
		if(arr[i].get().size() == 1) {
			numOfLists--;
			arr[i] = null;
			size--;
			return true;
		}
		arr[i].get().remove(toDelete);
		size--;
		if(numOfLists < lowerLoadFactor * capacity)
			resize(false);
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int capacity() {
		return capacity;
	}


	/**
	 * Clamps hashing indices to fit within the current table capacity
	 * @param index the index before clamping
	 * @return an index properly clamped
	 */
	@Override
	protected int clamp(int index) {
		return index & (capacity - 1);
	}

	private void resize(boolean up) {
		capacity = Math.max(up ? capacity * 2 : capacity / 2, 1);
		LinkedListStr[] old = this.arr;
		this.arr = new LinkedListStr[capacity];
		size = 0;
		numOfLists = 0;
		for(LinkedListStr lst : old) {
			if(lst == null)
				continue;
			for(String str : lst.get()) {
				add(str);
			}
		}
	}

}
