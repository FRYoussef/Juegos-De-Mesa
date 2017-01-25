package Excepciones;

public class RowException extends Exception{
	public RowException() {
	}
	
	public RowException(String msg) {
		super(msg);
	}
	
	public RowException(Throwable arg) {
		super(arg);
	}
}
