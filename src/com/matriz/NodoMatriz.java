package com.matriz;

public class NodoMatriz {
	protected NodoMatriz up;
	protected NodoMatriz down;
	protected NodoMatriz left;
	protected NodoMatriz right;
	private Item item;
	private boolean ocupado;
	private String ocupante;
	
	public NodoMatriz() {
		down=left=right=up=null;
	}
	
	public void setEstado(boolean v,String nom){
		ocupado=v;
		ocupante=nom;
	}
	public String getOcupante(){
		return ocupante;
	}
	public boolean getEstado(){
		return ocupado;
	}
	
	public void setItem(Item ele){
		item=ele;
	}
	public void setItem(HiperVelocidad ele){
		item=ele;
	}
	public void setItem(Bomba ele){
		item=ele;
	}
	public void setItem(Gas ele){
		item=ele;
	}
	public void setItem(AumentaEstela ele){
		item=ele;
	}
	public void setItem(Escudo ele){
		item=ele;
	}
	public Item getItem(){
		return item;
	}
	

}
