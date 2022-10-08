package grafoPrueba;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AGM_test {

	@Test
	void agmFalse() {
		GrafoPesoL grafoRaiz = crearGrafo();
		assertFalse(AGM.iguales(grafoRaiz, new AGM().arbolGenerador(grafoRaiz)));
	}
	
	@Test
	void agmTrue() {
		GrafoPesoL grafoRaiz = crearArbolAGM();
		assertTrue(AGM.iguales(grafoRaiz, new AGM().arbolGenerador(grafoRaiz)));
		//AGM.print(new AGM().arbolGenerador(grafoRaiz));
	}
	
	private GrafoPesoL crearGrafo() {
		GrafoPesoL g0 = new GrafoPesoL();//1
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
		g0.agregarArista(g1,4);
		return g0;
	}
	private GrafoPesoL crearArbolAGM() {
		GrafoPesoL g0 = new GrafoPesoL();//1
		GrafoPesoL g1 = new GrafoPesoL();//1
		GrafoPesoL g2 = new GrafoPesoL();//2
		GrafoPesoL g3 = new GrafoPesoL();//3
		GrafoPesoL g4 = new GrafoPesoL();//4
		GrafoPesoL g5 = new GrafoPesoL();//5
		g1.agregarArista(g2,4);
		g1.agregarArista(g3,3);
		g2.agregarArista(g4,6);
		g4.agregarArista(g5,4);
		g0.agregarArista(g1,4);
		return g0;
	}
	
	@Test
	void removerAristaPesadaTrue() {
		GrafoPesoL grafoRaiz = crearArbolAGM();
		
		GrafoPesoL.Arista res=  GrafoUtils.getAristaPesada(grafoRaiz);
		System.out.println(res.getPeso());
		grafoRaiz.eliminarArista();
		assertTrue(AGM.iguales(grafoRaiz, new AGM().arbolGenerador(grafoRaiz)));
		//AGM.print(new AGM().arbolGenerador(grafoRaiz));
	}
	
}
