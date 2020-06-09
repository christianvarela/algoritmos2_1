package uy.edu.ort.obli;

public class CentroMedico extends Punto {
	//(String nombre, Double coordX, Double coordY, EnumCriticidad criticidad)
		
	private String nombre;
	private EnumCriticidad criticidad; 
	
	public CentroMedico() {
		super();
	}
	
	public CentroMedico (String nombre, double coordX, double coordY, EnumCriticidad criticidad) {
		super(coordX, coordY);
		this.nombre = nombre;
		this.criticidad = criticidad;
	}
	

	public String getNombre() {
		return this.nombre;
	}
	
	public EnumCriticidad getCriticidad() {
		return this.criticidad;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
