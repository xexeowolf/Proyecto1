package com.matriz;

public class NodoMatriz {
	protected NodoMatriz up;
	protected NodoMatriz down;
	protected NodoMatriz left;
	protected NodoMatriz right;
	public NodoMatriz() {
		down=left=right=up=null;
	}

}
