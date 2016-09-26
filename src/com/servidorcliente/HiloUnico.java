package com.servidorcliente;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONObject;

public class HiloUnico extends Thread {

	private int puertoentrada;
	private Servidor servidor;
	private int val;
	private String ip;
	private int pe;
	private int ps;
	
	public HiloUnico(int p,Servidor servid) {
		puertoentrada=p;
		servidor=servid;
	}

	public void run(){
		try{
			ServerSocket serv=new ServerSocket(puertoentrada);
			Socket cli;
			while(true){
				cli=serv.accept();
				ObjectInputStream flujo = new ObjectInputStream(cli.getInputStream());
				JSONObject recibido=new JSONObject();
				recibido=(JSONObject)flujo.readObject();
				val=Integer.parseInt(recibido.getString("conexion"));
				ip=(String)recibido.getString("IP");
				pe=Integer.parseInt(recibido.getString("PE"));
				ps=Integer.parseInt(recibido.getString("PS"));
				if(val==1){
					servidor.setCliente(ip, pe, ps, servidor);
				}
				else{
					System.out.print("No conecto");
				}
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal");g.printStackTrace();}
	}
	
	public static void main(String[] args){
		Servidor n=new Servidor();
		HiloUnico h= new HiloUnico(9095,n);
		h.start();
	}
}

