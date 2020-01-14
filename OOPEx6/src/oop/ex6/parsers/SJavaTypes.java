package oop.ex6.parsers;

import java.util.regex.Pattern;

import oop.ex6.parsers.processing.Patterns;

/**
 * An enum representing the s-java types
 */
public enum SJavaTypes {
	INT, DOUBLE, BOOLEAN, CHAR, STRING;
	
	
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
	
	public static Pattern getAssignmentPattern() {
		return Patterns.ASSIGNMENT;
	}
}
