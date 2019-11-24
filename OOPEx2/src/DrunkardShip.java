/**
 * The drunkard ship type, this ship type acts randomly, meaning it's movement and shooting
 * patterns are all randomized and depend on random variables
 *
 */
public class DrunkardShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	@Override
	public void doAction(SpaceWars game) {
		this.startRound();
		DrunkardShip.drunkardTurn(this, game);
		this.endRound();
	}
	
	/**
	 * Performs a drunkard turn for a certain spaceship
	 * @param drunkard the spaceship which performs the turn
	 * @param game the game object to which this ship belongs. 
	 */
	public static void drunkardTurn(SpaceShip drunkard, SpaceWars game) {
		SpaceShip closestShip = game.getClosestShipTo(drunkard);
		double angleToNearestShip = drunkard.physics.angleTo(closestShip.getPhysics());
		// Teleport chance of 10%
		if(((int)(Math.random() * 100)) % 10 == 0)
			drunkard.teleport();
		// Acceleration chance of 20% 
		boolean acc = ((int)(Math.random() * 100)) % 5 == 0;
		// The ship turns left when accelerating, right otherwise
		int turn = acc ? SpaceShip.LEFT_TURN : SpaceShip.RIGHT_TURN;
		drunkard.physics.move(acc, turn);
		// Shooting depends on the angle to the nearest ship and PI / 6 + Random * PI / 3
		if(Math.abs(angleToNearestShip) < (Math.random() * Math.PI / 3 + Math.PI / 6))
			drunkard.fire(game);
	}

}
