import java.awt.Image;

import oop.ex2.*;

/**
 * The human ship type, controlled by the user.
 * The keys are: left-arrow and right-arrow to
 * turn, up-arrow to accelerate, ’d’ to fire a shot, ’s’ to turn on the shield, ’a’ to teleport
 */
public class HumanShip extends SpaceShip {


	/**
	 * Gets the image of this ship. This method should return the image of the
	 * ship with or without the shield. This will be displayed on the GUI at
	 * the end of the round.
	 * 
	 * @return the image of this ship.
	 */
	@Override
	public Image getImage() {
		if(this.shieldUp)
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		return GameGUI.SPACESHIP_IMAGE;
	}

	/**
	 * Does the actions of this ship for this round. 
	 * This is called once per round by the SpaceWars game driver.
	 * 
	 * @param game the game object to which this ship belongs.
	 */
	@Override
	public void doAction(SpaceWars game) {
		this.startRound();
		GameGUI gui = game.getGUI();
		if(gui.isTeleportPressed()) 
			this.teleport();
		this.move(game);
		if(gui.isShieldsPressed())
			this.shieldOn();
		if(gui.isShotPressed())
			this.fire(game);
		this.endRound();
	}
	
	/**
	 * Handles all of the movement for the human controlled ship
	 * @param game the game object to which this ship belongs.
	 */
	private void move(SpaceWars game) {
		GameGUI gui = game.getGUI();
		int turn = 0;
		if(gui.isLeftPressed() && gui.isRightPressed())
			turn = 0;
		else if(gui.isLeftPressed())
			turn = SpaceShip.LEFT_TURN;
		else if(gui.isRightPressed())
			turn = SpaceShip.RIGHT_TURN;
		this.physics.move(gui.isUpPressed(), turn);
	}
}
