package vistas.grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import jugadores.ModoJuego;
import jugadores.ModoJuegoAutomatico;
import jugadores.ModoJuegoHumano;
import jugadores.TipoTurno;
import logica.Ficha;
import logica.Observador;
import logica.TableroInmutable;
import control.ControlSwing;
import control.TipoDeJuego;

public class VistaSwing extends JFrame {
	private ControlSwing controlador;
	private TableroInmutable tab;
	private TipoTurno Tblancas;
	private TipoTurno Tnegras;
	private ModoJuego Mblancas;
	private ModoJuego Mnegras;
	private boolean enableTablero;

	private boolean terminada;// argumento que se utiliza para indicar que la partida esta terminada
	// si lo esta no comienza un nuevo movimiento 

	private boolean sonidoOn = false;//activa o desactiva el sonido 
	//(desactivar si se quiere ejecutar con javaSE 1.7) 

	public VistaSwing (ControlSwing  control, TableroInmutable _tab){
		super("Practica 5 - TP");
		terminada = false;
		Tblancas = TipoTurno.Humano;
		Tnegras = TipoTurno.Humano;
		Mblancas = new ModoJuegoHumano();
		Mnegras = new ModoJuegoHumano();
		tab = _tab;
		enableTablero = true;
		//SoundEffect.init();
		controlador = control;
		this.setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		//declaracion de paneles
		panelEast panelEste = new panelEast();
		PanelTablero tablero = new PanelTablero(controlador.getTurno(),tab);

		//add observadores
		controlador.addObserver(tablero);
		controlador.addObserver(panelEste);

		//anado paneles al frame
		getContentPane().add(panelEste,BorderLayout.EAST);
		getContentPane().add(tablero,BorderLayout.WEST);

		refrescar();
	}

	public void salir(){
		this.dispose();
	}

	public void refrescar(){
		this.revalidate();
		this.pack();
	}



	/**
	 * panel de botones de la derecha
	 * @author josemanuel
	 *
	 */
	private class panelEast extends JPanel implements Observador {
		JButton Bdeshacer ;
		JButton Breiniciar;
		JButton Bsalir;
		JButton Bcambiar;
		JComboBox<TipoDeJuego> jugando;
		JComboBox<TipoTurno> jug1;
		JComboBox<TipoTurno> jug2;

