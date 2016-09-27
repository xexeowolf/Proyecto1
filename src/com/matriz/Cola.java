package com.matriz;
import com.servidorcliente.*;
public class Cola {
	
	protected NodoItem head,tail;
	private int tam;

	public Cola() {
		head=tail=null;
		tam=0;
	
	}
	public void add(Item itm){
		NodoItem nuevo= new NodoItem(itm);
		if(tam==0){
			head=tail=nuevo;
			tam++;
		}else{
			tail.next=nuevo;
			tail=nuevo;
			tam++;
		}
	}
	
	public Item getItem(){
		return head.getItem();
	}
	
	public Item remove(){
		if(tam!=0){
			Item itm=head.getItem();
			head=head.next;
			tam--;
			return itm;
			}
		else{
			return null;
		}
	}
	
	public int getTam(){
		return tam;
	}
	
	
}
