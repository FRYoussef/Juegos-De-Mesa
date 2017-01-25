package Excepciones;

public class ComandException extends Exception{
	
	public ComandException() {
	}
	
	public ComandException(String msg) {
		super(msg);
	}
	
	public ComandException(Throwable arg) {
		super(arg);
	}
}
