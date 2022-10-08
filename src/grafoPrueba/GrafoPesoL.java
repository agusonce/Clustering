package grafoPrueba;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class GrafoPesoL {
		private Map<Integer,Arista> vecinos;
		private Integer identificador;
		public static int count = 0;
		
		// La cantidad de vertices esta predeterminada desde el constructor
		public GrafoPesoL()
		{
			identificador = count;
			count++;
			vecinos = new HashMap<Integer,Arista>();
		}
		private GrafoPesoL(Integer identificador)
		{
			this.identificador = identificador;
			vecinos = new HashMap<Integer,Arista>();
		}
		
		// Agregado de aristas
		public void agregarArista(GrafoPesoL grafo, Integer peso)
		{
			if(grafo == this) {throw new IllegalArgumentException("no puede tenerse a si mismo");}
			
			if(!vecinos.containsKey(grafo.getIdentificador()))
				
				vecinos.put(grafo.identificador,new Arista(peso,grafo));
		}
		
		
		// Eliminacion de aristas
		public void eliminarArista(GrafoPesoL grafo)
		{
			if(grafo == this) {throw new IllegalArgumentException("no puede eliminarse a si mismo");}

			vecinos.remove(grafo);
		}

		public void eliminarAristaG(GrafoPesoL origen, GrafoPesoL.Arista arista )
		{
			if(origen == this) {
				for (Map.Entry<Integer,Arista> entry : this.vecinos.entrySet()) {
					if(entry.getValue().equals(arista)) {this.vecinos.remove(arista);}
				}
			}else {
				
			}

			vecinos.remove(grafo);
		}
		// Informa si existe la arista especificada
		public boolean existeArista(GrafoPesoL grafo)
		{

			return vecinos.containsKey(grafo.getIdentificador());
		}

		// Cantidad de vertices
		public int tamano()
		{
			return vecinos.size();
		}
		
		// Vecinos de un vertice
		public Map<Integer,Arista> vecinos()
		{
			return vecinos;
		}
		
		public Integer getIdentificador() {
			return identificador;
		}

		public void setIdentificador(Integer identificador) {
			this.identificador = identificador;
		}
		
		
	public static GrafoPesoL copy(GrafoPesoL g) {
		if(g == null) {
			throw new IllegalArgumentException("grafo null");
		}
		return new GrafoPesoL(g.getIdentificador());
	}
		
	public class Arista implements Comparable<Arista>{
		private Integer peso;
		private GrafoPesoL grafo;
		public Arista(int peso,GrafoPesoL grafo){
			super();
			this.peso = peso;
			this.grafo = grafo;
		}
		
		public Integer getPeso() {
			return this.peso;
		}
		public GrafoPesoL getGrafo() {
			return grafo;
		}
		
		@Override
		public int compareTo(GrafoPesoL.Arista o) {

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
		
		public boolean equals(GrafoPesoL.Arista o) {
			
			if(o == null || o.getGrafo() == null) {throw new IllegalArgumentException("parametro null");}
			
			if (o.getGrafo().getIdentificador() == this.grafo.getIdentificador() 
				&& o.peso == this.peso) {
				return true;
			}
			return false;
		}
	}
	
	public boolean compareTo(GrafoPesoL g) {
		return g.getIdentificador() == this.identificador;
	}
	
	
}
