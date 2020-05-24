package uy.edu.ort.obli;


import uy.edu.ort.obli.Repartidor;

public class Repartidores implements IRepartidores {

	private Repartidor inicio;
	
	@Override
	public void Repartidores() {
		this.inicio = null;		
	}

	@Override
	public void insertarInicio(String mat, String nom) {
		Repartidor nuevo = new Repartidor(mat, nom);
		nuevo.setSiguiente(inicio);
		this.inicio = nuevo;
	}

	@Override
	public boolean esVacia() {
		return this.inicio == null;
	}
	
	public String head() {
		return this.inicio.obtenerRepartidor();
	}
	
	public Repartidor tail() {
		return (this.inicio.obtenerSiguiente());
	}
 
	@Override
	public void imprimir() {
		if (this.esVacia()) {
			System.out.println("La lista de repartidores es vacia");
		}
		else {
			Repartidor aux = this.inicio;
			while(aux != null) {
				System.out.println(aux.obtenerRepartidor());
				aux = aux.obtenerSiguiente();
			}
		}
	}

	@Override
	public void borrarInicio() {
		if (!this.esVacia()) {
			this.inicio = this.inicio.obtenerSiguiente();
		}
	}

	@Override
	public void vaciarLista() {
		while( inicio != null) {
			borrarInicio();
		}
	}

}
