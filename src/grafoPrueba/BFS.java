package grafoPrueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BFS {
	
	public static List<GrafoPesoL> bfs(GrafoPesoL grafo) {
		return bfs(grafo, new ArrayList<GrafoPesoL>(), new ArrayList<GrafoPesoL>());
	}
	
	private static List<GrafoPesoL> bfs(GrafoPesoL grafo, List<GrafoPesoL> recorridos, List<GrafoPesoL> marcados) {
		marcados.remove(grafo);
		recorridos.add(grafo); 
		
		for (Map.Entry<Integer, GrafoPesoL.Arista> entry : grafo.vecinos().entrySet()) {
		    if (!marcados.contains(entry.getValue().getGrafo()) && !recorridos.contains(entry.getValue().getGrafo())){
				marcados.add(entry.getValue().getGrafo());
			}
		}
		
		if(marcados.size()!=0) {
			bfs(marcados.get(0), recorridos, marcados);
		}
		return recorridos;
	}

	
	public static boolean esConexo(GrafoPesoL g, int cantGrafos) {
		if (g==null)
			throw new IllegalArgumentException("Se intento consultar con un grafo null!" );
		
		if (g.tamano() == 0)
			return true;
		
		return bfs(g).size() == cantGrafos;
	}
}
