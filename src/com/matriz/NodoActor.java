package com.matriz;

import java.awt.Image;

import javax.swing.ImageIcon;

public class NodoActor {

	private Image imagen;
	private int posX;
	private int posY;
	public NodoActor next;
	private String nombre;
	
	
	public NodoActor(String direccion,int x,int y) {
		imagen=new ImageIcon(getClass().getClassLoader().getResource("Imagenes/"+direccion)).getImage();
		posX=x;
		posY=y;
		next=null;
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
	public Image getImagen(){
		return imagen;
	}
	public void setImagen(String direccion){
		imagen=new ImageIcon(getClass().getClassLoader().getResource(direccion)).getImage();
	}
	public String getNombre(){
		return nombre;
	}
	public void setNombre(String k){
		nombre=k;
	}
	

}
