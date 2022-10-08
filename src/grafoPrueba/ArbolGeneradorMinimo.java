package grafoPrueba;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



//////////////////////////////////////
// 				HashMap 			//
// HashMap.getValue/Key => O(1) 	//
// HashMap.put => O(1)				//
//////////////////////////////////////

public class ArbolGeneradorMinimo {
	private GrafoList grafo = new GrafoList();
	private GrafoPesoL grafoP = new GrafoPesoL();
	public ArbolGeneradorMinimo () {
//		generarGrafo();
//		mostrarGrafo(grafo, new ArrayList<GrafoList>(), new ArrayList<GrafoList>());
		generarGrafoPeso();
		printBFS(grafoP);
		System.out.println("---------------------- ");
		printBFS(arbolGenerador(grafoP));
		
		
	}
	private void generarGrafo() {
		GrafoList g1 = new GrafoList();//1
		GrafoList g2 = new GrafoList();//2
		GrafoList g3 = new GrafoList();//3
		GrafoList g4 = new GrafoList();//4
		GrafoList g5 = new GrafoList();//5
		g1.agregarArista(g2);
		g1.agregarArista(g3);
		g2.agregarArista(g4);
		g4.agregarArista(g5);
		g3.agregarArista(g5);
		g5.agregarArista(g3);
		
		this.grafo.agregarArista(g1);
	}

	
	private void mostrarGrafo(GrafoList grafo, List<GrafoList> recorridos, List<GrafoList> marcados) {
		marcados.remove(grafo);
		recorridos.add(grafo);
		System.out.print(grafo.getIdentificador()+ " : ");
		for(GrafoList gr : grafo.vecinos()) {
			if (!marcados.contains(gr) && !recorridos.contains(gr)){
				marcados.add(gr);
			}
			System.out.print(gr.getIdentificador()+",");
		}
		System.out.println(";");
		if(marcados.size()!=0) {
				mostrarGrafo(marcados.get(0), recorridos, marcados);
		}

	}
	
//////////////////////////////////////////////////////////////////
/////				  Grafo Con Peso						//////	
//////////////////////////////////////////////////////////////////
		private void generarGrafoPeso() {
		GrafoPesoL g1 = new GrafoPesoL();//1
		GrafoPesoL g2 = new GrafoPesoL();//2
		GrafoPesoL g3 = new GrafoPesoL();//3
		GrafoPesoL g4 = new GrafoPesoL();//4
		GrafoPesoL g5 = new GrafoPesoL();//5
		g1.agregarArista(g2,4);
		g1.agregarArista(g3,3);
		g2.agregarArista(g4,6);
		g4.agregarArista(g5,4);
		g3.agregarArista(g5,7);
		g5.agregarArista(g3,2);
		this.grafoP.agregarArista(g1,4);
	}
		
	private void printBFS(GrafoPesoL grafo) {
		printBFS(grafo, new ArrayList<GrafoPesoL>(), new ArrayList<GrafoPesoL>());
	}
	private void printBFS(GrafoPesoL grafo, List<GrafoPesoL> recorridos, List<GrafoPesoL> marcados) {
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
	
	Map<Integer,GrafoPesoL> agm = new HashMap<Integer,GrafoPesoL>() ;
	int iterador;
	public GrafoPesoL arbolGenerador(GrafoPesoL grafoPeso){
		
		int cantVertices = BFS.bfs(grafoPeso).size();
		Map<Integer,GrafoPesoL> amarillos;
		GrafoPesoL.Arista aristaMinima;
	
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
	public static void main(String[] args) {
		new ArbolGeneradorMinimo();
	}
}
