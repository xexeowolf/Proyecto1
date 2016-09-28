package com.servidorcliente;

import com.matriz.ListaMoto;

public class HiloItem extends Thread {

	ListaMoto moto;
	
	public HiloItem(ListaMoto m){
		moto=m;
		
	}
	
	public void run(){
		moto.aplicarItem();
		

	}
		}
