package jugadores;

import java.util.Random;

import Excepciones.NoMoveException;
import juegos.Movimiento;
import juegos.ReglasReversi;
import logica.Ficha;
import logica.TableroInmutable;
import control.factorias.FactoriaDeJuego;

public class JugadorReversiAleatorio extends Jugador{

	private Random r;

	public JugadorReversiAleatorio(){
		r = new Random();
	}

	/**
	 * retorna el siguiente movimiento que va ha realizar el jugador aleatorio
	 * @param fct, tab, color
	 */
	public Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tab, Ficha color) {
		int fil = r.nextInt(tab.GetFil());
		int col = r.nextInt(tab.GetCol());
		if(	ReglasReversi.hayMovimientoEnElTurno(tab, color)){
			while(tab.GetFicha(fil, col) != Ficha.VACIA || !ReglasReversi.compruebaReversi(fct.crearMovimiento(fil, col, color), tab)){	
				fil = r.nextInt(tab.GetFil());
				col = r.nextInt(tab.GetCol());
			}
		}
		return fct.crearMovimiento(fil, col, color);
	}

}
