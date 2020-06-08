package uy.edu.ort.obli;

public class ArbolRepartidor {
	protected NodoRepartidor raiz;
	protected int recorridosBusqueda; 
	
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
		this.recorridosBusqueda = 0;
		if (nodo == null) {
			return nodo;
		}
		else{
			if ( mat == nodo.getMatricula()) {
				return nodo;
			}
			else if ( Integer.parseInt(mat) < Integer.parseInt(nodo.getMatricula())) {
				this.recorridosBusqueda ++;
				return obtenerRepartidor(mat, nodo.getIzq());
			}
			else {
				this.recorridosBusqueda ++;
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
	
    public void mostrarPreOrder(){
    	mostrarPreOrder(this.raiz);
    }
    
    public void mostrarPreOrder(NodoRepartidor a){
        if (a!=null){
            System.out.print(a.getDato()+"|");
            mostrarPreOrder(a.getIzq());
            mostrarPreOrder(a.getDer());
        }
    }
}
