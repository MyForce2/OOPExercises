/**
 * The runner ship type, This spaceship attempts 
 * to run away from the fight
 */
public class RunnerShip extends SpaceShip {
	
	/**
	 * The minimal distance from another ship, under it the runner ship
	 * may feel threatened 
	 */
	private static final double TELEPORT_DISTANCE = 0.25d;
	
	/**
	 * The minimal angle to another ship, under it when close enough
	 * (TELEPORT_DISTANCE) the runner will teleport
	 */
	private static final double TELEPORT_ANGLE = 0.23d;

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
	@Override
	public void doAction(SpaceWars game) {
		this.startRound();
		RunnerShip.runnerTurn(this, game);
		this.endRound();
	}
	
	/**
	 * Performs a runner turn for a certain ship
	 * @param runner the spaceship which performs the turn 
	 * @param game the game object to which this ship belongs.
	 */
	public static void runnerTurn(SpaceShip runner, SpaceWars game) {
		SpaceShip closestShip = game.getClosestShipTo(runner);
		boolean teleported = false;
		double distanceToClosestShip = runner.physics.distanceFrom(closestShip.getPhysics());
		double angleRelativeToClosestShip = runner.physics.angleTo(closestShip.getPhysics());
		// Since an angle can be both negative and positive, we use Math.abs to perform the comparison
		// An angle of -1.0 radians should work without using Math.abs but that would be incorrect behavior 
		if(distanceToClosestShip < TELEPORT_DISTANCE && Math.abs(angleRelativeToClosestShip) < TELEPORT_ANGLE) {
			runner.teleport();
			teleported = true;
		}
		if(teleported) {
			// Since we changed locations we need re initialize these variables
			distanceToClosestShip = runner.physics.distanceFrom(closestShip.getPhysics());
			angleRelativeToClosestShip = runner.physics.angleTo(closestShip.getPhysics());
		}
		// Runner tries to run away so we want the opposite angle
		int turn = runner.getTurnAngle(closestShip) * -1;
		runner.physics.move(true, turn);
	}
}
