package com.servidorcliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONObject;

public class HiloReceptor extends Thread{

	String texto;
	int puertoentrada;
	Servidor servidor;
	int numJugador;
	
	public HiloReceptor(int pe,Servidor serv,int num) {
		puertoentrada=pe;
		servidor=serv;
		numJugador=num;
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
				texto=recibido.getString("mensaje");
				switch(texto){
				case "der": servidor.moverJugador(numJugador,1,0);break;
				case "izq": servidor.moverJugador(numJugador,-1,0);break;
				case "arr": servidor.moverJugador(numJugador,0,-1);break;
				case "abj": servidor.moverJugador(numJugador,0,1);break;
				}
			}
			}catch(Exception f){f.printStackTrace();}
	}
}
