package oop.ex6.parsers.processing;

import java.util.regex.Pattern;

/**
 *	Patterns class, contains all of various valid patterns for
 *  A SJava file
 */
public class Patterns {
	
	/**
	 * Pattern for used to test reserved key words names
	 */
	public static final Pattern RESERVED_KEY_WORDS_NAMES =
			Pattern.compile("[\\s]*(void|final|true|false|void|if|while|return)[\\s]*");
	
	/**
	 * Pattern for used to find more than one white space of any 
	 * white space type
	 */
	public static final Pattern MORE_THAN_ONE_SPACE =
			Pattern.compile("[\\s]{2,}");
	
	/**
	 * Pattern for a return line
	 */
	public static final Pattern RETURN_LINE = 
			Pattern.compile("[\\s]*return[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a void method declaration
	 */
	public static final Pattern VOID_METHOD_DECLARATION =
			Pattern.compile("[\\s]*void[\\s]+[a-zA-Z][\\w]*[\\s]*[(][\\w,\\s]*[)][\\s]*[{]");
	
	/**
	 * Pattern for calling a method
	 */
	public static final Pattern CALLING_METHOD = 
			Pattern.compile("[\\s]*[a-zA-Z][\\w]*[\\s]*[(][\\w,.\"\'\\s-]*[)][\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for an if expression
	 */
	public static final Pattern IF_EXPRESSION =
			Pattern.compile("[\\s]*if[\\s]*[(][\\w\\s&|.]+[)][\\s]*[{]");
	
	/**
	 * Pattern for a while expression
	 */
	public static final Pattern WHILE_EXPRESSION =
			Pattern.compile("[\\s]*while[\\s]*[(][\\w\\s&|.]+[)][\\s]*[{]");
	
	/**
	 * Pattern for the condition separator in a while / if expression
	 */
	public static final Pattern CONDITION_SEPARATOR =
			Pattern.compile("([&]{2})|([|]{2})");
	
	/**
	 * Pattern for a closing bracker
	 */
	public static final Pattern CLOSING_BRACKET = 
			Pattern.compile("[\\s]*[}][\\s]*");
	
	/**
	 * Pattern for an int literal
	 */
	public static final Pattern INT_VALUE = 
			Pattern.compile("[-]?[\\d]+");
	
	/**
	 * Pattern for a double literal
	 */
	public static final Pattern DOUBLE_VALUE =
			Pattern.compile("[-]?(([\\d]+[.]?[\\d]*)|([\\d]*[.][\\d]+))");
	
	/**
	 * Pattern for a boolean literal
	 */
	public static final Pattern BOOLEAN_VALUE =
			Pattern.compile("(true|false)");
	
	/**
	 * Pattern for a char literal
	 */
	public static final Pattern CHAR_VALUE =
			Pattern.compile("[\'][\\w\\W]?[\']");
	
	/**
	 * Pattern for a string literal
	 */
	public static final Pattern STRING_VALUE =
			Pattern.compile("[\"][\\w\\W]*[\"]");
	
	/**
	 * Pattern for an int declaration without an assignment
	 */
	public static final Pattern INT_DECLRATION = 
			Pattern.compile("(final|)[\\s]*int[\\s]*[a-zA-Z_][\\w]*[\\s]*[;]?[\\s]*");

	/**
	 * Pattern for a double declaration without an assignment
	 */
	public static final Pattern DOUBLE_DECLARATION = 
			Pattern.compile("(final|)[\\s]*double[\\s]*[a-zA-Z_][\\w]*[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a boolean declaration without an assignment
	 */
	public static final Pattern BOOLEAN_DECLARATION = 
			Pattern.compile("(final|)[\\s]*boolean[\\s]*[a-zA-Z_][\\w]*[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a char declaration without an assignment
	 */
	public static final Pattern CHAR_DECLARATION = 
			Pattern.compile("(final|)[\\s]*char[\\s]*[a-zA-Z_][\\w]*[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a string declaration without an assignment
	 */
	public static final Pattern STRING_DECLARATION = 
			Pattern.compile("(final|)[\\s]*String[\\s]*[a-zA-Z_][\\w]*[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for an int assignment with a literal value assignment
	 */
	public static final Pattern INT_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*int[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*[-]?[\\d]+[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a double assignment with a literal value assignment
	 */
	public static final Pattern DOUBLE_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*double[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*[-]?(([\\d]+[.]?[\\d]*)|([\\d]*[.][\\d]+))[;]?[\\s]*");
	
	/**
	 * Pattern for a boolean assignment with a literal value assignment
	 */
	public static final Pattern BOOLEAN_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*boolean[\\s]*[a-zA-Z_][\\w]*[\\s]*=[\\s]*"
					+ "((false|true)|([\\d]+])|" + DOUBLE_VALUE.pattern() + ")[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a char assignment with a literal value assignment
	 */
	public static final Pattern CHAR_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*char[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*'[\\w\\W]?'[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a string assignment with a literal value assignment
	 */
	public static final Pattern STRING_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*String[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*[\"][\\w\\W]*[\"][\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for an int variable literal assignment
	 */
	public static final Pattern INT_LITERAL_ASSIGNMENT = 
			Pattern.compile("[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*[-]?[\\d]+[\\s]*[;]?[\\s]*");

	/**
	 * Pattern for a double variable literal assignment
	 */
	public static final Pattern DOUBLE_LITERAL_ASSIGNMENT = 
			Pattern.compile("[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*[-]?(([\\d]+[.]?[\\d]*)|"
					+ "([\\d]*[.][\\d]+))[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a boolean variable literal assignment
	 */
	public static final Pattern BOOLEAN_LITERAL_ASSIGNMENT = 
			Pattern.compile("[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*(false|true)[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a char variable literal assignment
	 */
	public static final Pattern CHAR_LITERAL_ASSIGNMENT = 
			Pattern.compile("[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*'[\\w\\W]?'[\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for a string variable literal assignment
	 */
	public static final Pattern STRING_LITERAL_ASSIGNMENT = 
			Pattern.compile("[\\s]*[a-zA-Z_][\\w]*[\\s]*="
					+ "[\\s]*[\"][\\w\\W]*][\"][\\s]*[;]?[\\s]*");
	
	/**
	 * Pattern for an int declaration with an assignment to another variable
	 */
	public static final Pattern INT_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*int[\\s]*[a-zA-Z_][\\w]*[\\s]*"
					+ "=[\\s]*[a-zA-Z_][\\w]*[;]?[\\s]*");
	
	/**
	 * Pattern for a double declaration with an assignment to another variable
	 */
	public static final Pattern DOUBLE_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*double[\\s]*[a-zA-Z_][\\w]*[\\s]*"
					+ "=[\\s]*[a-zA-Z_][\\w]*[;]?[\\s]*");
	
	/**
	 * Pattern for a boolean declaration with an assignment to another variable
	 */
	public static final Pattern BOOLEAN_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*boolean[\\s]*[a-zA-Z_][\\w]*[\\s]*"
					+ "=[\\s]*[a-zA-Z_][\\w]*[;]?[\\s]*");
	
	/**
	 * Pattern for a char declaration with an assignment to another variable
	 */
	public static final Pattern CHAR_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*char[\\s]*[a-zA-Z_][\\w]*[\\s]*"
					+ "=[\\s]*[a-zA-Z_][\\w]*[;]?[\\s]*\"");
	
	/**
	 * Pattern for a string declaration with an assignment to another variable
	 */
	public static final Pattern STRING_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[\\s]*String[\\s]*[a-zA-Z_][\\w]*[\\s]*"
					+ "=[\\s]*[a-zA-Z_][\\w]*[;]?[\\s]*");
	
	/**
	 * Pattern for a variable assignment to another variable
	 */
	public static final Pattern ASSIGNMENT = 
			Pattern.compile("[\\s]*[a-zA-Z_][\\w]*[\\s]*"
					+ "=[\\s]*[a-zA-Z_][\\w]*[;]?[\\s]*");
}
