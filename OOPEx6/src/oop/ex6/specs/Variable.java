package oop.ex6.specs;

import oop.ex6.main.errors.SJavaAssigmentException;
import oop.ex6.parsers.SJavaKeyWords;
import oop.ex6.parsers.SJavaTypes;
import oop.ex6.parsers.processing.Patterns;

/**
 * A Variable class for SJava variables, holds the state
 * of a SJava variable
 */
public class Variable {
	
	/**
	 * The SJava type of this variable
	 */
	private SJavaTypes type;
	
	/**
	 * The name of this variable
	 */
	private String name;
	
	/**
	 * Is this a final variable
	 */
	private boolean isFinal;
	
	/**
	 * Was this variable assigned a value
	 */
	private boolean assigned;
	
	/**
	 * Used to test whether or not the given name is valid, a
	 * single underscore name isn't valid
	 */
	private static final String UNDERSCORE_PREFIX = "_";
	
	
	/**
	 * True value for a boolean
	 */
	private static final String TRUE = SJavaKeyWords.getType(SJavaKeyWords.TRUE);
	
	/**
	 * False value for a boolean
	 */
	private static final String FALSE = SJavaKeyWords.getType(SJavaKeyWords.FALSE);
	
	/**
	 * Variable constructor
	 * @param name Variable name
	 * @param type Variable type
	 * @param isFinal Is this variable final
	 * @param assigned Is this variable assigned
	 * @throws SJavaAssigmentException Thrown in the case a final variable is created
	 * and isn't assigned, or the name isn't valid
	 */
	public Variable(String name, SJavaTypes type, boolean isFinal, boolean assigned) 
					throws SJavaAssigmentException {
		this.name = name;
		validName();
		this.type = type;
		this.isFinal = isFinal;
		this.assigned = assigned;
		if(this.isFinal && !this.assigned)
			throw new SJavaAssigmentException("Creating a final variable: " + name 
					+ " without assigning it");
	}
	
	/**
	 * A Variable constructor
	 * @param assignVar This variable is assigned to this given variable
	 * @param name Variable name
	 * @param type Variable type
	 * @param isFinal Is this variable final 
	 * @throws SJavaAssigmentException Thrown in the case this variable has an invalid name,
	 * or an assignment to the given variable couldn't be performed
	 */
	public Variable(Variable assignVar, String name, 
			SJavaTypes type, boolean isFinal) throws SJavaAssigmentException{
		this.name = name;
		validName();
		this.type = type;
		assign(assignVar);
		this.isFinal = isFinal;
	}
	
	/**
	 * @throws SJavaAssigmentException Thrown if the given name isn't a valid name
	 */
	private void validName() throws SJavaAssigmentException {
		if(name.startsWith(Variable.UNDERSCORE_PREFIX)) {
			if(name.length() == 1)
				throw new SJavaAssigmentException("Creating a "
						+ "variable of name _");
		}
	}
	
