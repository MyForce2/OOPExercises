package oop.ex6.main;

import java.io.IOException;

import oop.ex6.main.errors.SJavaException;
import oop.ex6.main.errors.SJavaFormatException;
import oop.ex6.parsers.initial.InitialParser;
import oop.ex6.parsers.processing.Parser;

public class Sjavac {

	public static void main(String[] args) {
		String[] names = { "501.txt", "502.txt", "503.txt", "504.txt", "505.txt" };
		try {
			InitialParser parser = new InitialParser("C:\\Users\\nadav\\eclipse-workspace\\OOPEx6\\src\\Data\\" + names[0]);
			parser.readFile();
			parser.initialValidation();
			Parser p = new Parser(parser.getCodeLines());
			p.processFormat();
			System.out.println("Valid :)");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SJavaException e) {
			System.err.println(e.getMessage());
		}
	}
}
