package control.factorias;

import java.util.Scanner;

import juegos.Movimiento;
import juegos.MovimientoGravity;
import juegos.ReglasGravity;
import juegos.ReglasJuego;
import jugadores.Jugador;
import jugadores.JugadorGravity;
import jugadores.JugadorGravityAleatorio;
import logica.Ficha;

public class FactoriaJuegoGravity implements FactoriaDeJuego {

	private int fil;
	private int col;
	
	public FactoriaJuegoGravity(){
		fil = 10;
		col = 10;
	}
	
	public FactoriaJuegoGravity(int _fil, int _col){
		fil = _fil;
		col = _col;
	}
	
	/**
	 * devuelve un atributo de la clase ReglasGravity
	 * @return new ReglasGravity() 
	 */
	
	public ReglasJuego creaReglas() {
		return new ReglasGravity(fil, col);
	}
	
	/**
	 * devuelve un atributo de la clase MovimientoGravity
	 * @return new MovimientoGravity() 
	 */
	
	public Movimiento crearMovimiento(int f, int c, Ficha turno) {
		return new MovimientoGravity(c ,f,turno);
	}

	/**
	 * devuelve un atributo de la clase JugadorGravityAleatorio
	 * @return new JugadorGravityAleatorio()
	 */
	
	public Jugador crearJugadorAleatorio() {
		return new JugadorGravityAleatorio();
	}

	/**
	 * devuelve un atributo de la clase JugadorGravity
	 * @return new JugadorGravity()
	 */
	
	public Jugador crearJugadorHumano(Scanner sc) {
		return new JugadorGravity(sc);
	}

}
