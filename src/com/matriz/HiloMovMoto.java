package com.matriz;


public class HiloMovMoto extends Thread {


	ListaMoto moto;
	
	public HiloMovMoto(ListaMoto m){
		moto=m;
		
	}
	
	public void run(){
		while(true){
			moto.moverse();
		}
	}
	

	
}
