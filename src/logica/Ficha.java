package logica;


public enum Ficha {
	VACIA , BLANCA , NEGRA;
	
	
	public String toString (){
	String mostrar = "";
	switch (this){
	case VACIA: 
		mostrar = " ";
		break;
	case NEGRA:
		mostrar = "X";
		break;
	case BLANCA:
		mostrar = "O";
	}
	return mostrar;
	}
	
	/**
	 * Muestra el nombre de la ficha 
	 */
	
	public String toName (){
		String mostrar = "";
		switch (this){
		case VACIA: 
			mostrar = "Vacia";
			break;
		case NEGRA:
			mostrar = "Negra";
			break;
		case BLANCA:
			mostrar = "Blanca";
		}
		return mostrar;
	}
	
	/**
	 * @return color contrario
	 */
	public Ficha colorContrario(){
		return this ==  BLANCA ? NEGRA : BLANCA;
	}
}