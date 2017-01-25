package Excepciones;

public class ColumnException extends Exception{
	public ColumnException() {
	}
	
	public ColumnException(String msg) {
		super(msg);
	}
	
	public ColumnException(Throwable arg) {
		super(arg);
	}
}
