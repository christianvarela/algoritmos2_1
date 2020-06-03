package uy.edu.ort.obli;

public class ArbolRepartidor {
	private NodoRepartidor raiz;
	
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
		} else {
			return false;
		}
	}
	public NodoRepartidor obtenerRepartidor (String mat, NodoRepartidor nodo) {
		if (nodo == null) {
			return nodo;
		}
		else{
			if ( mat == nodo.getMatricula()) {
				return nodo;
			}
			else if ( Integer.parseInt(mat) < Integer.parseInt(nodo.getMatricula())) {
				return obtenerRepartidor(mat, nodo.getIzq());
			}
			else {
				return obtenerRepartidor(mat, nodo.getDer());
			}
		}
	}
	public void insertarRepartidor(String mat,String nombre, NodoRepartidor nodo) {
		NodoRepartidor nuevo = null;

        if (this.esArbolRepartidorVacio())
            this.raiz = new NodoRepartidor(mat, nombre);

        else if( Integer.parseInt(mat) < Integer.parseInt(nodo.getMatricula()))
        {   // n < dato => insertaré en subárbol izq.
            if(nodo.getIzq() == null)
            {
                nuevo = new NodoRepartidor(mat, nombre);
                nodo.setIzq(nuevo);
             }
             else
                 insertarRepartidor(mat,nombre, nodo.getIzq());
        }
        else if( Integer.parseInt(mat) < Integer.parseInt(nodo.getMatricula()))
        {   // n > dato => insertaré en subárbol derecho
			if(nodo.getDer() == null)
            {
				nuevo = new NodoRepartidor(mat, nombre);
				nodo.setDer(nuevo);
			}
            else
				insertarRepartidor(mat,nombre, nodo.getDer());
		}
	}
}
