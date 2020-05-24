package uy.edu.ort.obli;

public class Repartidor {
	
	private String matricula;
	private String nombre;
	private Repartidor sig;
	
	//Constructor
	public Repartidor(String matricula, String nombre){
		this.matricula = matricula;
		this.nombre = nombre;
		this.sig = null;
	}
	//Modificar
	public void modRepartidor(String nombre){
		this.nombre = nombre;
	}
	
	//Obtener Repartidor
	public String obtenerRepartidor() {
		return this.matricula;
	}
	
	//Siguiente
		public void setSiguiente(Repartidor sig) {
			this.sig = sig;
		}
	
	//Obtener Siguiente
		public Repartidor obtenerSiguiente() {
			return this.sig;
		}

}
