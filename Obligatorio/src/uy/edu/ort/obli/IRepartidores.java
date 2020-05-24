package uy.edu.ort.obli;

public interface IRepartidores {
	//Pos: Constructor, crea la lista vacía.
	void Repartidores();
	//Pos: Inserta el objeto n al principio de la lista.
	void insertarInicio(String mat, String nombre);
	//Pre: La lista no es vacía.
	//Pos: Retorna el primer Objeto de la lista.
	String head();
	//Pos: Retorna true sii la lista no tiene nodos.
	boolean esVacia();
	//Pre: La lista no es vacía.
	//Pos: Retorna la lista resultante de remover el head de la lista.
	Repartidor tail();
	//Pos: Imprime la lista en pantalla.
	void imprimir();
	//Pos: Borra el primer nodo.
	void borrarInicio();
	//Pos: Borra todos los nodos de la lista.
	void vaciarLista();
}
