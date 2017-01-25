package vistas.consola;

import java.util.Scanner;
import control.ControlConsola;
import control.factorias.FactoriaComplica;
import control.factorias.FactoriaDeJuego;
import control.factorias.FactoriaJuegoConecta4;
import control.factorias.FactoriaJuegoGravity;
import control.factorias.FactoriaReversi;
import logica.Ficha;
import logica.Observador;
import logica.Partida;
import logica.TableroInmutable;

public class VistaConsola implements Observador {
	final public static String newline = System.getProperty("line.separator");

	Scanner in;
	ControlConsola controlador;
	boolean salir;
	
	// preguntar por pasa directamente atributo ControlConsola
	public VistaConsola(FactoriaDeJuego _fct, Partida _partida, Scanner _in){
		in = _in;
		controlador = new ControlConsola(_fct,_partida,in);
	}
	
	
	/**
	 * flujo principal de ejecucion del programa en la vista de consola
	 */
	
	public void run(){
		String opcion,aux = "";
		salir = false;
		String turno = ""; 
		System.out.println("Juega la " + turno);
		System.out.println(controlador.getTabLectura());
		controlador.addObserver(this);
		while(!salir){
			System.out.println("Que quieres hacer? ");
			opcion = in.nextLine();	
			
			if(opcion.length() >= 9) aux = opcion.substring(0,8);
		
			if(opcion.equals("poner")){
				controlador.poner();
			} else if(opcion.equals("deshacer")){
				controlador.undo();
			} else if(opcion.equals("jugar c4")) {
				controlador.reset(new FactoriaJuegoConecta4());
			} else if(opcion.equals("jugar co")){
				controlador.reset(new FactoriaComplica());
			} else if(opcion.equals("jugar re")){
					controlador.reset(new FactoriaReversi());
			} else if(opcion.equals("jugar gr")){
				controlador.reset(new FactoriaJuegoGravity());
			}else if (aux.equals("jugar gr")){
				aux = opcion.substring(9);
				int i = 0,j = 1;
				while(!aux.substring(i, j).equals(" ") && j < aux.length()){
					i++;
					j++;
				}
				try{
					int f = Integer.parseInt(aux.substring(0,i));
					int c = Integer.parseInt(aux.substring(i+1,aux.length()));
					controlador.reset(new FactoriaJuegoGravity(f,c));
				}catch(NumberFormatException e){
					System.err.println("Valores introducidos no validos" + newline);
				}
			} else if (opcion.equals("jugador blancas aleatorio")){
				System.out.println(controlador.getTabLectura());
				controlador.cambiarJugador(Ficha.BLANCA, "aleatorio");
			} else if(opcion.equals("jugador negras aleatorio")){
				System.out.println(controlador.getTabLectura());
				controlador.cambiarJugador(Ficha.NEGRA, "aleatorio");
			} else if (opcion.equals("jugador blancas humano")){
				System.out.println(controlador.getTabLectura());
				controlador.cambiarJugador(Ficha.BLANCA, "humano");
			} else if(opcion.equals("jugador negras humano")){
				System.out.println(controlador.getTabLectura());
				controlador.cambiarJugador(Ficha.NEGRA, "humano");
			} else if(opcion.equals("reiniciar")){
				controlador.reset();
			} else if(opcion.equals("salir")){
				salir = true;
			} else if(opcion.equals("ayuda")){
				System.out.println(muestraAyuda());
			}else {
				System.err.println("Opcion incorrecta" + newline);
				System.out.println("Juega la " + turno);
				System.out.println(controlador.getTabLectura());
			}
		}

	}


	@Override
	public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
		salir = true;
		System.out.println(tabFin + newline);
		System.out.println("Gana la " + ganador.toName());
	}

	@Override
	public void onMovimientoStart(Ficha turno) {}

	@Override
	public void onMovimientoEnd(Ficha turno, TableroInmutable tab) {
		System.out.println(tab);
	}

	@Override
	public void onUndo(TableroInmutable tab, boolean  hayMas) {
		System.out.println(tab);
	}

	@Override
	public void onResetPartida(TableroInmutable tabIni, Ficha turno) {
		System.out.println("Juega la " + turno.toName() + newline);
		System.out.println(newline + tabIni + newline);
	}

	@Override
	public void onUndoNotPossible(Ficha turno) {
		System.err.println("No se puede deshacer" + newline);
		System.out.println(controlador.getTabLectura());
	}

	@Override
	public void onCambioTurno(Ficha turno) {
		System.out.println("juega la " + turno.toName() + newline);
	}

	@Override
	public void onMovimientoIncorrecto(String explicacion) {
		System.err.println(explicacion);
	}

	@Override
	public void onCambioJuego(TableroInmutable tabIni,Ficha turno) {
		System.out.println("Juega la " + turno.toName() + newline);
		System.out.println(newline + tabIni + newline);
	}
	
	/**
	 * Notifica que no hay movimiento posible
	 */
	public void onNoMovimiento(String msg){
		System.out.println(newline + msg + newline);
	}
	
	/**
	 * Devuelve un string con la ayuda
	 * @return
	 */
	
	public String muestraAyuda(){
		String newline = System.getProperty("line.separator");
		String ayuda = newline + "PONER: utilizalo para poner la siguiente ficha." + newline +
				"DESHACER: deshace el ultimo movimiento hecho en la partida." + newline +
				"REINICIAR: reinicia la partida." + newline +
				"JUGAR [c4|co|gr|re] [tamX tamY]: cambia el tipo de juego." + newline +
				"JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador." + newline +
				"SALIR: termina la aplicacion." + newline +
				"AYUDA: muestra esta ayuda.";

		return ayuda;
	}

}
