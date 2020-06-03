package uy.edu.ort.obli;

public class NodoRepartidor {
	//Atributos
	private String matricula;
	private String nombre;
	private NodoRepartidor izq;
	private NodoRepartidor der;
	
	public NodoRepartidor (String matricula, String nombre) {
		this.matricula =matricula;
		this.nombre =nombre;
		this.izq =null;
		this.der =null;
	}
	public NodoRepartidor (String mat, String nomb, NodoRepartidor izq, NodoRepartidor der) {
		this.matricula = mat;
		this.nombre = nomb;
		this.izq = izq;
		this.der = der;
	}
	public NodoRepartidor getIzq() {
		return this.izq;
	}
	public NodoRepartidor getDer() {
		return this.der;
	}
	public String getMatricula() {
		return this.matricula;
	}
	public String getDato() {
		return this.matricula +";" + this.nombre;
	}
	public void setDato(String nombre) {
		this.nombre = nombre;
	}
	public void setDer(NodoRepartidor der) {
		this.der = der;
	}
	public void setIzq(NodoRepartidor izq) {
		this.izq = izq;
	}
}
