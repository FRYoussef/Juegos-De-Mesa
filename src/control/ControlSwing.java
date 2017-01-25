package control;

import juegos.Movimiento;
import jugadores.Jugador;
import logica.Ficha;
import logica.Observador;
import logica.Partida;
import control.factorias.FactoriaComplica;
import control.factorias.FactoriaDeJuego;
import control.factorias.FactoriaJuegoConecta4;
import control.factorias.FactoriaJuegoGravity;
import control.factorias.FactoriaReversi;

public class ControlSwing {
	private FactoriaDeJuego fct;
	private Jugador jugador;
	private Partida partida;
	
	public ControlSwing (FactoriaDeJuego _fct , Partida _partida){
		fct = _fct;
		partida = _partida;	
		jugador = fct.crearJugadorAleatorio();
		
	}
	
	/**
	 * resetea la partida 
	 * @param f
	 */
	public void cambioDeJuego(TipoDeJuego t, int fil, int col){
		if(t == TipoDeJuego.CONECTA4)
			fct = new FactoriaJuegoConecta4();
		else if(t == TipoDeJuego.COMPLICA)
			fct = new FactoriaComplica();
		else if (t == TipoDeJuego.GRAVITY && fil > 0 && col > 0)
			fct = new FactoriaJuegoGravity(fil,col);
		else if(t == TipoDeJuego.REVERSI)
			fct = new FactoriaReversi();
		else
			fct = new FactoriaJuegoGravity();
		jugador = fct.crearJugadorAleatorio();
		partida.cambiaJuego(fct.creaReglas());
	}
	
	
	/**
	 * resetea la partida
	 */
	public void reset(){
		partida.reset(fct.creaReglas());
	}
	
	/**
	 * pone un movimiento automatico
	 */
	public void ponerAutomatico(){
		try {
			Thread.sleep(0);
			Movimiento mov = partida.getMovimientoAuto(fct);
			partida.ejecutaMovimiento(mov);
		} catch (InterruptedException e) {
			System.out.println("hebra terminada");
		}
		
	}
	/**
	 * pone una ficha en el tablero en la posicion (fil,col)
	 * @param fil
	 * @param col
	 */
	public void poner(int fil,int col){
		Movimiento mov;
		mov = fct.crearMovimiento(fil, col, partida.GetTurno());
		partida.ejecutaMovimiento(mov);
	}
	
	/**
	 * deshace el ultimo movimiento utilizando el metodo deshacer de la partida
	 */
	public void undo(){
		partida.Deshacer();
	}
	
	/**
	 * utiliza los jugadores aleatorios para poner una ficha en el tablero
	 */
	public void ponerAleatorio (){
		Movimiento mov;
		mov = partida.getMovimiento(fct, jugador);
		partida.ejecutaMovimiento(mov);
	}
	
	/**
	 *a�ade un observador al modelo(clase partida)
	 * @param o
	 */
	public void addObserver(Observador o){
		partida.addObserver(o);
	}
	
	
	public Ficha getTurno(){
		return partida.GetTurno();
	}
	
}
