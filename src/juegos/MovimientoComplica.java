package juegos;

import logica.Ficha;
import logica.Tablero;

public class MovimientoComplica extends Movimiento  {
	boolean colLlena;
	
	public MovimientoComplica (int c,Ficha turno){
		super(c, turno);
		colLlena = false;
	}
	
	/**
	 * Si la fila esta llena, recoloca las fichas de esta para introducir la nueva ficha
	 * en la pocicion 0 del tablero.
	 * Si no lo esta, coloca la nueva ficha en la posicion dada por
	 * el atributo fila de Movimiento
	 * @param tab
	 */
	
	public boolean EjecutarMovimiento(Tablero tab) {
		Ficha aux = tab.GetFicha(0, super.getColumna());
		 if (aux != Ficha.VACIA){
			 Ficha vieja = tab.GetFicha(tab.GetFil()-1, super.getColumna());
			 recolocarPoner(tab);
			 tab.setFicha(0, super.getColumna(), super.getTurno());
			 super.setFila(tab.GetFil()-1);
			 super.setTurno(vieja);
			 colLlena = true;
		 }
		 else{
			 int i = tab.GetFil() - 1;
			 while(tab.GetFicha(i, super.getColumna()) != Ficha.VACIA){
				 i--;
			 }
			 tab.setFicha(i, super.getColumna(), super.getTurno());
			 super.setFila(i);
		 }
		 return true;
	 }
	
	/**
	 * deshace un movimiento y recoloca la columna si es necesario
	 * @param tab
	 */
	
	 public void Undo(Tablero tab){
		 if (!colLlena){
			 int i = 0;
			 while((i < tab.GetFil()) && (tab.GetFicha(i, super.getColumna()) == Ficha.VACIA)){
				 i++;
			 }
			 tab.setFicha(i, super.getColumna(), Ficha.VACIA);
		 }
		 else {
			 recolocarDeshacer(tab); 
			 tab.setFicha(super.getFila(), super.getColumna(), super.getTurno());
		 }
	 }
	 
	 /**
	  * recoloca el tablero para deshacer un movimiento en una columna llena
	  * @param tab
	  */
	 
	 private void recolocarDeshacer(Tablero tab){
		 for (int i = 0; i < tab.GetFil() - 1; i++){
			 tab.setFicha(i, super.getColumna(), tab.GetFicha(i+1, super.getColumna()));
		 }
	 }
	 
	 /**
	  * recoloca el tablero para ejecutar un movimieno en una columna llena
	  * @param tab
	  */
	 
	 private void recolocarPoner(Tablero tab){
		 for (int i = tab.GetFil() - 1; i > 0; i--){
			 tab.setFicha(i, super.getColumna(), tab.GetFicha(i-1, super.getColumna()));
		 }
	 }
}

