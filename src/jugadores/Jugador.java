package jugadores;

import Excepciones.FormatException;
import control.factorias.FactoriaDeJuego;
import juegos.Movimiento;
import logica.Ficha;
import logica.TableroInmutable;

abstract public class Jugador {
	public abstract Movimiento getMovimiento(FactoriaDeJuego fct, TableroInmutable tableroInmutable, Ficha color) throws FormatException;
}
