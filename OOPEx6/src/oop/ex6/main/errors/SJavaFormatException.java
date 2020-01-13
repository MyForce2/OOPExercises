package oop.ex6.main.errors;

public class SJavaFormatException extends SJavaException {


	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaFormatException has occured";
	
	public SJavaFormatException() {
		super(SJavaFormatException.DEFAULT_ERROR_MESSAGE);
	}
	
	public SJavaFormatException(String message) {
		super(message);
	}

}
