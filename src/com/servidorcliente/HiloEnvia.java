package com.servidorcliente;

public class HiloEnvia extends Thread {

	ServidorVentana servidor;
	HiloEnvia self;
	
	public HiloEnvia(ServidorVentana serv) {
		servidor=serv;
	}

	
	@SuppressWarnings("deprecation")
	public void run(){
		while(true){
		if(servidor.jugadores.getTam()==0){
			self.stop();
			break;
		}
		NodoJugador tmp=servidor.jugadores.head;
		while(tmp!=null && servidor.jugadores.getTam()!=0){
			servidor.enviar(tmp.getIP(), tmp.getPuerto(),tmp.getPlayer());
			tmp=tmp.next;
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}
