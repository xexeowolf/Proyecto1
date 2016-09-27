package com.matriz;

import com.servidorcliente.Actor;

public class NodoItem{
	private Item item;
	protected NodoItem next;
	
	public NodoItem(Item itm){
		next=null;
		item=itm;
	}
	
	public Item getItem(){
		return item;
	}

}
