package oop.ex6.specs;

import java.util.ArrayList;
import java.util.regex.Matcher;

import oop.ex6.main.errors.SJavaAssigmentException;
import oop.ex6.main.errors.SJavaConditionException;
import oop.ex6.main.errors.SJavaException;
import oop.ex6.main.errors.SJavaMethodException;
import oop.ex6.parsers.SJavaTypes;
import oop.ex6.parsers.processing.Parser;
import oop.ex6.parsers.processing.Patterns;

/**
 * A Scope class, represents a Scope in an SJava code file
 */
public class Scope {
	
	/**
	 * The variables declared in this scope
	 */
	private ArrayList<Variable> variables;
	
	/**
	 * Methods declared in this scope, only the global scope
	 * owns a methos list since methods can only be declared
	 * in the global scope
	 */
	private ArrayList<Method> methods;
	
	/**
	 * Inner scopes in this scope
	 */
	private ArrayList<Scope> innerScopes;
	
	/**
	 * The parent scope for this scope, the scope
	 * in which this scope was created
	 */
	private Scope parentScope;
	
	/**
	 * A Scope constructor
	 * @param parentScope the scope in which this scope was created
	 * null for the global scope
	 */
	public Scope(Scope parentScope) {
		this.parentScope = parentScope;
		variables = new ArrayList<Variable>();
		innerScopes = new ArrayList<Scope>();
		if(isGlobal())
			methods = new ArrayList<Method>();
	}
	
	/**
	 * @return The variables owned by this scope
	 */
	public ArrayList<Variable> getVariables() {
		return variables;
	}
	
	/**
	 * @return The methods declared in this scope
	 */
	public ArrayList<Method> getMethods() {
		return methods;
	}
	
	/**
	 * @return The scopes contained in this scope
	 */
	public ArrayList<Scope> getScopes() {
		return innerScopes;
	}
	
	/**
	 * @return The scope in which this scope was created
	 */
	public Scope getParentScope() {
		return parentScope;
	}
	
	/**
	 * @return True if this scope is the global scope, false otherwise
	 */
	public boolean isGlobal() {
		return parentScope == null;
	}
	
	/**
	 * Clears all of the data contained in this scope
	 */
	public void pop() {
		variables.clear();
		innerScopes.clear();
	}
	
	/**
	 * Parses a variable declaration and assignment to a literal value
	 * @param declaration The variable declaration
	 * @throws SJavaAssigmentException Thrown in the case there is a problem
	 * with the variable declaration
	 */
	public void parseVariableDeclarationAndAssigmentLiteral(String declaration) 
			throws SJavaAssigmentException {
		String[] split = declaration.replaceAll(Parser.ASSIGNMENT_OPERATOR, Parser.SPACE)
				.replaceAll(Patterns.MORE_THAN_ONE_SPACE.pattern(),  Parser.SPACE).split(Parser.SPACE);
		int index = declaration.startsWith(Parser.FINAL_KEY_WORD) ? 1 : 0;
		SJavaTypes type = SJavaTypes.getType(split[index]);
		String name = split[index + 1];
		if(Patterns.RESERVED_KEY_WORDS_NAMES.matcher(name).matches())
			throw new SJavaAssigmentException("Trying to name a variable using a reserved keyword");
		String value = split[index + 2];
		value = value.endsWith(";") ? value.substring(0, value.length() - 1) : value;
		if(variableExistsInScope(name))
			throw new SJavaAssigmentException("A variable with this name "
					+ "already exists in this scope");
		Variable v = new Variable(name, type, 
				declaration.startsWith(Parser.FINAL_KEY_WORD), true);
		variables.add(v);
	}
	
	/**
	 * Parses a variable declaration with no assignment
	 * @param declaration The variable declaration
	 * @throws SJavaAssigmentException Thrown in the case there is a problem
	 * with the variable declaration
	 */
	public void parseVariableDeclaration(String declaration) throws SJavaAssigmentException {
		String[] split = declaration.split(" ");
		int index = declaration.startsWith(Parser.FINAL_KEY_WORD) ? 1 : 0;
		SJavaTypes type = SJavaTypes.getType(split[index]);
		String name = split[index + 1];
		if(Patterns.RESERVED_KEY_WORDS_NAMES.matcher(name).matches())
			throw new SJavaAssigmentException("Trying to name a variable using a reserved keyword");
		name = name.replace(";", "");
		if(variableExistsInScope(name))
			throw new SJavaAssigmentException("A variable with this name "
					+ "already exists in this scope");
		Variable v = new Variable(name, type, 
				declaration.startsWith(Parser.FINAL_KEY_WORD), false);
		variables.add(v);
	}
	
