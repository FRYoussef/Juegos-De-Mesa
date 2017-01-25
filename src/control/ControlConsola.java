package control;

import java.util.Scanner;
import juegos.Movimiento;
import jugadores.Jugador;
import logica.Ficha;
import logica.Observador;
import logica.Partida;
import logica.TableroInmutable;
import Excepciones.ColumnException;
import Excepciones.FormatException;
import Excepciones.FullPositionException;
import Excepciones.RowException;
import Excepciones.StackException;
import control.factorias.FactoriaDeJuego;

public class ControlConsola {
	private Partida partida;
	private Scanner in;
	private FactoriaDeJuego fct;
	private Jugador jugador1;
	private Jugador jugador2;
	
	public ControlConsola (FactoriaDeJuego _fct, Partida _partida, Scanner _in){
		partida = _partida;
		in = _in;
		fct = _fct;
		jugador1 = fct.crearJugadorHumano(in);
		jugador2 = fct.crearJugadorHumano(in);
	}
	
	/**
	 * resetea la partida
	 * @param f
	 */
	
	public void reset(FactoriaDeJuego f){
		fct = f;
		jugador1 = fct.crearJugadorHumano(in);
		jugador2 = fct.crearJugadorHumano(in);
		partida.cambiaJuego(fct.creaReglas());
	}
	
	public void reset(){
		jugador1 = fct.crearJugadorHumano(in);
		jugador2 = fct.crearJugadorHumano(in);
		partida.reset(fct.creaReglas());
	}
	
	/**
	 * deshace un movimiento haciendo uso del atributo partida 
	 * @throws StackException
	 */
	
	public void undo() {
		partida.Deshacer();
	}
	
	/*public void turnoLectura(String turno){
		turno = partida.GetTurno().toName();
	}*/
	
	/**
	 * cambia el tipo de jugador
	 * @param color
	 * @param tipoJugador
	 */
	
	public void cambiarJugador(Ficha color, String tipoJugador){	// alomejor hay que poner excepcion aqui por tipojugador
		if(tipoJugador.equals("aleatorio")){
			if(color == Ficha.BLANCA)
				jugador1 = fct.crearJugadorAleatorio();
			else
				jugador2 = fct.crearJugadorAleatorio();
		}else if (tipoJugador.equals("humano")){
			if(color == Ficha.BLANCA)
				jugador1 = fct.crearJugadorAleatorio();
			else
				jugador2 = fct.crearJugadorHumano(in);
		}
	}
	
	/**
	 * pone una ficha en el tablero utilizando el atributo Partida
	 * @throws FormatException
	 * @throws ColumnException
	 * @throws RowException
	 * @throws FullPositionException
	 */
	
	public void poner(){
			Movimiento mov = null;
			if(partida.GetTurno() == Ficha.BLANCA)
				mov = partida.getMovimiento(fct, jugador1);
			else
				mov = partida.getMovimiento(fct, jugador2);
			if(mov != null) partida.ejecutaMovimiento(mov);
	}
	
	public TableroInmutable getTabLectura(){
		return partida.getTabIn();
	}
	
	public void addObserver(Observador o){
		partida.addObserver(o);
	}
	
	
}
