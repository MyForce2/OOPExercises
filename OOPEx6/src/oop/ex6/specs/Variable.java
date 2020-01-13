package oop.ex6.specs;

import oop.ex6.main.errors.SJavaAssigmentException;
import oop.ex6.parsers.SJavaTypes;

public class Variable {
	
	private SJavaTypes type;
	
	private String name;
	
	private boolean isFinal;
	
	private boolean assigned;
	
	private static final String INT_REGEX = "\\d+";
	
	private static final String DOUBLE_REGEX = "\\d+[.]|\\d+";
	
	private static final String CHARACTER_REGEX = "['][\\w\\W][']";
	
	private static final String STRING_REGEX = "[\"][\\w\\W]+[\"]";
	
	
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	
	public Variable(String name, SJavaTypes type, boolean isFinal, boolean assigned) {
		this.name = name;
		this.type = type;
		this.isFinal = isFinal;
		this.assigned = assigned;
	}
	
	public void assign(String value) throws SJavaAssigmentException {
		if(isFinal)
			throw new SJavaAssigmentException("Trying to assign a value to a final variable ," + this.name);
		switch(type) {
		case INT:
			assignAsInt(value);
			break;
		case DOUBLE:
			assignAsDouble(value);
			break;
		case BOOLEAN:
			assignAsBoolean(value);
			break;
		case CHAR:
			assignAsChar(value);
			break;
		case STRING:
			assignAsString(value);
			break;
		}
	}
	
	public boolean isAssigned() {
		return assigned;
	}
	
	public boolean isFinal() {
		return isFinal;
	}
	
	private void assignAsInt(String value) throws SJavaAssigmentException {
		if(!value.matches(Variable.INT_REGEX))
			throw new SJavaAssigmentException("Assigning a non integer value to an int");
		assigned = true;
	}
	
	private void assignAsDouble(String value) throws SJavaAssigmentException {
		if(!value.matches(Variable.DOUBLE_REGEX))
			throw new SJavaAssigmentException("Assigning a non double value to a double");
		assigned = true;
	}
	
	private void assignAsBoolean(String value) throws SJavaAssigmentException {
		if(!(value.equals(Variable.TRUE) || value.equals(Variable.FALSE)))
			throw new SJavaAssigmentException("Assigning a non boolean value to a boolean");
		assigned = true;
	}
	
	private void assignAsChar(String value) throws SJavaAssigmentException {
		if(!value.matches(Variable.CHARACTER_REGEX))
			throw new SJavaAssigmentException("Assigning a non character value to a char");
		assigned = true;
	}
	
	private void assignAsString(String value) throws SJavaAssigmentException {
		if(!value.matches(Variable.STRING_REGEX))
			throw new SJavaAssigmentException("Assigning a non string value to a string");
		assigned = true;
	}
	
	
	
	
}
