/**
 * The basher ship type, this ship attempts to collide with other ships.
 *
 */
public class BasherShip extends SpaceShip {
	
	public static final double SHIELD_ACTIVATION_DISTANCE = 0.19d;

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	@Override
	public void doAction(SpaceWars game) {
		this.startRound();
		SpaceShip closestShip = game.getClosestShipTo(this);
		this.moveToClosest(closestShip);
		BasherShip.basherTurn(this, closestShip);
		this.endRound();
	}
	
	/**
	 * Performs a basher turn for a certain ship.
	 * @param basher the spaceship
	 * @param closestShip closest ship
	 */
	public static void basherTurn(SpaceShip basher, SpaceShip closestShip) {
		double distanceToClosestShip = basher.physics.distanceFrom(closestShip.getPhysics());
		if(distanceToClosestShip <= SHIELD_ACTIVATION_DISTANCE)
			basher.shieldOn();
	}
	

}
