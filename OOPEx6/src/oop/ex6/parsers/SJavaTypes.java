package oop.ex6.parsers;

import java.util.regex.Pattern;

import oop.ex6.parsers.processing.Patterns;

/**
 * An enum representing the s-java types
 */
public enum SJavaTypes {
	INT, DOUBLE, BOOLEAN, CHAR, STRING;
	
	
	/**
	 * @param type A textual representation of any SJavaType
	 * @return The SJavaTypes enum value of the given string
	 */
	public static SJavaTypes getType(String type) {
		switch(type) {
		case "int":
			return INT;
		case "double":
			return DOUBLE;
		case "boolean":
			return BOOLEAN;
		case "char":
			return CHAR;
		case "String":
			return STRING;
		default:
			return STRING;
		}
	}
	
	/**
	 * @param type A SJavaType enum value
	 * @return The textual representation of the given SJavaType enum value
	 * default value
	 */
	public static String getDefaultVal(SJavaTypes type) {
		switch(type) {
		case INT:
			return "0";
		case DOUBLE:
			return "0";
		case BOOLEAN:
			return "true";
		case CHAR:
			return "\'a\'";
		case STRING:
			return "";
		default:
			return "";
		}
	}
	
	/**
	 * @param type A SJavaType enum value
	 * @return The textual of the given SJavaType enum value 
	 */
	public static String getType(SJavaTypes type) {
		switch(type) {
		case INT:
			return "int";
		case DOUBLE:
			return "double";
		case BOOLEAN:
			return "boolean";
		case CHAR:
			return "char";
		case STRING:
			return "String";
		default:
			return "";
		}
	}
	
	/**
	 * @param type A SJavaType enum value
	 * @return The Declaration pattern for the given type
	 */
	public static Pattern getDeclarationPattern(SJavaTypes type) {
		switch(type) {
		case INT:
			return Patterns.INT_DECLRATION;
		case DOUBLE:
			return Patterns.DOUBLE_DECLARATION;
		case BOOLEAN:
			return Patterns.BOOLEAN_DECLARATION;
		case CHAR:
			return Patterns.CHAR_DECLARATION;
		case STRING:
			return Patterns.STRING_DECLARATION;
		default:
			return null;
		}
	}
	
	/**
	 * @param type A SJavaType enum value
	 * @return The declaration and assignment to a literal pattern for
	 * the given type
	 */
	public static Pattern getDeclarationLiteralAssignmentPattern(SJavaTypes type) {
		switch(type) {
		case INT:
			return Patterns.INT_DECLARATION_LITERAL_ASSIGNMENT;
		case DOUBLE:
			return Patterns.DOUBLE_DECLARATION_LITERAL_ASSIGNMENT;
		case BOOLEAN:
			return Patterns.BOOLEAN_DECLARATION_LITERAL_ASSIGNMENT;
		case CHAR:
			return Patterns.CHAR_DECLARATION_LITERAL_ASSIGNMENT;
		case STRING:
			return Patterns.STRING_DECLARATION_LITERAL_ASSIGNMENT;
		default:
			return null;
		}
	}
	
	/**
	 * @param type A SJavaType enum value
	 * @return The literal assignment pattern for a variable of the given
	 * type
	 */
	public static Pattern getLiteralAssignmentPattern(SJavaTypes type) {
		switch(type) {
		case INT:
			return Patterns.INT_LITERAL_ASSIGNMENT;
		case DOUBLE:
			return Patterns.DOUBLE_LITERAL_ASSIGNMENT;
		case BOOLEAN:
			return Patterns.BOOLEAN_LITERAL_ASSIGNMENT;
		case CHAR:
			return Patterns.CHAR_LITERAL_ASSIGNMENT;
		case STRING:
			return Patterns.STRING_LITERAL_ASSIGNMENT;
		default:
			return null;
		}
	}
	
	/**
	 * @param type A SJavaType enum value
	 * @return The declaration and assignment pattern for a variable
	 * of the given type
	 */
	public static Pattern getDeclarationAssignmentPattern(SJavaTypes type) {
		switch(type) {
		case INT:
			return Patterns.INT_DECLARATION_ASSIGNMENT;
		case DOUBLE:
			return Patterns.DOUBLE_DECLARATION_ASSIGNMENT;
		case BOOLEAN:
			return Patterns.BOOLEAN_DECLARATION_ASSIGNMENT;
		case CHAR:
			return Patterns.CHAR_DECLARATION_ASSIGNMENT;
		case STRING:
			return Patterns.STRING_DECLARATION_ASSIGNMENT;
		default:
			return null;
		}
	}
	
	/**
	 * @param type A SJavaType enum value
	 * @return The literal pattern for the given type
	 */
	public static Pattern getLiteralPattern(SJavaTypes type) {
		switch(type) {
		case INT:
			return Patterns.INT_VALUE;
		case DOUBLE:
			return Patterns.DOUBLE_VALUE;
		case BOOLEAN:
			return Patterns.BOOLEAN_VALUE;
		case CHAR:
			return Patterns.CHAR_VALUE;
		case STRING:
			return Patterns.STRING_VALUE;
		default:
			return null;
		}
	}
	
	/**
	 * @return The assignment pattern for any variable to another variable
	 */
	public static Pattern getAssignmentPattern() {
		return Patterns.ASSIGNMENT;
	}
}
