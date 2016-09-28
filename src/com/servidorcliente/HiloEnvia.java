package com.servidorcliente;

import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.JSONObject;

public class HiloEnvia extends Thread {
	
	int puertosalida;
	String IP;
	ServidorVentana servidor;
	int numJugador;
	
	public HiloEnvia(String ip,int ps,int num) {
		puertosalida=ps;
		IP=ip;
		numJugador=num;
	}

	
	public void run(){
		while(true){
		try{	
			JSONObject enviado=new JSONObject();
			enviado.put("X",servidor.getPosX(numJugador));
			enviado.put("Y", servidor.getPosY(numJugador));
			Socket cenvia= new Socket(IP,puertosalida);
			ObjectOutputStream flujoen=new ObjectOutputStream(cenvia.getOutputStream());
			flujoen.writeObject(enviado);
			cenvia.close();
		}catch(Exception e){e.printStackTrace();}
	  }
	}

}
