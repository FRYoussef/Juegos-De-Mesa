package juegos;

import logica.Ficha;
import logica.Tablero;

public class MovimientoReversi extends Movimiento {
	
	public MovimientoReversi(int _fila, int _columna, Ficha _turno) {
		super(_columna, _fila, _turno);
	}

	/**
	 * Cambiamos todas las fichas en nuestra dir, hasta encontrar una de nuestro color
	 * (esto es porque ya hay previa comprobacion)
	 */
	
	public boolean EjecutarMovimiento(Tablero tab) {
		//Solo ponemos si la ficha era vacia
		Ficha aux = tab.GetFicha(getFila(), getColumna());
		if(aux == Ficha.VACIA){
			tab.setFicha(getFila(), getColumna(), getTurno());
			reversi(tab);
		}
		return aux == Ficha.VACIA ? true : false;
	}
	
	/**
	 * Gira las fichas en reversi de una linea
	 */
	private void reversiLinea(int incX, int incY, Tablero tab){
		int i = getFila() + incX;
		int j = getColumna() + incY;
		boolean salir = false;
		int giros = 0;
		
		//primera comprobacion de que si la inmediatamente siguiente es igual no haya reversi
		if(tab.GetFicha(i, j) == getTurno()){
			salir = true;
		}
		while(!salir){
			if(tab.GetFicha(i, j) != getTurno() && tab.GetFicha(i, j) != Ficha.VACIA){
				giros++;
				i += incX;
				j += incY;
			}
			else salir = true;
		}
		if(tab.GetFicha(i, j) != getTurno()) giros = 0;
		i = getFila() + incX;
		j = getColumna() + incY;
		for(; giros > 0; giros--) {
			tab.setFicha(i, j, getTurno());
			i += incX;
			j += incY;
		}
	}

	/**
	 * Comprueba si hay reversi en todas dirs
	 */
	private void reversi(Tablero tab){
		reversiLinea(0, 1, tab);
		reversiLinea(0, -1, tab);
		reversiLinea(1, 0, tab);
		reversiLinea(-1, 0, tab);
		reversiLinea(1, 1, tab);
		reversiLinea(-1, -1, tab);
		reversiLinea(-1, 1, tab);
		reversiLinea(1, -1, tab);
	}
	
	/**
	 * Deshacemos la ficha del tablero, y con ello el cambio que genero previamente
	 */
	//AUN NO FUNCIONA
	public void Undo(Tablero tab) {
		//invReversi(tab);
		//tab.setFicha(getFila(), getColumna(), getTurno());
	}
	
	/**
	 * Deshace el reversi en todas dirs
	 
	private void invReversi(Tablero tab){
		invReversiLinea(0, 1, tab);
		invReversiLinea(0, -1, tab);
		invReversiLinea(1, 0, tab);
		invReversiLinea(-1, 0, tab);
		invReversiLinea(1, 1, tab);
		invReversiLinea(-1, -1, tab);
		invReversiLinea(-1, 1, tab);
		invReversiLinea(1, -1, tab);
	}
	*/
	
	/**
	 * Gira las fichas en reversi de una linea
	private void invReversiLinea(int incX, int incY, Tablero tab){
		int i = getFila() + incX;
		int j = getColumna() + incY;
		Ficha aux = tab.GetFicha(getFila(), getColumna());
		boolean salir = false;
		int giros = 0;
		
		while(!salir){
			if(tab.GetFicha(i, j) == aux){
				giros--;
				tab.setFicha(i, j, getTurno());
				i += incX;
				j += incY;
			}
			else salir = true;
		}
		i -= incX;
		j -= incY;
		tab.setFicha(i, j, aux);
	}
	*/

}