	/**
	 * Parses a variable assignment to a literal value
	 * @param declaration The variable declaration
	 * @throws SJavaAssigmentException Thrown in the case there is a problem
	 * with the variable declaration
	 */
	public void parseVariableLiteralAssignment(String declaration)
			throws SJavaAssigmentException {
		String[] split = declaration.replaceAll(Parser.ASSIGNMENT_OPERATOR, Parser.SPACE)
				.replaceAll(Patterns.MORE_THAN_ONE_SPACE.pattern(),  Parser.SPACE).split(Parser.SPACE);
		String name = split[0];
		String value = split[1].replace(";", "");
		Variable v = getVariable(name);
		if(v == null)
			throw new SJavaAssigmentException("Assigning to a non existing variable"); 
		v.assign(value);
	}
	
	/**
	 * Parses a variable declaration and assignment to another variable
	 * @param declaration The variable declaration
	 * @throws SJavaAssigmentException Thrown in the case there is a problem
	 * with the variable declaration
	 */
	public void parseVariableDeclarationAssignment(String declaration) throws SJavaAssigmentException {
		String[] split = declaration.replaceAll(Parser.ASSIGNMENT_OPERATOR, Parser.SPACE)
				.replaceAll(Patterns.MORE_THAN_ONE_SPACE.pattern(),  Parser.SPACE).split(Parser.SPACE);
		int index = declaration.startsWith(Parser.FINAL_KEY_WORD) ? 1 : 0;
		SJavaTypes type = SJavaTypes.getType(split[index]);
		String name = split[index + 1];
		String name2 = split[index + 2].replace(";", "");
		if(Patterns.RESERVED_KEY_WORDS_NAMES.matcher(name).matches())
			throw new SJavaAssigmentException("Trying to name a variable using a reserved keyword");
		if(Patterns.RESERVED_KEY_WORDS_NAMES.matcher(name2).matches())
			return;
		if(variableExistsInScope(name))
			throw new SJavaAssigmentException("A variable with this name "
					+ "already exists in this scope");
		if(!variableExists(name2))
			throw new SJavaAssigmentException("Assigning non existing variables");
		Variable v = getVariable(name2);
		Variable v2 = new Variable(v, name, type,
				declaration.startsWith(Parser.FINAL_KEY_WORD));
		variables.add(v2);
	}
	
	/**
	 * Parses a variable assignment to another variable
	 * @param declaration The variable declaration
	 * @throws SJavaAssigmentException Thrown in the case there is a problem
	 * with the variable declaration
	 */
	public void parseVariableAssignment(String declaration) throws SJavaAssigmentException {
		String[] split = declaration.replaceAll(Parser.ASSIGNMENT_OPERATOR, Parser.SPACE)
				.replaceAll(Patterns.MORE_THAN_ONE_SPACE.pattern(), Parser.SPACE).split(Parser.SPACE);
		String name = split[0];
		String name2 = split[1].replace(";", "");
		if(Patterns.RESERVED_KEY_WORDS_NAMES.matcher(name2).matches())
			return;
		if(!variableExists(name) || !variableExists(name2))
			throw new SJavaAssigmentException("Assigning non existing variables");
		Variable v1 = getVariable(name), v2 = getVariable(name2);
		if(!v2.isAssigned())
			throw new SJavaAssigmentException("Assigning variables to non initialized variables");
		v1.assign(v2);;
	}
	
	
	/**
	 * Returns the inner most variable object corresponding to this String name
	 * @param name Given variable name
	 * @return The inner most variable object corresponding to this name
	 * null if doesn't exist
	 */
	private Variable getVariable(String name) {
		for(Variable v : variables) {
			if(v.getName().equals(name))
				return v;
		}
		if(!isGlobal())
			return parentScope.getVariable(name);
		return null;
	}
	
	/**
	 * @param name Given variable name
	 * @return True if the given variable exists, false otherwise
	 */
	private boolean variableExists(String name) {
		if(isGlobal())
			return variableExistsInScope(name);
		return variableExistsInScope(name) || parentScope.variableExists(name);
	}
	
	/**
	 * @param name Given variable name
	 * @return True if the given variable exists, in this scope,
	 * false otherwise
	 */
	private boolean variableExistsInScope(String name) {
		for(Variable v : variables) {
			if(v.getName().equals(name))
				return true;
		}
		return false;
	}
	
	/**
	 * @param m Given method to add
	 * @throws SJavaMethodException Thrown in the case a method already exists with the
	 * same name as this given method
	 */
	public void addMethod(Method m) throws SJavaMethodException {
		if(!isGlobal())
			throw new SJavaMethodException("Trying to create a method in a non global scope");
		for(Method method : methods) {
			if(method.getName().equals(m.getName()))
				throw new SJavaMethodException("Trying to create a method with the same name : " + m.getName());
		}
		methods.add(m);
	}
	
