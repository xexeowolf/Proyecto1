package com.matriz;

import com.servidorcliente.Actor;

public class NodoMoto {
	
	private int estela;
	private int combustible;
	private int velocidad;
	private String direccion;
	private Pila poderes;
	private Cola items;
	private Actor imagen;
	public NodoMatriz abajo;
	public NodoMoto sig;
	public NodoMoto ant;
	
	
	public NodoMoto(int ancho,int alto,String nombre){
		estela=3;
		velocidad=1;
		combustible=100;
		poderes=new Pila();
		items=new Cola();
		imagen=new Actor(ancho,alto,nombre);
		abajo=null;
		sig=null;
		ant=null;
		
	}
	
	public NodoMoto(int ancho,int alto,int ID){
		imagen=new Actor(ancho,alto,"estela"+Integer.toString(ID)+".png");
		abajo=null;
		sig=null;
		ant=null;
	}

	public void cambiarImagen(String h){
		imagen.cambiarImagen(h);
	}
	
	public void agregarPoder(String n){
		poderes.add(n);
	}
	public void agregarItem(String k,int y){
		items.add(k,y);
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
		combustible--;
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
	public void aumGas(int g){
		combustible=combustible+g;
		if(combustible>100){
			combustible=100;
		}
	}
	
}
