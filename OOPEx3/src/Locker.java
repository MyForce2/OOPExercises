import java.util.HashMap;
import java.util.Map;

import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;

/**
 * An implementation of the Locker class, according to the given API
 *
 */
public class Locker {
	
	/**
	 * The default error string, printed along with every error
	 */
	public static final String DEFAULT_ERROR = "Error: Your request cannot be completed at this time.";
	
	/**
	 * Constraints error code, returned whenever an error related to item constraints
	 * has occurred when trying to add an item
	 */
	public static final int CONSTRAINTS_ERROR = -2;
	
	/**
	 * Operation failed error code, returned whenever an operation has failed
	 */
	public static final int OPEARATION_FAILED_ERROR = -1;
	
	/**
	 * Operation successful error code, returned whenever an operation is successful
	 */
	public static final int OPERATION_SUCCESSFUL = 0;
	
	/**
	 * Operation successful error code, but with a warning, e.g Adding item but moving several other
	 * to the LTS unit
	 */
	public static final int OPERATION_SUCCESSFUL_WARNING = 1;
	
	/**
	 * The maximum amount of space one type of item can take in this locker,
	 * whenever an item takes more space than this, some of it's units are moved to 
	 * the LTS unit
	 */
	public static final float MOVE_TO_LTS_PERCENTAGE = 0.5f;
	
	/**
	 * The maximum amount of space one item type can take after a transfer to the
	 * LTS unit
	 */
	public static final float MAX_AMOUNT_AFTER_TRANSFER_TO_LTS = 0.2f;
	
	/**
	 * The maximum capacity for this locker
	 */
	private final int maxCapacity;
	
	/**
	 * The currently available capacity
	 */
	private int availableCapacity;
	
	/**
	 * A map containing all of the inventory, maps items to amount
	 */
	private final Map<String, Integer> inventory;
	
	/**
	 * The LTS unit associated with this locker
	 */
	private final LongTermStorage lts;
	
	/**
	 * A list of item constraints, items that can't go together in this locker
	 */
	private final Item[][] constraints;
	
	/**
	 * Locker class ctor
	 * @param lts the LTS unit associated with this locker
	 * @param capacity this locker max capacity
	 * @param constraints the item constraints for this locker
	 */
	public Locker(LongTermStorage lts, int capacity, Item[][] constraints) {
		maxCapacity = capacity;
		availableCapacity = maxCapacity;
		this.lts = lts;
		this.constraints = new Item[constraints.length][constraints[0].length];
		for(int i = 0; i < this.constraints.length; i++) {
			for(int j = 0; j < this.constraints[i].length; j++) {
				this.constraints[i][j] = ItemFactory.createSingleItem(constraints[i][j].getType());
			}
		}
		inventory = new HashMap<String, Integer>();
	}
	
	/**
	 * Adds an item to this locker, if possible
	 * @param item The item to be added
	 * @param n How many items of this item type should be added
	 * @return -2 if the operation failed because of constraints, -1 if the addition failed for any other reason
	 *  0 if it was successful, 1 if it was successful with a warning
	 */
	public int addItem(Item item, int n) {
		if(item == null || n < 0) {
			System.out.println(Locker.DEFAULT_ERROR);
			return Locker.OPEARATION_FAILED_ERROR;
		}
		if(n == 0) 
			return Locker.OPERATION_SUCCESSFUL;
		if(doesItemViolateConstraints(item)) {
			System.out.println(Locker.DEFAULT_ERROR + " Problem: the locker cannot contains items of type " 
					+ item.getType() + ", as it contains a contradicting item");
			return Locker.CONSTRAINTS_ERROR;
		}
		int itemsVolume = item.getVolume() * n;
		if(availableCapacity - itemsVolume < 0) {
			System.out.println(Locker.DEFAULT_ERROR + " Problem: no room for " + n + " items of type " + item.getType());
			return Locker.OPEARATION_FAILED_ERROR;
		}
		int totalVolume = getItemCount(item.getType()) * item.getVolume() + itemsVolume;
		float percentageOutOfWholeStorage = ((float) totalVolume)/ maxCapacity;
		boolean moveToLTSStorage = percentageOutOfWholeStorage > Locker.MOVE_TO_LTS_PERCENTAGE;
		if(moveToLTSStorage) 
			return handleMoveToLTSStorage(item, n);
		availableCapacity -= itemsVolume;
		inventory.put(item.getType(), n + getItemCount(item.getType()));
		return OPERATION_SUCCESSFUL;
	}
	
