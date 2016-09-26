package com.servidorcliente;

public class HiloMovimiento extends Thread {

	private Actor actor;
	private Interfaz interfaz;
	private int x;
	private int y;

	public HiloMovimiento(Actor A,Interfaz L) {
		actor=A;
		interfaz=L;
		x=y=0;
	}
	
	public void setDireccion(int a,int b){
		if(a==0){
			x=0;
			y=b;
		}
		else{
			y=0;
			x=a;
		}
	}

	
	public void run() {
		while(true){
			
				int aumX=actor.getPosX()+x;
				int aumY=actor.getPosY()+y;
				if(0<=aumX && aumX<interfaz.ancho-30){
					actor.setPosX(aumX);
				}
				if(0<=aumY && aumY<interfaz.alto-30){
					actor.setPosY(aumY);
				}
				interfaz.aplicarCambios(interfaz.actores.getTam());
				interfaz.aplicarBuffer(interfaz.actores.getTam());
			}
	}
}
