package juegos;

import Excepciones.ColumnException;
import Excepciones.FullPositionException;
import Excepciones.NoMoveException;
import Excepciones.RowException;
import logica.Ficha;
import logica.Tablero;
import logica.TableroInmutable;

public abstract class ReglasFamiliaC4 extends ReglasJuego {
	
	final protected static int EN_RAYA = 4;
	
	abstract public void MovimientoValido (Movimiento mov , Tablero tab)throws RowException, ColumnException, FullPositionException;
	abstract public  Ficha  CommprobarGanador (Tablero tab, Movimiento mov);
	abstract public boolean HayTablas (Tablero tab, Movimiento mov);
	abstract public Tablero IniciaTablero();
	
	public boolean hayMovimiento(TableroInmutable tab, Ficha turno) {
		return true;
	}
	
	/**
	 * Devuelve quien empieza a jugar
	 */
	public Ficha Empieza(){
		return Ficha.BLANCA;
	}
	
	/** 
	 * Cambia el turno al jugador contrario
	 */
	
	public Ficha SiguienteTurno (Tablero tab, Ficha turno){
		return (turno == Ficha.NEGRA) ? Ficha.BLANCA : Ficha.NEGRA;
	}
	
	/**
	 * Comprueba apartir de una ficha, si hay cuatro en raya en una semirrecta
	 * @param tablero
	 * @param f
	 * @param c
	 * @param modF
	 * @param modC
	 * @param seguidas
	 */
	
	protected int CuatroEnRayaSemiRecta(Tablero tablero, int f, int c, int modF, int modC, int _seguidas){
		Ficha fichaAdy = Ficha.VACIA;
		int i = f;
		int j = c;
		int seguidas = _seguidas;
		boolean limite = (i >= 0) && (i < tablero.GetFil()) && (j >= 0) && (j < tablero.GetCol());
		boolean salir = false;
		boolean cuatroEnRaya = seguidas == EN_RAYA;
		while(!salir && limite && !cuatroEnRaya){
			fichaAdy = tablero.GetFicha(i + modF, j + modC);
			
			if(tablero.GetFicha(i, j).equals(fichaAdy)) seguidas++;
			else{ salir = true;}
			
			cuatroEnRaya = seguidas == EN_RAYA;
			i += modF;
			j += modC;
			
			limite = (i >= 0) && (i < tablero.GetFil()) && (j >= 0) && (j < tablero.GetCol());
		}
		return seguidas;
	}
}
