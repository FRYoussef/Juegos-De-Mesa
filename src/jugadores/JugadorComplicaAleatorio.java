package jugadores;

import java.util.Random;

import juegos.Movimiento;
import logica.Ficha;
import logica.TableroInmutable;
import control.factorias.FactoriaDeJuego;

public class JugadorComplicaAleatorio extends Jugador{
	
	private Random r;
	
	public JugadorComplicaAleatorio(){
		r = new Random();
	}
	
	/**
	 * Genera un movimiento aleatorio 
	 */
	
	public Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tab, Ficha color){
		int col = r.nextInt(tab.GetCol());
		return fct.crearMovimiento(0, col, color);
	}
}
