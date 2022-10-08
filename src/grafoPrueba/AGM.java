package grafoPrueba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AGM {
	Map<Integer,GrafoPesoL> agm = new HashMap<Integer,GrafoPesoL>() ;
	int iterador;
	 
	public AGM () {
	}
	public GrafoPesoL arbolGenerador(GrafoPesoL grafoPeso){
		
		int cantVertices = BFS.bfs(grafoPeso).size();
		Map<Integer,GrafoPesoL> amarillos;
	
		if(grafoPeso.tamano()==0)
			throw new IllegalArgumentException("La cantidad de vertices tiene que ser mayor a 0");
		if(cantVertices==1)
			return grafoPeso;
		
		agm.put(grafoPeso.getIdentificador(), GrafoPesoL.copy(grafoPeso));
		amarillos=new HashMap<Integer,GrafoPesoL>();
		amarillos.put(grafoPeso.getIdentificador(), grafoPeso);
		iterador=1;
		
		while (this.iterador < cantVertices) {
			GrafoPesoL.Arista aristaLiviana= obtenerAristaLiviana(grafoPeso, amarillos);
			amarillos.put(aristaLiviana.getGrafo().getIdentificador(),aristaLiviana.getGrafo());
			iterador++;
		}
		return agm.get(grafoPeso.getIdentificador());
	}

	private GrafoPesoL.Arista obtenerAristaLiviana(GrafoPesoL grafoPeso, Map<Integer,GrafoPesoL> amarillos) {
		int pesoMinimo=-1;
		GrafoPesoL.Arista aristaLiviana=null;
		GrafoPesoL grafoActual = null;
		for (Map.Entry<Integer, GrafoPesoL> gAmarillo : amarillos.entrySet()) {//recorro amarillo(seleccionado)
			for (Map.Entry<Integer, GrafoPesoL.Arista> vAmarillo : gAmarillo.getValue().vecinos().entrySet()) {//recorro vecino de amarillo
				if(
					(pesoMinimo==-1 || aristaLiviana.getPeso() >= vAmarillo.getValue().getPeso())
					&& (!amarillos.containsKey(vAmarillo.getKey()))
				){ 
					grafoActual = gAmarillo.getValue();
					pesoMinimo=vAmarillo.getValue().getPeso(); //obtener peso arista parado (vecino de maarillo actual))
					aristaLiviana = vAmarillo.getValue(); // copio la arista : direccion del grafo y su peso
				}
			}
		}
		copiarGrafoArista(grafoActual, aristaLiviana);
		return aristaLiviana;
	}
	
	private void copiarGrafoArista(GrafoPesoL grafoActual, GrafoPesoL.Arista aristaLiviana) {
		if (agm.get(grafoActual.getIdentificador())== null){
			agm.put(grafoActual.getIdentificador(),GrafoPesoL.copy(grafoActual));
		}
		
		if (agm.get(aristaLiviana.getGrafo().getIdentificador())== null){
			agm.put(aristaLiviana.getGrafo().getIdentificador(), GrafoPesoL.copy(aristaLiviana.getGrafo()));
		}
		agm.get(grafoActual.getIdentificador()).agregarArista(agm.get(aristaLiviana.getGrafo().getIdentificador()), aristaLiviana.getPeso());
	}
	
	public static void print(GrafoPesoL grafo) {
		printBFS(grafo, new ArrayList<GrafoPesoL>(), new ArrayList<GrafoPesoL>());
	}
	private static void printBFS(GrafoPesoL grafo, List<GrafoPesoL> recorridos, List<GrafoPesoL> marcados) {
		marcados.remove(grafo);
		recorridos.add(grafo); 
		System.out.print(grafo.getIdentificador()+ " : ");
		
		for (Map.Entry<Integer, GrafoPesoL.Arista> entry : grafo.vecinos().entrySet()) {
		    if (!marcados.contains(entry.getValue().getGrafo()) && !recorridos.contains(entry.getValue().getGrafo())){
				marcados.add(entry.getValue().getGrafo());
			}
			System.out.print(entry.getValue().getGrafo().getIdentificador()+"("+entry.getValue().getPeso()+"),");
		}
		System.out.println(";");
		if(marcados.size()!=0) {
			printBFS(marcados.get(0), recorridos, marcados);
		}

	}

	private static Map<Integer,List<String>> toArray(GrafoPesoL g) {
		return toArray(g, new ArrayList<GrafoPesoL>(), new ArrayList<GrafoPesoL>(), new HashMap<Integer,List<String>>());
	}

	private static Map<Integer,List<String>> toArray(GrafoPesoL grafo, List<GrafoPesoL> recorridos, List<GrafoPesoL> marcados,Map<Integer,List<String>> map) {
		marcados.remove(grafo);
		recorridos.add(grafo); 
		map.put(grafo.getIdentificador(), new ArrayList());
		for (Map.Entry<Integer, GrafoPesoL.Arista> entry : grafo.vecinos().entrySet()) {
		    if (!marcados.contains(entry.getValue().getGrafo()) && !recorridos.contains(entry.getValue().getGrafo())){
				marcados.add(entry.getValue().getGrafo());
			}
		    map.get(grafo.getIdentificador()).add(entry.getValue().getGrafo().getIdentificador()+"("+entry.getValue().getPeso()+")");

		}
		if(marcados.size()!=0) {
			toArray(marcados.get(0), recorridos, marcados, map);
		}
		return map;
	}
	public static boolean iguales(GrafoPesoL g1, GrafoPesoL g2) {
		Map<Integer,List<String>> resG1= toArray(g1);
		Map<Integer,List<String>> resG2= toArray(g2);
		for (Map.Entry<Integer, List<String>> entry : resG1.entrySet()) {
			if(entry.getValue().size() > 1) {
				for(String arista : entry.getValue()) {
					if(!resG2.get(entry.getKey()).contains(arista)) 
						return false;
				}
			}else {
				if(!resG2.get(entry.getKey()).equals(entry.getValue()))
					return false;
			}
		}
		return true;
	}
}
