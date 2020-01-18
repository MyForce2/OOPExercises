package oop.ex6.parsers.initial;

/**
 * A FileLine objects, represents a line in the file
 * holding the LineType, line number, line data
 * @author nadav
 *
 */
public class FileLine {
	
	/**
	 * Line's line type
	 */
	private LineTypes type;
	
	/**
	 * The line number
	 */
	private int lineNum;
	
	/**
	 * The actual data of the file line
	 */
	private String data;
	
	/**
	 * A FileLine constructor
	 * @param data The data, text of the line
	 * @param type The line type, LineTypes enum value
	 * @param lineNum The line number
	 */
	public FileLine(String data, LineTypes type, int lineNum) {
		this.data = data;
		this.type = type;
		this.lineNum = lineNum;
	}
	
	/**
	 * @return The line num
	 */
	public int getLineNum() {
		return lineNum;
	}
	
	/**
	 * @return The data of this line, the textual representation of this line
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * @return The type of the line
	 */
	public LineTypes getType() {
		return type;
	}
}
