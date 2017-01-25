package jugadores;

import java.util.InputMismatchException;
import java.util.Scanner;

import Excepciones.FormatException;
import juegos.Movimiento;
import logica.Ficha;
import logica.TableroInmutable;
import control.factorias.FactoriaDeJuego;

public class JugadorComplica extends Jugador{
	Scanner in;
	
	public JugadorComplica (Scanner sc){
		in = sc;
	}
	
	/**
	 * retorna el siguiente movimiento que va ha realizar el jugador
	 * @param fct, tab, color
	 */
	public Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tab, Ficha color) 
		throws FormatException {
		int columna;
		try{
			System.out.println("Introduce la columna: ");
			columna = in.nextInt() - 1;
			in.nextLine();
		}catch(InputMismatchException e){
			in.nextLine();
			throw new FormatException("Formato incorrecto");
		}
		return fct.crearMovimiento(0, columna, color);
	}
}
