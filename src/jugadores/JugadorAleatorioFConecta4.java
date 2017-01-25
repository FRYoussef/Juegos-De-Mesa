package jugadores;

import java.util.Random;

import juegos.Movimiento;
import logica.Ficha;
import logica.TableroInmutable;
import control.factorias.FactoriaDeJuego;

public class JugadorAleatorioFConecta4 extends Jugador {
	private Random r;
	
	public JugadorAleatorioFConecta4(){
		r = new Random();
	}
	/**
	 * retorna el siguiente movimiento que va ha realizar el jugador aleatorio
	 * @param fct, tab, color
	 */
	
	public Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tab, Ficha color) {
		//int col = (int) Math.random() * tab.GetCol();
		int col = r.nextInt(tab.GetCol());
		while(tab.GetFicha(0, col) != Ficha.VACIA){
			col = r.nextInt(tab.GetCol());
		}
		return fct.crearMovimiento(col, col, color);
	}
	
}
