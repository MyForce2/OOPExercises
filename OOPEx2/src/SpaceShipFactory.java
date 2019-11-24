
public class SpaceShipFactory {
	
	
	public static final String HUMAN_SHIP = "h";
	public static final String RUNNER_SHIP = "r";
	public static final String BASHER_SHIP = "b";
	public static final String AGGRESSIVE_SHIP = "a";
	public static final String DRUNKARD_SHIP = "d";
	public static final String SPECIAL_SHIP = "s";
	
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceships = new SpaceShip[args.length];
    	for(int i = 0; i < args.length; i++) {
    		String arg = args[i];
    		switch(arg) {
    		case HUMAN_SHIP:
    			spaceships[i] = new HumanShip();
    			break;
    		case RUNNER_SHIP:
    			spaceships[i] = new RunnerShip();
    			break;
    		case BASHER_SHIP:
    			spaceships[i] = new BasherShip();
    			break;
    		case AGGRESSIVE_SHIP:
    			spaceships[i] = new AggressiveShip();
    			break;
    		case DRUNKARD_SHIP:
    			spaceships[i] = new DrunkardShip();
    			break;
    		case SPECIAL_SHIP:
    			spaceships[i] = new SpecialShip();
    			break;
    		}
    	}
    	return spaceships;
    }
    
}
