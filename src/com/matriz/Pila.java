package com.matriz;

public class Pila {
	protected NodoItem top;
	private int tam;
	public Pila() {
		top=null;
		tam=0;
	}
	public void add(Item itm){
		NodoItem nuevo= new NodoItem(itm);
		if(top==null){
			top=nuevo;
		}else{
			nuevo.next=top;
			top=nuevo;
		}
		tam++;
	}
	public Item remove(){
		if(top!=null){
			Item itm=top.getItem();
			top=top.next;
			tam--;
			return itm;
		}else{
			return null;
		}
	}

	public int getTam(){
		return tam;
	}
	
}