	/**
	 * Removes an item from this locker, if possible
	 * @param item The item type to be removed
	 * @param n The amount of items of this type that should be removed
	 * @return -1 if the removal failed, 0 if the removal was successful
	 */
	public int removeItem(Item item, int n) {
		if(item == null) {
			System.out.println(Locker.DEFAULT_ERROR);
			return Locker.OPEARATION_FAILED_ERROR;
		}
		if(n == 0) 
			return Locker.OPERATION_SUCCESSFUL;
		if(n < 0) {
			System.out.println(Locker.DEFAULT_ERROR + " Problem: cannot remove a negative number of items of type " + item.getType());
			return Locker.OPEARATION_FAILED_ERROR;
		}
		if(getItemCount(item.getType()) < n) {
			System.out.println(Locker.DEFAULT_ERROR + " Problem: the locker does not contain " + n + 
					" items of type " + item.getType());
			return Locker.OPEARATION_FAILED_ERROR;
		}
		availableCapacity += item.getVolume() * n;
		if(n == getItemCount(item.getType())) {
			inventory.remove(item.getType());
			return Locker.OPERATION_SUCCESSFUL;
		}
		inventory.put(item.getType(), getItemCount(item.getType()) - n);
		return Locker.OPERATION_SUCCESSFUL;
		
	}
	
	/**
	 * Handles moving items to the LTS unit, if needed during item addition
	 * @param item The item type 
	 * @param n The amount of items during the addItem method call
	 * @return -1 if the operation failed, 0 if the operation was successful
	 */
	private int handleMoveToLTSStorage(Item item, int n) {
		int totalItems = getItemCount(item.getType()) + n;
		int n1 = getAmountToMoveToLTS(item, n);
		if(lts.addItem(item, n1) == Locker.OPEARATION_FAILED_ERROR) {
			System.out.println(DEFAULT_ERROR + " Problem: no room for " + n1 + " items of type " + item.getType());
			return Locker.OPEARATION_FAILED_ERROR;
		}
		inventory.put(item.getType(), totalItems - n1);
		availableCapacity += ((totalItems - n) * item.getVolume());
		availableCapacity -= ((totalItems - n1) * item.getVolume());
		System.out.println("Warning: Action successful, but has caused items to be moved to storage");
		return Locker.OPERATION_SUCCESSFUL_WARNING;
	}
	
	/**
	 * Calculates the amount of items for the LTS transfer
	 * @param item Item type
	 * @param n The amount of items during the addItem method call
	 * @return The amount of items for the LTS transfer
	 */
	private int getAmountToMoveToLTS(Item item, int n) {
		int currentVolume = (getItemCount(item.getType()) + n) * item.getVolume();
		int n1 = 0;
		float percentageOutOfWholeStorage = ((float) currentVolume)/ maxCapacity; 
		while(percentageOutOfWholeStorage > 0.2f) {
			currentVolume -= item.getVolume();
			n1++;
			percentageOutOfWholeStorage = ((float) currentVolume) / maxCapacity;
		}
		return n1;
	}
	
	/**
	 * A method to check if a certain item addition violates 
	 * the locker constraints
	 * @param item Item type
	 * @return true if the addition is a violation, false otherwise
	 */
	private boolean doesItemViolateConstraints(Item item) {
		for(int i = 0; i < constraints.length; i++) {
			if(constraints[i][0].getType().equals(item.getType())) {
				if(itemInLocker(constraints[i][1]))
					return true;
			} else if(constraints[i][1].getType().equals(item.getType())) {
				if(itemInLocker(constraints[i][0]))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * A method to check if an item exists in the locker
	 * @param item Item type
	 * @return true if the item exists in the locker, false otherwise
	 */
	private boolean itemInLocker(Item item) {
		return getItemCount(item.getType()) > 0;
	}
	
	/**
	 * Getter for the locker inventory
	 * @return The locker's inventory
	 */
	public Map<String, Integer> getInventory() {
		return inventory;
	}
	
	/**
	 * Getter for the item count of a certain item type
	 * @param type Item type string
	 * @return The item amount of a certain item in the locker
	 */
	public int getItemCount(String type) {
		if(inventory.containsKey(type))
			return inventory.get(type);
		else
			return 0;
	}
	
	/**
	 * A getter for the locker max capacity
	 * @return This locker maximum capacity
	 */
	public int getCapacity() {
		return maxCapacity;
	}
	
	/**
	 * A getter for the locker currently available capacity
	 * @return This locker available capacity
	 */
	public int getAvailableCapacity() {
		return availableCapacity;
	}
	
	
}
