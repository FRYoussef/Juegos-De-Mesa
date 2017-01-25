package control.factorias;

import java.util.Scanner;

import juegos.Movimiento;
import juegos.MovimientoComplica;
import juegos.ReglasComplica;
import juegos.ReglasJuego;
import jugadores.Jugador;
import jugadores.JugadorComplicaAleatorio;
import jugadores.JugadorFConecta4;
import logica.Ficha;

public class FactoriaComplica implements FactoriaDeJuego  {
	
	/**
	 * devuelve un atributo de la clase ReglasComplica
	 * @return new ReglasComplica() 
	 */
	
	public  ReglasJuego creaReglas (){
		return new ReglasComplica();
	}
	
	/**
	 * devuelve un atributo de la clase MovimientoComplica
	 * @return new MovimientoComplica() 
	 */
	
	public Movimiento crearMovimiento(int f,int c,Ficha turno) {
		return new MovimientoComplica(c,turno);
	}
	
	/**
	 * devuelve un atributo de la clase JugadorAleatorioFConecta4
	 * @return new JugadorAleatorioFConecta4()
	 */
	
	public Jugador crearJugadorAleatorio (){
		return new JugadorComplicaAleatorio();
	}
	
	/**
	 * devuelve un atributo de la clase JugadorFConecta4
	 * @return new JugadorFConecta4()
	 */
	
	public Jugador crearJugadorHumano(Scanner sc){
		return new JugadorFConecta4(sc);
	}
}
