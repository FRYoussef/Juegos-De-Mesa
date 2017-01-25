package juegos;

import Excepciones.ColumnException;
import Excepciones.FullPositionException;
import Excepciones.RowException;
import logica.Ficha;
import logica.Tablero;

public abstract class ReglasGravityYC4 extends ReglasFamiliaC4 {

	abstract public Tablero IniciaTablero();
	abstract public boolean HayTablas (Tablero tab, Movimiento mov);
	abstract public void MovimientoValido (Movimiento mov , Tablero tab) throws RowException, ColumnException, FullPositionException;
	
	
	/** 
	 * Devuelve si hay ganador
	 */
	
	public Ficha CommprobarGanador (Tablero tab, Movimiento mov){
		return CuatroEnRaya(tab, mov.getFila(), mov.getColumna()) ?  mov.getTurno() : Ficha.VACIA;
	}
	
	/**
	 * Comprueba el cuatro en raya en una recta 
	 * @param f
	 * @param c
	 * @param modF
	 * @param modC
	 * @param tab
	 * @return seguidas == EN_RAYA
	 */
	
	private boolean CuatroEnRayaEnLinea(Tablero tab, int f, int c, int modF, int modC){
		int seguidas = 1;
		seguidas = CuatroEnRayaSemiRecta(tab, f, c, modF, modC, seguidas);
		seguidas = CuatroEnRayaSemiRecta(tab, f, c, -modF, -modC, seguidas);
		return (seguidas == EN_RAYA);
	}
	
	/**
	 * Comprueba si hay cuatro en raya, en todas las direcciones 
	 */
	
	private boolean CuatroEnRaya(Tablero tab, int f, int c){
		
		//Comprueba lin Horizontal
		int modF = 0;
		int modC = 1;
		boolean cuatroEnRaya = CuatroEnRayaEnLinea(tab,f, c, modF, modC);
		
		//Comprueba lin vertical
		if(!cuatroEnRaya){
			modF = -1;
			modC = 0;
			cuatroEnRaya = CuatroEnRayaEnLinea(tab,f, c, modF, modC);
		}
		
		//Comprueba lin diagonal derecha
		if(!cuatroEnRaya){
			modF = -1;
			modC = -1;
			cuatroEnRaya = CuatroEnRayaEnLinea(tab,f, c, modF, modC);
		}
		
		//Comprueba lin diagonal izquierda
		if(!cuatroEnRaya){
			modF = 1;
			modC = -1;
			cuatroEnRaya = CuatroEnRayaEnLinea(tab,f, c, modF, modC);
		}
		return cuatroEnRaya;
	}
}
