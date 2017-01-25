package logica;

public class Tablero implements TableroInmutable {
	private Ficha tablero[][];
	private int fil;
	private int col;
	private int numFichasBlancas;
	private int numFichasNegras;
	final public static String newline = System.getProperty("line.separator");
	
	public Tablero (int _fil, int _col){
		fil = _fil;
		col = _col;
		tablero = new Ficha[fil][col];
		for (int i = 0; i < fil; i++){
			for (int j = 0; j < col; j++){
				tablero[i][j] = Ficha.VACIA;
			}
		}
		numFichasBlancas = 0;
		numFichasNegras = 0;
	} 
	
	/**
	 * Devuelve el string con el tablero
	 * @return Tablero dibujado
	 */
	public String toString(){
		String mostrar = "";
		for(int i = 0; i < fil; i++){
			mostrar += "|";
			for(int j = 0;j < col;j++){
				mostrar += tablero[i][j].toString();
			}
			mostrar += "|";
			mostrar += newline;
		}
		
		//Muestra la ultima linea
		mostrar += "+";
		for (int i = 0; i < col ;i++){
			mostrar += "-";
		}
		mostrar += "+" + newline + " ";
		
		//Muestra el num de las columnas
		for (int i = 1; i <= col;i++){
			mostrar += i % 10;
		}
		mostrar += newline;
		return mostrar;
	}
	
	public Ficha GetFicha(int f, int c){	
		return ((f >=0) && (f < fil) && (c >= 0) && (c < col) ) ? tablero[f][c] : Ficha.VACIA;
	}
	
	public void setFicha(int f, int c, Ficha ficha){
		if(tablero[f][c] != ficha){
			
			if(tablero[f][c].equals(Ficha.BLANCA)){
				numFichasBlancas--;
			}else if (tablero[f][c].equals(Ficha.NEGRA)) {
				numFichasNegras--;
			}
			
			if (ficha.equals(Ficha.BLANCA)){
				numFichasBlancas++;
			}else if (ficha.equals(Ficha.NEGRA)){
				numFichasNegras++;
			}
		}
		tablero[f][c] = ficha;
	}
	
	public Tablero copia (){
		Tablero retorno = new Tablero(fil,col);
		for (int i = 0; i < fil;i++){
			for (int j = 0; j < col; j++){
				retorno.setFicha(i, j, tablero[i][j]);
			}
		}
		return retorno;
	}
	
	public int GetFil(){
		return fil;
	}
	
	public int GetCol(){
		return col;
	}

	public int getNumFichasBlancas() {
		return numFichasBlancas;
	}

	public void setNumFichasBlancas(int numFichasBlancas) {
		this.numFichasBlancas = numFichasBlancas;
	}

	public int getNumFichasNegras() {
		return numFichasNegras;
	}

	public void setNumFichasNegras(int numFichasNegras) {
		this.numFichasNegras = numFichasNegras;
	}

}