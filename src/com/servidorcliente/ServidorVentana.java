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
	public Jugadores jugadores;
	
	private boolean conexion1;
	private boolean conexion2;
	private boolean conexion3;
	private boolean conexion4;
	private boolean falloconexion;
	
	private HiloMovMoto hiloM;
	private HiloColocador hiloC;
	private HiloItem hiloI;
	
	public HiloEnvia hiloE;
	
	
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
		hiloI=new HiloItem(nuevo);
		nuevo.hilo=hiloM;
		hiloM.start();
		//hiloI.start();
		
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
			Socket cli= new Socket(ip,ps);
			JSONObject medio= new JSONObject();
			int tam=pantalla.matriz.getListaActores().getTam();
			medio.put("Cantidad", tam);
			NodoActor tmp=pantalla.matriz.getListaActores().getHead();
			while(tam!=0 && tmp!=null){
				medio.put("Imagen"+Integer.toString(tam),tmp.getNomImagen());
				medio.put("PosX"+Integer.toString(tam),tmp.getPosX());
				medio.put("PosY"+Integer.toString(tam),tmp.getPosY());
				tam--;
				tmp=tmp.next;
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
					jugadores.eliminar(recibido.getInt("puerto"));
					hiloE.stop();
					
				}
				else{
					NodoJugador tmp=jugadores.buscar(recibido.getInt("puerto"));
					tmp.getPlayer().getHead().setDireccion(recibido.getString("direccion"));
					
				}
				cli.close();
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal");g.printStackTrace();}
	}

	
	public static void main(String[] args) {
			ServidorVentana s=new ServidorVentana();
			HiloEnvia n=new HiloEnvia(s);
			s.hiloE=n;
			s.start();
			
	}

}
