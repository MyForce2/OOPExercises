package oop.ex6.main.errors;

public class SJavaAssigmentException extends SJavaException{

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_ERROR_MESSAGE = "A SJavaAssigmentException has occured";
	
	public SJavaAssigmentException() {
		super(SJavaAssigmentException.DEFAULT_ERROR_MESSAGE);
	}
	
	public SJavaAssigmentException(String message) {
		super(message);
	}

}
