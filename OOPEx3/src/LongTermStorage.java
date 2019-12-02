import java.util.HashMap;
import java.util.Map;

import oop.ex3.spaceship.Item;

/**
 * A long term storage class, implements the long term storage behavior
 * according to the API
 *
 */
public class LongTermStorage {
	
	/**
	 * The maximum capacity for a long term storage instance
	 */
	private static final int CAPACITY = 1000;
	
	/**
	 * The currently available capacity
	 */
	private int availableCapacity;
	
	/**
	 * A map of the storage's inventory, maps an item to type to the amount
	 * currently in the storage
	 */
	private final Map<String, Integer> inventory;
	
	/**
	 * Lts constructor
	 */
	public LongTermStorage() {
		availableCapacity = LongTermStorage.CAPACITY;
		inventory = new HashMap<String, Integer>();
	}
	
	/**
	 * Adds an item to the lts instance
	 * @param item The given item to add
	 * @param n The amount of items that should be added of the given item type
	 * @return 0 if the operation was successful, -1 if the operation failed
	 */
	public int addItem(Item item, int n) {
		if(item == null || n < 0) {
			System.out.println(Locker.DEFAULT_ERROR);
			return Locker.OPEARATION_FAILED_ERROR;
		}
		if(n == 0) 
			return Locker.OPERATION_SUCCESSFUL;
		int itemsVolume = item.getVolume() * n;
		if(availableCapacity - itemsVolume < 0) {
			System.out.println(Locker.DEFAULT_ERROR + "Problem: no room for " + n + " items of type " + item.getType());
			return Locker.OPEARATION_FAILED_ERROR;
		}
		availableCapacity -= itemsVolume;
		inventory.put(item.getType(), n + getItemCount(item.getType()));
		return Locker.OPERATION_SUCCESSFUL;
	}
	
	/**
	 * Resets the lts's inventory, clears the inventory and resets the available capcacity
	 */
	public void resetInventory() {
		availableCapacity = LongTermStorage.CAPACITY;
		inventory.clear();
	}
	
	/**
	 * Gets the item count of a certain item type
	 * @param type The type of the item which count is returned
	 * @return 0 if the item isn't in the lts, otherwise it's count in the lts
	 */
	public int getItemCount(String type) {
		if(inventory.containsKey(type)) 
			return inventory.get(type);
		else
			return 0;
	}
	
	/**
	 * A getter for the inventory
	 * @return the lts's inventory
	 */
	public Map<String, Integer> getInvetory() {
		return inventory;
	}
	
	/**
	 * A getter for the total capacity
	 * @return The total / max capacity
	 */
	public int getCapacity() {
		return LongTermStorage.CAPACITY;
	}
	
	/**
	 * A getter for the currently available capacity
	 * @return the currently available capacity
	 */
	public int getAvailableCapacity() {
		return availableCapacity;
	}
	
	

}
