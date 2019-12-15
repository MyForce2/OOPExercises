
public class ClosedHashSet extends SimpleHashSet {

	private String[] arr;
	private boolean[] deleted;
	private int size;


	ClosedHashSet() {
		super();
		arr = new String[SimpleHashSet.INITIAL_CAPACITY];
		deleted = new boolean[SimpleHashSet.INITIAL_CAPACITY];
		size = 0;
	}

	ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);
		arr = new String[SimpleHashSet.INITIAL_CAPACITY];
		deleted = new boolean[SimpleHashSet.INITIAL_CAPACITY];
		size = 0;
	}

	ClosedHashSet(String[] data) {
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
			int quad = (int) (i + Math.pow(i, 2)) / 2;
			int index = clamp(hash + quad);
			if(arr[index] == null) {
				if(arr[index] != null) 
					continue;
				arr[index] = newValue;
				size++;
				if(size + 1 > capacity * upperLoadFactor)
					resize(true);
				return true;
			}
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
			int quad = (int) (i + Math.pow(i, 2)) / 2;
			int index = clamp(hash + quad);
			if(arr[index] == null) {
				if(!deleted[index])
					return false;
				else
					continue;
			}
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
			int quad = (int) (i + Math.pow(i, 2)) / 2;
			int index = clamp(hash + quad);
			if(arr[index] == null) {
				if(!deleted[index])
					return false;
				else
					continue;
			}
			if(arr[index].equals(toDelete)) {
				arr[index] = null;
				deleted[index] = true;
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

	public void resize(boolean up) {
		capacity = Math.max(up ? capacity * 2 : capacity / 2, 1);
		String[] old = arr;
		arr = new String[capacity];
		deleted = new boolean[capacity];
		size = 0;
		for(String str : old) {
			add(str);
		}
	}

}
