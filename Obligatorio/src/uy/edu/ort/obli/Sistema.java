package uy.edu.ort.obli;

import uy.edu.ort.obli.Arco;
import uy.edu.ort.obli.Retorno.Resultado;
import uy.edu.ort.obli.Repartidor;

public class Sistema implements ISistema {
	

	int size;				//Tamanio actual en cantidad de nodos
	int maxPuntos;			//Maxima cantidad de nodos permitida
	Arco[][] matrizAdy;		//Representamos las conexiones entre los vertices. 
	boolean [] nodosUsados; //Representamos los vertices (la existencia o no existencia)
	
	//Repartidores
	
	
	
	@Override
	//Crea el grafo vacio (sin nodos ni aristas) con capacidad de almacenamiento maxPuntos	
	public Retorno inicializarSistema(int maxPuntos) {
		
			this.size = 0;
			this.maxPuntos = maxPuntos;
		
			if (this.maxPuntos <= 0) {
				return new Retorno(Resultado.ERROR_1);
			}
			else
			{
				this.matrizAdy = new Arco[maxPuntos+1][maxPuntos+1];
				for (int i=1; i<=maxPuntos; i++) {
					for (int j=1; j<=maxPuntos; j++) {
						this.matrizAdy[i][j] = new Arco();
					}
				}
				this.nodosUsados = new boolean[maxPuntos+1];
				return new Retorno(Resultado.OK);	
			}
	}
	
	@Override
	public Retorno destruirSistema() {
		
		for (int i=1; i<=maxPuntos; i++) {
			this.nodosUsados[i] = false;
			this.size--;
			
			for (int j=1; j<=maxPuntos; j++) {
				this.matrizAdy[i][j] = new Arco();
				this.matrizAdy[j][i] = new Arco();
			}
		}
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarRepartidor(String matricula, String nombre) {
		//this.Repartidores = new Repartidor(matricula, nombre);
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno buscarRepartidor(String matricula) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno listarRepartidores() {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
	}

	@Override
	public Retorno registrarCentro(String nombre, double coordX, double coordY, EnumCriticidad criticidad) {
		return new Retorno(Resultado.NO_IMPLEMENTADA);
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
