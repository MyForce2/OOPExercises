package oop.ex6.parsers.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import oop.ex6.main.errors.SJavaException;
import oop.ex6.main.errors.SJavaFormatException;
import oop.ex6.parsers.SJavaTypes;
import oop.ex6.parsers.initial.FileLine;
import oop.ex6.parsers.initial.LineTypes;
import oop.ex6.specs.Scope;
import oop.ex6.specs.Variable;

public class Parser {

	private ArrayList<FileLine> codeLines;

	private Scope globalScope;

	public static final String FINAL_KEY_WORD = "final";

	public Parser(List<FileLine> list) {
		codeLines = new ArrayList<FileLine>(list);
		globalScope = new Scope(null);
	}

	public void processFormat() throws SJavaException {
		checkBrackets();
		initGlobalScope(getGlobalLines());
	}

	private void initGlobalScope(ArrayList<String> globalLines) throws SJavaException {
		for(String line : globalLines) {
			boolean valid = false;
			String[] declarations = getDeclarations(line);
			for(String declaration : declarations) {
				valid = false;
				for(SJavaTypes type : SJavaTypes.values()) {
					if(SJavaTypes.getLiteralAssignmentPattern(type).matcher(declaration).matches()) {
						valid = true;
						globalScope.parseVariableLiteralAssignment(declaration);
					}
					else if(SJavaTypes.getDeclarationPattern(type).matcher(declaration).matches()) {
						valid = true;
						globalScope.parseVariableDeclaration(declaration);
					}
					else if(SJavaTypes.getDeclarationLiteralAssignmentPattern(type).matcher(declaration).matches()) {
						valid = true;
						globalScope.parseVariableDeclarationAndAssigmentLiteral(declaration);
					}
					else if(SJavaTypes.getDeclarationAssignmentPattern(type).matcher(declaration).matches()) {
						valid = true;
						globalScope.parseVariableDeclarationAssignment(declaration);
					}
					else if(SJavaTypes.getAssignmentPattern().matcher(declaration).matches()) {
						valid = true;
						globalScope.parseVariableAssignment(declaration);
					}
				}
				if(!valid)
					throw new SJavaFormatException(line);
			}
		}
		for(Variable v : globalScope.getVariables())
			System.out.println(v);
	}
	


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

	private void checkBrackets() throws SJavaFormatException {
		int closing = 0, opening = 0;
		for(FileLine line : codeLines) {
			if(line.getType() == LineTypes.CODE_OPEN)
				opening++;
			if(line.getType() == LineTypes.CODE_CLOSE)
				closing++;
			if(closing > opening)
				throw new SJavaFormatException();
		}
	}

	private ArrayList<String> getGlobalLines() {
		int opening = 0;
		ArrayList<String> globalLines = new ArrayList<String>();
		for(FileLine line : codeLines) {
			if(line.getType() == LineTypes.CODE_OPEN)
				opening++;
			if(line.getType() == LineTypes.CODE_CLOSE)
				opening--;
			if(opening == 0 && line.getType() == LineTypes.CODE)
				globalLines.add(line.getData());
		}
		System.out.println(globalLines);
		System.out.println("--------------");
		return globalLines;
	}





}
