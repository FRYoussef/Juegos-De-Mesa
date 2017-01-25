package juegos;

import Excepciones.ColumnException;
import Excepciones.FullPositionException;
import logica.Ficha;
import logica.Tablero;

public class ReglasConecta4 extends ReglasGravityYC4 {
	final public static int FIL = 6;
	final public static int COL = 7;
	
	/**
	 * Inicializa el tablero con fichas vacias
	 * @return tab
	 */
	
	public Tablero IniciaTablero(){
		return new Tablero(FIL, COL);
	}
	
	/**
	 * comprueba si hay tablas
	 * @param tablero , movimiento
	 */
	
	public boolean HayTablas (Tablero tab, Movimiento mov){
		return EstaLleno(tab);
	}
	
	/**
	 * comprueba si el movimiento ha ejecutar es valido, viendo si la columna no excede los limites
	 * , y que la ficha que mas arriba esta es vacia
	 * @param tablero , movimiento
	 */
	
	 public void MovimientoValido (Movimiento mov , Tablero tab) throws ColumnException, FullPositionException{
		 if(mov.getColumna() < 0 || mov.getColumna() > COL) throw new ColumnException("Columna incorrecta tiene que estar entre 1 y " + COL);
		 if(!(tab.GetFicha(0, mov.getColumna()) == Ficha.VACIA)) throw new FullPositionException("ERROR: Columna Llena");
	 }
	 
	/**
	 * Comprueba si el tablero se ha llenado
	 * @param tablero 
	 */
	 
	private boolean EstaLleno(Tablero tab){
		return (tab.getNumFichasNegras() + tab.getNumFichasBlancas()) == FIL*COL;
	}


}
