package control.comandos;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import Excepciones.ComandException;
import control.factorias.FactoriaComplica;
import control.factorias.FactoriaDeJuego;
import control.factorias.FactoriaJuegoConecta4;
import control.factorias.FactoriaJuegoGravity;
import control.factorias.FactoriaReversi;

public class LecturaDeComandos {
	private CommandLineParser parser;  
	private CommandLine cmdLine;   
	private Options options;
	public LecturaDeComandos(String[] args) throws ParseException{
		options = new Options();  
        options.addOption("g", "game", true,  "introducir tipo de juego (gr, co, c4, re)");  
        options.addOption("x", "tamX", true,  "introducir numero de columnas(solo disponible en gravity)");  
        options.addOption("y", "tamY", true, "introducir numero de filas (solo disponible en gravity)");
        options.addOption("h", "help", false, "muestra el mensaje de ayuda");
        options.addOption("u", true, "introducir console o window");
        parser  = new BasicParser(); 
        try{
        	cmdLine = parser.parse(options, args);
        }
        catch(ParseException exp){
        	throw(exp);
        }
	}
	
	
	/**
	 * muestra la ayuda y retorna false cuando el comando h no existe
	 * @return
	 */
	public boolean help(){
		if(cmdLine.hasOption("h")){
			new HelpFormatter().printHelp(LecturaDeComandos.class.getCanonicalName(), options);
			return true;
		}	
		return false;
	}
	
	public boolean tipoVista() throws ComandException{
		if(cmdLine.hasOption("u")){
			if(cmdLine.getOptionValue("u").equals("console"))
				return true;
			else if (cmdLine.getOptionValue("u").equals("swing"))
				return false;
			else
				throw new ComandException("comando de variable 'u' incorrecto, el comando -h muestra la ayuda");
		}else{
			return false;
		}
	}
	
	/**
	 * retorna el tipo de juego que entra como argumento por la consola 
	 * con: -g o --game y el acronimo del juego
	 * @return
	 */
	public FactoriaDeJuego tipoDeJuego() throws ComandException{
		if(!cmdLine.hasOption("g")){
			return new FactoriaJuegoConecta4(); 
		}
		String opcion = cmdLine.getOptionValue("g");
		int f = numeroFilas() , c = numeroColumnas();
		boolean aux = f == -1 && c == -1;
		if(opcion.equals("gr") && aux)
			return new FactoriaJuegoGravity();
		else if(opcion.equals("gr") && !aux){
			return new FactoriaJuegoGravity(f,c);
		} else if(opcion.equals("co") && aux)
			return new FactoriaComplica();
		else if (opcion.equals("c4") && aux)
			return new FactoriaJuegoConecta4(); 
		else if(opcion.equals("re") && aux)
			return new FactoriaReversi();
		else if(f == -1 || c == -1){
			throw new ComandException("valores introducidos incorrectos");
		}
		else{
			throw new ComandException("comando incorrecto,el comando -h muestra la ayuda");
		}
	}
	
	/**
	 * retorna el numero de filas que entran como argumento por la consola
	 * con: -y o --tamY y numero de filas
	 * @return
	 * @throws ComandException 
	 */
	public int numeroFilas() throws ComandException{
		try {
			if(cmdLine.hasOption("y")){
				int f = Integer.parseInt( cmdLine.getOptionValue("y"));
				return f;
			}
			else{
				return -1;
			}
		}
		catch(NumberFormatException e){
			throw new ComandException("comando incorrecto. introduzca -h para mostrar la ayuda");
		}
	}
	
	/**
	 * retorna el numero de columnas que entran como argumento por la consola
	 * con: -x o --tamX y numero de columnas
	 * @return
	 * @throws ComandException 
	 */
	
	public int numeroColumnas() throws ComandException{
		try {
			if(cmdLine.hasOption("x")){
				int c = Integer.parseInt( cmdLine.getOptionValue("x"));
				return c;
			}
			else{
				return -1;
			}
		}
		catch(NumberFormatException e){
			throw new ComandException("comando incorrecto. introduzca -h para mostrar la ayuda");
		}
	}
}
