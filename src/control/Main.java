package control;

import java.awt.EventQueue;
import java.util.Scanner;

import Excepciones.ComandException;
import control.comandos.LecturaDeComandos;
import control.factorias.FactoriaDeJuego;
import control.factorias.FactoriaJuegoGravity;
import logica.Partida;

import org.apache.commons.cli.*; 

import vistas.consola.VistaConsola;
import vistas.grafica.VistaSwing;

public class Main {
	public static void main(String[] args) {		
		FactoriaDeJuego fct = null;
		Scanner in = new Scanner(System.in);
		ControlSwing control;
		VistaConsola vistaConsole;
		final VistaSwing vistaSwing;
		try{
			LecturaDeComandos cmd = new LecturaDeComandos(args);
			if(!cmd.help()){
				fct = cmd.tipoDeJuego();
				Partida partida = new Partida(fct.creaReglas());
				if(cmd.tipoVista()){
					vistaConsole = new VistaConsola(fct, partida, in);
					vistaConsole.run();
				}else{
					control = new ControlSwing(fct,partida);
					vistaSwing = new VistaSwing(control, partida.getTabIn());
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							vistaSwing.setVisible(true);
						}});
				}
			}
		} catch(ParseException exp){
			System.err.println("Parsing failed. Reason:" + exp.getMessage());
		} catch(NumberFormatException e1){
			fct = new FactoriaJuegoGravity();
		} catch(ComandException e2){
			System.err.println(e2.getMessage());
		} 
	}
}
