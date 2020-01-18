package oop.ex6.parsers.processing;

import java.util.ArrayList;
import java.util.List;

import oop.ex6.main.errors.SJavaException;
import oop.ex6.main.errors.SJavaFormatException;
import oop.ex6.main.errors.SJavaMethodException;
import oop.ex6.parsers.SJavaKeyWords;
import oop.ex6.parsers.SJavaTypes;
import oop.ex6.parsers.initial.FileLine;
import oop.ex6.parsers.initial.LineTypes;
import oop.ex6.specs.Method;
import oop.ex6.specs.Scope;
import oop.ex6.specs.Variable;

/**
 * The main parser class, parses a given code file
 */
public class Parser {

	/**
	 * The list of code lines in the given SJava file
	 */
	private ArrayList<FileLine> codeLines;

	/**
	 * The global scope of the given SJavaFile
	 */
	private Scope globalScope;
	

	
	/**
	 * Final Key word constant
	 */
	public static final String FINAL_KEY_WORD = SJavaKeyWords.getType(SJavaKeyWords.FINAL);
	
	/**
	 * Assignment operator constant
	 */
	public static final String ASSIGNMENT_OPERATOR = "=";
	
	/**
	 * A space constant
	 */
	public static final String SPACE = " ";
	
	/**
	 * Method Parameters list starts with this character
	 */
	public static final String PARAM_START = "(";
	
	/**
	 * Method Parameters list ends with this character
	 */
	public static final String PARAM_END = ")";
	
	/**
	 * A Parameter separator character
	 */
	public static final String PARAM_SEPARATOR = ",";
	
	/**
	 * And character single constant
	 */
	public static final String AND_CHARACTER = "&";
	
	/**
	 * Or character single constant
	 */
	public static final String OR_CHARACTER = "|";
	
	/**
	 * Valid and character appearance in an expression
	 */
	public static final String VALID_AND_CHARACTER = "&&";
	
	/**
	 * Valid or character appearance in an expression
	 */
	public static final String VALID_OR_CHARACTER = "||";

	/**
	 * Parser constructor
	 * @param list Initialized list of FileLine objects of the given SJava
	 * file
	 */
	public Parser(List<FileLine> list) {
		codeLines = new ArrayList<FileLine>(list);
		globalScope = new Scope(null);
	}

	/**
	 * Processes the file 
	 * @throws SJavaException thrown in the case there is an error
	 * with the given file
	 */
	public void processFile() throws SJavaException {
		initGlobalScope(getGlobalLines());
		boolean[] assignedState = new boolean[globalScope.getVariables().size()];
		for(int i = 0; i < assignedState.length; i++) {
			assignedState[i] = globalScope.getVariables().get(i).isAssigned();
		}
		for(Method m : globalScope.getMethods()) {
			parseMethodBody(m);
			for(int i = 0; i < assignedState.length; i++) {
				globalScope.getVariables().get(i).setAssigned(assignedState[i]);
			}
		}
	}

	/**
	 * Initializes the global scope with the variables and methods
	 * @param globalLines Global lines of the file, inside no other scope
	 * @throws SJavaException thrown in the case there is an error
	 * with global lines
	 */
	private void initGlobalScope(ArrayList<FileLine> globalLines) 
			throws SJavaException {
		for(FileLine line : globalLines) {
				if(line.getType().equals(LineTypes.CODE))
					parseCodeLine(line, globalScope);
				else {
					parseMethodDeclaration(line);
					validateMethodBody(line);
				}
		}
	}
	
	/**
	 * Parses a given method body
	 * @param method Given parsed method
	 * @throws SJavaException thrown in the case there is an error
	 * with the method body
	 */
	private void parseMethodBody(Method method) throws SJavaException {
		int startingLine = method.getDeclarationLine().getLineNum() + 1;
		Scope currentScope = new Scope(globalScope);
		currentScope.getVariables().addAll(method.getParameters());
		for(int i = startingLine; i < codeLines.size(); i++) {
			FileLine line = codeLines.get(i);
			if(line.getType().equals(LineTypes.CODE)) {
				parseCodeLine(line, currentScope);
			} else if(line.getType().equals(LineTypes.CODE_CLOSE)) {
				currentScope = currentScope.getParentScope();
			} else if(line.getType().equals(LineTypes.CODE_OPEN)) {
				parseBracketOpenLine(line, currentScope);
				Scope innerScope = new Scope(currentScope);
				currentScope = innerScope;
			}
			if(Patterns.RETURN_LINE.matcher(line.getData()).matches())
				break;
		}
	}
	
