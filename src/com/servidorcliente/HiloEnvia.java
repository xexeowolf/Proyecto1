package com.servidorcliente;

/**
 * Clase encargada de enviar la actualizacion del estado del servidor a todos los clientes conectados.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see ServidorVentana,Thread
 */
public class HiloEnvia extends Thread {

	public ServidorVentana servidor;
	public HiloEnvia self;
	
	/**
	 * Constructor de la clase.
	 * @param serv servidor sobre del cual obtendra la informacion necesario que se desea enviar.
	 */
	public HiloEnvia(ServidorVentana serv) {
		servidor=serv;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@SuppressWarnings("deprecation")
	public void run(){
		while(true){
		if(servidor.jugadores.getTam()==0){//Se detiene el a si mismo si detecta que no existen mas jugadores conectados
			self.stop();
			break;
		}
		NodoJugador tmp=servidor.jugadores.head;
		while(tmp!=null && servidor.jugadores.getTam()!=0){
			servidor.enviar(tmp.getIP(), tmp.getPuerto(),tmp.getPlayer());//Envia a cada jugador conectado la informacion del servidor.
			tmp=tmp.next;
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

}
