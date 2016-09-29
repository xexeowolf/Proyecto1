package com.matriz;

public class ListaActores {
	
	private NodoActor head,tail;
	private int tam;
	
	public ListaActores() {
		head=tail=null;
		tam=0;
	}
	
	
	
	public void eliminarMoto(String nom){
	if(tam>0){
		while(head!=null){
			if(head.getNomImagen()==nom){
				head=head.next;
				head.prev=null;
				tam--;
			}
			else{
				System.out.print("\n"+nom+"\n"+head.getNomImagen()+"\n-------\n");
				break;
			}
		}
		if(tam>1){
			NodoActor tmp=head.next;
			while(tmp!=null){
				if(tmp.getNomImagen()==nom){
					tmp=tmp.prev;
					tmp.next=tmp.next.next;
					if(tmp.next!=null){
						tmp.next.prev=tmp;	
					}
					tam--;
					eliminarMoto(nom);
				}
				else{
					System.out.print("\n"+nom+"\n"+tmp.getNomImagen()+"\n-------\n");
					tmp=tmp.next;
				}
			}
		}
	}
		}
	
	public void imprimir(){
		NodoActor tmp=head;
		while(tmp!=null){
			System.out.print(tmp.getNomImagen()+"\n");
			tmp=tmp.next;
		}
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
			nuevo.prev=tail;
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
			nuevo.prev=tail;
			tail=nuevo;
			tam++;
		}
		return puntero;
	}
	
	public void remove(int x,int y,String nombre){
	if(tam!=0){
		if(head.getPosX()==x && head.getPosY()==y && head.getNombre()==nombre){
			head=head.next;
			head.prev=null;
			tam--;
		}
		else{
			if(tam>1){
			NodoActor tmp=head;
			while(tmp.next!=null){
				if(tmp.next.getPosX()==x && tmp.next.getPosY()==y && tmp.next.getNombre()==nombre){
					if(tmp.next==tail){
						tail=tmp;
					}
					tmp.next=tmp.next.next;
					if(tmp.next!=null){
					tmp.next.prev=tmp;}
					tam--;
					break;
				}
				tmp=tmp.next;
			}
		}
	}
}
}
	
	public int getTam(){
		return tam;
	}
	 public NodoActor getHead(){
		 return head;
	 }
	 
	 
	 public static void main(String[] args){
		 ListaActores  j=new ListaActores();
		 j.add("estela1.gif", 5, 6, "algo");
		 j.add("estela1.gif", 5, 6, "algo");
		 j.add("gas.gif", 5, 6, "algo");
		 j.add("estela1.gif", 5, 6, "algo");
		 j.add("moto.gif", 5, 6, "algo");
		 j.add("moto.gif", 5, 6, "algo");
		 j.add("estela1.gif", 5, 6, "algo");
		 j.add("moto.gif", 5, 6, "algo");
		 j.add("estela1.gif", 5, 6, "algo");
		 j.add("moto.gif", 5, 6, "algo");
		 j.add("estela1.gif", 5, 6, "algo");
		 System.out.print(j.getTam());
		 j.imprimir();
		 j.eliminarMoto("gas.gif");
		System.out.print(j.getTam());
		 j.imprimir();
		 
		 
	 }
}
