package uy.edu.ort.obli;
import uy.edu.ort.obli.ArbolRepartidor;
import uy.edu.ort.obli.Retorno.Resultado;
import uy.edu.ort.obli.Grafo;
import uy.edu.ort.obli.CentroMedico;

public class Sistema implements ISistema {

	ArbolRepartidor repartidores = new ArbolRepartidor(); //Inicializamos arbol de repartidores
	Grafo GrafoSistema; //Grafo del sistema compuesto por Puntos
	Punto [] vertices; //Vector de vertices por herencia
	
	
	@Override
	//Crea el grafo vacio (sin nodos ni aristas) con capacidad de almacenamiento maxPuntos	
	public Retorno inicializarSistema(int maxPuntos) {

		if (maxPuntos <= 0) {
			return new Retorno(Resultado.ERROR_1);
		}
		else
		{
			Grafo GrafoSistema = new Grafo(maxPuntos);
			this.GrafoSistema = GrafoSistema;
			
			this.vertices = new Punto[GrafoSistema.cantNodos+1];
			
			return new Retorno(Resultado.OK);
		}
	}
	
	@Override
	public Retorno destruirSistema() {
		
		for (int i=1; i<=GrafoSistema.cantNodos; i++) {
			GrafoSistema.eliminarVertice(i);
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
		if (GrafoSistema.size >= GrafoSistema.cantNodos) {
			return new Retorno(Resultado.ERROR_1);
		}
		else {
			for (int i = 0; i <= vertices.length; i++) {
				if (vertices[i].getCoordY() == coordY && vertices[i].getCoordX() == coordX) {
					return new Retorno(Resultado.ERROR_2);
				}	
			}
		}
		
		CentroMedico CM = new CentroMedico(nombre, coordX, coordY, criticidad);
		int j = 0;
		boolean encontre = false;
				
		while (j <= GrafoSistema.nodosUsados.length && encontre == false) {
			if (GrafoSistema.nodosUsados[j] == false) {
				GrafoSistema.agregarVertice(j);
				vertices[j] = CM;
				encontre = true;
			}
			j++;
		}
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarEsquina(double coordX, double coordY) {
		if (GrafoSistema.size >= GrafoSistema.cantNodos) {
			return new Retorno(Resultado.ERROR_1);
		}
		else {
			for (int i = 0; i <= vertices.length; i++) {
				if (vertices[i].getCoordY() == coordY && vertices[i].getCoordX() == coordX) {
					return new Retorno(Resultado.ERROR_2);
				}	
			}
		}
		
		Esquina ESQ = new Esquina(coordX, coordY);
		int j = 0;
		boolean encontre = false;
				
		while (j <= GrafoSistema.nodosUsados.length && encontre == false) {
			if (GrafoSistema.nodosUsados[j] == false) {
				GrafoSistema.agregarVertice(j);
				vertices[j] = ESQ;
				encontre = true;
			}
			j++;
		}
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros) {
		
		int inicio = 0;
		int fin = 0;
		
		if (metros <= 0) {
			return new Retorno(Resultado.ERROR_1);
		}
		else
		{
			for (int i = 0; i <= vertices.length; i++) {
				
				if (vertices[i].getCoordY() == coordYi && vertices[i].getCoordX() == coordXi) {
					inicio = i;
				}
				if (vertices[i].getCoordY() == coordYf && vertices[i].getCoordX() == coordXf) {
					fin = i;
				}
			}
			
			if(inicio ==0 && fin == 0) {
				return new Retorno(Resultado.ERROR_2);
			}
		}
		if (GrafoSistema.matrizAdyacencia[inicio][fin].existe == false) {
			return new Retorno(Resultado.ERROR_3);
		}
		else
		{
			GrafoSistema.agregarArista(inicio, fin, metros);
			return new Retorno(Resultado.OK);
		}
	}

	@Override
	public Retorno centroCriticoMasCercano(double coordX, double coordY) {
		
		CentroMedico CM = new CentroMedico();
		int menorCosto = Integer.MAX_VALUE;

		//Repartidor
		int origen = 0;
		boolean encontreRepartidor = false;
		int x = 0;
		
	
		while (x <= vertices.length && encontreRepartidor == false)
		{
			if (vertices[x].getCoordY() == coordY && vertices[x].getCoordX() == coordX) {
				origen = x;
				encontreRepartidor = true;
			}
			x++;
		}
		
		if(encontreRepartidor == false) {
			return new Retorno(Resultado.ERROR_1);
		}
		else {
			//CentroMedico
			int destino = 0;
			int costoMinimo = Integer.MAX_VALUE;
			
			for (int i = 0; i <= vertices.length; i++) {
				if (vertices[i] instanceof CentroMedico) {	
					
					CM = (CentroMedico) vertices[i];
					EnumCriticidad alta = EnumCriticidad.ALTA;
						
					if(CM.getCriticidad() == alta) {
						destino = i;
						
						int caminoMinimoCM = GrafoSistema.caminoMinimo(origen, destino);
						if(costoMinimo > caminoMinimoCM) {
							costoMinimo = caminoMinimoCM;
						}
					}
				}
			}
		}
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
