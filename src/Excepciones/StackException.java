package Excepciones;

public class StackException extends Exception{
	public StackException() {
	}
	
	public StackException(String msg) {
		super(msg);
	}
	
	public StackException(Throwable arg) {
		super(arg);
	}
}
