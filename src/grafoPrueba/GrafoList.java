package grafoPrueba;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class GrafoList{
	// Representamos el grafo por su matriz de adyacencia
	private List<GrafoList> vecinos;
	private Integer identificador;
	public static int count = 0;
	
	// La cantidad de vertices esta predeterminada desde el constructor
	public GrafoList()
	{
		identificador = count;
		count++;
		vecinos = new ArrayList<GrafoList>();
	}
	
	// Agregado de aristas
	public void agregarArista(GrafoList grafo)
	{
		if(grafo == this) {throw new IllegalArgumentException("no puede tenerse a si mismo");}
		
		if(!vecinos.contains(grafo))
			vecinos.add(grafo);
	}
	
	// Eliminacion de aristas
	public void eliminarArista(GrafoList grafo)
	{
		if(grafo == this) {throw new IllegalArgumentException("no puede eliminarse a si mismo");}

		vecinos.remove(grafo);
	}

	// Informa si existe la arista especificada
	public boolean existeArista(GrafoList grafo)
	{

		return vecinos.contains(grafo);
	}

	// Cantidad de vertices
	public int tamano()
	{
		return vecinos.size();
	}
	
	// Vecinos de un vertice
	public List<GrafoList> vecinos()
	{
		return vecinos;
	}
	



	public Integer getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}



}

