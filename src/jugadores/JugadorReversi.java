package jugadores;

import java.util.InputMismatchException;
import java.util.Scanner;

import juegos.Movimiento;
import logica.Ficha;
import logica.TableroInmutable;
import Excepciones.FormatException;
import control.factorias.FactoriaDeJuego;

public class JugadorReversi extends Jugador {
	Scanner in;
	
	public JugadorReversi (Scanner sc){
		in = sc;
	}
	
	/**
	 * Retorna el siguiente movimiento que va ha realizar el jugador 
	 */
	public Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tableroInmutable, Ficha color)
			throws FormatException {
		int columna, fila;
		try{
			System.out.println("Introduce la fila: ");
			fila = in.nextInt() - 1;
			in.nextLine();
			System.out.println("Introduce la columna: ");
			columna = in.nextInt() - 1;
			in.nextLine();
		}catch(InputMismatchException e){
			in.nextLine();
			throw new FormatException("Formato incorrecto");
		}
		return fct.crearMovimiento(fila, columna, color);
	}

}
