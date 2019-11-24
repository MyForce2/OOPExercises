
/**
 * 
 * The aggressive ship type, it pursues other ships and tries to fire
 * at them.
 *
 */
public class AggressiveShip extends SpaceShip {

	/**
	 * The minimal angle between this ship and the closest ship in order
	 * for this ship to fire at the closest one.
	 */
	public static final double FIRE_ANGLE = 0.21d;
	
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
		AggressiveShip.aggressiveTurn(this, closestShip, game);
		this.endRound();
	}
	
	/**
	 * Performs an aggressive turn for a certain spaceship
	 * @param aggressive the spaceship which performs the turn
	 * @param closestShip the closest spaceship to it
	 * @param game the game object to which this ship belongs
	 */
	public static void aggressiveTurn(SpaceShip aggressive, SpaceShip closestShip, SpaceWars game) {
		double angleToNearestShip = aggressive.physics.angleTo(closestShip.getPhysics());
		// Since an angle can be both negative and positive, we use Math.abs to perform the comparison
		// An angle of -1.0 radians should work without using Math.abs but that would be incorrect behavior 
		if(Math.abs(angleToNearestShip) < FIRE_ANGLE) 
			aggressive.fire(game);
	}
	


}
