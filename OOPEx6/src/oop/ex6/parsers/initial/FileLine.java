package oop.ex6.parsers.initial;

public class FileLine {
	
	private LineTypes type;
	
	private String data;
	
	public FileLine(String data, LineTypes type) {
		this.data = data;
		this.type = type;
	}
	
	public String getData() {
		return data;
	}
	
	public LineTypes getType() {
		return type;
	}
}
