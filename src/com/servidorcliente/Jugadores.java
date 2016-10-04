package com.servidorcliente;

import com.matriz.ListaMoto;

public class Jugadores {
	
	public NodoJugador head,tail;
	private int tam;
	
	
	
	public Jugadores() {
		head=tail=null;
		tam=0;
	}
	
	public void agregar(ListaMoto jugador,String ip,int ps){
		NodoJugador nuevo=new NodoJugador(jugador,ip,ps);
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
	public int eliminar(int ps){
		int num=0;
		if(head.getPuerto()==ps){
			num=head.getPlayer().ID;
			head=head.next;
		}
		else{
			NodoJugador tmp=head;
			while(tmp.next!=null){
				if(tmp.next.getPuerto()==ps){
					num=tmp.next.getPlayer().ID;
					if(tmp.next==tail){
						tail=tmp;
					}
					tmp.next=tmp.next.next;
					tam--;
				}	
			}
		}
		return num;
	}
	
	public NodoJugador buscar(int pto){
		NodoJugador tmp=head;
		do{
			if(tmp.getPuerto()==pto){
				return tmp;
			}
			tmp=tmp.next;
		}while(tmp!=null);
		return null;
	}
	
	public void eliminarAlInicio(){
		head=head.next;
	}
	
	public void setTam(int t){
		tam=t;
	}
	
	public int getTam(){
		return tam;
	}

}
