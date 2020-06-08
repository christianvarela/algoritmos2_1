package uy.edu.ort.obli;

public class CentroMedico {
	//(String nombre, Double coordX, Double coordY, EnumCriticidad criticidad)
	
	private enum EnumCriticidad {
		BAJA,
		MEDIA,
		ALTA
	};
	
	private String nombre;
	private Double coordX;
	private Double coordY;
	private EnumCriticidad criticidad; 
	
	
	public CentroMedico (String nombre, Double coordX, Double coordY, EnumCriticidad criticidad) {
		this.nombre = nombre;
		this.coordX = coordX;
		this.coordY = coordY;
		this.criticidad = criticidad;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Double getCoordX() {
		return this.coordX;
	}
	public Double getCoordY() {
		return this.coordY;
	}
	public EnumCriticidad getCriticidad() {
		return this.criticidad;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCoordX(Double coordX) {
		this.coordX = coordX;
	}
	public void setCoordY(Double coordY) {
		this.coordY = coordY;
	}
}
