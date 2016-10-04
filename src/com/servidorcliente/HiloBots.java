package com.servidorcliente;

public class HiloBots extends Thread {

	ServidorVentana servidor;
	
	public HiloBots(ServidorVentana serv) {
		servidor=serv;
	}
	
	public void run(){
		while(true){
			NodoJugador tmp=servidor.listabots.head;
			if(tmp!=null && tmp.getPlayer()!=null && tmp.getPlayer().getHead()!=null && tmp.getPlayer().getHead().abajo!=null){
				while(tmp!=null){
					tmp.getPlayer().getHead().setDireccion(servidor.seguirMoto(tmp.getPlayer().getHead().abajo.getPosX(), tmp.getPlayer().getHead().abajo.getPosY()));
					tmp=tmp.next;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.print("Error en la espera del hilo de los bots");
				e.printStackTrace();
			}
		}	
	}
}
