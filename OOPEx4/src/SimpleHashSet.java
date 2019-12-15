public abstract class SimpleHashSet implements SimpleSet {
	
	/**
	 * Describes the higher load factor of a newly created hash set
	 */
	protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
	
	/**
	 * Describes the lower load factor of a newly created hash set
	 */
	protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;
	
	
	/**
	 * Describes the capacity of a newly created hash set
	 */
	protected static final int INITIAL_CAPACITY = 16;
	
	
	protected int capacity;
	
	/**
	 * Upper load factor before rehashing, default to 
	 * DEFAULT_HIGHER_CAPACITY
	 */
	protected float upperLoadFactor;
	
	/**
	 * Lower load factor before rehashing, default to 
	 * DEFAULT_LOWER_CAPACITY
	 */
	protected float lowerLoadFactor;
	
	/**
	 * Constructs a new hash set with the default capacities given in 
	 * DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY
	 */
	SimpleHashSet() {
		capacity = SimpleHashSet.INITIAL_CAPACITY;
		upperLoadFactor = SimpleHashSet.DEFAULT_HIGHER_CAPACITY;
		lowerLoadFactor = SimpleHashSet.DEFAULT_LOWER_CAPACITY;
	}
	
	/**
	 * Constructs a new has set 
	 * @param upperLoadFactor
	 * @param lowerLoadFactor
	 */
	SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
		capacity = SimpleHashSet.INITIAL_CAPACITY;
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
	}
	
	
	/**
	 * @return The current capacity(number of cells) of the table.
	 */
	public abstract int capacity();
	
	
	/**
	 * Clamps hashing indices to fit within the current table capacity
	 * @param index the index before clamping
	 * @return an index properly clamped
	 */
	protected int clamp(int index) {
		return index;
	}
	
	/**
	 * @return The lower load factor of the table.
	 */
	protected float getLowerLoadFactor() {
		return lowerLoadFactor;
	}
	
	/**
	 * @return The higher load factor of the table
	 */
	protected float getUpperLoadFactor() {
		return upperLoadFactor;
	}
	
}
