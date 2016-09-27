package com.matriz;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.servidorcliente.Ventana;

public class HiloA extends Thread {


	ListaMoto moto;
	
	public HiloA(ListaMoto m){
		moto=m;
		
	}
	
	public void run(){
		moto.moverse();
	}

	
}
