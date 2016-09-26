package com.matriz;

public class ListaMoto {
	private NodoMoto head;
	private NodoMoto tail;
	private int tam;
	private int ID;
	private int alto;
	private int ancho;
	private String escudo;

	
	public ListaMoto(int id,int a,int l,String nombre,MatrizDinamica m) {
		NodoMoto nuevo=new NodoMoto(a,l,nombre);
		ID=id;
		nuevo.abajo=m.buscarLugar();
		while(nuevo.abajo==null){
			nuevo.abajo=m.buscarLugar();
		}
		head=tail=nuevo;
		tam=0;
		alto=l;
		ancho=a;
		int k=3;
		while(k!=0){
			add();
			k--;
		}
		
	}
	
	public void add(){
		NodoMoto est=new NodoMoto(ancho,alto,ID);
		tail.sig=est;
		est.ant=tail;
		tail=est;
		tam++;
	}
	
	public void aumentarLista(){
		if(head.getEstela()>tam){
			add();
		}
	}
	
	public void enlazar(){
		NodoMoto tmp=tail;
		tmp.abajo.setEstado(false, "");
		tmp.abajo=tmp.ant.abajo;
		tmp=tmp.ant;
		tmp.abajo.setEstado(true, "estela");
		while(tmp!=head){
			tmp.abajo=tmp.ant.abajo;
			tmp=tmp.ant;
			tmp.abajo.setEstado(true, "estela");
		}	
		
		switch(head.getDireccion()){
		case "arr": head.abajo=head.abajo.up;break;
		case "abj": head.abajo=head.abajo.down;break;
		case "der": head.abajo=head.abajo.right;break;
		case "izq": head.abajo=head.abajo.left;break;
		}
		if(head.abajo.getEstado()==false){
			head.abajo.setEstado(true, "moto");
		}
		else{
			switch(head.abajo.getOcupante()){
			case "item":{
			Item k=head.abajo.getItem();
			if(k!=null){
				switch(k.getNombre()){
				case "combustible": head.agregarItem(k.getNombre(),k.getValor());break;
				case "velocidad":head.agregarPoder("velocidad");break; //Deberia agregar el item
				case "aumestela":head.agregarItem(k.getNombre(),k.getValor());break;
				case "escudo":head.agregarPoder("escudo");break;    //Deberia agregar el item
				case "bomba":destruirMoto();break;
				}
			}break;}
			case "moto":{destruirMoto();break;}
			case "estela":{if(escudo!="activo"){destruirMoto();}else{escudo="desac";};break;}
			}
		}
		
	}
	
	public void destruirMoto()
	{
		NodoMoto tm=head;
		while(tm!=null){
			tm.abajo=null;
			tm=tm.sig;
		}
		head=tail=null;
	}
	
	public void aplicarItem(){
		while(true){
			if(head.getItems().getTam()>0){
				switch(head.getItems().getInfo()){
				case "combustible":head.aumGas(head.getItems().getValor());head.getItems().remove();break;
				case "aumestela":head.setEstela(head.getItems().getValor());head.getItems().remove();break;
				}
			}
		}
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
