package filesprocessing;

/**
 * A Section class, Section is just a POD class
 * to wrap the order text and filter text in a given section
 */
public class Section {
	
	/**
	 * The text of the order sub section
	 */
	private String orderText;
	
	/**
	 * The text of the filter sub section
	 */
	private String filterText;
	
	private int startingLine;
	
	/**
	 * No text, default text for this class
	 */
	public static final String NO_TEXT = "";
	
	/**
	 * Section default constructor, initializes both fields to the 
	 * constant field NO_TEXT
	 */
	public Section(int startingLine) {
		orderText = NO_TEXT;
		filterText = NO_TEXT;
		this.startingLine = startingLine;
	}

	
	/**
	 * @return Text of the order sub section of this section
	 */
	public String getOrderText() {
		return orderText;
	}

	/**
	 * @param orderText Sets this orderText to the given orderText
	 */
	public void setOrderText(String orderText) {
		this.orderText = orderText;
	}
	
	/** 
	 * @return Text of the filter sub section
	 */
	public String getFilterText() {
		return filterText;
	}

	/**
	 * @param filterText Sets this filterText to the given filterText
	 */
	public void setFilterText(String filterText) {
		this.filterText = filterText;
	}
	
	
	public int getStartingLine() {
		return startingLine;
	}
}
