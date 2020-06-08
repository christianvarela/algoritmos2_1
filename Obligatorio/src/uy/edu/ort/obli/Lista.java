package uy.edu.ort.obli;

public class Lista {
	private NodoLista inicio;

	public Lista() {
		this.inicio = null;
	}
	public void insertarInicio(int n) {
		NodoLista nuevo = new NodoLista(n);
		nuevo.setSig(inicio);
		this.inicio = nuevo;
	}
	public boolean esVacia() {
		return this.inicio == null;
	}

	public int head(){
		return this.inicio.getElem();
	}

	public NodoLista tail(){
		return this.inicio.getSig();
	}
	public void imprimir() {
		if (this.esVacia())
			System.out.println("La lista es vacía");
		else{
			NodoLista aux = this.inicio;
			while (aux != null){
				System.out.println(aux.getElem());
				aux = aux.getSig();
			}
		}
	}
}
