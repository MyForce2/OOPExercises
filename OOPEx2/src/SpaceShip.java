import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 *  This class acts as the base class to the other ship types, and is an abstract
 *  class.
 *  It implements all of the functionality that is identical across all ship types
 *  so that less code is used.
 *  
 * @author oop
 */
public abstract class SpaceShip {
	
	
	/**
	 * The current amount of health a spaceship has
	 * Starts at 22
	 */
	protected int health;
	
	/**
	 * The amount of energy a ship currently has
	 */
	protected int currentEnergy;
	
	/**
	 * The current max amount of energy a ship can have
	 */
	protected int energyCap;
	
	/**
	 * The space ship physics object this ship has, manages all the
	 * physics actions related to this ship (e.g collision)
	 */
	protected SpaceShipPhysics physics;
	
	
	/**
	 * True if this ship is currently dead, false otherwise
	 */
	protected boolean dead;
	
	/**
	 * True if this ship shield is currently up, false otherwise
	 */
	protected boolean shieldUp;
	
	/**
	 * The amount of rounds that passed since a ship has last
	 * used it's guns
	 */
	protected int roundsSinceGunsUsed;
	

	
	/**
	 * The amount of health a ship starts with
	 */
	public static final int STARTING_HEALTH = 22;
	
	/**
	 * The amount of health lost when ships collides
	 * Both ships lose the same the amount of health
	 */
	public static final int COLLISION_HEALTH_LOST = 1;
	
	/**
	 * The starting cap for energy levels
	 */
	public static final int STARTING_MAX_ENERGY = 210;
	
	/**
	 * The amount of energy a ship starts with
	 */
	public static final int STARTING_CURRENT_ENERGY = 190;
	
	/**
	 * The bonus to a ship's energy cap after it bashes another ship
	 */
	public static final int ENERGY_BASHING_BONUS = 18;
	
	/**
	 * The amount reduced from a ship's energy cap after a ship
	 * bashes it, or it was hit by a shot
	 */
	public static final int ENERGY_REDUCTION = 10;
	
	/**
	 * The amount of energy gained per round
	 */
	public static final int ENERGY_PER_ROUND = 1;
	
	/**
	 * The amount of energy required to fire
	 */
	public static final int ENERGY_TO_FIRE = 19;
	
	/**
	 * The amount of energy required to teleport
	 */
	public static final int ENERGY_TO_TELEPORT = 140;
	
	/**
	 * The amount of energy used if a ship's shields are up
	 * in a certain round
	 */
	public static final int ENERGY_PER_SHIELD_ROUND = 3;
	
	/**
	 * The amount of rounds a ship has to wait until it can
	 * fire again
	 */
	public static final int GUNS_ROUND_COOLDOWN = 7;
	
	/**
	 * Right turn value for the physics class
	 */
	public static final int RIGHT_TURN = -1;
	
	/**
	 * Left turn value for the physics class
	 */
	public static final int LEFT_TURN = 1;
	

	public SpaceShip() {
		init();
	}
   
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip() {
    	if(!this.shieldUp) {
    		shotCollisionPenalty();
    		return;
    	}
    	this.energyCap += ENERGY_BASHING_BONUS;
    	this.currentEnergy += ENERGY_BASHING_BONUS;
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset() {
    	init();
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
    	return this.health <= 0;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
    	if(this.shieldUp)
    		return;
    	this.shotCollisionPenalty();
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage() {
		if(this.shieldUp)
			return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
    	if(this.gunsOnCooldown())
    		return;
    	if(this.currentEnergy < ENERGY_TO_FIRE)
    		return;
    	game.addShot(this.physics);
    	this.currentEnergy -= ENERGY_TO_FIRE;
    	this.roundsSinceGunsUsed = 0;
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
    	// Checks if there's enough energy to use the shields for this round
    	if(this.currentEnergy < ENERGY_PER_SHIELD_ROUND)
    		return;
        this.shieldUp = true;
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
       if(this.currentEnergy < ENERGY_TO_TELEPORT)
    	   return;
       this.physics = new SpaceShipPhysics();
       this.currentEnergy -= ENERGY_TO_TELEPORT;
 
    }
    
    /**
     * 
     * @return True if this ship's guns are on cool down, false otherwise
     */
    protected boolean gunsOnCooldown() {
    	return this.roundsSinceGunsUsed < 7 && this.roundsSinceGunsUsed != -1;
    }
    
    /**
     * Resets the round specific variables
     */
    protected void startRound() {
    	this.shieldUp = false;
    	this.roundsSinceGunsUsed += this.roundsSinceGunsUsed == -1 ? 0 : 1;
    }
    
    /**
     * Performs the constant actions at the end of each round
     * e.g adding energy
     */
    protected void endRound() {
    	this.currentEnergy += this.currentEnergy < this.energyCap ? 1 : 0;
    }
    
    /**
     * 
     * @return true if the shields are up, false otherwise
     */
    protected boolean areShieldsUp() {
    	return this.shieldUp;
    }
    
    /**
     * Reduces health and energy cap, used when hit by a shot or a collision 
     * happens while the shields are down
     */
    protected void shotCollisionPenalty() {
    	this.health--;
    	this.energyCap -= ENERGY_REDUCTION;
    	this.currentEnergy = this.currentEnergy > this.energyCap ? this.energyCap : this.currentEnergy;
    }
    
    /**
     * Returns the turn value used in the move method,
     * 1 = RIGHT_TURN, 2 = LEFT_TURN, 0 = Forward
     * @param ship the ship you want to turn towards
     * @return the turn value used to turn in the move method
     */
    protected int getTurnAngle(SpaceShip ship) {
		int turn = 0;
		double angle = this.physics.angleTo(ship.getPhysics());
		if(angle > 0)
			turn = SpaceShip.LEFT_TURN;
		else if(angle < 0)
			turn = SpaceShip.RIGHT_TURN;
		return turn;
    }
    
    protected void moveToClosest(SpaceShip closestShip) {
		int turn = this.getTurnAngle(closestShip);
		this.physics.move(true, turn);
    }
    
    /**
     * Initializes all of the ship's variables, used in the constructor and when
     * a ship dies in the reset method
     */
    protected void init() {
		this.health = SpaceShip.STARTING_HEALTH;
		this.currentEnergy = SpaceShip.STARTING_CURRENT_ENERGY;
		this.energyCap = SpaceShip.STARTING_MAX_ENERGY;
		this.physics = new SpaceShipPhysics();
		this.shieldUp = false;
		// Starts at -1 so we know it was never used
		this.roundsSinceGunsUsed = -1;
    }
    
}
