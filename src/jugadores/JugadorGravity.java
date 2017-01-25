package jugadores;

import java.util.InputMismatchException;
import java.util.Scanner;

import Excepciones.FormatException;
import juegos.Movimiento;
import logica.Ficha;
import logica.TableroInmutable;
import control.factorias.FactoriaDeJuego;

public class JugadorGravity extends Jugador{
	private Scanner in;
	
	public JugadorGravity(Scanner sc){
		in = sc;
	}
	
	/**
	 * retorna el siguiente movimiento que va ha realizar el jugador 
	 * @param fct, tab, color
	 */
	public Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tab,Ficha color)throws FormatException  {
		int columna, fila;
		try{
			System.out.println("Introduce la columna: ");
			columna = in.nextInt() - 1;
			in.nextLine();
			System.out.println("Introduce la fila: ");
			fila = in.nextInt() - 1;
			in.nextLine();
		}catch(InputMismatchException e){
			in.nextLine();
			throw new FormatException("Formato incorrecto");
		}
		return fct.crearMovimiento(fila, columna, color);
	}
}
