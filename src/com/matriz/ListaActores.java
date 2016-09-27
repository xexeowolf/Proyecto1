package com.matriz;

public class ListaActores {
	
	private NodoActor head,tail;
	private int tam;
	
	public ListaActores() {
		head=tail=null;
		tam=0;
	}
	
	public void add(String direccion,int x,int y,String nombre){
		NodoActor nuevo= new NodoActor(direccion,x,y);
		nuevo.setNombre(nombre);
		if(tam==0){
			head=tail=nuevo;
			tam++;
		}
		else{
			tail.next=nuevo;
			tail=nuevo;
			tam++;
		}
	}
	
	public NodoActor add(String direccion,int x,int y,NodoActor puntero){
		NodoActor nuevo= new NodoActor(direccion,x,y);
		nuevo.setNombre("moto");
		puntero=nuevo;
		if(tam==0){
			head=tail=nuevo;
			tam++;
		}
		else{
			tail.next=nuevo;
			tail=nuevo;
			tam++;
		}
		return puntero;
	}
	
	public void remove(int x,int y,String nombre){
		if(head.getPosX()==x && head.getPosY()==y && head.getNombre()==nombre){
			head=head.next;
			tam--;
		}
		else{
			NodoActor tmp=head;
			while(tmp.next!=null){
				if(tmp.next.getPosX()==x && tmp.next.getPosY()==y && tmp.next.getNombre()==nombre){
					tmp.next=tmp.next.next;
					tam--;
					break;
				}
				tmp=tmp.next;
			}
		}
	}
	
	public int getTam(){
		return tam;
	}
	 public NodoActor getHead(){
		 return head;
	 }
}
