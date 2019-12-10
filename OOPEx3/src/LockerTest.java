import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;

public class LockerTest {
	
	private static Locker locker;
	private static LongTermStorage lts;
	
	private static final int LOCKER_DEFAULT_CAPACITY = 10;
	
	private static final int OPERATION_FAILED_ERROR = -1;
	private static final int OPERATION_SUCCESSFUL = 0;
	private static final int OPERATION_SUCCESSFUL_WARNING = 1;
	private static final int CONSTRAINTS_ERROR = -2;

	@BeforeClass
	public static void createObjects() {
		lts = new LongTermStorage();
		locker = new Locker(lts, LOCKER_DEFAULT_CAPACITY, ItemFactory.getConstraintPairs());
	}
	
	/**
	 * Test function for addItem method in locker class
	 */
	@Test
	public void testAddItem() {
		Item[] items = ItemFactory.createAllLegalItems();
		for(int i = 0; i < items.length; i++) 
			locker.removeItem(items[i], locker.getItemCount(items[i].getType()));
		lts.resetInventory();
		assertTrue("Locker class add item doesn't work with null values", locker.addItem(null, 1) == OPERATION_FAILED_ERROR);
		assertTrue("Locker class add item doesn't work with 0 values", locker.addItem(items[0], 0) == OPERATION_SUCCESSFUL);
		assertTrue("Locker class add item doesn't work with negative values", locker.addItem(items[0], -3) == OPERATION_FAILED_ERROR);
		Item baseball = ItemFactory.createSingleItem(ItemFactory.getConstraintPairs()[0][0].getType());
		Item football = ItemFactory.createSingleItem(ItemFactory.getConstraintPairs()[0][1].getType());
		locker.removeItem(football, locker.getItemCount(football.getType()));
		assertTrue("Locker class add item doesn't work properly ", locker.addItem(baseball, 1) == OPERATION_SUCCESSFUL);
		assertTrue("Locker doesn't move to LTS unit when needed", locker.addItem(baseball, 4) == OPERATION_SUCCESSFUL_WARNING);
		assertTrue("Locker", locker.addItem(football, 1) == CONSTRAINTS_ERROR);
		// Fill up the lts unit
		lts.addItem(baseball, 496);
		// Now moving objects to the lts units should fail
		assertTrue("Failed", locker.addItem(baseball, 3) == OPERATION_FAILED_ERROR);
		for(int i = 0; i < items.length; i++) 
			locker.removeItem(items[i], locker.getItemCount(items[i].getType()));
	}
	
	
	/**
	 * Test function for removeItem method in locker class
	 */
	@Test
	public void testRemoveItem() {
		Item[] items = ItemFactory.createAllLegalItems();
		for(int i = 0; i < items.length; i++) 
			locker.removeItem(items[i], locker.getItemCount(items[i].getType()));
		lts.resetInventory();
		assertTrue("Locker class remove item doesn't work with null values", locker.removeItem(null, 1) == OPERATION_FAILED_ERROR);
		assertTrue("Locker class remove item doesn't work with 0 values", locker.removeItem(items[0], 0) == OPERATION_SUCCESSFUL);
		assertTrue("Locker class remove item doesn't work with negative values", locker.removeItem(items[0], -1) == OPERATION_FAILED_ERROR);
		locker.removeItem(items[0], locker.getItemCount(items[0].getType()));
		assertTrue("Locker class remove item doesn't work values", locker.removeItem(items[0], 1) == OPERATION_FAILED_ERROR);
		for(int i = 0; i < items.length; i++) 
			locker.removeItem(items[i], locker.getItemCount(items[i].getType()));
	}
	
	/**
	 * Test function for getItem method in locker class
	 */
	@Test
	public void testGetItemCount() {
		Item[] items = ItemFactory.createAllLegalItems();
		for(int i = 0; i < items.length; i++) 
			locker.removeItem(items[i], locker.getItemCount(items[i].getType()));
		lts.resetInventory();
		locker.addItem(items[0], 1);
		assertTrue("Locker getItemCount doesn't work with addition", locker.getItemCount(items[0].getType()) == 1);
		locker.removeItem(items[0], 1);
		assertTrue("Locker getItemCount doesn't work with removal", locker.getItemCount(items[0].getType()) == 0);
		locker.addItem(items[0], 4);
		assertTrue("Locker getItemCount doesn't work with transfer to lts unit", locker.getItemCount(items[0].getType()) != 4);
	}
}
