package juegos;

import logica.Ficha;
import logica.Tablero;

public class MovimientoGravity extends Movimiento {
	
	public MovimientoGravity(int _columna, int _fila, Ficha _turno) {
		super(_columna, _fila, _turno);
	}
	
	/**
	 * coloca la ficha en la posicion correcta en el tablero segun la inclinacion del tablero, 
	 * tambien guarda la posicion donde la ha colocado en los atributos fila y columna heredados de Movimiento 
	 * @param modx
	 * @param mody
	 * @param tab
	 */
	
	private void colocaFicha(int modX, int modY, Tablero tab){
		int i = getFila();
		int j = getColumna();
		boolean lim = (i > 0) && (i < tab.GetFil() - 1) && (j > 0) && (j < tab.GetCol() - 1);
		boolean posEncontrada = false;
		boolean centro = (modX == 0 && modY == 0);
		while (!posEncontrada && lim && !centro){
			posEncontrada = !(tab.GetFicha(i + modX, j + modY) == Ficha.VACIA);
			if(!posEncontrada){
				i += modX;
				j += modY;
			}
			lim = (i > 0) && (i < tab.GetFil() - 1) && (j > 0) && (j < tab.GetCol() - 1);
		}
		setFila(i);
		setColumna(j);
		tab.setFicha(i, j, getTurno());
	}
	
	/**
	 * Calcula la direccion que va ha tomar la ficha segun su pos
	 * @param tab 
	 */
	
	public boolean EjecutarMovimiento(Tablero tab) {
		Ficha vacia = tab.GetFicha(getFila(), getColumna());
		if(vacia == Ficha.VACIA){
			int incX = 0, incY = 0;
			int dimX = tab.GetFil();
			int dimY = tab.GetCol(); 
			double midX = ((dimX % 2) == 0) ? dimX/2 - 0.5 : dimX/2;
			double midY = ((dimY % 2) == 0) ? dimY/2 - 0.5 : dimY/2;
			
			// Restamos al tam del tablero las coordenadas de la ficha
			double x = dimX - getFila() - 1;
			double y = dimY - getColumna() - 1;
			if(x < midX)
				incX = 1;
			else if(x > midX)
				incX = -1;
			
			if(y < midY) 
				incY = 1;
			else if(y > midY) 
				incY = -1;
			
			
			if(incX != 0 && incY != 0){
				if(incX == 1 && incY == 1){
					if(x > y)incX = 0;
					if(x < y) incY = 0;
				}
				else if(incX == -1 && incY == -1){
					if(getFila() > getColumna())incX = 0;
					if(getFila() < getColumna()) incY = 0;
				}
				else if(incX == 1 && incY == -1){
					if(x > getColumna())incX = 0;
					if(x < getColumna()) incY = 0;
				}
				else if(incX == -1 && incY == 1){
					if(getFila() > y)incX = 0;
					if(getFila() < y) incY = 0;
				}
			}
			colocaFicha(incX, incY, tab);
		}
		return vacia == Ficha.VACIA ? true : false;
	}

	/**
	 *  extrae una ficha del tablero de la columna dada por
	  *	el atributo columna de la clase movimiento
	 * @param tab 
	 */
	
	public void Undo(Tablero tab) {
		tab.setFicha(getFila(), getColumna(), Ficha.VACIA);
	}

}
