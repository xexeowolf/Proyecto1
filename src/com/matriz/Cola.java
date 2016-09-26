package com.matriz;
import com.servidorcliente.*;
public class Cola {
	
	protected NodoString head,tail;
	private int tam;

	public Cola() {
		head=tail=null;
		tam=0;
	
	}
	public void add(String dato,int y){
		NodoString nuevo= new NodoString(dato,y);
		if(tam==0){
			head=tail=nuevo;
			tam++;
		}else{
			tail.next=nuevo;
			tail=nuevo;
			tam++;
		}
	}
	
	public String getInfo(){
		return head.info;
	}
	
	public int getValor(){
		return head.valor;
	}
	
	
	public void remove(){
		if(tam!=0){
			head=head.next;
			tam--;
			}
	}
	
	public int getTam(){
		return tam;
	}
	
	
}
