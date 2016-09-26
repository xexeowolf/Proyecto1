package com.matriz;

public class Pila {
	NodoString top;
	int tam;
	public Pila() {
		top=null;
		tam=0;
	}
	public void add(String dato){
		NodoString nuevo= new NodoString(dato,null);
		if(top==null){
			top=nuevo;
		}else{
			nuevo.next=top;
			top=nuevo;
		}
		tam++;
	}
	public String remove(){
		if(top!=null){
			String temp=top.info;
			top=top.next;
			tam--;
			return temp;
		}else{
			return "vacio";
		}
	}

}
