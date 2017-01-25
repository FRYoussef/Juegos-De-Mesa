package Excepciones;

public class NoMoveException extends Exception{
	public NoMoveException() {
	}
	
	public NoMoveException(String msg) {
		super(msg);
	}
	
	public NoMoveException(Throwable arg) {
		super(arg);
	}
}