package grafoPrueba;

import java.util.Set;

public interface IGrafo {
	/*** ***/
	// Agregado de aristas
	public void agregarArista(int i, int j);
	
	// Eliminacion de aristas
	public void eliminarArista(int i, int j);

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j);

	// Cantidad de vertices
	public int tamano();
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i);
	
}
