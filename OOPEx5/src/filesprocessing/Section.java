package filesprocessing;

public class Section {
	
	private String orderText;
	private String filterText;
	
	public static final String NO_TEXT = "";
	
	public Section() {
		orderText = NO_TEXT;
		filterText = NO_TEXT;
		
	}

	public String getOrderText() {
		return orderText;
	}


	public void setOrderText(String orderText) {
		this.orderText = orderText;
	}


	public String getFilterText() {
		return filterText;
	}


	public void setFilterText(String filterText) {
		this.filterText = filterText;
	}
	
}
