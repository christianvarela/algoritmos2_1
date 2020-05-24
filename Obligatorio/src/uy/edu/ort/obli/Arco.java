package uy.edu.ort.obli;

public class Arco {
	
	public boolean exite;
	public int peso;

	public Arco() {
		this.exite = false;
		this.peso = 0;
	}
	
	public Arco(int peso) {
		this.exite = true;
		this.peso = peso;
	}
	
}
