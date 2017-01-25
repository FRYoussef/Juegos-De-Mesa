package logica;
/**
 * Pila de elementos circular 
 */

public class PilaDeElementos  {
	private Tablero[] undoStack;
	private int numUndo;
	private int capacidad;
	private int pointStack;
	
	public PilaDeElementos(int _capacidad){
		capacidad = _capacidad;
		undoStack = new Tablero [capacidad];
		numUndo = 0;
		pointStack = 0;
	}
	
	/**
	 * Introduce un elemento en la pila 
	 */
	
	public void introduceElemento(Tablero tab){
		undoStack[pointStack] = tab;
		if (!(numUndo < (capacidad-1))){
			if (pointStack < (capacidad - 1)) pointStack++; 
			else{ pointStack = 0; }
		}
		else{
			numUndo++;
			if (pointStack < (capacidad - 1)) pointStack++; 
			else{ pointStack = 0; }
		}
	}
	
	/**
	 * Saca un elemento de la pila  
	 */
	
	public Tablero sacarElemento(){
		Tablero aux = new Tablero(0,0);
		if (numUndo > 0){
			if (pointStack > 0){
				pointStack--;
			}
			else{
				pointStack = capacidad - 1;
			}
			numUndo--;
			aux = undoStack[pointStack];
		}
		return aux;
	}
	
	public boolean empty(){
		return (numUndo == 0);
	}
	/**
	 *  Resetea la pila 
	 */
	
	public void reset(){
		numUndo = 0;
		pointStack = 0;
	}
}