package clustering;

import java.util.HashMap;
import java.util.Map;


public class Grafo {
	private Map<Integer,Map<Integer,Integer>> G;
	private Integer vertice;
	private Integer identificador; //vertice
	public static int count = 0;
	
	// La cantidad de vertices esta predeterminada desde el constructor
	public Grafo()
	{
		identificador = count;
		count++;
		G = new HashMap<Integer,Map<Integer,Integer>>();
		G.put(identificador, new HashMap<Integer, Integer>());
		
	}
	private Grafo(Integer identificador)
	{
		this.identificador = identificador;
		G = new HashMap<Integer,Map<Integer,Integer>>();
		G.put(identificador, new HashMap<Integer, Integer>());
	}
	
	// Agregado de aristas
	public void agregarArista(int x, int i, Integer peso)
	{
		//validacion
		
		if(G.get(x) == null) {
			G.put(x, new HashMap<Integer, Integer>(i , peso));
		}else {
			G.get(x).put(i, peso);
		}
	}
	
	
	// Eliminacion de aristas
	public void eliminarArista(int x, int i)
	{
		//if(grafo == this) {throw new IllegalArgumentException("no puede eliminarse a si mismo");}

		if(G.get(x).get(i) != null)
			G.get(x).remove(i);
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

	// Cantidad de vertices
	public int tamano()
	{
		return G.size();
	}
	
	// Vecinos de un vertice
	public Map<Integer,Integer> vecinos()
	{
		return G.get(identificador);
	}
	
	public Integer getIdentificador() {
		return identificador;
	}

	

public class Arista implements Comparable<Arista>
{
	private int vertice1;
	private int vertice2;
	private int peso;
	
	public Arista(int vertice1, int vertice2, int peso)
	{
		if (vertice1!=vertice2)
		{
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
		this.peso = peso;
		}
		else
		{
			throw new IllegalArgumentException("No puede haber ciclos");
		}
	}
	

	public int obtenerVertice1()
	{
		return this.vertice1;
	}
	
	public int obtenerVertice2()
	{
		return this.vertice2;
	}
	
	public int obtenerPeso()
	{
		return this.peso;
	}
	
	
	
	@Override
	public int compareTo(Arista o) {

        int resultado=0;

        if (this.peso>o.peso) 
        {   
        	resultado = -1;
        }

        else if (this.peso<o.peso) 
        {
        	resultado = 1;
        }
		return resultado;       
	}
	
	@Override
	public boolean equals (Object o) {

        if (o instanceof Arista) {

            Arista arista= (Arista) o;

            if (arista.obtenerVertice1()==this.obtenerVertice1() &&
            	arista.obtenerVertice2()==this.obtenerVertice2() && arista.obtenerPeso()==this.obtenerPeso()) 
            	{
            		return true;
            	}
           
            
        }
        return false;
	}
	
	public String toString()
	{
		return this.obtenerVertice1()+", "+this.obtenerVertice2()+", "+this.obtenerPeso();
	}
}

}
