package com.matriz;


import java.io.Serializable;


public class NodoActor implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	public NodoActor next,prev,extra;
	private String nombre;
	private String nomImagen;
	
	
	
	public NodoActor(String direccion,int x,int y) {
		posX=x;
		posY=y;
		next=prev=extra=null;
		nomImagen=direccion;
	}
	
	public String getNomImagen(){
		return nomImagen;
	}
	public void setNombreImagen(String k){
		nomImagen=k;
	}
	
	public int getPosX(){
		return posX;
	}
	public int getPosY(){
		return posY;
	}
	public void setPosX(int x){
		posX=x;
	}
	public void setPosY(int y){
		posY=y;
	}
	
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String k){
		nombre=k;
	}
	

}
