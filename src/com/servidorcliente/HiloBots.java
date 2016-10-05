package com.servidorcliente;

/**
 * Hilo encargado de definir los movimientos de los bots de manera aleatoria, simulando a un jugador conectado.
 * @author Luis Alfredo Piedra Esquivel
 * @since 02/10/2016
 * @see Thread
 */
public class HiloBots extends Thread {

	public ServidorVentana servidor;
	
	/**
	 * Constructor de la clase.
	 * @param serv servidor al cual pertenecen los bots(ListaMotos) que el hilo modificara.
	 */
	public HiloBots(ServidorVentana serv) {
		servidor=serv;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		while(true){
			NodoJugador tmp=servidor.listabots.head;//Inicio de la lista que contiene los bots en la partida
			if(servidor.jugadores.getTam()>0 && tmp!=null && tmp.getPlayer()!=null && tmp.getPlayer().getHead()!=null && tmp.getPlayer().getHead().abajo!=null){
				while(tmp!=null){
					tmp.getPlayer().getHead().setDireccion(servidor.seguirMotoX(tmp.getPlayer().getHead().abajo.getPosX()));//Direccion aleatoria en el eje x	
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.print("Error en la espera del hilo de los bots");
						e.printStackTrace();
					}
					tmp.getPlayer().getHead().setDireccion(servidor.seguirMotoY(tmp.getPlayer().getHead().abajo.getPosY()));//Direccion aleatoria en el eje y	
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
