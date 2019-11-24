
/**
 * 
 * The special ship type, a combo of the Aggressive ship and the Basher
 * ship, thus making it very hard to destroy and tough to play against
 */
public class SpecialShip extends SpaceShip {
	

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
		AggressiveShip.aggressiveTurn(this, closestShip, game);
		this.endRound();
	}

}
