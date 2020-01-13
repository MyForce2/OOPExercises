package oop.ex6.specs;

import java.util.ArrayList;

public class Scope {
	
	private ArrayList<Variable> variables;
	
	private ArrayList<Scope> innerScopes;
	
	private Scope parentScope;
	
	public Scope(Scope parentScope) {
		this.parentScope = parentScope;
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
	
}
