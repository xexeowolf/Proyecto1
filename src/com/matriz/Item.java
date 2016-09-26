package com.matriz;

import java.lang.Math;

public class Item {
	
	private String nombre;
	private int valor;
	
	public Item(String Nombre,int numero) {
		nombre=Nombre;
		valor=numero;
	}
	
	public Item(String Nombre){
		nombre=Nombre;
		valor=-1;
	}
	
	public static int generarNumero(int n){
		int l =(int)(Math.random()*n)+1;
		return l;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public int getValor(){
		return valor;
	}

}