	/**
	 * 
	 * @param name Method name
	 * @return The method object corresponding to the given name
	 * @throws SJavaMethodException Thrown in case the no method exists with
	 * this name
	 */
	public Method getMethod(String name) throws SJavaMethodException {
		if(!isGlobal())
			return parentScope.getMethod(name);
		for(Method m : methods) {
			if(name.equals(m.getName()))
				return m;
		}
		throw new SJavaMethodException("Trying to call a non existent method");
	}
	
	/**
	 * Attempts to call the method specified in this expression
	 * @param expression Method call expression
	 * @throws SJavaException Thrown in the case there is a problem when parsing
	 * this expression to a method call
	 */
	public void callMethod(String expression) throws SJavaException {
		String name = expression.substring(0, expression.indexOf(Parser.PARAM_START));
		name = name.trim();
		Method method = getMethod(name);
		String parameters = expression.substring(expression.indexOf(Parser.PARAM_START) + 1,
				expression.indexOf(Parser.PARAM_END));
		if(parameters.equals("")) {
			method.call(new ArrayList<Variable>());
			return;
		}
		String[] params = parameters.split(Parser.PARAM_SEPARATOR);
		ArrayList<Variable> paramList = new ArrayList<Variable>();
		for(int i = 0; i < params.length; i++) {
			params[i] = params[i].trim();
			boolean handled = false;
			for(SJavaTypes type : SJavaTypes.values()) {
				if(SJavaTypes.getLiteralPattern(type).matcher(params[i]).matches()) {
					Variable v = new Variable("", type, false, true);
					paramList.add(v);
					handled = true;
					break;
				}
			}
			if(handled)
				continue;
			if(!variableExists(params[i]))
				throw new SJavaMethodException("Trying to call a method with a non existing variable : " + params[i]);
			Variable var = getVariable(params[i]);
			if(!var.isAssigned())
				throw new SJavaMethodException("Trying to call a method with a non initialized variable");
			paramList.add(var);
		}
		method.call(paramList);
	}
	
	/**
	 * Evaluates the expression given as a condition
	 * @param expression Condition expression
	 * @throws SJavaException Thrown in the case there is an error when parsing this
	 * condition expression
	 */
	public void evaluateCondition(String expression) throws SJavaException {
		String condition = expression.substring(expression.indexOf(Parser.PARAM_START) + 1,
				expression.indexOf(Parser.PARAM_END));
		if(condition.contains(Parser.AND_CHARACTER) && 
				!condition.contains(Parser.VALID_AND_CHARACTER)) 
			throw new SJavaConditionException("Non valid and operator");
		if(condition.contains(Parser.OR_CHARACTER) &&
				!condition.contains(Parser.VALID_OR_CHARACTER))
			throw new SJavaConditionException("Non valid or operator");
		ArrayList<String> conditions = new ArrayList<String>();
		Matcher matcher = Patterns.CONDITION_SEPARATOR.matcher(condition);
		int index = 0;
		while(matcher.find()) {
			int end = matcher.start();
			conditions.add(condition.substring(index, end).trim());
			index = end + Parser.VALID_AND_CHARACTER.length();
		}
		String lastCondition = condition.substring(index).trim();
		conditions.add(lastCondition);
		for(String conditionExpression : conditions) {
			if(!isValidCondition(conditionExpression))
				throw new SJavaConditionException("Non valid condition : "
			+ conditionExpression);
		}
	}
	
	/**
	 * @param expression Given condition expression
	 * @return True if the given condition expression is a valid condition
	 * false otherwise
	 */
	private boolean isValidCondition(String expression) {
		if(SJavaTypes.getLiteralPattern(SJavaTypes.INT).
				matcher(expression).matches())
			return true;
		if(SJavaTypes.getLiteralPattern(SJavaTypes.DOUBLE).
				matcher(expression).matches())
			return true;
		if(SJavaTypes.getLiteralPattern(SJavaTypes.BOOLEAN).
				matcher(expression).matches())
			return true;
		if(!variableExists(expression))
			return false;
		Variable v = getVariable(expression);
		if(!v.isAssigned())
			return false;
		return v.getType() == SJavaTypes.INT || v.getType() == SJavaTypes.BOOLEAN
				|| v.getType() == SJavaTypes.DOUBLE;
	}
	
	@Override
	public String toString() {
		return "[Methods : " + methods + "\nVariables : " + variables + "]";
	}
	
}
