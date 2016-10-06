package com.servidorcliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.JSONObject;

import com.matriz.ListaActores;

/**
 * Clase cliente encargada de mantener un intercambio constante de informacion con el servidor.
 * Ademas presenta una interfaz grafica que muestra las diferentes acciones realizadas por el usuario.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see Thread, SubVentana
 */
public class ClienteVentana extends Thread implements KeyListener {

	private SubVentana pantalla;
	private JFrame ventana;
	private JLabel label1,label2,label3;
	private JTextField ip,nick,puerto;
	private JButton btn;
	private int PuertoV;
	public ClienteVentana mismo;
	
	/**
	 * Constructor de la clase.
	 * Inicializa los parametros de la interfaz
	 */
	public ClienteVentana() {
		pantalla=new SubVentana();
		pantalla.addKeyListener(this);
		pantalla.listapoderes.addKeyListener(this);
		pantalla.btn.addKeyListener(this);
		pantalla.setFocusable(true);
		ventana=new JFrame("Bienvenido");
		ventana.setBounds(0,0,600,175);
		label1=new JLabel("Ingrese un nombre: ");
		label1.setBounds(10,30,200,20);
		label2=new JLabel("Ingrese la direccion IP: ");
		label2.setBounds(10,60,200,20);
		label3=new JLabel("Ingrese un puerto para iniciar la conexion: ");
		label3.setBounds(10,90,400,20);
		ip=new JTextField();
		ip.setBounds(350,60,200,20);
		nick=new JTextField();
		nick.setBounds(350,30,200,20);
		puerto=new JTextField();
		puerto.setBounds(350,90,200,20);
		btn=new JButton("Enviar");
		btn.setBounds(250,120,80,20);
		btn.addActionListener(new ActionListener(){ //Accion ejecutada por el boton que se presenta en la interfaz inicial.

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btn){
					setConexion();
					mismo.start();
					ventana.setVisible(false);
					pantalla.setVisible(true);
					pantalla.setTitle("Usuario-"+nick.getText());
					
				}
				
			}
			
		});
		pantalla.btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg1) { //accion realizada por el boton presentado en la segunda ventana de la interfaz.
				if(arg1.getSource()==pantalla.btn){
					try{
						Socket cli= new Socket("192.168.1.62",9095);
						JSONObject medio= new JSONObject();
						medio.put("val",3);
						medio.put("puerto",PuertoV);
						String poder=(String)pantalla.listapoderes.getSelectedItem();
						if(poder=="Escudo"){ //envia el poder que se desea buscar y aplicar.
							medio.put("poder", 1);
						}
						else{
							medio.put("poder", 2);
						}
						ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
						flujo.writeObject(medio);
						cli.close();
					} catch(Exception ex){System.out.print("Error al enviar aplicacion de poder en el cliente\n");ex.printStackTrace();}
					
				}
				
			}
			
		});
		ventana.setVisible(true);
		ventana.setLayout(null);
		ventana.setResizable(false);
		ventana.add(label1);
		ventana.add(label2);
		ventana.add(label3);
		ventana.add(ip);
		ventana.add(nick);
		ventana.add(puerto);
		ventana.add(btn);
		pantalla.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ep){ //Ultima comunicacion realizada por el usuario al momento de cerrar la ventana.
				try{									
					Socket cli= new Socket("192.168.1.62",9095);
					JSONObject medio= new JSONObject();
					medio.put("val",2);	//Indica que se desea terminar la conexion.
					medio.put("puerto",PuertoV);
					ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
					flujo.writeObject(medio);
					cli.close();
				} catch(Exception ex){System.out.print("Error al enviar cierre en el cliente\n");ex.printStackTrace();}
				System.exit(0);
			}
		});
		}

	/**
	 * Metodo que envia al servidor una peticion de conexion.
	 * Por orden de ejecucion es la primera comunicacion que se realiza con el servidor.
	 * @see Sockets,JSONObject
	 */
	public void setConexion(){
		try{
			Socket cli= new Socket("192.168.1.62",9095);
			JSONObject medio= new JSONObject();
			PuertoV=Integer.parseInt(puerto.getText());
			medio.put("puerto", PuertoV);
			medio.put("val",1);
			medio.put("IP",ip.getText());
			medio.put("nick",nick.getText());
			ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
			flujo.writeObject(medio);
			cli.close();
		} catch(Exception ex){System.out.print("Error al enviar inicio de conexion en el cliente\n");ex.printStackTrace();}
	}
	
	/**
	 * Metodo que comunica al servidor un cambio en la direccion de la moto(ListaMoto) relacionada con el usuarion en la matriz e interfaz.
	 * Ademas es utilizado constantemente por parte del usuario.
	 * @param dir texto que contiene la nueva direccion deseada.
	 */
	public void enviar(String dir){
		try{
			Socket cli= new Socket("192.168.1.62",9095);
			JSONObject medio= new JSONObject();
			medio.put("val",5);
			medio.put("direccion", dir);
			medio.put("puerto",PuertoV);
			ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
			flujo.writeObject(medio);
			cli.close();
		} catch(Exception ex){System.out.print("Error al enviar actualizacion en el cliente\n");ex.printStackTrace();}
	}



	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent x) {
		switch(x.getKeyCode()){
		case KeyEvent.VK_DOWN:enviar("abj");break;
		case KeyEvent.VK_UP:enviar("arr");break;
		case KeyEvent.VK_LEFT:enviar("izq");break;
		case KeyEvent.VK_RIGHT:enviar("der");break;}
		}

	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		try{
			ServerSocket serv=new ServerSocket(PuertoV);//Inicia la recepcion de informacion
			Socket cli;
			while(true){
				cli=serv.accept();//Queda a la espera de informacion
				ObjectInputStream flujo = new ObjectInputStream(cli.getInputStream());
				JSONObject recibido=new JSONObject();
				recibido=(JSONObject)flujo.readObject();//ultimo paso de la obtencion de la informacion
				pantalla.setImagenes((ListaActores)recibido.get("lista"));//Imagenes destinadas a representarse en la interfaz del cliente.
				pantalla.t1.setText(null);
				pantalla.t2.setText(null);
				pantalla.t3.setText(null);
				if(recibido.getBoolean("val")==true){ //Se actualizan los atributos del jugador.
					pantalla.t1.append(Integer.toString(recibido.getInt("gas")));
					pantalla.t2.append(Integer.toString(recibido.getInt("escudo")));
					pantalla.t3.append(Integer.toString(recibido.getInt("veloc")));	
				}
				cli.close();
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal del cliente");g.printStackTrace();}
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Metodo principal necesario para iniciar el cliente.
	 * @param args
	 */
	public static void main(String[] args) {
		ClienteVentana cliente=new ClienteVentana();
		cliente.mismo=cliente;//Se habilita que el mismo objeto puede acceder a ciertos metodos y atributos que requieren una instanciacion previa.
	}

}
