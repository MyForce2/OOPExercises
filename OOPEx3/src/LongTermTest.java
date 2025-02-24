import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import oop.ex3.spaceship.Item;
import oop.ex3.spaceship.ItemFactory;

public class LongTermTest {
	
	private static LongTermStorage lts;
	
	private static final int OPERATION_FAILED_ERROR = -1;
	private static final int OPERATION_SUCCESSFUL = 0;


	@BeforeClass
	public static void createObjects() {
		lts = new LongTermStorage();
	}
	
	/**
	 * Test function for addItem method in LTS class
	 */
	@Test
	public void testAddItem() {
		Item[] items = ItemFactory.createAllLegalItems();
		lts.resetInventory();
		assertTrue("LTS class add item doesn't work with null values", lts.addItem(null, 1) == OPERATION_FAILED_ERROR);
		assertTrue("LTS class add item doesn't work with 0 values", lts.addItem(items[0], 0) == OPERATION_SUCCESSFUL);
		assertTrue("LTS class add item doesn't work with negative values", lts.addItem(items[0], -3) == OPERATION_FAILED_ERROR);
		for(int i = 0; i < items.length; i++) {
			int itemsNumber = 5;
			int volume = items[i].getVolume() * itemsNumber;
			int preCapacity = lts.getAvailableCapacity();
			if(items[i].getVolume() * 5 <= lts.getAvailableCapacity())
				assertTrue("LTS class doesn't add items when it has available storage", lts.addItem(items[i], itemsNumber) == OPERATION_SUCCESSFUL);
			else
				assertTrue("LTS class adds items when it doesn't have available storage", lts.addItem(items[i], itemsNumber) == OPERATION_FAILED_ERROR);
			assertTrue("LTS class doesn't lower capacity after item addition", lts.getAvailableCapacity() == preCapacity - volume);
		}
		lts.resetInventory();
		lts.addItem(items[0], 1);
		lts.addItem(items[0], 1);
		assertTrue("LTS doesn't add items correctly if they are already placed", lts.getItemCount(items[0].getType()) == 2);
	}
	
	/**
	 * Test function for ResetInventory method in LTS class
	 */
	@Test
	public void testResetInventory() {
		Item[] items = ItemFactory.createAllLegalItems();
		lts.resetInventory();
		int itemsNumber = 5;
		for(int i = 0; i < items.length; i++) 
			lts.addItem(items[i], itemsNumber);
		lts.resetInventory();
		assertTrue("LTS class doesn't reset capacity after inventory reset", lts.getAvailableCapacity() == lts.getCapacity());
		assertTrue("LTS class doesn't reset inventory map after inventory reset", lts.getInvetory().size() == 0);
	}
	
	/**
	 * Test function for getItem method in LTS class
	 */
	@Test
	public void testGetItemCount() {
		Item[] items = ItemFactory.createAllLegalItems();
		lts.resetInventory();
		for(int i = 0; i < items.length; i++) 
			lts.addItem(items[i], i + 1);
		for(int i = 0; i < items.length; i++)
			assertTrue("LTS class added item count isn't correct", lts.getItemCount(items[i].getType()) == i + 1 && lts.getItemCount(items[i].getType()) != 0);
	}
}
