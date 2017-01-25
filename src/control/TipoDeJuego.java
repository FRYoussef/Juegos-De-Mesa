package control;

public enum TipoDeJuego {
	CONECTA4, COMPLICA, GRAVITY, REVERSI;
	
	public String toString(){
		if(this == TipoDeJuego.CONECTA4)
			return "Conecta4";
		else if (this == TipoDeJuego.COMPLICA)
			return "Complica";
		else if (this == TipoDeJuego.GRAVITY)
			return "Gravity";
		else
			return "Reversi";
	}
}
