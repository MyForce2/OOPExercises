package oop.ex6.main;

import java.io.IOException;

import oop.ex6.main.errors.SJavaException;
import oop.ex6.parsers.initial.InitialParser;
import oop.ex6.parsers.processing.Parser;

public class Sjavac {

	public static void main(String[] args) {
		Sjavac c = new Sjavac();
		//args = new String[] { "C:\\Users\\nadav\\eclipse-workspace\\OOPEx6\\src\\Data\\501.txt"};
		System.out.println(c.testRun(args));
	}
	
	public int testRun(String[] args) {
		try {
			InitialParser parser = new InitialParser(args[0]);
			parser.readFile();
			parser.initialValidation();
			Parser p = new Parser(parser.getCodeLines());
			p.processFile();
		} catch (IOException e) {
			return 2;
		} catch (SJavaException e) {
			return 1;
		}
		return 0;
	}
}