	/**
	 * Assigns the given String value to this variable
	 * @param value Given string value
	 * @throws SJavaAssigmentException Thrown if there is 
	 * an error when attempting to assign
	 * to the given string value
	 */
	public void assign(String value) throws SJavaAssigmentException {
		if(isFinal)
			throw new SJavaAssigmentException("Trying to assign a "
					+ "value to a final variable");
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
	
	/**
	 * Attempts to assign this variable to the given variable
	 * @param assign Given variable
	 * @throws SJavaAssigmentException Thrown in the case this given variable 
	 * can't be assigned to the given variable
	 */
	public void assign(Variable assign) throws SJavaAssigmentException {
		if(isFinal)
			throw new SJavaAssigmentException("Trying to assign a value "
					+ "to a final variable");
		if(!assign.isAssigned())
			throw new SJavaAssigmentException("Trying to assign to a non "
					+ "initialized variable");
		if(!compatibleTypes(type, assign.type))
			throw new SJavaAssigmentException("Trying to assign variables "
					+ "of non compatible types");
		assigned = true;
	}
	
	/**
	 * @param type1 A SJava type
	 * @param type2 A SJava type
	 * @return Return true if a variable of type1 can be assigned to a variable
	 * of type2
	 */
	public static boolean compatibleTypes(SJavaTypes type1, SJavaTypes type2) {
		if(type1.equals(SJavaTypes.DOUBLE)) 
			return type1.equals(type2) || type2.equals(SJavaTypes.INT);
		if(type1.equals(SJavaTypes.BOOLEAN))
			return type1.equals(type2) || type2.equals(SJavaTypes.INT) ||
					type2.equals(SJavaTypes.DOUBLE);
		return type1.equals(type2);
	}
	
	/**
	 * @return Was this variable assigned
	 */
	public boolean isAssigned() {
		return assigned;
	}
	
	/**
	 * @return Is this variable final
	 */
	public boolean isFinal() {
		return isFinal;
	}
	
	/**
	 * Assigns this variable as an int
	 * @param value Given string value
	 * @throws SJavaAssigmentException Thrown in the case the given value
	 * isn't a valid int literal
	 */
	private void assignAsInt(String value) throws SJavaAssigmentException {
		if(!value.matches(Patterns.INT_VALUE.pattern()))
			throw new SJavaAssigmentException("Assigning a non integer value to an int");
		assigned = true;
	}
	
	/**
	 * Assigns this variable as an double
	 * @param value Given string value
	 * @throws SJavaAssigmentException Thrown in the case the given value
	 * isn't a valid double literal
	 */
	private void assignAsDouble(String value) throws SJavaAssigmentException {
		if(!value.matches(Patterns.DOUBLE_VALUE.pattern()))
			throw new SJavaAssigmentException("Assigning a non double value to a double");
		assigned = true;
	}
	
	/**
	 * Assigns this variable as an boolean
	 * @param value Given string value
	 * @throws SJavaAssigmentException Thrown in the case the given value
	 * isn't a valid boolean literal
	 */
	private void assignAsBoolean(String value) throws SJavaAssigmentException {
		if(!(value.equals(Variable.TRUE) || value.equals(Variable.FALSE))) {
			if(!(value.matches(Patterns.DOUBLE_VALUE.pattern()))) {
				if(!value.matches(Patterns.INT_VALUE.pattern()))
					throw new SJavaAssigmentException("Assigning a non boolean value to a boolean");
			}
		}
		assigned = true;
	}
	
	/**
	 * Assigns this variable as an char
	 * @param value Given string value
	 * @throws SJavaAssigmentException Thrown in the case the given value
	 * isn't a valid char literal
	 */
	private void assignAsChar(String value) throws SJavaAssigmentException {
		if(!value.matches(Patterns.CHAR_VALUE.pattern()))
			throw new SJavaAssigmentException("Assigning a non character value to a char");
		assigned = true;
	}
	
	/**
	 * Assigns this variable as an String
	 * @param value Given string value
	 * @throws SJavaAssigmentException Thrown in the case the given value
	 * isn't a valid String literal
	 */
	private void assignAsString(String value) throws SJavaAssigmentException {
		if(!value.matches(Patterns.STRING_VALUE.pattern()))
			throw new SJavaAssigmentException("Assigning a non string value to a string");
		assigned = true;
	}
	
	/**
	 * @return This variable's name
	 */
	public String getName() {	
		return name;
	}
	
	/**
	 * @return This variable's type
	 */
	public SJavaTypes getType() {
		return type;
	}
	
	/**
	 * @param state Sets this assigned state to the given state
	 */
	public void setAssigned(boolean state) {
		assigned = state;
	}
	
	
	@Override
	public String toString() {
		return "[Type: " + SJavaTypes.getType(type) + " ,Name: " + name + 
				" ,Final: " + isFinal + " ,Assigned: " + assigned + "]";
	}
}
