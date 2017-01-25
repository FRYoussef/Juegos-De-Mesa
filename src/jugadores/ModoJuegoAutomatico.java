package jugadores;

import control.ControlSwing;

public class ModoJuegoAutomatico implements ModoJuego {
	private ControlSwing control;
	private Thread hilo; 
	
	public ModoJuegoAutomatico(ControlSwing _control){
		control = _control;
	}
	
	@Override
	/**
	 * comienza con la ejecucion de una nueva hebra que ejecuta un movimiento automatico 
	 */
	public void comenzar() {
		// TODO Auto-generated method stub
		hilo = new Thread(){
			public void run(){
				control.ponerAutomatico();
			}
		};
		hilo.start();
		
	}

	@Override
	/**
	 * interrumpe la hebra 
	 */
	public void terminar() {
		if(!(hilo == null))
			hilo.interrupt();
	}

	@Override
	/**
	 * si se pulsa deshacer se ejecuta este metodo para deshacer el movimiento automatico
	 */
	public void deshacerPulsado() {
		control.undo();
	}

}
