package juegos;

import Excepciones.ColumnException;
import logica.Ficha;
import logica.Tablero;

public class ReglasComplica extends ReglasFamiliaC4 {
	final public static int FIL = 7;
	final public static int COL = 4;
	
	/**
	 * Inicializa el tablero con fichas vacias
	 * @param tab
	 */
	
	public Tablero IniciaTablero(){
		return new Tablero(FIL, COL);
	}
	
	/**
	 * comprueba si con el ultimo movimiento alguien ha ganado
	 * @param tab
	 * @param mov
	 */
	
	public Ficha CommprobarGanador (Tablero tab, Movimiento mov){
		return ProcesaTablero(tab, mov);
	}
	
	/**
	 * comprueba si hay tablas
	 * @param tablero , movimiento
	 */
	
	public boolean HayTablas (Tablero tab, Movimiento mov){
		return false;
	}
	
	/**
	 * comprueba si el movimiento a ejecutar es valido(no se sale de los limites)
	 * @param tablero , movimiento
	 */
	
	 public void MovimientoValido (Movimiento mov , Tablero tab) throws ColumnException{
		 if(mov.getColumna() < 0 || mov.getColumna() >= COL) throw new ColumnException("Columna incorrecta tiene que estar entre 1 y " + COL);
	 }
	 
	 /**
	 * Devuelve si hay cuatro en raya el color de la ficha que lo hace, sino vacio
	 * @param tab
	 * @param f
	 * @param c
	 */
	 
	private Ficha CuatroEnRaya(Tablero tab, int f, int c){
		 	int seguidas = 1;
		 
			//Comprueba lin Horizontal
			int modF = 0;
			int modC = 1;
			seguidas = CuatroEnRayaSemiRecta(tab, f, c, modF, modC, seguidas);
			boolean cuatroEnRaya = seguidas == EN_RAYA;
			
			//Comprueba lin vertical
			if(!cuatroEnRaya){
				seguidas = 1;
				modF = -1;
				modC = 0;
				seguidas = CuatroEnRayaSemiRecta(tab, f, c, modF, modC, seguidas);
				cuatroEnRaya = seguidas == EN_RAYA;
			}
			
			//Comprueba lin diagonal
			if(!cuatroEnRaya){
				seguidas = 1;
				modF = -1;
				modC = 1;
				seguidas = CuatroEnRayaSemiRecta(tab, f, c, modF, modC, seguidas);
				cuatroEnRaya = seguidas == EN_RAYA;
			}
			
			return cuatroEnRaya ? tab.GetFicha(f, c) : Ficha.VACIA;
		}
	
	/**
	 * Devuelve si el color de la ficha del ganador, y vacia si hay empate o no hay ganador
	 * @param tab
	 * @param mov
	 */
	
	private Ficha ProcesaTablero(Tablero tab, Movimiento mov){
		boolean grupoN = false;
		boolean grupoB = false;
		Ficha ganador = Ficha.VACIA;
		
		for(int i = 0; i < tab.GetFil(); i++){
			for(int j = 0; j < tab.GetCol(); j++){
				if(tab.GetFicha(i, j) != Ficha.VACIA){
					ganador = CuatroEnRaya(tab, i, j);
					if(ganador == Ficha.NEGRA) grupoN = true;
					else if (ganador == Ficha.BLANCA) grupoB = true;
				}
			}
		}
		if(grupoN && !grupoB){
			mov.setTurno(Ficha.NEGRA);
			ganador = Ficha.NEGRA;
		} 
		else if (!grupoN && grupoB){
			mov.setTurno(Ficha.BLANCA);
			ganador = Ficha.BLANCA;
		}
		return ganador;
	}

}
