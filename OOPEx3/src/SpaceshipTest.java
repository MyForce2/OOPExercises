import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class SpaceshipTest {
	
	private static Spaceship spaceship;
	
	private static final String SHIP_NAME = "USS";
	private static final int[] CREW_IDS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static final int LOCKER_AMOUNT = 10;
	private static final int DEFAULT_CAPACITY = 10;
	
	@BeforeClass
	public static void createObjects() {
		spaceship = new Spaceship(SHIP_NAME, CREW_IDS, LOCKER_AMOUNT);
	}
	
	/**
	 * A test function for the createLocker method in the spaceship class
	 */
	@Test
	public void testLockerCreation() {
		assertTrue("", spaceship.createLocker(-1, 10) == Spaceship.NON_VALID_ID_ERROR);
		assertTrue("", spaceship.createLocker(2, -1) == Spaceship.NON_VALID_CAPACITY_ERROR);
		for(int i = 0; i < CREW_IDS.length; i++) 
			assertTrue("", spaceship.createLocker(CREW_IDS[i], DEFAULT_CAPACITY) == Spaceship.LOCKER_CREATION_SUCCESSFUL);
		assertTrue("", spaceship.createLocker(CREW_IDS[0], DEFAULT_CAPACITY) == Spaceship.MAX_LOCKERS_ERROR);
	}
	
}
