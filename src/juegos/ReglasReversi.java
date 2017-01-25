package juegos;

import logica.Ficha;
import logica.Tablero;
import logica.TableroInmutable;
import Excepciones.ColumnException;
import Excepciones.FullPositionException;
import Excepciones.NoMoveException;
import Excepciones.RowException;

public class ReglasReversi extends ReglasJuego {
	final public static int FIL = 8;
	final public static int COL = 8;
	private boolean movBlanca;
	private boolean movNegra;
	
	public ReglasReversi(){
		movBlanca = true;
		movNegra = true;
	}
	
	/**
	 * Devuelve quien empieza a jugar
	 */
	public Ficha Empieza(){
		return Ficha.NEGRA;
	}
	
	/**
	 * Devuelve el tablero inicializado de reversi
	 */
	public Tablero IniciaTablero() {
		Tablero tab = new Tablero(FIL, COL);
		tab.setFicha(3, 3, Ficha.BLANCA);
		tab.setFicha(3, 4, Ficha.NEGRA);
		tab.setFicha(4, 3, Ficha.NEGRA);
		tab.setFicha(4, 4, Ficha.BLANCA);
		return tab;
	}
	
	
	
	/**
	 * Hay ganador si el tablero se ha llenado y uno de los dos tiene mas fichas, o si ya no se puede mover 
	 * y uno tienes mas fichas que el otro
	 */
	public Ficha CommprobarGanador(Tablero tab, Movimiento mov) {
		Ficha ganador = Ficha.VACIA;
		if(estaLleno(tab) && (tab.getNumFichasBlancas() != tab.getNumFichasNegras()) || 
				(!movBlanca && !movNegra && (tab.getNumFichasBlancas() != tab.getNumFichasNegras()))){
			if(tab.getNumFichasBlancas() > tab.getNumFichasNegras()) ganador = Ficha.BLANCA;
			else ganador = Ficha.NEGRA;
		}
		return ganador;
	}

	/**
	 * Habra tablas si el tablero se llena y los dos jugadores tienen el mismo num de fichas, 
	 * o si no hay mas movimientos y tienen las mismas fichas
	 */
	public boolean HayTablas(Tablero tab, Movimiento mov) {
		return (estaLleno(tab) && (tab.getNumFichasBlancas() == tab.getNumFichasNegras()) || 
				(!movBlanca && !movNegra && (tab.getNumFichasBlancas() == tab.getNumFichasNegras())));
	}

	/**
	 * Cambia el turno 
	 */
	public Ficha SiguienteTurno(Tablero tab, Ficha turno) {
		if(turno == Ficha.NEGRA && movBlanca) return Ficha.BLANCA;
		else if(turno == Ficha.BLANCA && movNegra) return Ficha.NEGRA;
		return turno;
	}

	/**
	 * Solo consideramos valido el movimiento si cambia fichas rivales.
	 */
	public void MovimientoValido(Movimiento mov, Tablero tab)
			throws RowException, ColumnException, FullPositionException{
		if(!compruebaReversi(mov ,tab)) throw new FullPositionException ("Movimiento incorrecto, no haces ningun cambio");
	}
	
	
	/**
	 * Vemos si el tablero esta lleno viendo las 4 esquinas del tablero, si alguna es aun vacia 
	 * aun no se ha llenado el tab
	 */
	private boolean estaLleno(Tablero tab){
		return tab.getNumFichasBlancas() + tab.getNumFichasNegras() == FIL*COL;
	}
	
	/**
	 * Comprueba si hay reversi en todas dirs
	 */
	public static boolean compruebaReversi(Movimiento mov, TableroInmutable tab){
		boolean reversi = compruebaReversiLinea(0, 1, mov, tab);
	
		if(tab.GetFicha(mov.getFila(), mov.getColumna()) != Ficha.VACIA)
			return false;
		
		if(!reversi) reversi = compruebaReversiLinea(0, -1, mov, tab);
		if(!reversi) reversi = compruebaReversiLinea(1, 0, mov, tab);
		if(!reversi) reversi = compruebaReversiLinea(-1, 0, mov, tab);
		if(!reversi) reversi = compruebaReversiLinea(1, 1, mov, tab);
		if(!reversi) reversi = compruebaReversiLinea(-1, -1, mov, tab);
		if(!reversi) reversi = compruebaReversiLinea(-1, 1, mov, tab);
		if(!reversi) reversi = compruebaReversiLinea(1, -1, mov, tab);
		return reversi;
	}
	
	/**
	 * Devuelve si hay reversi
	 */
	private  static boolean compruebaReversiLinea(int incX, int incY, Movimiento mov, TableroInmutable tab){
		int i = mov.getFila() + incX;
		int j = mov.getColumna() + incY;
		boolean salir = false;
		boolean falsoPositivo = false;
		
		//primera comprobacion de que si la inmediatamente siguiente es igual no haya reversi
		if(tab.GetFicha(i, j) == mov.getTurno()){
			salir = true;
			falsoPositivo = true;
		}
		while(!salir){
			if(tab.GetFicha(i, j) != mov.getTurno() && tab.GetFicha(i, j) != Ficha.VACIA){
				i += incX;
				j += incY;
			}
			else salir = true;
		}
		return ((tab.GetFicha(i, j) == mov.getTurno()) && !falsoPositivo) ? true : false;
	}
	
	/**
	 * Comprobamos si los jugadores de blancas y negras tienen movimiento
	 * y modificamos movBlancas y movNegras segun el resultado 
	 */
	public boolean hayMovimiento(TableroInmutable tab, Ficha turno) {
		movBlanca = hayMovimientoEnElTurno(tab, Ficha.BLANCA);
		movNegra = hayMovimientoEnElTurno(tab, Ficha.NEGRA);
		if((!movBlanca && turno == Ficha.NEGRA) || (!movNegra && turno == Ficha.BLANCA)) return false;
		return true;
	}
	
	
	/**
	 * comprobamos si el jugador del turno tiene movimientos 
	 * (El metodo es static para poder utilizarlo en jugadorReversiAleatorio)
	 * @param tab
	 * @param turno
	 * @return
	 */
	public static boolean hayMovimientoEnElTurno(TableroInmutable tab, Ficha turno){
		boolean hayMov = false;
		for(int i = 0; i < FIL && !hayMov ; i++){
			for(int j = 0; j < COL && !hayMov; j++){
				hayMov = compruebaReversi(new MovimientoReversi(i, j, turno) ,tab);
			}
		}
		return hayMov;
	}
}
