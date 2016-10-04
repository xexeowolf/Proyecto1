package com.servidorcliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;
import com.matriz.HiloMovMoto;
import com.matriz.ListaMoto;

/**
 * Clase ServidorVentana, interfaz que tiene funciones de servidor.
 * Una vez especificada ciertas caracteristicas de la interfaz establece conexion con usuarios mediante sockets y transmite un actualizacion de su interfaz a estos.
 * Ademas maneja los usuarios y sus acciones con una cantidad limitada pero editable de conexiones.
 * @author: Luis Alfredo Piedra Esquivel
 * @version: 02/10/2016
 * @see Thread
 *
 */
public class ServidorVentana extends Thread implements ActionListener,ChangeListener{
	
	private Ventana pantalla;		//Interfaz grafica en la cual se muestran las imagenes relacionadas al juego.
	public Jugadores jugadores;		//Lista enlazada simple que guarda la informacion de los usuarios conectados al servidor.
	private Jugadores listaespera;
	public Jugadores listabots;
	private boolean conexion1;
	private boolean conexion2;
	private boolean conexion3;
	private boolean conexion4;
	private boolean falloconexion;
	private boolean empezar;
	private boolean agregarBots;
	public int maxBots;
	private HiloMovMoto hiloM;		//Hilo encargado de mantener en movimiento constante en la interfaz una serie de imagenes asociadas a un usuaria(moto).
	private HiloColocador hiloC;	//Hilo encargado de colocar de manera aleatoria items a lo largo y ancho de la pantalla.
	public HiloEnvia hiloE;			//Hilo que mantiene una comunicacion constante del servidor hacia los clientes.
	private HiloBots hiloB;
	public ServidorVentana self;
	private JFrame ventana;
	private JLabel textmatriz,textfilas,textcolumnas,textBot;
	private JTextField filas,columnas;
	private JButton iniciar;
	private JCheckBox opcion;
	private JComboBox numBot;
	
	
	Timer aplicaItems=new Timer(1000, new ActionListener(){	//Metodo que se ejecuta cada segundo, encargado de aplicar los efectos de los items que obtiene cada jugador.

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			NodoJugador tmp=jugadores.head;
			while(tmp!=null){
				tmp.getPlayer().aplicarItem();
				tmp=tmp.next;
			}
		}
		
	});
	
	Timer Conectar=new Timer(5000, new ActionListener(){	//Metodo que intenta cada 5 segundos conectar a un usuario en espera.

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg1) {
			boolean intento=buscarConexion(listaespera.head.getIP(),listaespera.head.getPuerto());
			if(intento==true){
				listaespera.eliminarAlInicio();
			}
		}
		
	});
	
	Timer NumBots=new Timer(2000, new ActionListener(){	

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg1) {
			if(self.listabots.getTam()<self.maxBots){
				self.agregarBot();
			}
			
		}
		
	});
	
	/**
	 * Constructor de la clase. 
	 * Inicializa la interfaz, la lista de clientes(vacia) que va a contener su respectiva informacion 
	 * y una serie de booleans necesarios para verificar conexiones. 
	 * @see: Ventana
	 */
	public ServidorVentana() {
		agregarBots=false;
		ventana = new JFrame("Configuracion de la partida");
		ventana.setBounds(0,0,450,190);
		ventana.setLayout(null);
		ventana.setResizable(false);
		textBot=new JLabel("Cantidad de bots");
		textBot.setBounds(240,120,150,20);
		numBot=new JComboBox();
		numBot.setBounds(380,120,50,20);
		numBot.addItem(1);
		numBot.addItem(2);
		numBot.addItem(3);
		numBot.addItem(4);
		numBot.addItem(5);
		textmatriz=new JLabel("Creacion de la Matriz");
		textmatriz.setBounds(150,20,200,20);
		textfilas=new JLabel("Ingrese numero de filas(max.43): ");
		textfilas.setBounds(30,50,400,20);
		textcolumnas=new JLabel("Ingrese numero de columnas(max.43): ");
		textcolumnas.setBounds(30,80,600,20);
		opcion=new JCheckBox("Utilizar bots en la partida");
		opcion.setBounds(20,120,210,20);
		opcion.addChangeListener(this);
		filas=new JTextField();
		filas.setBounds(325,50,100,20);
		columnas=new JTextField();
		columnas.setBounds(325,80,100,20);
		iniciar=new JButton("Iniciar Partida");
		iniciar.setBounds(150,150,150,20);
		iniciar.addActionListener(this);
		ventana.add(textmatriz);
		ventana.add(textfilas);
		ventana.add(textcolumnas);
		ventana.add(opcion);
		ventana.add(filas);
		ventana.add(columnas);
		ventana.add(iniciar);
		ventana.setVisible(true);
		ventana.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ep){
				System.exit(0);
			}
		});
	}
	
	/**
	 * Metodo que inicializa el objeto que representara el usuario graficamente(ListaMoto) ademas de guardar su informacion y cambios.
	 * Ademas inicializa una clase que hereda de Thread que modifica (HiloMovMoto) a la ListaMoto en segun el usuario desee.
	 * @param nombre parametro nombre define la imagen inicial que se mostrara en la interfaz grafica tanto del servidor como el usuario.
	 * @param ip parametro que guarda la direccion IP del dispositivo que desea conectarse al servidor, necesario para la comunicacion con sockets.
	 * @param ps numero entero que representa al puerto de salida, necesario para la comunicacion entre el servidor y un usuario. 
	 * @param id numero entero que identifica al cliente como jugador 1,2,3,4 necesario para saber cual objeto modifica un cliente en la interfaz grafica.
	 */
	public void agregarJugador(String nombre,String ip,int ps,int id){
		ListaMoto nuevo=new ListaMoto(id,nombre,pantalla.matriz); //Se crea la lista doblemente enlazada representada como una moto en la interfaz grafica.
		jugadores.agregar(nuevo,ip,ps); 
		hiloM=new HiloMovMoto(nuevo);
		nuevo.hilo=hiloM;
		hiloM.start();	//Se inicializa el hilo encargado del movimiento continuo de la moto en la interfaz.
		
	}
	
	/**
	 * Metodo que busca una conexion libre en el servidor para de esta manera agregar a un cliente,su limite esta especificado en 4 posibles conexiones.
	 * Si se supera el limite de conexiones establecido un usuario que este sobre este limite entra en una lista de espera hasta que se libere una conexion.
	 * @param ip parametro que representa la direccion IP del dispositivo que desea conectarse.
	 * @param ps puerto de salida representado por un numero entero, en caso de lograrse la conexion se utiliza en como medio de envio de informacion.
	 * @return true si se logra conectar un usuario y false si no.
	 */
	public boolean buscarConexion(String ip,int ps){
		if(conexion1==true){
			if(empezar==false){		//Verificacion necesaria para asegurar que solo existe un hilo colocador la pantalla. 
				hiloC=new HiloColocador(pantalla.matriz);//Se ejecuta con el primer cliente que logra la conexion y solo una  vez.
				hiloC.start();
				aplicaItems.start();			//Se inicia el timer encargado de aplicar los efectos de los items.
			}
			agregarJugador("moto1abj.gif",ip,ps,1);
			conexion1=false;	//Se cierra la conexion hasta que el jugador cierra la aplicacion del cliente.
			return true;
			
		}
		else if(conexion2==true){
			agregarJugador("moto2abj.gif",ip,ps,2);
			conexion2=false;//Se cierra la conexion hasta que el jugador cierra la aplicacion del cliente.
			return true;
		}
		else if(conexion3==true){
			agregarJugador("moto3abj.gif",ip,ps,3);
			conexion3=false;//Se cierra la conexion hasta que el jugador cierra la aplicacion del cliente.
			return true;
		}else if(conexion4==true){
			agregarJugador("moto4abj.gif",ip,ps,4);
			conexion4=false;//Se cierra la conexion hasta que el jugador cierra la aplicacion del cliente.
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Metodo que envia la informacion contenida en la interfaz del servidor para su posterior representacion en las interfaces de los usuarios.
	 * @param ip direccion IP utilizada para destinar la informacion.
	 * @param ps numero entero que representa el puerto de salida, es decir, el medio de envio.
	 * @param jug lista enlazada doble utilizada para acceder a la informacion propia de la interfaz y logica del juego, necesaria para una representacion grafica.
	 */
	public void enviar(String ip,int ps,ListaMoto jug) {
		try{
			Socket cli= new Socket(ip,ps);
			JSONObject medio= new JSONObject();
			medio.put("lista", pantalla.matriz.getListaActores());	//Se incluye la lista enlazada doble encargada de almacenar las imagenes que se muestran en la interfaz tanto del servidor como del cliente.
			if(jug!=null && jug.getHead()!=null){
			medio.put("gas",(int)jug.getHead().getGas()); // Combustible que tiene cada jugador, importante para la logica del juego.
			medio.put("escudo", jug.getHead().getPoderes().inventario("escudo"));//Numero de escudos almacenados por el usuario
			medio.put("veloc", jug.getHead().getPoderes().inventario("velocidad"));//Numero de hipervelocidades almacenados por el usuario
			medio.put("val", true);}//validacion de que el parametro jug no es nulo, por lo tanto se sabe que el cliente matiene la conexion.
			else{
				medio.put("val", false); //validacion del parametro jug que indica que un cliente ha sido eliminado.
			}
			ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
			flujo.writeObject(medio);
			cli.close();
		} catch(Exception ex){System.out.print("Error al enviar actualizacion en el servidor\n");ex.printStackTrace();}
	}
	
	public void agregarBot(){
		ListaMoto bot=new ListaMoto(4,"moto4abj.gif",pantalla.matriz);//CAMBIAR POR IMAGENES DE LOS BOTS
		listabots.agregar(bot, "l", 0);
		hiloM=new HiloMovMoto(bot);
		bot.hilo=hiloM;
		hiloM.start();
	}
	
	public String movimientoBots(int X,int Y,int XP,int YP){
		int distX = Math.abs(X - XP);
		int distY = Math.abs(Y - YP);
		if (distX >= distY){
			if (X >= XP){
				return "izq";
			}
			else{
				return "der";
			}
		}
		else {
			if (Y >= YP){
				return "arr";
			}
			else{
				return "abj";
			}
		}
	}
	
	 public String seguirMoto(int x,int y){
		 int ran =(int)((Math.random()* jugadores.getTam())+ 1);
		 NodoJugador tmp = jugadores.head;
		 if(ran==1){
			 return movimientoBots(x,y,tmp.getPlayer().getHead().abajo.getPosX(),tmp.getPlayer().getHead().abajo.getPosY());
		 }
		 else if(ran==2){
			 tmp=tmp.next;
			 return movimientoBots(x,y,tmp.getPlayer().getHead().abajo.getPosX(),tmp.getPlayer().getHead().abajo.getPosY());
		 }
		 else if(ran==3){
			 tmp=tmp.next.next;
			 return movimientoBots(x,y,tmp.getPlayer().getHead().abajo.getPosX(),tmp.getPlayer().getHead().abajo.getPosY());
		 }
		 else if(ran==4){
			 tmp=tmp.next.next.next;
			 return movimientoBots(x,y,tmp.getPlayer().getHead().abajo.getPosX(),tmp.getPlayer().getHead().abajo.getPosY());
		 }else{
			 return "error";
		 }

		 }
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@SuppressWarnings("deprecation")
	public void run(){	//Metodo que se mantiene ejecutando paralelamente a la interfaz, mantiene la comunicacion del cliente al servidor. 
		try{
			ServerSocket serv=new ServerSocket(9095); //Se define como puerto de entrada general del servidor como 9095.
			Socket cli;
			while(true){
				cli=serv.accept();
				ObjectInputStream flujo = new ObjectInputStream(cli.getInputStream());
				JSONObject recibido=new JSONObject();
				recibido=(JSONObject)flujo.readObject();
				int validacion=recibido.getInt("val");
				if(validacion==1){ //El usuario busca una conexion.
					boolean exito=buscarConexion(recibido.getString("IP"),recibido.getInt("puerto"));//Se intenta conectar al usuario con el servidor. 
					if(exito==false){
						listaespera.agregar(null, recibido.getString("IP"),recibido.getInt("puerto"));
						if(falloconexion==false){
							Conectar.start();
							falloconexion=true;
						}
					}
					if(empezar==false){// validacion necesario para asegurar que el hilo que envia la informacion del servidor al cliente se inicie una sola vez.
						hiloE.start();
						NumBots.start();
						hiloB.start();
						empezar=true;
					}
						
					}
				else if(validacion==2){//El usuario desea terminar con la conexion.
					int numJug=jugadores.eliminar(recibido.getInt("puerto"));
					if(jugadores.getTam()==0){//Si ya no existen usuarios conectados
						hiloE.stop();
						aplicaItems.stop();
						if(NumBots.isRunning()==true){
							NumBots.stop();
						}
						if(Conectar.isRunning()==true){
							Conectar.stop();
						}
						
					}
					switch(numJug){
					case 1:conexion1=true;break;
					case 2:conexion2=true;break;
					case 3:conexion3=true;break;
					case 4:conexion4=true;break;
					}
				}
				
				else if(validacion==3){//El usuario desea aplicar un poder
					NodoJugador jug=jugadores.buscar(recibido.getInt("puerto"));//Se identifica el cliente que envio la orden.
					if(jug!=null){
						if(recibido.getInt("poder")==1){
							if(jug.getPlayer().getHead().getPoderes().inventario("escudo")>0 && jug.getPlayer().escudo!="activo"){
								jug.getPlayer().getHead().getPoderes().ubicar("escudo");
								jug.getPlayer().aplicarPoder();
							}
						}
						else{
							if(jug.getPlayer().getHead().getPoderes().inventario("velocidad")>0){
								jug.getPlayer().getHead().getPoderes().ubicar("velocidad");
								jug.getPlayer().aplicarPoder();
							}
						}
					}
				}
				else{//Un usuario desea cambiar la direccion de movimiento de la moto.
					NodoJugador tmp=jugadores.buscar(recibido.getInt("puerto"));//Se identifica el cliente que envio la orden.
					if(tmp!=null && tmp.getPlayer()!=null && tmp.getPlayer().getHead()!=null){
					tmp.getPlayer().getHead().setDireccion(recibido.getString("direccion"));}
					
				}
				cli.close();
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal del servidor");g.printStackTrace();}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ew) {
		if(ew.getSource()==iniciar){
			ventana.setVisible(false);
			pantalla=new Ventana(Integer.parseInt(filas.getText()),Integer.parseInt(columnas.getText()));
			jugadores=new Jugadores();
			listaespera=new Jugadores();
			listabots=new Jugadores();
			conexion1=conexion2=conexion3=conexion4=true;
			falloconexion=empezar=false;
			maxBots=0;
			if(agregarBots==true){
				maxBots=(int)numBot.getSelectedItem();
			}
			start();
			
			
			
		}
		
		
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		if(opcion.isSelected()==true){
			ventana.add(textBot);
			ventana.add(numBot);
			ventana.repaint();
			agregarBots=true;
		}
		
		
	}
	/**
	 * Metodo principal necesario para ejecutar el servidor.
	 * Inicializa un objeto de la clase ServidorVentana, ademas de un HiloEnvia necesario para la comunicacion paralela mediante sockets.
	 * @param args
	 * @see HiloEnvia
	 */
	public static void main(String[] args) {
			ServidorVentana principal=new ServidorVentana();
			principal.self=principal;
			HiloEnvia envia=new HiloEnvia(principal);
			principal.hiloE=envia;
			envia.self=envia;
			HiloBots bots=new HiloBots(principal);
			principal.hiloB=bots;
	}

}
