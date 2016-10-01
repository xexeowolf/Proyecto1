package com.matriz;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class NodoActor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagen;
	private int posX;
	private int posY;
	public NodoActor next;
	public NodoActor prev;
	private String nombre;
	private String nomImagen;
	
	
	public NodoActor(String direccion,int x,int y) {
		imagen=new ImageIcon(getClass().getClassLoader().getResource("Imagenes/"+direccion)).getImage();
		posX=x;
		posY=y;
		next=prev=null;
		nomImagen=direccion;
	}
	
	public String getNomImagen(){
		return nomImagen;
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
