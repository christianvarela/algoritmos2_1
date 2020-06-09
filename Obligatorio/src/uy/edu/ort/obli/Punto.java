package uy.edu.ort.obli;

public abstract class Punto {
	
	protected double coordX;
	protected double coordY;
	
	
	public Punto(double coordX, double coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}
	
	public Punto() {};
	
	public Double getCoordX() {
		return this.coordX;
	}
	public Double getCoordY() {
		return this.coordY;
	}
	public void setCoordX(Double coordX) {
		this.coordX = coordX;
	}
	public void setCoordY(Double coordY) {
		this.coordY = coordY;
	}
}
