package Excepciones;

public class FullPositionException extends Exception{
	public FullPositionException() {
	}
	
	public FullPositionException(String msg) {
		super(msg);
	}
	
	public FullPositionException(Throwable arg) {
		super(arg);
	}
}
