package oop.ex6.main.errors;

public class SJavaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaException has occured";
	
	public SJavaException() {
		super(SJavaException.DEFAULT_ERROR_MESSAGE);
	}
	
	public SJavaException(String message) {
		super(message);
	}

}
