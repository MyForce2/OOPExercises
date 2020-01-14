package oop.ex6.specs;

import java.util.ArrayList;

import oop.ex6.main.errors.SJavaAssigmentException;
import oop.ex6.parsers.SJavaTypes;
import oop.ex6.parsers.processing.Parser;

public class Scope {
	
	private ArrayList<Variable> variables;
	
	private ArrayList<Scope> innerScopes;
	
	private Scope parentScope;
	
	public Scope(Scope parentScope) {
		this.parentScope = parentScope;
		variables = new ArrayList<Variable>();
		innerScopes = new ArrayList<Scope>();
	}
	
	public ArrayList<Variable> getVariables() {
		return variables;
	}
	
	public ArrayList<Scope> getScopes() {
		return innerScopes;
	}
	
	public boolean isGlobal() {
		return parentScope == null;
	}
	
	public void pop() {
		variables.clear();
		innerScopes.clear();
	}
	
	public void parseVariableDeclarationAndAssigmentLiteral(String declaration) 
			throws SJavaAssigmentException {
		String[] split = declaration.split(" ");
		int index = declaration.startsWith(Parser.FINAL_KEY_WORD) ? 1 : 0;
		SJavaTypes type = SJavaTypes.getType(split[index]);
		String name = split[index + 1];
		String value = split[index + 3];
		value = value.endsWith(";") ? value.substring(0, value.length() - 1) : value;
		if(variableExistsInScope(name))
			throw new SJavaAssigmentException("A variable with this name "
					+ "already exists in this scope");
		Variable v = new Variable(name, type, 
				declaration.startsWith(Parser.FINAL_KEY_WORD), true);
		variables.add(v);
	}
	
	public void parseVariableDeclaration(String declaration) throws SJavaAssigmentException {
		String[] split = declaration.split(" ");
		int index = declaration.startsWith(Parser.FINAL_KEY_WORD) ? 1 : 0;
		SJavaTypes type = SJavaTypes.getType(split[index]);
		String name = split[index + 1];
		name = name.replace(";", "");
		if(variableExistsInScope(name))
			throw new SJavaAssigmentException("A variable with this name "
					+ "already exists in this scope");
		Variable v = new Variable(name, type, 
				declaration.startsWith(Parser.FINAL_KEY_WORD), false);
		variables.add(v);
	}
	
	public void parseVariableLiteralAssignment(String declaration) 
			throws SJavaAssigmentException {
		String[] split = declaration.split(" ");
		String name = split[0];
		String value = split[2].replace(";", "");
		Variable v = getVariable(name);
		v.assign(value);
	}
	
	public void parseVariableDeclarationAssignment(String declaration) throws SJavaAssigmentException {
		String[] split = declaration.split(" ");
		int index = declaration.startsWith(Parser.FINAL_KEY_WORD) ? 1 : 0;
		SJavaTypes type = SJavaTypes.getType(split[index]);
		String name = split[index + 1];
		String name2 = split[index + 3].replace(";", "");
		if(variableExistsInScope(name))
			throw new SJavaAssigmentException("A variable with this name "
					+ "already exists in this scope");
		if(!variableExistsInScope(name2))
			throw new SJavaAssigmentException("Assigning non existing variables");
		Variable v = getVariable(name2);
		Variable v2 = new Variable(v, name, type,
				declaration.startsWith(Parser.FINAL_KEY_WORD));
		variables.add(v2);
	}
	
	public void parseVariableAssignment(String declaration) throws SJavaAssigmentException {
		String[] split = declaration.split(" ");
		String name = split[0];
		String name2 = split[2].replace(";", "");
		System.out.println(name + '\n' + name2);
		if(!variableExists(name) || !variableExists(name2))
			throw new SJavaAssigmentException("Assigning non existing variables");
		Variable v1 = getVariable(name), v2 = getVariable(name2);
		if(!v1.getType().equals(v2.getType()))
			throw new SJavaAssigmentException("Assigning variables of different type");
		if(!v2.isAssigned())
			throw new SJavaAssigmentException("Assigning variables to non initialized variables");
		v2.assign(SJavaTypes.getDefaultVal(v2.getType()));
	}
	
	
	private Variable getVariable(String name) {
		for(Variable v : variables) {
			if(v.getName().equals(name))
				return v;
		}
		if(!isGlobal())
			return parentScope.getVariable(name);
		return null;
	}
	
	private boolean variableExists(String name) {
		if(isGlobal())
			return variableExistsInScope(name);
		return variableExistsInScope(name) && parentScope.variableExists(name);
	}
	
	private boolean variableExistsInScope(String name) {
		for(Variable v : variables) {
			if(v.getName().equals(name))
				return true;
		}
		return false;
	}
	
}
