package uy.edu.ort.obli;

public class Grafo {
	
	int size;				//Tamanio actual en cantidad de nodos
	int cantNodos;			//Maxima cantidad de nodos permitida
	Arco[][] matrizAdyacencia;		//Representamos las conexiones entre los vertices. 
	boolean [] nodosUsados; //Representamos los vertices (la existencia o no existencia)
	
	
	public Grafo(int cantNodos) {
		this.size = 0;
		this.cantNodos = cantNodos;
		
		this.matrizAdyacencia = new Arco[cantNodos+1][cantNodos+1];
		for (int i=1; i<=cantNodos; i++) {
			for (int j=1; j<=cantNodos; j++) {
				this.matrizAdyacencia[i][j] = new Arco();
			}
		}
		this.nodosUsados = new boolean[cantNodos+1];			
	}
	
	public void agregarArista(int origen, int destino, int peso) {
		Arco nuevo = new Arco(peso);
		this.matrizAdyacencia[origen][destino] = nuevo;
	}

	public void agregarVertice(int v) {
		this.nodosUsados[v]=true;
		this.size ++;
	}

	public void eliminarArista(int origen, int destino) {
		Arco nuevo = new Arco();
		this.matrizAdyacencia[origen][destino] = nuevo;		
	}
	
	public void eliminarVertice(int v) {
		this.nodosUsados[v]=false;
		this.size --;
		
		//Elimino las aristas donde v es miembro
		for(int i=1;i<=this.cantNodos;i++){
			this.matrizAdyacencia[i][v] = new Arco();
			this.matrizAdyacencia[v][i] = new Arco();
		}
	}

	public boolean esVacio() {
		return this.size==0;
	}

	public boolean sonAdyacentes(int a, int b) {
		return this.matrizAdyacencia[a][b].existe;
	}
	
	public Lista verticesAdyacentes(int v) {
		Lista l = new Lista();
		for(int i=1; i<=this.cantNodos; i++){
			if(this.sonAdyacentes(v, i)){
				l.insertarInicio(i);
			}
		}
		return l;
	}

	public boolean estaVertice(int v) {
		return this.nodosUsados[v];
	}
	
	public int distanciaMasCorta(int[] costos, boolean[] visitados){
		
		int costoMinimo = Integer.MAX_VALUE;
		
		for(int i = 0; i <= visitados.length; i++) {
			if (visitados[i] == false) {
				if (costos[i] < costoMinimo)
					costoMinimo = costos[i];
			}
		}
		return costoMinimo;	
	}
	
	public int caminoMinimo(int o, int d) {
		
		int[] costos = new int[this.cantNodos+1];
		
		for(int i=1; i<=this.cantNodos; i++){
			if (i!=o)
				if (sonAdyacentes(o,i))
					costos[i] = this.matrizAdyacencia[i][o].peso;
				else	
					costos[i] = Integer.MAX_VALUE;
		}

		int[] camino = new int[this.cantNodos+1];
		boolean[] visitados = new boolean[this.cantNodos+1];
			
		for(int i=1; i<=this.cantNodos; i++){
			//vertice con la distancia mas corta no visitado
			int w = distanciaMasCorta(costos, visitados);
			visitados[w]=true;

			for(int j=1; j<=this.cantNodos; j++){
				if(this.sonAdyacentes(w, j) && !visitados[j]){
								 							
					if(this.matrizAdyacencia[w][j].peso+costos[w] < costos[j])
					{			
						costos[j] = this.matrizAdyacencia[w][j].peso + costos[w];
						camino[j]= w;
					}
				}
			}			
		}
		
		imprimirCaminosAux(o, d, camino);
		return costos[d];
	}
	
	private void imprimirCaminosAux(int v, int i, int[] camino){
			
		if(i!=v){
			imprimirCaminosAux(v, camino[i], camino);
		}
		
		System.out.print(i +" ");
	}
}

