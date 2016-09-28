package com.servidorcliente;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONObject;

public class HiloUnico extends Thread {

	private int puertoentrada;
	private ServidorVentana servidor;
	private String ip;
	private int pe;
	private int ps;
	
	public HiloUnico(int p,ServidorVentana servid) {
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
				ip=(String)recibido.getString("IP");
				pe=Integer.parseInt(recibido.getString("PE"));
				ps=Integer.parseInt(recibido.getString("PS"));
				servidor.agregarCliente(ip, pe, ps, 1,servidor);
				cli.close();
			}
		} catch(Exception g){System.out.print("Error al recibir en el hilo principal");g.printStackTrace();}
	}
	
	public static void main(String[] args){
		//Servidor n=new Servidor();
		//HiloUnico h= new HiloUnico(9095,n);
		//h.start();
	}
}