	/**
	 * Parses a line of type CODE_BLOCK_OPEN
	 * @param line Given FileLine object
	 * @param scope The scope in which the line resides
	 * @throws SJavaException Thrown in the case there is an error
	 * with given line
	 */
	private void parseBracketOpenLine(FileLine line, Scope scope) 
			throws SJavaException {
		boolean valid = false;
		if(Patterns.IF_EXPRESSION.matcher(line.getData()).matches()) {
			valid = true;
		}
		if(Patterns.WHILE_EXPRESSION.matcher(line.getData()).matches())
			valid = true;
		if(!valid)
			throw new SJavaFormatException("While | If error");
		scope.evaluateCondition(line.getData());
	}
	
	/**
	 * Validates the method body, make sure it has the correct code structure
	 * and ends with the line return
	 * @param line The given file line
	 * @throws SJavaFormatException Thrown whenever there is an error with 
	 * the structure of this method
	 */
	private void validateMethodBody(FileLine line) throws SJavaFormatException {
		int startingLine = line.getLineNum();
		int opening = 1, closing = 0;
		int endLine = startingLine + 1;
		for(int i = startingLine + 1; i < codeLines.size(); i++) {
			if(codeLines.get(i).getType().equals(LineTypes.CODE_OPEN))
				opening++;
			if(codeLines.get(i).getType().equals(LineTypes.CODE_CLOSE))
				closing++;
			endLine = i;
			if(opening == closing)
				break;
		}
		FileLine returnLine = codeLines.get(endLine - 1);
		if(!Patterns.RETURN_LINE.matcher(returnLine.getData()).matches())
			throw new SJavaFormatException("Method doesn't end with return");
	}
	
	/**
	 * Parses a method declaration
	 * @param line The method declaration line
	 * @throws SJavaException thrown whenever there is an error with the
	 * method declaration
	 */
	private void parseMethodDeclaration(FileLine line) 
			throws SJavaException {
		String declaration = line.getData();
		if(!Patterns.VOID_METHOD_DECLARATION.matcher(declaration).matches())
			throw new SJavaMethodException("Invalid method declaration : " + 
		declaration);
		String name = declaration.split("["+ Parser.PARAM_START + "]")[0].
				split(Parser.SPACE)[1];
		String parameters = declaration.substring(
				declaration.indexOf(Parser.PARAM_START) + 1, 
				declaration.indexOf(Parser.PARAM_END));
		if(parameters.equals("")) {
			globalScope.addMethod(new Method(name, line));
			return;
		}
		String[] params = parameters.split(",");
		for(int i = 0; i < params.length; i++) {
			params[i] = params[i].trim();
			params[i].replaceAll(Patterns.MORE_THAN_ONE_SPACE.pattern(), " ");
			boolean valid = false;
			for(SJavaTypes type : SJavaTypes.values()) {
				if(SJavaTypes.getDeclarationPattern(type).
						matcher(params[i]).matches())
					valid = true;
			}
			if(!valid)
				throw new SJavaMethodException("Declaring a method "
						+ "with invalid parameters");
		}
		ArrayList<Variable> variables = new ArrayList<Variable>();
		for(int i = 0; i < params.length; i++) {
			String[] tokens = params[i].split(Parser.SPACE);
			int index = params[i].startsWith(Parser.FINAL_KEY_WORD) ? 1 : 0;
			SJavaTypes type = SJavaTypes.getType(tokens[index]);
			String paramName = tokens[index + 1];
			for(Variable v : variables) {
				if(v.getName().equals(paramName))
					throw new SJavaMethodException("Method declaration has "
							+ "two variables with the same name");
			}
			variables.add(new Variable(paramName, type, params[i]
					.startsWith(Parser.FINAL_KEY_WORD)
					, true));
		}
		globalScope.addMethod(new Method(name, variables, line));
	}
	
	/**
	 * Parses a code line
	 * @param line The code line FileLine object
	 * @param scope The scope in which the line resides
	 * @throws SJavaException thrown in the case there is an error with
	 * the given code line
	 */
	private void parseCodeLine(FileLine line, Scope scope) throws SJavaException {
		if(scope.isGlobal())
			parseLineAsGlobalScope(line, scope);
		else
			parseLineAsRegularScope(line, scope);
	}
	
