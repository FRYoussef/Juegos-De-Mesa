package control.factorias;

import java.util.Scanner;

import juegos.Movimiento;
import juegos.ReglasJuego;
import jugadores.Jugador;
import logica.Ficha;

 public interface FactoriaDeJuego {
	 
	/**
	 * devuelve un atributo de la clase ReglasJuego
	 * @return new ReglasJuego() 
	 */
	 
	public abstract ReglasJuego creaReglas();
	
	/**
	 * devuelve un atributo de la clase Movimiento
	 * @return new Movimiento() 
	 */
	
	public abstract Movimiento crearMovimiento(int f, int c,Ficha turno);
	
	/**
	 * devuelve un atributo de la clase jugador
	 * @return new jugador()
	 */
	
	public abstract Jugador crearJugadorAleatorio ();
	
	/**
	 * devuelve un atributo de la clase jugador
	 * @return new jugador()
	 */
	
	public abstract Jugador crearJugadorHumano(Scanner sc);
}
