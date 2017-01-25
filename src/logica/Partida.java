package logica;

import java.util.ArrayList;
import control.factorias.FactoriaDeJuego;
import Excepciones.ColumnException;
import Excepciones.FormatException;
import Excepciones.FullPositionException;
import Excepciones.NoMoveException;
import Excepciones.RowException;
import Excepciones.StackException;
import juegos.MovimientoConecta4;
import juegos.ReglasJuego;
import juegos.Movimiento;
import jugadores.Jugador;

public class Partida {
	private Tablero tablero;
	private Ficha ganador;
	private Ficha turno;
	private PilaDeElementos pila;
	private ReglasJuego reglas;
	private ArrayList<Observador> observers;
	final public static int M = 11;


	public Partida (ReglasJuego _reglas){
		reglas = _reglas;
		tablero = reglas.IniciaTablero();
		pila = new PilaDeElementos(M);
		ganador = Ficha.VACIA;
		turno = reglas.Empieza();;
		observers = new ArrayList<Observador>();

	}

	/**
	 * Ejecuta movimiento si este es vaido y si no ha terminado la partida.
	 * Si lo ejecuta actualiza la pila y cambia el turno
	 * @param mov
	 * @return correcto
	 */
	public void ejecutaMovimiento(Movimiento mov) {
		if(mov != null){
			try{
				OnMovimientoStar();
				Tablero tabPila = tablero.copia();
				reglas.MovimientoValido(mov, tablero);
				mov.EjecutarMovimiento(tablero);
				if(reglas.hayMovimiento(tablero, turno))
					CambiaTurno();
				Ficha gan = reglas.CommprobarGanador(tablero, mov);
				if(gan != Ficha.VACIA && !reglas.HayTablas(tablero, mov)){
					ganador = gan; 
					terminada();
				} else if(reglas.HayTablas(tablero, mov)){
					ganador = Ficha.VACIA;
					terminada();
				} else{ 
					pila.introduceElemento(tabPila);
				}

			}catch (ColumnException e){
				movimientoIncorrecto(e.getMessage());
			}catch (RowException e){
				movimientoIncorrecto(e.getMessage());
			}catch (FullPositionException e){
				movimientoIncorrecto(e.getMessage());
			}
			finMovimiento();
		}
		//compruebaMov(mov);
	}

	/**
	 * Devuelve el siguiente turno
	 * @param mov
	 */
	public void CambiaTurno(){
		turno = reglas.SiguienteTurno(tablero, turno);
		cambioDeTurnoOn();
	}

	/**
	 * Comprueba si la Pila tiene elementos, si los tiene devuelve el turno al anterior jugador y
	 * deshace el ultimo movimiento
	 * @return vacia
	 * @throws StackException 
	 */
	public void Deshacer() {
		if (pila.empty()){
			noUndoPosible();
		}
		else {
			tablero = pila.sacarElemento();
			CambiaTurno();
			UndoOn();
		}
	}

	/**
	 * Resetea la Pila y el tablero y cambia las reglas de juego
	 * @param _reglas
	 */
	public void cambiaJuego(ReglasJuego _reglas){
		reglas = _reglas;
		turno = reglas.Empieza();
		pila.reset();
		tablero = reglas.IniciaTablero();
		cambiaJuegoOn();	
	}

	/**
	 * Devuelve si hay movimientos posibles 
	 * @return
	 */
	/*public void compruebaMov(Movimiento mov){
		try{
			reglas.hayMovimiento(tablero, turno);
		}catch (NoMoveException e){
			noMovimiento(e.getMessage());
			Ficha gan = reglas.CommprobarGanador(tablero, mov);
			if(gan != Ficha.VACIA && !reglas.HayTablas(tablero, mov)){
				ganador = gan; 
				terminada();
			} else if(reglas.HayTablas(tablero, mov)){
				ganador = Ficha.VACIA;
				terminada();
			}else CambiaTurno();
		}
	} */


	/**
	 * resetea la partida
	 */
	public void reset(ReglasJuego _reglas){
		reglas = _reglas;
		turno = reglas.Empieza();
		pila.reset();
		tablero = reglas.IniciaTablero();
		resetOn();
	}

	/**
	 * devuelve el movimiento
	 * @param fct
	 * @param jug
	 * @return
	 */
	public Movimiento getMovimiento(FactoriaDeJuego fct, Jugador jug) {
		Movimiento mov = null;
		try{
			mov = jug.getMovimiento(fct, tablero, turno);
		}catch(FormatException e){
			movimientoIncorrecto(e.getMessage());
		}
		return mov;
	}

	/**
	 * genera un nuevo movimiento automatico
	 * @param fct
	 * @return
	 */
	public Movimiento getMovimientoAuto(FactoriaDeJuego fct){
		Movimiento retorno = new MovimientoConecta4(0, Ficha.VACIA);
		try {
			retorno = fct.crearJugadorAleatorio().getMovimiento(fct, tablero, turno);
		} catch (FormatException e) {}
		return retorno;
	}

	/**
	 * Devuelve el string de partida
	 */
	public String toString(){ 
		return tablero.toString();
	}


	//GETTERS******************


	public Ficha GetGanador(){
		return ganador;
	}
	public Ficha GetTurno(){	
		return turno;
	}
	public int getFila(){	
		return tablero.GetFil();
	}
	public int getCol(){
		return tablero.GetCol();
	}
	public TableroInmutable getTabIn(){	
		return tablero;
	}



	//OBSERVADORES****************

	/**
	 * introduce un nuevo observador en la lista de observadores
	 * @param o
	 */
	public void addObserver (Observador o){
		observers.add(o);
	}

	/**
	 * borra un observador de la lista de observadores
	 * @param o
	 */

	public void removeObserver (Observador o){
		observers.remove(o);
	}


	public void OnMovimientoStar(){
		for(Observador ob: observers)
			ob.onMovimientoStart(turno);
	}

	/**
	 * notifica a los observadores que la partida ha finalizado
	 */

	public void terminada(){
		for(Observador ob: observers)
			ob.onPartidaTerminada(tablero, ganador);
	}

	/**
	 * notifica a los observadores que el movimiento ha finalizado con exito
	 */

	public void finMovimiento(){
		for(Observador ob: observers)
			ob.onMovimientoEnd(turno, tablero);
	}

	/**
	 * notificar que se puede deshacer a los observadores
	 */
	public void UndoOn(){
		for(Observador ob: observers)
			ob.onUndo(tablero, !pila.empty());// preguntar por hayMas
	}

	public void noUndoPosible(){
		for(Observador ob: observers)
			ob.onUndoNotPossible(turno);
	}

	/**
	 * notifica a los observadores que el juego se ha reseteado
	 */
	public void resetOn(){
		for(Observador ob: observers)
			ob.onResetPartida(tablero, turno);
	}

	/**
	 * avisa al observador que se ha cambiado el turno
	 */
	public void cambioDeTurnoOn(){
		for (Observador ob: observers)
			ob.onCambioTurno(turno);
	}


	/**
	 * avisa al observador que el ultimo movimiento ha ejecutar es incorreto
	 * @param msg
	 */
	public void movimientoIncorrecto(String msg){
		for(Observador ob: observers)
			ob.onMovimientoIncorrecto(msg);
	}

	/**
	 * En caso de jugar a reversi, que no haya mas movimientos para un jug
	 * @param msg
	 */
	public void noMovimiento(String msg){
		for(Observador ob: observers)
			ob.onNoMovimiento(msg);
	}

	public void cambiaJuegoOn(){
		for(Observador ob: observers)
			ob.onCambioJuego(tablero,turno);
	}

}
