package oop.ex6.parsers.initial;

/**
 * This a line type enum, used to fetch the correct filter method to verify the state of a certain line
 */
public enum LineTypes {
	/**
	 * An empty line containing nothing
	 */
	EMPTY, 
	/**
	 * A comment line containing a comment
	 */
	COMMENT, 
	/**
	 * A Code block opener (opening curly bracket)
	 */
	CODE_OPEN, 
	/**
	 * A code line 
	 */
	CODE, 
	/**
	 * A Code block closer (closing curly bracket)
	 */
	CODE_CLOSE
}
