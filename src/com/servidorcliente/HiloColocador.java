package com.servidorcliente;

import com.matriz.MatrizDinamica;

public class HiloColocador extends Thread {

	MatrizDinamica m;
	
	public HiloColocador(MatrizDinamica j){
		m=j;
	}
	
	public void run(){
		m.rellenarMatriz();
	
	}

}
