package com.matriz;

import com.servidorcliente.Actor;

public class NodoMoto {
	
	private int estela;
	private double combustible;
	private int velocidad;
	private String direccion;
	private Pila poderes;
	private Cola items;
	public NodoMatriz abajo;
	public NodoMoto sig;
	public NodoMoto ant;
	
	
	public NodoMoto(){
		estela=3;
		velocidad=300;
		combustible=100.0;
		poderes=new Pila();
		items=new Cola();
		abajo=null;
		sig=null;
		ant=null;
		direccion="abj";
		
	}
	
	public NodoMoto(boolean g){
		abajo=null;
		sig=null;
		ant=null;
	}

	public void agregarPoder(Item itm){
		poderes.add(itm);
	}
	public void agregarItem(Item itm){
		items.add(itm);
	}
	public Cola getItems(){
		return items;
	}
	
	public Pila getPoderes(){
		return poderes;
	}
	
	public void setEstela(int i){
		estela=estela+i;
	}
	
	public void reduceGas(){
		combustible=combustible-0.2;
	}
	
	public double getGas(){
		return combustible;
	}
	
	public int getEstela(){
		return estela;
	}
	
	public void setVel(int v){
		velocidad=v;
	}
	public int getVel(){
		return velocidad;
	}
	public void setDireccion(String nom){
		direccion=nom;
	}
	public String getDireccion(){
		return direccion;
	}
	public void aumGas(double g){
		combustible=combustible+g;
		if(combustible>100.0){
			combustible=100.0;
		}
	}
	
}
