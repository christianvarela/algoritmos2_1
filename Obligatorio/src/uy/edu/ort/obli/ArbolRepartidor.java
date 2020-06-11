package uy.edu.ort.obli;

public class ArbolRepartidor {
	protected NodoRepartidor raiz;
	//protected int recorridosBusqueda; 
	
	public ArbolRepartidor() {
		this.raiz = null;
	}
	
	public NodoRepartidor getRaiz() {
		return raiz;
	}
	
	public boolean esArbolRepartidorVacio() {
		return (this.raiz == null);
	}
	
	public boolean existeRepartidor(String mat) {
		NodoRepartidor aux = obtenerRepartidor(mat, raiz);
		
		if(aux != null) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	public NodoRepartidor obtenerRepartidor(String mat, NodoRepartidor nodo) {

		if (nodo == null) {
			//this.recorridosBusqueda = recorridos;
			return nodo;
		}
		else
		{	
			if ( mat.compareTo(nodo.getMatricula()) < 0 ) {
				//recorridos++;
				return obtenerRepartidor(mat, nodo.getIzq());
			}
			else if ( mat.compareTo(nodo.getMatricula()) > 0 ){
				//recorridos++;
				return obtenerRepartidor(mat, nodo.getDer());
			}
			//recorridos++;
			//this.recorridosBusqueda = recorridos;
			return nodo;
		}
	}
	
    public int cantBusquedas(String matricula,NodoRepartidor nodo) {
    	int cont = 0;	
		if(nodo != null)
        {
			if (nodo.getMatricula() == matricula) {
				cont++;
				return cont;
			}
			cont += cantBusquedas(matricula,nodo.getIzq());
			cont++;
			if (nodo.getMatricula() == matricula) {
				cont++;
				return cont;
			}
			cont += cantBusquedas(matricula,nodo.getDer());
        }
		return cont;
	}
	
	public void insertarRepartidor(String mat,String nombre, NodoRepartidor nodo) {
		NodoRepartidor nuevo = null;

        if (this.esArbolRepartidorVacio())
            this.raiz = new NodoRepartidor(mat, nombre);

        else if( mat.compareTo(nodo.getMatricula()) < 0)
        {   // n < dato => insertaré en subarbol izq.
            if(nodo.getIzq() == null)
            {
                nuevo = new NodoRepartidor(mat, nombre);
                nodo.setIzq(nuevo);
             }
             else
                 insertarRepartidor(mat,nombre, nodo.getIzq());
        }
        else if( mat.compareTo(nodo.getMatricula()) > 0)
        {   // n > dato => insertaré en subarbol derecho
			if(nodo.getDer() == null)
            {
				nuevo = new NodoRepartidor(mat, nombre);
				nodo.setDer(nuevo);
			}
            else
				insertarRepartidor(mat,nombre, nodo.getDer());
		}
	}
	
    public String mostrarInOrder(){
    	return mostrarInOrder(this.raiz);
    }
    
    public String mostrarInOrder(NodoRepartidor a){
    	String resultado = "";	
    	if (a!=null){
        	resultado += mostrarInOrder(a.getDer());
        	resultado += a.getDato()+"|";
            resultado += mostrarInOrder(a.getIzq());
        }
    	return resultado;
    }
}
