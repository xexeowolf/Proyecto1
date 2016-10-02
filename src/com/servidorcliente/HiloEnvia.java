package com.servidorcliente;

public class HiloEnvia extends Thread {

	ServidorVentana servidor;
	
	public HiloEnvia(ServidorVentana serv) {
		servidor=serv;
	}

	
	public void run(){
		while(true){
		NodoJugador tmp=servidor.jugadores.head;
		while(tmp!=null && servidor.jugadores.getTam()!=0){
			servidor.enviar(tmp.getIP(), tmp.getPuerto(),tmp.getPlayer());
			tmp=tmp.next;
		}
	}
}

}
