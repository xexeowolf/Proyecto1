package com.matriz;

public class Fabrica {
	
	

	public Fabrica() {
	}
	
	public Item crearItem(){
		int x= (int)(Math.random()*5+1);
		if(x==1){
			return new Bomba();
		}
		else if(x==2){
			return new AumentaEstela();
		}
		else if(x==3){
			return new Gas();
		}
		else if(x==4){
			return new Escudo();
		}else{
			return new HiperVelocidad();
		}
	}
	
	public static void main(String[] args){
	}

}

