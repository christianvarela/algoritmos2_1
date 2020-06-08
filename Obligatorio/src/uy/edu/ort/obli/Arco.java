package uy.edu.ort.obli;

public class Arco {
	
	public boolean existe;
	public int peso;

	public Arco() {
		this.existe = false;
		this.peso = 0;
	}
	
	public Arco(int peso) {
		this.existe = true;
		this.peso = peso;
	}
	
}
