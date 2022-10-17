package clustering;

import java.util.HashMap;
import java.util.Map;


public class Grafo {
	private Map<Integer,Map<Integer,Integer>> G;
	private Integer cantAristas = 0;
	private Integer identificador; //vertice
	public static int count = 1;
	
	/* junto  a la variable Identificador son ideas sueltas que no tiene sentido en esta implementacion,
	porfavor ignorla*/
	
	public Grafo()
	{
		G = new HashMap<Integer,Map<Integer,Integer>>();
	}
	public Grafo(Integer identificador)
	{
		this.identificador = identificador;
		G = new HashMap<Integer,Map<Integer,Integer>>();
		G.put(identificador, new HashMap<Integer, Integer>());
	}
	
	public Map<Integer,Map<Integer,Integer>> getGrafo(){
		return G;
	}
	// Agregado de aristas
	public void agregarArista(Integer x, Integer i, Integer peso)
	{
		//validacion
		validarCiclo(x, i);
		validarNegativos(x, i);
		
		if(G.get(i)==null) {
			G.put(i, new HashMap<Integer, Integer>());
		}
		if(G.get(x) == null) {
			G.put(x, new HashMap<Integer, Integer>());
		}
		if(G.get(x).get(i) == null) {
			G.get(x).put(i, peso);
			cantAristas++;
		}
	}
	
	
	// Eliminacion de aristas
	public void eliminarArista(int x, int i)
	{
		if(x == i) {throw new IllegalArgumentException("no puede eliminarse a si mismo");}

		if(G.get(x).get(i) != null){
			G.get(x).remove(i);
			cantAristas++;
		}
			
	}

	// Eliminacion de aristas
	public void eliminarArista(Arista a)
	{
		if(a.obtenerVertice1() == a.obtenerVertice2()) {throw new IllegalArgumentException("no puede eliminarse a si mismo");}

		if(G.get(a.obtenerVertice1()).get(a.obtenerVertice2()) != null)
			G.get(a.obtenerVertice1()).remove(a.obtenerVertice2());
	}
	// Informa si existe la arista especificada
	public boolean existeArista(int x, int i)
	{
		if (G.get(x) == null)
			return false;
		if(G.get(x).get(i)!= null)
			return true;
		else
			return false;
	}

	// Informa si existe la arista especificada
	public int getPesoArista(int x, int i)
	{
		if (G.get(x) == null || (G.get(x).get(i)== null))
			throw new IllegalArgumentException("no exite arista");
			
			return G.get(x).get(i);
	}
	// Cantidad de vertices
	public int tamano()
	{
		return G.size();
	}

	public int cantAristas()
	{
		return this.cantAristas;
	}
	
	// Vecinos de un vertice
	public Map<Integer,Integer> vecinos()
	{
		return G.get(identificador);
	}
	public Map<Integer,Integer> vecinos(Integer x)
	{
		return G.get(x);
	}
	public Integer getIdentificador() {
		return identificador;
	}

	private void validarCiclo(int x, int i) {
		if(x == i)
			throw new IllegalArgumentException("no se permite loop: " + i);
	}
	
	private void validarNegativos(int x, int i) {
		if(x < 0 || i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
	}



}
