package uy.edu.ort.obli;

public class NodoLista {
	private int elem;
	private NodoLista sig;
	//Constructor
	public NodoLista(int n){
		this.elem = n;
		this.sig = null;
	}
	//Elemento
	public void setElem(int n){
		this.elem = n;
	}
	public int getElem(){
		return this.elem;
	}
	//Siguiente
	public void setSig(NodoLista sig){
		this.sig = sig;
	}

	public NodoLista getSig(){
		return this.sig;
	}

}
