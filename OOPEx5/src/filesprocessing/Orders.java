package filesprocessing;

/**
 * Orders enum, contain enum values for all valid orders
 */
public enum Orders {
	ABS, TYPE, SIZE, BAD_ORDER;
	
	
	/**
	 * 
	 * @param name The name of the order as it should appear in the commands file
	 * @return The corresponding order in the enum, BAD_ORDER is returned for all non
	 * valid orders
	 */
	public static Orders getOrder(String name) {
		switch(name) {
		case "abs":
			return Orders.ABS;
		case "type":
			return Orders.TYPE;
		case "size":
			return Orders.SIZE;
		default:
			return Orders.BAD_ORDER;
		}
	}
}
