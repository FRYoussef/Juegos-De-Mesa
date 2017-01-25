package juegos;

import Excepciones.ColumnException;
import Excepciones.FullPositionException;
import Excepciones.NoMoveException;
import Excepciones.RowException;
import logica.Ficha;
import logica.Tablero;
import logica.TableroInmutable;

abstract public class ReglasJuego {
	abstract public boolean hayMovimiento(TableroInmutable tab, Ficha turno) ;
	abstract public Ficha Empieza();
	abstract public Tablero IniciaTablero();
	abstract public  Ficha CommprobarGanador (Tablero tab, Movimiento mov);
	abstract public boolean HayTablas (Tablero tab, Movimiento mov);
	abstract public Ficha SiguienteTurno (Tablero tab, Ficha turno);
	abstract public void MovimientoValido (Movimiento mov, Tablero tab) throws RowException, ColumnException, FullPositionException;
}
