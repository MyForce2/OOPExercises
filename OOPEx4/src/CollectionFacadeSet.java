import java.util.Collection;
import java.util.HashSet;

/**
 * Wraps an underlying Collection and serves 
 * to both simplify its API and give it a common type with the implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {
	
	/**
	 * Any java collection
	 */
	private Collection<String> collection;
	
	/**
	 * Creates a new facade wrapping the specified collection.
	 * @param collection The Collection to wrap.
	 */
	public CollectionFacadeSet(Collection<String> collection) {
		// We create a set from this collection to remove duplicates
		HashSet<String> s = new HashSet<String>(collection);
		collection.clear();
		// Add set back to collection after clearing
		collection.addAll(s);
		this.collection = collection;
	}
	
	@Override
	public boolean add(String newValue) {
		// Only add values if they aren't in the collection, prevents duplicates
		if(!collection.contains(newValue))
			return collection.add(newValue);
		return false;
	}

	@Override
	public boolean contains(String searchVal) {
		return collection.contains(searchVal);
	}

	@Override
	public boolean delete(String toDelete) {
		return collection.remove(toDelete);
	}

	@Override
	public int size() {
		return collection.size();
	}

}
