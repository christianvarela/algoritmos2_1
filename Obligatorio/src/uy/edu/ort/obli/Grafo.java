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
		this.matrizAdyacencia[destino][origen] = nuevo;
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
		int w = 0;
		
		for(int i = 1; i < visitados.length; i++) {
			if (visitados[i] == false) {
				if (costos[i] < costoMinimo) {
					costoMinimo = costos[i];
					w = i;
				}
			}
		}
		return w;	
	}
	
	public int[] caminoMinimo(int o) {
		
		int[] costos = new int[this.cantNodos+1];
		boolean[] visitados = new boolean[this.cantNodos+1];
		
		for(int i=1; i<=this.cantNodos; i++){
			if (i!=o) {
				if (sonAdyacentes(o,i))
					costos[i] = this.matrizAdyacencia[i][o].peso;
				else	
					costos[i] = Integer.MAX_VALUE;
			}
			else {
				visitados[i] = true;
			}
		}

		int[] camino = new int[this.cantNodos+1];
			
		for(int i=1; i<=this.cantNodos; i++){
			int w = distanciaMasCorta(costos, visitados);
			visitados[w]=true;
			
			if ( w != 0) {
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
		}
		//imprimirCaminosAux(o, d, camino);
		return costos;
	}
	
	public void prim() {
	    int visited[] = new int [this.cantNodos+1];
	    int d[] = new int[this.cantNodos+1];
	    int p[] = new int[this.cantNodos+1];
	    
	    int verticeCount;
	    int current;
	    int total;
	    int mincost;

        verticeCount = this.cantNodos+1;
   
       for (int i = 1; i < verticeCount; i++) {
           p[i] = 0; 
           visited[i] = 0;
           d[i] = Integer.MAX_VALUE;
        }
      
        Arco weightArray[][] = this.matrizAdyacencia; 
        
        current = 1;
        d[current] = 0;
        total = 1;
        visited[current] = 1;
        while( total != verticeCount) {
            for (int i = 1; i < verticeCount; i++) {
                if ( weightArray[current][i].peso != 0) {
                    if( visited[i] == 0) { 
                        if (d[i] > weightArray[current][i].peso) {
                            d[i] = weightArray[current][i].peso;
                            p[i] = current;
                        }
                    }
                }
            }
            mincost = Integer.MAX_VALUE;;
            for (int i = 1 ; i < verticeCount; i++) {
                if (visited[i] == 0) {
                    if (d[i] < mincost) {
                        mincost = d[i];
                        current = i;
                    }
                }
            }
            visited[current]=1;
            total++;
        }

        mincost=0;
        for(int i=1; i<verticeCount ;i++) {
        	mincost=mincost+d[i];
        }

        System.out.print("\n Minimum cost="+mincost);
        System.out.print("\n Minimum Spanning tree is");

        for(int i=1;i<verticeCount;i++) {
        	System.out.print("\n vertex" +i+"is connected to"+p[i]);
        }
	    
	}
	
	private void imprimirCaminosAux(int v, int i, int[] camino){
			
		if(i!=v){
			imprimirCaminosAux(v, camino[i], camino);
		}
		
		System.out.print(i +" ");
	}
}

