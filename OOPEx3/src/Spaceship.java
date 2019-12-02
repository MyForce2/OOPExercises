import oop.ex3.spaceship.ItemFactory;

public class Spaceship {
	
	/**
	 * Return code for when a locker creation was successful
	 */
	public static final int LOCKER_CREATION_SUCCESSFUL = 0;
	
	/**
	 * Return code error for when a non valid crew id was given
	 */
	public static final int NON_VALID_ID_ERROR = -1;
	
	/**
	 * Return code error for when a non valid capacity was given 
	 */
	public static final int NON_VALID_CAPACITY_ERROR = -2;
	
	/**
	 * Return code error for when the max amount of lockers was created
	 */
	public static final int MAX_LOCKERS_ERROR = -3;
	
	/**
	 * This spaceship name
	 */
	private final String name;
	
	/**
	 * An array containing all of the crew ids
	 */
	private final int[] crewIds;
	
	/**
	 * An array containing all of lockers
	 */
	private final Locker[] lockers;
	
	/**
	 * The max amount of lockers in this ship, size of lockers array
	 */
	private final int numOfLockers;
	
	/**
	 * The amount of created lockers so far
	 */
	private int createdLockers;
	
	/**
	 * The LTS unit used by this ship
	 */
	private final LongTermStorage lts;
	
	/**
	 * SpaceShip ctor
	 * @param name The ship's name
	 * @param crewIDs List of crew ids
	 * @param numOfLockers The max amount of lockers for this ship
	 */
	public Spaceship(String name, int[] crewIDs, int numOfLockers) {
		this.name = name;
		this.numOfLockers = numOfLockers;
		createdLockers = 0;
		lockers = new Locker[numOfLockers];
		lts = new LongTermStorage();
		this.crewIds = new int[crewIDs.length];
		for(int i = 0; i < this.crewIds.length; i++)
			this.crewIds[i] = crewIDs[i];
	}
	
	public LongTermStorage getLongTermStorage() {
		return lts;
	}
	
	/**
	 * Creates a locker and returns the result of the creation
	 * @param crewID id of the crew member the locker belongs to
	 * @param capacity The capacity for the created locker
	 * @return 0 if the locker was created successfully, -1 if the given crew id 
	 * wasn't valid, -2 if the given capacity wasn't valid, -3 if the maximum amount of lockers was
	 * created
	 */
	public int createLocker(int crewID, int capacity) {
		if(!isIdValid(crewID))
			return Spaceship.NON_VALID_ID_ERROR;
		if(capacity <= 0)
			return Spaceship.NON_VALID_CAPACITY_ERROR;
		if(createdLockers >= numOfLockers)
			return Spaceship.MAX_LOCKERS_ERROR;
		lockers[createdLockers] = new Locker(lts, capacity, ItemFactory.getConstraintPairs());
		createdLockers++;
		return Spaceship.LOCKER_CREATION_SUCCESSFUL;
	}
	
	/**
	 * Checks if the given crew id is a valid one
	 * @param crewID Crew id to be tested
	 * @return True if the given id is a valid one, false otherwise
	 */
	private boolean isIdValid(int crewID) {
		for(int id : crewIds) {
			if(id == crewID)
				return true;
		}
		return false;
	}
	
	
	/**
	 * Getter for the crew id array
	 * @return This spaceship crew id array
	 */
	public int[] getCrewIDs() {
		return crewIds;
	}
	
	/**
	 * Getter for the locker array
	 * @return This spaceship locker array
	 */
	public Locker[] getLockers() {
		return lockers;
	}
}
