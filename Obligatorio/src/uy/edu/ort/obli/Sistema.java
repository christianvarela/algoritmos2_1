package uy.edu.ort.obli;
import uy.edu.ort.obli.ArbolRepartidor;
import uy.edu.ort.obli.NodoRepartidor;
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
		String regex = "^[A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9]$";
		if (matricula.matches(regex) && matricula.length() == 7) { 
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
		String regex = "^[A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9]$";
		
		if (matricula.matches(regex) && matricula.length() >= 4) {
			
			if (repartidores.existeRepartidor(matricula)){
				
				NodoRepartidor rep = repartidores.obtenerRepartidor(matricula, repartidores.raiz);
				
				return new Retorno(
						Resultado.OK,
						repartidores.cantBusquedas(matricula,repartidores.getRaiz()),
						rep.getDato()
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
		String stringRet = this.repartidores.mostrarInOrder();
		stringRet = stringRet.substring(0, stringRet.length() -1);
		
		return new Retorno(
				Resultado.OK,
				0,
				stringRet
		);
	}

	@Override
	public Retorno registrarCentro(String nombre, double coordX, double coordY, EnumCriticidad criticidad) {
		if (GrafoSistema.size >= GrafoSistema.cantNodos) {
			return new Retorno(Resultado.ERROR_1);
		}
		else if(GrafoSistema.size != 0) {
			for (int i = 1; i < GrafoSistema.nodosUsados.length; i++) {
				if (GrafoSistema.nodosUsados[i] == true) {
					if (vertices[i].getCoordY() == coordY && vertices[i].getCoordX() == coordX) {
						return new Retorno(Resultado.ERROR_2);
					}
				}
			}
		}
	
		CentroMedico CM = new CentroMedico(nombre, coordX, coordY, criticidad);
		int j = 1;
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
		else if(GrafoSistema.size != 0) {
			for (int i = 1; i < GrafoSistema.nodosUsados.length; i++) {
				if (GrafoSistema.nodosUsados[i] == true) {
					if (vertices[i].getCoordY() == coordY && vertices[i].getCoordX() == coordX) {
						return new Retorno(Resultado.ERROR_2);
					}
				}
			}
		}
		
		Esquina ESQ = new Esquina(coordX, coordY);
		int j = 1;
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
			for (int i = 0; i < GrafoSistema.nodosUsados.length; i++) {
				if (GrafoSistema.nodosUsados[i] == true) {
					if (vertices[i].getCoordY() == coordYi && vertices[i].getCoordX() == coordXi) {
						inicio = i;
					}
					if (vertices[i].getCoordY() == coordYf && vertices[i].getCoordX() == coordXf) {
						fin = i;
					}
				}
			}
			
			if(inicio == 0 || fin == 0) {
				return new Retorno(Resultado.ERROR_2);
			}
		}
		
		if (GrafoSistema.matrizAdyacencia[inicio][fin].existe == true) {
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

		//Repartidor
		int origen = 0;
		boolean encontreRepartidor = false;
		int x = 1;
		while (x < GrafoSistema.nodosUsados.length && encontreRepartidor == false) {
			if (GrafoSistema.nodosUsados[x] == true) {
				if (vertices[x].getCoordY() == coordY && vertices[x].getCoordX() == coordX) {
					origen = x;
					encontreRepartidor = true;
				}
			}
			x++;
		}
		
		int costoMinimo = Integer.MAX_VALUE;
		
		if(encontreRepartidor == false) {
			return new Retorno(Resultado.ERROR_1);
		}
		else {
			//CentroMedico
			int[] costos = GrafoSistema.caminoMinimo(origen);
			
			for (int i = 1; i < vertices.length; i++) {
				if (vertices[i] instanceof CentroMedico) {	
					
					CM = (CentroMedico) vertices[i];
					EnumCriticidad alta = EnumCriticidad.ALTA;
						
					if(CM.getCriticidad() == alta) {
						if (costos[i] == Integer.MAX_VALUE) {
							return new Retorno(Resultado.ERROR_2);
						}
						else if(costoMinimo > costos[i]) {
							costoMinimo = costos[i];
						}
					}
				}
			}
		}
		return new Retorno(
				Resultado.OK,
				costoMinimo,
				""
				);
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
