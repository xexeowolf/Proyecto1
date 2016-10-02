package com.servidorcliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.Timer;


import org.json.JSONObject;

import com.matriz.HiloMovMoto;
import com.matriz.ListaMoto;

public class ServidorVentana extends Thread {
	
	private Ventana pantalla;
	public Jugadores jugadores;
	
	private boolean conexion1;
	private boolean conexion2;
	private boolean conexion3;
	private boolean conexion4;
	private boolean falloconexion;
	
	private HiloMovMoto hiloM;
	private HiloColocador hiloC;
	
	public HiloEnvia hiloE;
	
	Timer t1=new Timer(1000, new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jugadores.head.getPlayer().aplicarItem();
			
		}
		
	});
	
	public ServidorVentana() {
		pantalla=new Ventana();
		jugadores=new Jugadores();
		conexion1=conexion2=conexion3=conexion4=true;
		falloconexion=false;
		
	}
	
	public void agregarJugador(String nombre,String ip,int ps,int id){
		ListaMoto nuevo=new ListaMoto(id,nombre,pantalla.matriz);
		jugadores.agregar(nuevo,ip,ps);
		hiloM=new HiloMovMoto(nuevo);
		nuevo.hilo=hiloM;
		hiloM.start();
		
	}
	
	public void buscarConexion(String ip,int ps){
		if(conexion1==true){
			hiloC=new HiloColocador(pantalla.matriz);
			hiloC.start();
			agregarJugador("moto1abj.gif",ip,ps,1);
			conexion1=false;
			t1.start();
		}
		else if(conexion2==true){
			agregarJugador("moto2abj.gif",ip,ps,2);
			conexion2=false;
		}
		else if(conexion3==true){
			agregarJugador("moto3abj.gif",ip,ps,3);
			conexion3=false;
		}else if(conexion4==true){
			agregarJugador("moto4abj.gif",ip,ps,4);
			conexion4=false;
		}
		else{
			falloconexion=true; //ver como manejar el fallo de conexion;
		}
	}
	
	public void enviar(String ip,int ps,ListaMoto jug) {
		try{
			Socket cli= new Socket(ip,ps);
			JSONObject medio= new JSONObject();
			medio.put("lista", pantalla.matriz.getListaActores());
			if(jug!=null && jug.getHead()!=null){
			medio.put("gas",(int)jug.getHead().getGas());
			medio.put("escudo", jug.getHead().getPoderes().inventario("escudo"));
			medio.put("veloc", jug.getHead().getPoderes().inventario("velocidad"));
			medio.put("val", true);}
			else{
				medio.put("val", false);
			}
			ObjectOutputStream flujo= new ObjectOutputStream(cli.getOutputStream());
			flujo.writeObject(medio);
			cli.close();
		} catch(Exception ex){System.out.print("Error al enviar actualizacion en el servidor\n");ex.printStackTrace();}
	}
	
	
	public void imprimir(){
		NodoJugador l=jugadores.head;
		while(l!=null){
			System.out.print(l.getIP()+"/n"+l.getPuerto());
			l=l.next;
		}
	}
	@SuppressWarnings("deprecation")
	public void run(){
		try{
			ServerSocket serv=new ServerSocket(9095);
			Socket cli;
			while(true){
				cli=serv.accept();
				ObjectInputStream flujo = new ObjectInputStream(cli.getInputStream());
				JSONObject recibido=new JSONObject();
				recibido=(JSONObject)flujo.readObject();
				int validacion=recibido.getInt("val");
				if(validacion==1){
					buscarConexion(recibido.getString("IP"),recibido.getInt("puerto"));
					hiloE.start();
					}
				else if(validacion==2){
					hiloE.stop();
					t1.stop();
					jugadores.eliminar(recibido.getInt("puerto"));
				}
				
				else if(validacion==3){
					NodoJugador jug=jugadores.buscar(recibido.getInt("puerto"));
					if(jug!=null){
						if(recibido.getInt("poder")==1){
							jug.getPlayer().getHead().getPoderes().ubicar("escudo");
						}
						else{
							jug.getPlayer().getHead().getPoderes().ubicar("velocidad");
						}
					jug.getPlayer().aplicarPoder();}
				}
				else{
					NodoJugador tmp=jugadores.buscar(recibido.getInt("puerto"));
					if(tmp!=null){
					tmp.getPlayer().getHead().setDireccion(recibido.getString("direccion"));}
					
				}
				cli.close();
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal del servidor");g.printStackTrace();}
	}

	
	public static void main(String[] args) {
			ServidorVentana s=new ServidorVentana();
			HiloEnvia n=new HiloEnvia(s);
			s.hiloE=n;
			s.start();
			
	}

}