		public panelEast(){
			//inicializo argumentos
			Bdeshacer = new JButton("Deshacer");
			Bdeshacer.setEnabled(false);
			Breiniciar = new JButton("Reinicar");
			Bsalir = new JButton("Salir");
			Bcambiar = new JButton("Cambiar");
			jugando = new JComboBox<TipoDeJuego>();
			jugando.addItem(TipoDeJuego.CONECTA4);
			jugando.addItem(TipoDeJuego.COMPLICA);
			jugando.addItem(TipoDeJuego.GRAVITY);
			jugando.addItem(TipoDeJuego.REVERSI);
			jugando.setBackground(Color.WHITE);
			jug1 = new JComboBox<TipoTurno>();
			jug1.addItem(TipoTurno.Humano);
			jug1.addItem(TipoTurno.Automatico);
			jug1.setBackground(Color.WHITE);
			jug2 = new JComboBox<TipoTurno>();
			jug2.addItem(TipoTurno.Humano);
			jug2.addItem(TipoTurno.Automatico);
			jug2.setBackground(Color.WHITE);

			//inicializovariables
			JPanel botonCambiar = new JPanel();
			JPanel panelNorte = new JPanel();
			JPanel panelCentro = new JPanel();
			JPanel panelTurno = new JPanel();
			JPanel panelTurnoAux = new JPanel();
			JPanel panelTurnoNom = new JPanel();
			JPanel panelCambiar = new JPanel();
			JPanel panelSur = new JPanel();
			final NfilsNcols nfc = new NfilsNcols();
			nfc.setVisible(false);



			//configuro los layouts
			setLayout(new BorderLayout());
			panelCentro.setLayout(new BorderLayout());
			panelCentro.setBorder(new TitledBorder("Cambio de Juego"));
			panelCentro.setBackground(Color.WHITE);
			panelNorte.setLayout(new FlowLayout());
			panelNorte.setBorder(new TitledBorder("Partida"));
			panelNorte.setBackground(Color.WHITE);
			panelCambiar.setLayout(new BorderLayout());
			panelCambiar.setBackground(Color.WHITE);
			panelSur.setBackground(Color.WHITE);
			botonCambiar.setLayout(new FlowLayout());
			botonCambiar.setBackground(Color.WHITE);
			panelTurno.setLayout(new BorderLayout());
			panelTurno.setBackground(Color.WHITE);
			panelTurnoAux.setLayout(new BorderLayout());
			panelTurnoAux.setBackground(Color.WHITE);
			panelTurnoNom.setLayout(new BorderLayout());
			panelTurnoNom.setBackground(Color.WHITE);

			//anado componentes del centro
			panelCentro.add(jugando,BorderLayout.NORTH);
			JLabel turno1 = new JLabel();
			turno1.setText("Negras");
			turno1.setFont(new Font("Verdana", Font.BOLD, 10));
			JLabel turno2 = new JLabel();
			turno2.setText("Blancas");
			turno2.setFont(new Font("Verdana", Font.BOLD, 10));
			panelTurnoNom.add(turno1, BorderLayout.NORTH);
			panelTurnoNom.add(turno2, BorderLayout.SOUTH);
			panelTurnoAux.add(jug1, BorderLayout.NORTH);
			panelTurnoAux.add(jug2, BorderLayout.SOUTH);
			panelTurno.add(panelTurnoAux, BorderLayout.EAST);
			panelTurno.add(panelTurnoNom, BorderLayout.WEST);
			panelCambiar.add(panelTurno, BorderLayout.SOUTH);
			panelCambiar.add(nfc, BorderLayout.NORTH);
			botonCambiar.add(Bcambiar);
			panelCambiar.add(botonCambiar,BorderLayout.CENTER);
			panelCentro.add(panelCambiar,BorderLayout.CENTER);


			//decoro los botones
			Bdeshacer.setIcon(new ImageIcon(Icon.UNDO.getUrl()));
			Bdeshacer.setOpaque(false);
			Bdeshacer.setContentAreaFilled(false);
			Breiniciar.setIcon(new ImageIcon(Icon.REINICIAR.getUrl()));
			Breiniciar.setOpaque(false);
			Breiniciar.setContentAreaFilled(false);
			Bsalir.setIcon(new ImageIcon(Icon.SALIR.getUrl()));
			Bsalir.setOpaque(false);
			Bsalir.setContentAreaFilled(false);
			Bcambiar.setIcon(new ImageIcon(Icon.ACEPTAR.getUrl()));
			Bcambiar.setOpaque(false);
			Bcambiar.setContentAreaFilled(false);

			//anado botones al panel norte
			panelNorte.add(Bdeshacer);
			panelNorte.add(Breiniciar);
			panelSur.add(Bsalir);
			add(panelCentro,BorderLayout.CENTER);
			add(panelNorte,BorderLayout.NORTH);
			add(panelSur,BorderLayout.SOUTH);

			//anado listeners a botones
			Bdeshacer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(sonidoOn) SoundEffect.REMOVE.play();
					controlador.undo();
				}
			});
			Breiniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(sonidoOn) SoundEffect.REBOOT.play();
					controlador.reset();
				}
			});
			Bsalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(sonidoOn) SoundEffect.CANCEL.play();
					salir();
				}
			});
			Bcambiar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(sonidoOn) SoundEffect.SWITCH.play();
					controlador.cambioDeJuego((TipoDeJuego)jugando.getSelectedItem(),nfc.getFil(),nfc.getCol());
				}
			});

			jugando.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jugando.getSelectedItem() == TipoDeJuego.GRAVITY) nfc.setVisible(true);// redimensionable
					else{ nfc.setVisible(false);}
				}
			});

			jug1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jug1.getSelectedItem().equals(TipoTurno.Humano)){
						Tnegras = TipoTurno.Humano;
						Mnegras = new ModoJuegoHumano();
					}else{
						Tnegras= TipoTurno.Automatico;
						Mnegras = new ModoJuegoAutomatico(controlador);
						if(controlador.getTurno() == Ficha.NEGRA) 
							Mnegras.comenzar();
					}

				}
			});

			jug2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jug2.getSelectedItem().equals(TipoTurno.Humano)){
						Tblancas = TipoTurno.Humano;
						Mblancas = new ModoJuegoHumano();
					}else{
						Tblancas = TipoTurno.Automatico;
						Mblancas = new ModoJuegoAutomatico(controlador);
						if(controlador.getTurno() == Ficha.BLANCA)
							Mblancas.comenzar();
					}

				}
			});

		}

		private class NfilsNcols extends JPanel{	
			private JTextField nFil,nCol;
			public NfilsNcols (){
				//inicializo atributos
				nFil = new JTextField("");
				JLabel filS = new JLabel();
				filS.setText("Filas");
				filS.setFont(new Font("Verdana", Font.BOLD, 10));
				nCol = new JTextField("");
				JLabel colS = new JLabel();
				colS.setText("Columnas");
				colS.setFont(new Font("Verdana", Font.BOLD, 10));

				//configuro layouts
				this.setLayout(new FlowLayout());
				this.setBackground(Color.WHITE);

				//configuro bordes
				nFil.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				nCol.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
				nFil.setPreferredSize(new Dimension(40,20));
				nCol.setPreferredSize(new Dimension(40,20));
				this.add(filS);
				this.add(nFil);
				this.add(colS);
				this.add(nCol);
				this.revalidate();
			}


			public int getFil(){
				int fil = 2;
				try{
					fil = Integer.parseInt(nFil.getText());
					if (fil < 0) return 0;
				}catch(NumberFormatException e){
					return 0;
				}
				return fil;
			}

			public int getCol(){
				int col = 2;
				try{
					col = Integer.parseInt(nCol.getText());
					if (col < 0) return 0;
				}catch(NumberFormatException e){
					return 0;
				}
				return col;
			}

		}	


		//OBSERVADORES PANELEAST

		/**
		 * Notifica que no hay movimiento posible
		 */
		public void onNoMovimiento(String msg){
		}

		@Override
		public void onPartidaTerminada(TableroInmutable tabFin, Ficha ganador) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					terminada = true;
					Mblancas.terminar();
					Mnegras.terminar();
					Bdeshacer.setEnabled(false);	
				}
			});
		}

		@Override
		public void onMovimientoStart(Ficha turno) {

		}

		@Override
		public void onMovimientoEnd(final Ficha turno,TableroInmutable tab) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					if(!terminada){ 
						if(turno == Ficha.NEGRA){
							Mblancas.terminar();
							Mnegras.comenzar();
						}
						else if (turno == Ficha.BLANCA){
							Mnegras.terminar();
							Mblancas.comenzar();
						}
					}
				}
			});

		}

		@Override
		public void onUndo(TableroInmutable tab, final boolean hayMas) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					Bdeshacer.setEnabled(hayMas);
					if(controlador.getTurno() == Ficha.BLANCA){
						Mblancas.deshacerPulsado();
					}else{
						Mnegras.deshacerPulsado();
					}
				}
			});
		}

		@Override
		public void onResetPartida(TableroInmutable tabIni, final Ficha turno) {

			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					enableTablero = true;
					Mblancas.terminar();
					Mnegras.terminar();
					if(turno == Ficha.BLANCA){
						Mblancas.comenzar();
					}else{
						Mnegras.comenzar();
					}
					terminada = false;
					Bdeshacer.setEnabled(false);
				}
			});
		}

		@Override
		public void onUndoNotPossible(final Ficha turno) {

			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					if(turno == Ficha.BLANCA){
						Mblancas.comenzar();
					}else if (turno == Ficha.NEGRA){
						Mnegras.comenzar();
					}
				}
			});
		}

		@Override
		public void onCambioTurno(final Ficha turno) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					if (turno == Ficha.BLANCA && Tblancas == TipoTurno.Humano || turno == Ficha.NEGRA && Tnegras == TipoTurno.Humano){
						Bdeshacer.setEnabled(true);
						enableTablero = true;
					}else{
						Bdeshacer.setEnabled(false);
						enableTablero = false;
					}
				}
			});
		}

		@Override
		public void onMovimientoIncorrecto(String explicacion) {}

		@Override
		public void onCambioJuego(TableroInmutable tabIni, Ficha turno) {
			onResetPartida(tabIni, turno);
		}
	}


	/**
	 * panel del tablero
	 * @author josemanuel
	 *
	 */
	private class PanelTablero extends JPanel implements Observador {
		private tabButton tab[][];
		private int filas;
		private int cols;
		private JPanel tablero;
		private JPanel central;
		private JPanel sur;
		private JLabel text;
		private JButton ponerAleatorio;


		public PanelTablero (Ficha turno , TableroInmutable tabIni){ // para probar luego cambiar por tableroInmutable los argumentos
			filas = tabIni.GetFil();
			cols= tabIni.GetCol();
			//inicializo
			tablero = inicializaTablero(tabIni);
			tablero.setBackground(Color.WHITE);
			central = new JPanel();
			sur = new JPanel();
			text = new JLabel(); 
			JPanel pnlMensajes = new JPanel();
			ponerAleatorio = new JButton("Poner Aleaorio");

			//layouts
			central.setLayout(new BorderLayout());
			central.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			this.setLayout(new BorderLayout());
			sur.setLayout(new FlowLayout());
			pnlMensajes.setLayout(new FlowLayout());

			pnlMensajes.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			pnlMensajes.setBackground(Color.WHITE);

			ponerAleatorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(sonidoOn) SoundEffect.RANDOM.play();
					controlador.ponerAleatorio();
				}
			});
			ponerAleatorio.setIcon(new ImageIcon(Icon.RANDOM.getUrl()));
			ponerAleatorio.setOpaque(false);
			ponerAleatorio.setContentAreaFilled(false);


			sur.add(ponerAleatorio);
			sur.setBackground(Color.WHITE);

			text.setText("Juegan  " + turno.toName() + 's');
			text.setFont(new Font("Verdana", Font.BOLD, 20));
			text.setForeground(Color.BLUE);
			pnlMensajes.add(text);

			central.add(pnlMensajes,BorderLayout.SOUTH);
			central.add(tablero,BorderLayout.CENTER);
			central.setBackground(Color.WHITE);
			central.revalidate();
			add(sur,BorderLayout.SOUTH);
			add(central,BorderLayout.CENTER);
			this.revalidate();
		}




		public JPanel inicializaTablero(TableroInmutable tabIni){
			JPanel retorno = new JPanel();
			retorno.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			tab = new tabButton[filas][cols];
			retorno.setLayout(new GridLayout(filas,cols));
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < cols; j++) {
					tab[i][j] = new tabButton(i,j);
					if (tabIni.GetFicha(i, j).equals(Ficha.BLANCA)){
						tab[i][j].setIcon(new ImageIcon(Icon.RED.getUrl()));
					}else if(tabIni.GetFicha(i, j).equals(Ficha.NEGRA)){
						tab[i][j].setIcon(new ImageIcon(Icon.BLACK.getUrl()));
					}
					tab[i][j].setBackground(Color.DARK_GRAY);
					tab[i][j].setPreferredSize(new Dimension(45,45));
					retorno.add(tab[i][j]);
				}
			}
			return retorno;
		}

		private class tabButton extends JButton{
			private int fil,col;		
			public tabButton (int f,int c){
				fil = f;
				col = c;
				this.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(sonidoOn) SoundEffect.BUTTON.play();
						if (enableTablero){
							controlador.poner(fil,col);
						}
					}
				});
			}
		}


		@Override
		public void onPartidaTerminada(TableroInmutable tabFin, final Ficha ganador) {

			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					ponerAleatorio.setEnabled(false);
					for(int i = 0; i < filas; i++){
						for (int j = 0; j < cols; j++){
							tab[i][j].setEnabled(false);
						}
					}
					if(ganador != Ficha.VACIA){
						text.setText("Ganan  " + ganador.toName() + 's');
						if(sonidoOn) SoundEffect.GANE.play();
					}else{
						text.setText("Tablas");
					}
				}
			});
		}


		@Override
		public void onMovimientoStart(Ficha turno) {}



		@Override
		public void onMovimientoEnd(Ficha turno,final TableroInmutable _tablero) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					for(int i = 0; i < filas; i++)
						for (int j = 0; j < cols; j++){
							if(_tablero.GetFicha(i, j) == Ficha.BLANCA){
								tab[i][j].setIcon(new ImageIcon(Icon.RED.getUrl()));
							}else if (_tablero.GetFicha(i, j) == Ficha.NEGRA){
								tab[i][j].setIcon(new ImageIcon(Icon.BLACK.getUrl()));
							}
						}	
				}
			});
		}

		public void onNoMovimiento(String msg){}

		@Override
		public void onUndo(final TableroInmutable tablero, boolean hayMas) {

			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					for(int i = 0; i < filas; i++)
						for (int j = 0; j < cols; j++){
							if(tablero.GetFicha(i, j) == Ficha.BLANCA){
								tab[i][j].setIcon(new ImageIcon(Icon.RED.getUrl()));
							}else if (tablero.GetFicha(i, j) == Ficha.NEGRA){
								tab[i][j].setIcon(new ImageIcon(Icon.BLACK.getUrl()));
							}else {
								tab[i][j].setIcon(null);
							}
						}
				}
			});
		}

		private void borrarCentral(){
			this.remove(central);
		}
		private void anadirCentral (){
			this.add(central);
		}

		@Override
		public void onResetPartida(final TableroInmutable tabIni, final Ficha turno) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					ponerAleatorio.setEnabled(true);
					text.setText("Juegan  " + turno.toName() + 's');
					central.remove(tablero);
					borrarCentral();
					filas = tabIni.GetFil();
					cols = tabIni.GetCol();
					tablero = inicializaTablero(tabIni);
					central.add(tablero);
					anadirCentral();
					refrescar();
				}
			});
		}

		@Override
		public void onUndoNotPossible(Ficha turno) {}

		@Override
		public void onCambioTurno(final Ficha turno) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					text.setText("Juegan  " + turno.toName() + 's');
					if (turno == Ficha.BLANCA && Tblancas == TipoTurno.Humano || turno == Ficha.NEGRA && Tnegras == TipoTurno.Humano){
						ponerAleatorio.setEnabled(true);
					}else{
						ponerAleatorio.setEnabled(false);
					}
				}
			});
		}

		@Override
		public void onMovimientoIncorrecto(String explicacion) {}

		@Override
		public void onCambioJuego(TableroInmutable tabIni,Ficha turno) {
			onResetPartida(tabIni, turno);
		}

	}

	/**
	 * Enumerado para la ruta de los iconos
	 * @author youssef
	 */

	public enum Icon {
		RED("recursos/iconos/red.png"),
		BLACK("recursos/iconos/black.png"),
		RANDOM("recursos/iconos/random.png"),
		UNDO("recursos/iconos/undo.png"),
		REINICIAR("recursos/iconos/reiniciar.png"),
		ACEPTAR("recursos/iconos/aceptar.png"),
		SALIR("recursos/iconos/exit.png");

		private String iconFileName;

		Icon(String _iconFileName){
			iconFileName = _iconFileName;
		}

		public URL getUrl(){
			return getClass().getResource(iconFileName);
		}
	}

	/**
	 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
	 * codes from the game codes.
	 * 1. Define all your sound effect names and the associated wave file.
	 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
	 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
	 *    sound files, so that the play is not paused while loading the file for the first time.
	 * 4. You can use the static variable SoundEffect.volume to mute the sound.
	 */
	public enum SoundEffect {
		RANDOM("recursos/sonidos/aleatorio.WAV"),  
		BUTTON("recursos/sonidos/buttonSound.WAV"),         
		REBOOT("recursos/sonidos/reboot.WAV"),
		CANCEL("recursos/sonidos/cancel.WAV"),
		SWITCH("recursos/sonidos/switch.WAV"),
		REMOVE("recursos/sonidos/remove.WAV"),
		GANE("recursos/sonidos/ganar.WAV");


		// Nested class for specifying volume
		public static enum Volume {
			MUTE, LOW, MEDIUM, HIGH
		}

		public static Volume volume = Volume.MEDIUM;

		// Each sound effect has its own clip, loaded with its own sound file.
		private Clip clip;

		// Constructor to construct each element of the enum with its own sound file.
		SoundEffect(String soundFileName) {
			try {
				// Use URL (instead of File) to read from disk and JAR.
				URL url = getClass().getResource(soundFileName);
				// Set up an audio input stream piped from the sound file.
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
				// Get a clip resource.
				clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				clip.open(audioInputStream);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}

		// Play or Re-play the sound effect from the beginning, by rewinding.
		public void play() {
			if (volume != Volume.MUTE) {
				if (clip.isRunning())
					clip.stop();   // Stop the player if it is still running
				clip.setFramePosition(0); // rewind to the beginning
				clip.start();     // Start playing
			}
		}

		// Optional static method to pre-load all the sound files.
		static void init() {
			values(); // calls the constructor for all the elements
		}
	}
}
