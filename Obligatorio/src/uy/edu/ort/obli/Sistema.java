package uy.edu.ort.obli;
import uy.edu.ort.obli.ArbolRepartidor;
import uy.edu.ort.obli.Arco;
import uy.edu.ort.obli.Retorno.Resultado;
import uy.edu.ort.obli.GrafoCentroMedico;

public class Sistema implements ISistema {

	ArbolRepartidor repartidores = new ArbolRepartidor(); //Inicializamos arbol de repartidores
	GrafoCentroMedico SistemaCentroMedico;
	
	
	@Override
	//Crea el grafo vacio (sin nodos ni aristas) con capacidad de almacenamiento maxPuntos	
	public Retorno inicializarSistema(int maxPuntos) {

		if (maxPuntos <= 0) {
			return new Retorno(Resultado.ERROR_1);
		}
		else
		{
			GrafoCentroMedico CentroMedico = new GrafoCentroMedico(maxPuntos);
			this.SistemaCentroMedico = CentroMedico;
			return new Retorno(Resultado.OK);
		}
	}
	
	@Override
	public Retorno destruirSistema() {
		
		for (int i=1; i<=SistemaCentroMedico.cantNodos; i++) {
			SistemaCentroMedico.eliminarVertice(i);
		}
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarRepartidor(String matricula, String nombre) {	
		String regex = "^[a-zA-Z]+[0-9]+$";
		if (matricula.matches(regex) && matricula.length() >= 4) { 
			if (!repartidores.existeRepartidor(matricula)){
				repartidores.insertarRepartidor(matricula, nombre, repartidores.raiz);
				return new Retorno(Resultado.OK);
			}
			else
			{
				return new Retorno(Resultado.ERROR_2);
			}
		}
		else {
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno buscarRepartidor(String matricula) {
		String regex = "^[a-zA-Z]+[0-9]+$";
		
		if (matricula.matches(regex) && matricula.length() >= 4) {
			
			if (repartidores.existeRepartidor(matricula)){
				
				return new Retorno(
						Resultado.OK,
						repartidores.recorridosBusqueda,
						(repartidores.obtenerRepartidor(matricula, repartidores.raiz)).getDato()
				);
				
			}
			else
			{
				return new Retorno(Resultado.ERROR_2);
			}
		}
		else {
			return new Retorno(Resultado.ERROR_1);
		}
	}

	@Override
	public Retorno listarRepartidores() {
		this.repartidores.mostrarPreOrder();
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarCentro(String nombre, double coordX, double coordY, EnumCriticidad criticidad) {
		if (SistemaCentroMedico.size >= SistemaCentroMedico.cantNodos) {
			return new Retorno(Resultado.ERROR_1);
		}
		else {
			
		}
	}

	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno centroCriticoMasCercano(double coordX, double coordY) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno caminoSeguro() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno dibujarMapa() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}
}
