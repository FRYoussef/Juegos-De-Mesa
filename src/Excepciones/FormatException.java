package Excepciones;

public class FormatException extends Exception {
	public FormatException() {
	}
	
	public FormatException(String msg) {
		super(msg);
	}
	
	public FormatException(Throwable arg) {
		super(arg);
	}
}
