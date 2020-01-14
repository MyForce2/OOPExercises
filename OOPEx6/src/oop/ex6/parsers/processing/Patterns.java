package oop.ex6.parsers.processing;

import java.util.regex.Pattern;

public class Patterns {
	
	
	public static final Pattern VOID_METHOD_DECLARATION =
			Pattern.compile("[ ]*void[ ]*[a-zA-Z_][\\w]*[ ]*[(][\\w\\W][)][ ]*[{]");
	
	public static final Pattern IF_EXPRESSION =
			Pattern.compile("[ ]*if[ ]*[(][\\w\\W][)][ ]*[{]");
	
	public static final Pattern WHILE_EXPRESSION =
			Pattern.compile("[ ]*while[ ]*[(][\\w\\W][)][ ]*[{]");
	
	public static final Pattern CLOSING_BRACKET = 
			Pattern.compile("[ ]*[}][ ]*");
	
	public static final Pattern METHOD_CALL =
			Pattern.compile("[ ]*[a-zA-Z][\\w]*[(][\\w\\W]*[)][;]");
	
	public static final Pattern INT_VALUE = 
			Pattern.compile("[\\d]+");
	
	public static final Pattern DOUBLE_VALUE =
			Pattern.compile("(([\\d]+[.]?[\\d]*)|([\\d]*[.][\\d]+))");
	
	public static final Pattern BOOLEAN_VALUE =
			Pattern.compile("(true|false)");
	
	public static final Pattern CHAR_VALUE =
			Pattern.compile("[\'][\\w\\W]?[\']");
	
	public static final Pattern STRING_VALUE =
			Pattern.compile("[\"][\\w\\W]*[\"]");
	
	public static final Pattern INT_DECLRATION = 
			Pattern.compile("(final|)[ ]*int[ ]*[a-zA-Z_][\\w]*[ ]*[;]?[ ]*");
	public static final Pattern DOUBLE_DECLARATION = 
			Pattern.compile("(final|)[ ]*double[ ]*[a-zA-Z_][\\w]*[ ]*[;]?[ ]*");
	public static final Pattern BOOLEAN_DECLARATION = 
			Pattern.compile("(final|)[ ]*boolean[ ]*[a-zA-Z_][\\w]*[ ]*[;]?[ ]*");
	public static final Pattern CHAR_DECLARATION = 
			Pattern.compile("(final|)[ ]*char[ ]*[a-zA-Z_][\\w]*[ ]*[;]?[ ]*");
	public static final Pattern STRING_DECLARATION = 
			Pattern.compile("(final|)[ ]*String[ ]*[a-zA-Z_][\\w]*[ ]*[;]?[ ]*");
	
	public static final Pattern INT_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*int[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[\\d]+[ ]*[;]?[ ]*");
	public static final Pattern DOUBLE_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*double[ ]*[a-zA-Z_][\\w]*[ ]*="
					+ "[ ]*(([\\d]+[.]?[\\d]*)|([\\d]*[.][\\d]+))[;]?[ ]*");
	public static final Pattern BOOLEAN_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*boolean[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*(false|true)[ ]*[;]?[ ]*");
	public static final Pattern CHAR_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*char[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*'[\\w\\W]?'[ ]*[;]?[ ]*");
	public static final Pattern STRING_DECLARATION_LITERAL_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*String[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[\"][\\w\\W]*[\"][ ]*[;]?[ ]*");
	
	public static final Pattern INT_LITERAL_ASSIGNMENT = 
			Pattern.compile("[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[\\d]+[ ]*[;]?[ ]*");
	public static final Pattern DOUBLE_LITERAL_ASSIGNMENT = 
			Pattern.compile("[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*([\\d]+[.]?[\\d]*)|([\\d]*[.][\\d]+)[ ]*[;]?[ ]*");
	public static final Pattern BOOLEAN_LITERAL_ASSIGNMENT = 
			Pattern.compile("[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*(false|true)[ ]*[;]?[ ]*");
	public static final Pattern CHAR_LITERAL_ASSIGNMENT = 
			Pattern.compile("[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*'[\\w\\W]?'[ ]*[;]?[ ]*");
	public static final Pattern STRING_LITERAL_ASSIGNMENT = 
			Pattern.compile("[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[\"][\\w\\W]*][\"][ ]*[;]?[ ]*");
	
	public static final Pattern INT_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*int[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[a-zA-Z_][\\w]*[;]?[ ]*");
	public static final Pattern DOUBLE_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*double[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[a-zA-Z_][\\w]*[;]?[ ]*");
	public static final Pattern BOOLEAN_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*boolean[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[a-zA-Z_][\\w]*[;]?[ ]*");
	public static final Pattern CHAR_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*char[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[a-zA-Z_][\\w]*[;]?[ ]*\"");
	public static final Pattern STRING_DECLARATION_ASSIGNMENT =
			Pattern.compile("(final|)[ ]*String[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[a-zA-Z_][\\w]*[;]?[ ]*");
	
	
	public static final Pattern ASSIGNMENT = 
			Pattern.compile("[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[a-zA-Z_][\\w]*[;]?[ ]*");
}
