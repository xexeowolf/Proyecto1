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
	
	public void ubicar(String nom){
		if(tam>0){
			if(top.getItem().getNombre()!=nom){
				NodoItem tempant=top;
				NodoItem temp=top.next;
				while(temp!=null){
					if(temp.getItem().getNombre()==nom){
						tempant.next=temp.next;
						temp.next=top;
						top=temp;
						break;
					}
					temp=temp.next;
					tempant=tempant.next;
				}
			}
		}
	}
	
	public int inventario(String nom){
		int i=0;
		NodoItem tmp=top;
		while(tmp!=null){
			if(tmp.getItem().getNombre()==nom){
				i++;
			}
			tmp=tmp.next;
		}
		return i;
	}

	public int getTam(){
		return tam;
	}
	public void imprimir(){
		NodoItem tmp=top;
		while(tmp!=null){
			System.out.print(tmp.getItem().getNombre()+"\n");
			tmp=tmp.next;
		}
	}
}
