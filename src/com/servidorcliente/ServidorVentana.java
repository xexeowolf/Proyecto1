package com.servidorcliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONObject;

import com.matriz.HiloMovMoto;
import com.matriz.ListaMoto;
import com.matriz.NodoActor;

public class ServidorVentana extends Thread {
	
	private Ventana pantalla;
	private Jugadores jugadores;
	
	private boolean conexion1;
	private boolean conexion2;
	private boolean conexion3;
	private boolean conexion4;
	private boolean falloconexion;
	
	private HiloMovMoto hiloM;
	private HiloColocador hiloC;
	private HiloItem hiloI;
	
	
	public ServidorVentana() {
		pantalla=new Ventana();
		jugadores=new Jugadores();
		conexion1=conexion2=conexion3=conexion4=true;
		falloconexion=false;
		
	}
	
	public void agregarJugador(String nombre,String ip,int ps,int id){
		ListaMoto nuevo=new ListaMoto(id,nombre,pantalla.matriz);
		hiloM=new HiloMovMoto(nuevo);
		hiloI=new HiloItem(nuevo);
		nuevo.hilo=hiloM;
		jugadores.agregar(nuevo,ip,ps);
		hiloM.start();
		hiloI.start();
		
	}
	
	public void buscarConexion(String ip,int ps){
		if(conexion1==true){
			hiloC=new HiloColocador(pantalla.matriz);
			hiloC.start();
			agregarJugador("moto.gif",ip,ps,1);
			conexion1=false;
		}
		else if(conexion2==true){
			agregarJugador("moto.gif",ip,ps,2);
			conexion2=false;
		}
		else if(conexion3==true){
			agregarJugador("moto.gif",ip,ps,3);
			conexion3=false;
		}else if(conexion4==true){
			agregarJugador("moto.gif",ip,ps,4);
			conexion4=false;
		}
		else{
			falloconexion=true; //ver como manejar el fallo de conexion;
		}
	}
	
	
	public void distribuirInfo(){
		NodoJugador tmp=jugadores.head;
		while(tmp!=null){
			enviar(tmp.getIP(),tmp.getPuerto());
			tmp=tmp.next;
		}
		
	}
	
	public void enviar(String ip,int ps) {
		try{
			Socket cli= new Socket("192.168.1.62",9090);
			JSONObject medio= new JSONObject();
			NodoActor n=new NodoActor("moto.gif",10,10);
			//medio.put("imagenes",pantalla.matriz.getListaActores());
			medio.put("imagenes", n);
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
	public void run(){
		try{
			ServerSocket serv=new ServerSocket(9095);
			Socket cli;
			while(true){
				cli=serv.accept();
				ObjectInputStream flujo = new ObjectInputStream(cli.getInputStream());
				JSONObject recibido=new JSONObject();
				recibido=(JSONObject)flujo.readObject();
				int validacion=Integer.parseInt(recibido.getString("val"));
				if(validacion==1){
					buscarConexion(recibido.getString("IP"),Integer.parseInt(recibido.getString("puerto")));
					}
				else{
					String direccion=recibido.getString("direccion");
					int pto=Integer.parseInt(recibido.getString("puerto"));
					NodoJugador tmp=jugadores.buscar(pto);
					tmp.getPlayer().getHead().setDireccion(direccion);
					
				}
					imprimir();
					distribuirInfo();
				
				cli.close();
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal");g.printStackTrace();}
	}

	
	public static void main(String[] args) {
			ServidorVentana s=new ServidorVentana();
			s.start();
	}

}
