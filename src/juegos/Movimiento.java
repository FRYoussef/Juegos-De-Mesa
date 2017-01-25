package juegos;

import logica.Ficha;
import logica.Tablero;


abstract public class Movimiento {
	private int fila;
	private int columna;
	private Ficha turno;
	
	abstract public boolean EjecutarMovimiento (Tablero tab);
	abstract public void Undo (Tablero tab);

	public Movimiento (){
		fila = 0;
		columna = 0;
		turno = Ficha.VACIA;
	}
	
	public Movimiento(int _columna, Ficha _turno){
		columna = _columna;
		turno = _turno;
	}
	
	public Movimiento(int _columna,int _fila, Ficha _turno){
		columna = _columna;
		fila = _fila;
		turno = _turno;
	}
	/**
	 * getter del atributo fila
	 * @return fila
	 */
	
	protected int getFila (){
		return fila;
	}
	
	/**
	 * setter del atributo fila
	 * @param _fila
	 */
	
	protected void setFila (int _fila){
		fila = _fila;
	}
	
	protected void setColumna (int col){
		columna = col;
	}
	
	public void setTurno(Ficha _turno){
		turno = _turno;
	}
	
	/**
	 * getter del atributo columna
	 * @return columna
	 */
	
	protected int getColumna (){
		return columna;
	}
	
	/**
	 * getter del atributo turno
	 * @return turno
	 */
	
	public Ficha getTurno(){
		return turno;
	}
}
