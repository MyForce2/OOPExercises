package oop.ex6.parsers.initial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import oop.ex6.main.errors.SJavaFormatException;

public class InitialParser {
	
	private File file;
	
	private LinkedList<String> lines;
	
	private static final char END_OF_LINE = '\n';
	
	
	
	public InitialParser(String path) {
		file = new File(path);
		lines = new LinkedList<String>();
	}
	
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
		for(FileLine line : codeLines)
			System.out.println(line.getData());
		System.out.println("---------------");
		checkBrackets();

	}

	
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
	
	
	public List<FileLine> getCodeLines() {
		LinkedList<FileLine> codeLines = new LinkedList<FileLine>();
		for(String line : lines) {
			for(LineTypes type : LineTypes.values()) {
				if(type == LineTypes.COMMENT || type == LineTypes.EMPTY)
					continue;
				if(LineFilters.getFilter(type).test(line))
					codeLines.addLast(new FileLine(line, type));
			}
		}
		return codeLines;
	}
	
	public void readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while(line != null) {
			line = line.replaceAll("[ ]{2,}", " ");
			line = line.trim();
			lines.add(line);
			line = reader.readLine();
		}
		reader.close();
	}
	
	
	
}
