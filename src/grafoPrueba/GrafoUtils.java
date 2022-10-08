package grafoPrueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrafoUtils {
	public static GrafoPesoL.Arista getAristaPesada(GrafoPesoL g){
		return getAristaPesada(g, new ArrayList<GrafoPesoL>(), new ArrayList<GrafoPesoL>(), null );
	}
	
	
	private static GrafoPesoL.Arista getAristaPesada(GrafoPesoL grafo, List<GrafoPesoL> recorridos, List<GrafoPesoL> marcados, GrafoPesoL.Arista aristaMaxima) {
		marcados.remove(grafo);
		recorridos.add(grafo); 
		
		for (Map.Entry<Integer, GrafoPesoL.Arista> entry : grafo.vecinos().entrySet()) {
		    if (!marcados.contains(entry.getValue().getGrafo()) && !recorridos.contains(entry.getValue().getGrafo())){
		    	if(aristaMaxima == null) {
		    		aristaMaxima = entry.getValue();
		    	}else {
		    		if(aristaMaxima.getPeso() <= entry.getValue().getPeso()) {
		    			aristaMaxima = entry.getValue();
		    		}
		    	}
				marcados.add(entry.getValue().getGrafo());
			}
		}
		
		if(marcados.size()!=0) {
			aristaMaxima = getAristaPesada(marcados.get(0), recorridos, marcados, aristaMaxima);
		}
		return aristaMaxima;
	}
}
