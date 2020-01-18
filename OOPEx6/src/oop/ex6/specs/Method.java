package oop.ex6.specs;

import java.util.ArrayList;

import oop.ex6.main.errors.SJavaMethodException;
import oop.ex6.parsers.initial.FileLine;

/**
 * A SJava method class, represents a method in an SJava code
 * file
 */
public class Method {
	
	/**
	 * Name of the method
	 */
	private String name;
	
	/**
	 * The method parameters, by default method parameters are always assigned
	 */
	private ArrayList<Variable> parameters;
	
	/**
	 * The file line in which this method is declared
	 */
	private FileLine declarationLine;
	
	/**
	 * A Method constructor for a method with no parameters
	 * @param name The method name
	 * @param declareLine The file line in which the method is declared
	 */
	public Method(String name, FileLine declareLine) {
		this.name = name;
		declarationLine = declareLine;
		parameters = new ArrayList<Variable>();
	}
	
	/**
	 * A Method constructor for a method with parameters
	 * @param name The method name
	 * @param parameters The method parameters
	 * @param declareLine The file line in which the method is declared
	 */
	public Method(String name, ArrayList<Variable> parameters, FileLine declareLine) {
		this.name = name;
		this.parameters = parameters;
		declarationLine = declareLine;
	}
	
	/**
	 * @return The method name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The method variables
	 */
	public ArrayList<Variable> getParameters() {
		return parameters;
	}
	
	/**
	 * @return The line in which is method declared
	 */
	public FileLine getDeclarationLine() {
		return declarationLine;
	}
	
	/**
	 * Attempts to call this method with the given parameters
	 * @param params The parameters given in the method call
	 * @throws SJavaMethodException Thrown in the case the given parameters list
	 * doesn't isn't valid
	 */
	public void call(ArrayList<Variable> params) throws SJavaMethodException {
		if(params.size() != parameters.size())
			throw new SJavaMethodException("Calling a method with wrong number of parameters");
		for(int i = 0; i < params.size(); i++) {
			Variable v1 = params.get(i), v2 = parameters.get(i);
			if(v1.getType() != v2.getType() && !(Variable.compatibleTypes(v2.getType(), v1.getType())))
				throw new SJavaMethodException("Calling a method with wrong parameter type");
			if(!v1.isAssigned())
				throw new SJavaMethodException("Calling a method with non initialized parameter");
		}
	}
	
	@Override
	public String toString() {
		return "[Name: " + name + ", Parameters: " + parameters + "]";
	}
}
