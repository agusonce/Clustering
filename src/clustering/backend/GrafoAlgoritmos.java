package clustering.backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class GrafoAlgoritmos {
	
	public static Grafo arbolGenerador(Grafo grafo) {
		AGM agm = new AGM();
		return agm.arbolGenerador(grafo);
	}
	
	public static Grafo clastering (Grafo g,int split) {
		
		if (split < 0)
			throw new IllegalArgumentException("paràmetro split negativo" );
		if (!BFS.esConexo(g)) 
			throw new IllegalArgumentException("el grafo no es conexo!" );
		

		
		Grafo agm = arbolGenerador(g);
		if(agm.cantAristas() <= split)
			throw new IllegalArgumentException("el grafo no tiene la cantidad de aristas para continuar" );
		
		for(int x = 1; x <= split; x++) {
			Arista a = AGM.obtenerAristaPesada(agm);
			System.out.println("se removera la arista:" + a.toString());
			
			agm.eliminarArista(AGM.obtenerAristaPesada(agm));
		}
		return agm;
		
	} 
	
	
	//Best forcast search (BFS)
	public static class BFS 
	{
		
		public static boolean esConexo(Grafo g) 
		{
			if (g==null)
				throw new IllegalArgumentException("Se intento consultar con un grafo null!" );
			
			if (g.tamano() == 0)
				return true;
			
			return alcanzables(g).size() == g.tamano();
		}

		public static Set<Integer> alcanzables(Grafo g)
		{
			Set<Integer> marcados = new HashSet<Integer>();
			ArrayList<Integer> pendientes= new ArrayList<Integer>();
			pendientes.add(g.getIdentificador());
			
			while (pendientes.size() != 0)
			{
				int actual = pendientes.get(0);
				marcados.add(actual);
				pendientes.remove(0);
				
				for ( Map.Entry<Integer, Integer> v : g.vecinos(actual).entrySet() ) {
					if(marcados.contains(v.getKey()) == false)
						pendientes.add(v.getKey());
				}

							
			}
			
			return marcados;
		}
	}
	
	
	
	//Arbol generador minimo(AGM)
	public static class AGM {
		Grafo agm;
		Set<Integer> amarillos;
		Arista aristaMinima;
		
		int iterador;
		public Grafo arbolGenerador(Grafo grafo)
		{
			if(grafo.tamano() == 0)
				throw new IllegalArgumentException("La cantidad de vertices tiene que ser mayor a 0");
			if(grafo.tamano() == 1)
				return grafo;
			agm= new Grafo(1);
			amarillos=new HashSet<Integer>();
			amarillos.add(grafo.getIdentificador());
			iterador=1;

			while (amarillos.size() < grafo.tamano()) {
				Arista aristaLiviana= obtenerAristaLiviana(grafo, amarillos);
				if(aristaLiviana != null) {
					agm.agregarArista(aristaLiviana.obtenerVertice1(), aristaLiviana.obtenerVertice2(), aristaLiviana.obtenerPeso());
					amarillos.add(aristaLiviana.obtenerVertice2());
				}
				
				iterador++;
			}
			
			return agm;
		}

		private Arista obtenerAristaLiviana(Grafo grafo, Set<Integer> amarillos) {
			int pesoMinimo=-1;
			Arista aristaLiviana=null;
			
			for (Integer a: amarillos) {
				if(grafo.vecinos(a) != null) {
					for (Map.Entry<Integer,Integer> col : grafo.vecinos(a).entrySet()) {
						if (!amarillos.contains(col.getKey())) {
							if(pesoMinimo==-1 || aristaLiviana.obtenerPeso() >= grafo.getPesoArista(a, col.getKey()))
							{
								pesoMinimo=grafo.getPesoArista(a, col.getKey());
								aristaLiviana = new Arista(a,col.getKey(),pesoMinimo);
							}
						}
					}
				}
			}
			
			return aristaLiviana;
		}
		
		public static Arista obtenerAristaPesada(Grafo grafo) {
			int pesoMinimo=-1;
			Arista aristaPesada=null;
			
			for (Map.Entry<Integer,Map<Integer,Integer>> a : grafo.getGrafo().entrySet()) {
				if(grafo.vecinos(a.getKey()) != null) {
					for (Map.Entry<Integer,Integer> col : grafo.vecinos(a.getKey()).entrySet()) {
						if(pesoMinimo==-1 || aristaPesada.obtenerPeso() <= grafo.getPesoArista(a.getKey(), col.getKey()))
						{
							pesoMinimo=grafo.getPesoArista(a.getKey(), col.getKey());
							aristaPesada = new Arista(a.getKey(),col.getKey(),pesoMinimo);
						}
					}
				}
			}
			
			return aristaPesada;
		}
	}

	
}
