package control.factorias;

import java.util.Scanner;

import juegos.Movimiento;
import juegos.MovimientoReversi;
import juegos.ReglasJuego;
import juegos.ReglasReversi;
import jugadores.Jugador;
import jugadores.JugadorReversi;
import jugadores.JugadorReversiAleatorio;
import logica.Ficha;

public class FactoriaReversi implements FactoriaDeJuego {

	/**
	 * Crea las reglas de reversi
	 */
	
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

	/**
	 * Crea un movimiento de reversi
	 */
	
	public Movimiento crearMovimiento(int f, int c, Ficha turno) {
		return new MovimientoReversi(f, c,turno);
	}

	/**
	 * Crea un jugador aleatorio de Reversi
	 */
	
	public Jugador crearJugadorAleatorio() {
		return new JugadorReversiAleatorio();
	}

	/**
	 * Crea un jugador humano de revrsi
	 */
	
	public Jugador crearJugadorHumano(Scanner sc) {
		return new JugadorReversi(sc);
	}
	
}
