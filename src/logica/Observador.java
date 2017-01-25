package logica;


public interface Observador {
	void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador);
	void onMovimientoStart(Ficha turno);
	void onMovimientoEnd(Ficha turno, TableroInmutable tab);
	void onUndo(TableroInmutable tab, boolean hayMas);
	void onResetPartida(TableroInmutable tabIni, Ficha turno);
	void onUndoNotPossible(Ficha turno);
	void onCambioTurno(Ficha turno);
	void onMovimientoIncorrecto(String explicacion);
	void onCambioJuego(TableroInmutable tabIni,Ficha turno);
	void onNoMovimiento(String msg);
}
