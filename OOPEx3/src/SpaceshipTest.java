import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class SpaceshipTest {
	
	private static SpaceShip spaceship;
	
	private static final String SHIP_NAME = "USS";
	private static final int[] CREW_IDS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static final int LOCKER_AMOUNT = 10;
	private static final int DEFAULT_CAPACITY = 10;
	
	@BeforeClass
	public static void createObjects() {
		spaceship = new SpaceShip(SHIP_NAME, CREW_IDS, LOCKER_AMOUNT);
	}
	
	/**
	 * A test function for the createLocker method in the spaceship class
	 */
	@Test
	public void testLockerCreation() {
		assertTrue("", spaceship.createLocker(-1, 10) == SpaceShip.NON_VALID_ID_ERROR);
		assertTrue("", spaceship.createLocker(2, -1) == SpaceShip.NON_VALID_CAPACITY_ERROR);
		for(int i = 0; i < CREW_IDS.length; i++) 
			assertTrue("", spaceship.createLocker(CREW_IDS[i], DEFAULT_CAPACITY) == SpaceShip.LOCKER_CREATION_SUCCESSFUL);
		assertTrue("", spaceship.createLocker(CREW_IDS[0], DEFAULT_CAPACITY) == SpaceShip.MAX_LOCKERS_ERROR);
	}
	
}
