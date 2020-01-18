package oop.ex6.parsers.initial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import oop.ex6.main.errors.SJavaFormatException;
import oop.ex6.parsers.processing.Patterns;

/**
 * An InitialParser, checks whether this given SJava file is initially valid,
 * meaning all of the lines are in valid format, right number of curly brackets and such
 * After it is done, it transfers the data onto the Parser class to verify the code
 */
public class InitialParser {
	
	/**
	 * The File object of the given SJava file
	 */
	private File file;
	
	/**
	 * A LinkedList of file lines, in the case the file is extremely large
	 * it is faster to use a linked list 
	 */
	private LinkedList<String> lines;
	
	/**
	 * Initial Parser constructor
	 * @param path The SJava file path
	 */
	public InitialParser(String path) {
		file = new File(path);
		lines = new LinkedList<String>();
	}
	
	/**
	 * Performs initial validation on the given file, as described
	 * in the class description
	 * @throws SJavaFormatException Thrown whenever the file isn't valid,
	 * meaning it has issues with it's format
	 */
	public void initialValidation() throws SJavaFormatException {
		for(String line : lines) {
			boolean valid = false;
			for(LineTypes type : LineTypes.values()) {
				if(LineFilters.getFilter(type).test(line))
					valid = true;
			}
			if(!valid)
				throw new SJavaFormatException("There is an error with line :\n" + line);
		}
		List<FileLine> codeLines = getCodeLines();
		checkBrackets();
	}

	/**
	 * Checks the brackets of the code file, has to be equal
	 * and a scope can't close before one opens
	 * @throws SJavaFormatException Thrown whenever the file brackets format
	 * isn't valid
	 */
	private void checkBrackets() throws SJavaFormatException {
		int closing = 0, opening = 0;
		List<FileLine> codeLines = getCodeLines();
		for(FileLine line : codeLines) {
			if(line.getType() == LineTypes.CODE_OPEN)
				opening++;
			if(line.getType() == LineTypes.CODE_CLOSE)
				closing++;
			if(closing > opening)
				throw new SJavaFormatException();
		}
	}
	
	/**
	 * @return A List of the actual code lines in the given SJavaFile
	 * no comment / empty lines, and each line has its number / LineType value
	 * The list is of the wrapper class FileLine
	 */
	public List<FileLine> getCodeLines() {
		LinkedList<FileLine> codeLines = new LinkedList<FileLine>();
		int lineNum = 0;
		for(String line : lines) {
			if(LineFilters.getFilter(LineTypes.EMPTY).test(line))
				continue;
			if(LineFilters.getFilter(LineTypes.COMMENT).test(line))
				continue;
			for(LineTypes type : LineTypes.values()) {
				if(LineFilters.getFilter(type).test(line)) {
					codeLines.addLast(new FileLine(line, type, lineNum));
					lineNum++;
				}
			}
		}
		return codeLines;
	}
	
	/**
	 * Attempts to read the file onto the lines member of this class
	 * @throws IOException Thrown in the case an IOException is thrown
	 * during the file reading attempt
	 */
	public void readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while(line != null) {
			line = line.replaceAll(Patterns.MORE_THAN_ONE_SPACE.pattern(), " ");
			line = line.trim();
			lines.add(line);
			line = reader.readLine();
		}
		reader.close();
	}
	
	
	
}
