package com.matriz;

import com.servidorcliente.Actor;

public class NodoString{
	protected String info;
	protected NodoString next;
	protected int valor;
	
	public NodoString(String inf,int h){
		next=null;
		valor=h;;
		info=inf;
	}
	
	public String getNombre(){
		return info;
	}
	
	public int getValor(){
		return valor;
	}

}
