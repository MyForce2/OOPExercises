package oop.ex6.parsers.processing;

import java.util.ArrayList;
import java.util.List;

import oop.ex6.main.errors.SJavaFormatException;
import oop.ex6.parsers.SJavaTypes;
import oop.ex6.parsers.initial.FileLine;
import oop.ex6.parsers.initial.LineTypes;
import oop.ex6.specs.Scope;

public class Parser {
	
	private ArrayList<FileLine> codeLines;
	
	private Scope globalScope;
	
	private static final String FINAL_KEY_WORD = "final";
	
	public Parser(List<FileLine> list) {
		codeLines = new ArrayList<FileLine>(list);
		globalScope = new Scope(null);
	}
	
	public void processFormat() throws SJavaFormatException {
		checkBrackets();
		initGlobalScope(getGlobalLines());
	}
	
	private void initGlobalScope(ArrayList<String> globalLines) throws SJavaFormatException {
		for(String line : globalLines) {
			boolean valid = false;
			String[] declarations;
			for(SJavaTypes type : SJavaTypes.values()) {
				if(line.matches(SJavaTypes.getRegexAssigment(type)))
					valid = true;
				if(line.matches(SJavaTypes.getRegexDeclaration(type)))
					valid = true;
				if(line.matches(SJavaTypes.getRegexDeclarationAndAssigment(type)))
					valid = true;
			}
			if(!valid)
				throw new SJavaFormatException(line);
		}
	}
	
	private String[] getDeclarations(String line) {
		String[] split = line.split(",");
		if(split.length == 1)
			return split;
		String append = "";
		if(line.startsWith(FINAL_KEY_WORD))
			append += FINAL_KEY_WORD;
		
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
