package control.factorias;

import java.util.Scanner;

import juegos.Movimiento;
import juegos.MovimientoConecta4;
import juegos.ReglasConecta4;
import juegos.ReglasJuego;
import jugadores.Jugador;
import jugadores.JugadorAleatorioFConecta4;
import jugadores.JugadorFConecta4;
import logica.Ficha;

public class FactoriaJuegoConecta4 implements FactoriaDeJuego {
	
	/**
	 * devuelve un atributo de la clase ReglasConecta4
	 * @return new ReglasConecta4() 
	 */
	
	public  ReglasJuego creaReglas (){
		return new ReglasConecta4();
	}
	
	/**
	 * devuelve un atributo de la clase MovimientoConecta4
	 * @return new MovimientoConecta4() 
	 */
	
	public Movimiento crearMovimiento(int f,int c,Ficha turno) {
		return new MovimientoConecta4(c, turno);
	}
	
	/**
	 * devuelve un atributo de la clase JugadorAleatorioFConecta4
	 * @return new JugadorAleatorioFConecta4()
	 */
	
	public Jugador crearJugadorAleatorio (){
		return new JugadorAleatorioFConecta4();
	}
	
	/**
	 * devuelve un atributo de la clase JugadorFConecta4
	 * @return new JugadorFConecta4()
	 */
	
	public Jugador crearJugadorHumano(Scanner sc){
		return new JugadorFConecta4(sc);
	}
}
