package jugadores;

import java.util.Random;

import juegos.Movimiento;
import logica.Ficha;
import logica.TableroInmutable;
import control.factorias.FactoriaDeJuego;

public class JugadorGravityAleatorio extends Jugador{

	private Random r;
	
	public JugadorGravityAleatorio(){
		r = new Random();
	}
	
	/**
	 * retorna el siguiente movimiento que va ha realizar el jugador aleatorio
	 * @param fct, tab, color
	 */

	public Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tab, Ficha color) {
		int fil = r.nextInt(tab.GetFil());
		int col = r.nextInt(tab.GetCol());
		while(tab.GetFicha(fil, col) != Ficha.VACIA){
			fil = r.nextInt(tab.GetFil());
			col = r.nextInt(tab.GetCol());
		}
		return fct.crearMovimiento(fil, col, color);
	}

}