	/**
	 * Parses this code line as a regular scope line
	 * @param line The FileLine object
	 * @param scope The scope in which this line resides, not the global
	 * scope
	 * @throws SJavaException Thrown in the case there is an 
	 * error with this line
	 */
	private void parseLineAsRegularScope(FileLine line, Scope scope) 
			throws SJavaException {
		boolean valid = false;
		String[] declarations = getDeclarations(line.getData());
		for(String declaration : declarations) {
			valid = false;
			for(SJavaTypes type : SJavaTypes.values()) {
				if(SJavaTypes.getLiteralAssignmentPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableLiteralAssignment(declaration);
				}
				else if(SJavaTypes.getDeclarationPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableDeclaration(declaration);
				}
				else if(SJavaTypes.getDeclarationLiteralAssignmentPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableDeclarationAndAssigmentLiteral(declaration);
				}
				else if(SJavaTypes.getDeclarationAssignmentPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableDeclarationAssignment(declaration);
				}
				else if(SJavaTypes.getAssignmentPattern()
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableAssignment(declaration);
				}
			}
			if(Patterns.RETURN_LINE.matcher(line.getData()).matches()) 
				valid = true;
			if(line.getData().matches(Patterns.CALLING_METHOD.pattern())) {
				valid = true;
				scope.callMethod(line.getData());
			}
			if(!valid) 
				throw new SJavaFormatException(line.getData());
		}
	}
	
	/**
	 * Parses this line as the global scope
	 * @param line FileLine object of the given line
	 * @param scope The global scope 
	 * @throws SJavaException Thrown in the case there is an error with
	 * this code line
	 */
	private void parseLineAsGlobalScope(FileLine line, Scope scope) 
			throws SJavaException {
		boolean valid = false;
		String[] declarations = getDeclarations(line.getData());
		for(String declaration : declarations) {
			valid = false;
			for(SJavaTypes type : SJavaTypes.values()) {
				if(SJavaTypes.getLiteralAssignmentPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableLiteralAssignment(declaration);
				}
				else if(SJavaTypes.getDeclarationPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableDeclaration(declaration);
				}
				else if(SJavaTypes.getDeclarationLiteralAssignmentPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableDeclarationAndAssigmentLiteral(declaration);
				}
				else if(SJavaTypes.getDeclarationAssignmentPattern(type)
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableDeclarationAssignment(declaration);
				}
				else if(SJavaTypes.getAssignmentPattern()
						.matcher(declaration).matches()) {
					valid = true;
					scope.parseVariableAssignment(declaration);
				}
			}
			if(!valid)
				throw new SJavaFormatException(line.getData());
		}
	}

	/**
	 * Returns the declaration contained in a variable declaration line
	 * e.g:
	 * int a, b, c = 2 --> {int a, int b, int c = 2}
	 * final double a = .2, b = 3.23 --> {final double a = .2, 
	 * final double b = 3.23}
	 * @param line The given line string
	 * @return The declarations in this code line
	 */
	private String[] getDeclarations(String line) {
		String[] split = line.split(",");
		if(split.length == 1)
			return split;
		String append = "";
		String type = "";
		if(line.startsWith(FINAL_KEY_WORD)) {
			append += FINAL_KEY_WORD;
			type = split[0].split(" ")[1];
		} else {
			type = split[0].split(" ")[0];
		}
		for(int i = 1; i < split.length; i++) {
			split[i] = append + ' ' + type + ' ' + split[i];
			split[i] = split[i].replaceAll("[ ]{2,}", " ");
			split[i] = split[i].trim();
		}
		return split;
	}

	/**
	 * Returns the global line of this SJava file
	 * @return An ArrayList<FileLine> containing the 
	 * global lines in this SJava file
	 */
	private ArrayList<FileLine> getGlobalLines() {
		int opening = 0;
		ArrayList<FileLine> globalLines = new ArrayList<FileLine>();
		for(FileLine line : codeLines) {
			if(opening == 0 && (line.getType() == LineTypes.CODE 
					|| line.getType() == LineTypes.CODE_OPEN))
				globalLines.add(line);
			if(line.getType() == LineTypes.CODE_OPEN)
				opening++;
			if(line.getType() == LineTypes.CODE_CLOSE)
				opening--;
		}
		return globalLines;
	}





}
