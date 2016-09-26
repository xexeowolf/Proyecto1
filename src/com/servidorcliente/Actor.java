package com.servidorcliente;

import java.awt.image.BufferedImage;

public class Actor{
	private int posX,posY;
	public int alto,ancho;
	public Imagen imagenes;
	public BufferedImage figura;
	public BufferedImage buffer;
	
	public Actor(int a,int h,String nombre) {
		ancho=a;
		alto=h;
		posX=posY=50;
		imagenes=new Imagen();
		buffer=new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		figura=imagenes.obtenerImagen(nombre);
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
	public BufferedImage getBuffer(){
		return buffer;
	}
	
	public BufferedImage getFigura(){
		return figura;
	}
	public void cambiarImagen(String nombre){
		figura=imagenes.obtenerImagen(nombre);
	}
}
