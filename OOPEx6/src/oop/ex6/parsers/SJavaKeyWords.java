package oop.ex6.parsers;

/**
 * An enum representing the reserved key words in s-java
 */
public enum SJavaKeyWords {
	VOID, FINAL, IF, WHILE, TRUE, FALSE, RETURN;
	
	/**
	 * @param keyWord The textual representation of the SJava key word
	 * @return An SJavaKeyWords enum value
	 */
	public static SJavaKeyWords getType(String keyWord) {
		switch(keyWord) {
		case "void":
			return VOID;
		case "final":
			return FINAL;
		case "if":
			return IF;
		case "while":
			return WHILE;
		case "true":
			return TRUE;
		case "false":
			return FALSE;
		case "return":
			return RETURN;
		default:
			return VOID;
		}
	}
	
	/**
	 * @param keyWord An SJavaKeyWords enum value
	 * @return The textual representation of the SJavaKeyWords key words
	 */
	public static String getType(SJavaKeyWords keyWord) {
		switch(keyWord) {
		case VOID:
			return "void";
		case FINAL:
			return "final";
		case IF:
			return "if";
		case WHILE:
			return "while";
		case TRUE:
			return "true";
		case FALSE:
			return "false";
		case RETURN:
			return "return";
		default:
			return "";
		}
	}
}
