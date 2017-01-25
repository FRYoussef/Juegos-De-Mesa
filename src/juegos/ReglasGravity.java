package juegos;

import Excepciones.ColumnException;
import Excepciones.FullPositionException;
import Excepciones.RowException;
import logica.Ficha;
import logica.Tablero;

public class ReglasGravity extends ReglasGravityYC4 {

	private int fil;
	private int col;
	
	public ReglasGravity(int _col, int _fil){
		fil = _fil;
		col = _col;
	}
	
	/**
	 * Inicializa el tablero con fichas vacias
	 * @return tab
	 */
	
	public Tablero IniciaTablero() {
		return new Tablero(fil, col);
	}
	
	/**
	 * Devuelve si se ha llegado a tablas
	 */
	
	public boolean HayTablas(Tablero tab, Movimiento mov) {
		return tableroLleno(tab);
	}

	/**
	 * Comprueba si el movimiento es correcto sino lanza una excepcion 
	 */
	
	public void MovimientoValido(Movimiento mov, Tablero tab) 
			throws RowException, ColumnException, FullPositionException{
		if(mov.getFila() < 0 || mov.getFila() >= fil) throw new RowException("Fila incorrecta tiene que estar entre 1 y " + fil);
		if(mov.getColumna() < 0 || mov.getColumna() >= col) throw new ColumnException("Columna incorrecta tiene que estar entre 1 y " + col);
		if(!(tab.GetFicha(mov.getFila(), mov.getColumna()) == Ficha.VACIA)) throw new FullPositionException("ERROR: Posicion ocupada");
	}
	
	/**
	 * Comprueba si el tablero esta lleno
	 * @param tab
	 * @return lleno
	 */
	
	private boolean tableroLleno(Tablero tab){
		return (tab.getNumFichasNegras() + tab.getNumFichasBlancas()) == fil*col;
	}


	
}
