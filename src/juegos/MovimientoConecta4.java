package juegos;

import logica.Ficha;
import logica.Tablero;


public class MovimientoConecta4 extends Movimiento {
	
	public MovimientoConecta4 (int c, Ficha turno){
		super(c, turno);
	}
	
	/**
	 * coloca una ficha en el tablero en la columna dada por
	 * el atributo columna de la clase movimiento 
	 * @param tablero 
	 */
	
	public boolean EjecutarMovimiento(Tablero tab){
		int i = tab.GetFil() - 1;
		while(tab.GetFicha(i, super.getColumna()) != Ficha.VACIA){
			i--;
		}
		tab.setFicha(i, super.getColumna(), super.getTurno());
		super.setFila(i);
		return true;
	}
	
	 /**
	  * extrae una ficha del tablero de la columna dada por
	  *	el atributo columna de la clase movimiento
	  *@param tablero
	  */
	
	 public void Undo(Tablero tab){
			int i = 0;
			while((i < tab.GetFil()) && (tab.GetFicha(i, super.getColumna()) == Ficha.VACIA)){
				i++;
			}
			tab.setFicha(i, super.getColumna(), Ficha.VACIA);
	 }
}
