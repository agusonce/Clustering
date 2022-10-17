package clustering.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import clustering.Grafo;
import clustering.GrafoAlgoritmos;



class GrafoAlgoritmoTest {
	Grafo grafo;
	Grafo grafoAGM;
	

	/*
	 * caso de exitoso, AGM
	 */
	@Test
	void agmTrue() {
		crearGrafos();
		assertTrue(compararGrafo(grafoAGM, GrafoAlgoritmos.arbolGenerador(grafo)));
	}
	

	/*
	 * caso de Error, grafo unico
	 */
	@Test
	void agmCasoDeError() {
		Grafo g = new Grafo(1);
		assertTrue(GrafoAlgoritmos.arbolGenerador(g).tamano() == 1);
	}
	
	
	/*
	 * caso de Error, grafo vacio
	 */
	@Test
	void agmGrafoVacio() {
		Grafo g = new Grafo();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			GrafoAlgoritmos.arbolGenerador(g);
		});
	}
	
	
	/*
	 * caso normal, solo verifico que termine bien
	 */
	@Test
	void clastering() {
		crearGrafos();
		assertTrue( GrafoAlgoritmos.clastering(grafo,2).cantAristas() != 0);
	}
	
	
	/*
	 * caso error, paràmetro negativo
	 */
	@Test
	void clasteringSpltErroneo() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			crearGrafos();
			GrafoAlgoritmos.clastering(grafo,-2);
		});
		
	}	
	
	/*
	 * testea que no se pueda realizar un clasterin con el parámetro split mayor a la cantidad de aristas del grafo
	 */
	@Test
	void clasteringGrafoChico() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			crearGrafos();
			GrafoAlgoritmos.clastering(grafo,12);
		});
		
	}
	
	//clustering grafo no conexo
	
	private void crearGrafos() {
		//Grafo Grande
		this.grafo = new Grafo(1);//1
		grafo.agregarArista(2,3,4);
		grafo.agregarArista(1,2,2);
		grafo.agregarArista(2,4,5);
		grafo.agregarArista(3,1,4);
		grafo.agregarArista(6,4,3);
		grafo.agregarArista(7,3,4);
		grafo.agregarArista(3,6,6);
		grafo.agregarArista(6,7,9);
		grafo.agregarArista(6,5,6);
		grafo.agregarArista(7,9,6);
		grafo.agregarArista(5,9,4);

		//Grafo AGM de Grafo Grande
		
		this.grafoAGM = new Grafo(1);//1
		grafoAGM.agregarArista(2,3,4);//
		grafoAGM.agregarArista(1,2,2);//
		grafoAGM.agregarArista(2,4,5);//
		grafoAGM.agregarArista(3,6,6);//
		grafoAGM.agregarArista(6,7,9);//
		grafoAGM.agregarArista(6,5,6);//
		grafoAGM.agregarArista(5,9,4);//
		
	}
	
	
	public void mostrarGrafo(Grafo grafoRaiz) {
		for (Map.Entry<Integer,Map<Integer,Integer>> col : grafoRaiz.getGrafo().entrySet()){
			System.out.print(col.getKey() + ": {");
			for (Map.Entry<Integer,Integer> vecino : grafoRaiz.vecinos(col.getKey()).entrySet()){
				System.out.print(vecino.getKey() + "(" + vecino.getValue() + ")" );
			}
			System.out.println("}");
		}
	}
	
	public boolean compararGrafo(Grafo g1, Grafo g2) {
		if(g1.tamano() != g1.tamano() || g1.cantAristas()!= g2.cantAristas()) {
			return false;
		}
		for (Map.Entry<Integer,Map<Integer,Integer>> col : g1.getGrafo().entrySet()){
			for (Map.Entry<Integer,Integer> vecino : g1.vecinos(col.getKey()).entrySet()){
				if(!g2.existeArista(col.getKey(), vecino.getKey())) {
					return false;
				}
			}
			
		}
		return true;
	}


}
