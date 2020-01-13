package oop.ex6.parsers;

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
	
	public static String getRegexDeclaration(SJavaTypes type) {
		switch(type) {
		case INT:
			return "(final|)[ ]*int[ ]*[a-zA-Z_][\\w]*[ ]*;[ ]*";
		case DOUBLE:
			return "(final|)[ ]*double[ ]*[a-zA-Z_][\\w]*[ ]*;[ ]*";
		case BOOLEAN:
			return "(final|)[ ]*boolean[ ]*[a-zA-Z_][\\w]*[ ]*;[ ]*";
		case CHAR:
			return "(final|)[ ]*char[ ]*[a-zA-Z_][\\w]*[ ]*;[ ]*";
		case STRING:
			return "(final|)[ ]*String[ ]*[a-zA-Z_][\\w]*[ ]*;[ ]*";
		default:
			return "";
		}
	}
	
	public static String getRegexDeclarationAndAssigment(SJavaTypes type) {
		switch(type) {
		case INT:
			return "(final|)[ ]*int[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*([\\d]+|[a-zA-Z_][\\w]*)[ ]*;[ ]*";
		case DOUBLE:
			return "(final|)[ ]*double[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[\\d]+[.]?[\\d]*[ ]*;[ ]*";
		case BOOLEAN:
			return "(final|)[ ]*boolean[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*(false|true)[ ]*;[ ]*";
		case CHAR:
			return "(final|)[ ]*char[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*'[\\w\\W]?'[ ]*;[ ]*";
		case STRING:
			return "(final|)[ ]*String[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*\"[\\w\\W]*]\"[ ]*;[ ]*";
		default:
			return "";
		}
	}
	
	public static String getRegexAssigment(SJavaTypes type) {
		switch(type) {
		case INT:
			return "[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[\\d]+[ ]*;[ ]*";
		case DOUBLE:
			return "[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*[\\d]+[.]?[\\d]*[ ]*;[ ]*";
		case BOOLEAN:
			return "[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*(false|true)[ ]*;[ ]*";
		case CHAR:
			return "[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*'[\\w\\W]?'[ ]*;[ ]*";
		case STRING:
			return "[ ]*[a-zA-Z_][\\w]*[ ]*=[ ]*\"[\\w\\W]*]\"[ ]*;[ ]*";
		default:
			return "";
		}
	}
}
