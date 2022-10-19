package clustering.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import clustering.backend.Grafo;

class GrafoTest {

	@Test
	@Order(1)
	void agregarAarista() {
		Grafo g = new Grafo(1);
 		g.agregarArista(1, 2, 3);
		assertTrue((g.getPesoArista(1, 2) == 3)?true:false);
	}
	
	@Test
	@Order(2)
	void generarCiclo() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(1);
			g.agregarArista(1, 1, 3);
		});
	}

	@Test
	void aristaNegativa() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Grafo g = new Grafo(1);
			g.agregarArista(-1, 1, 3);
		});
		
	}
	
	
	@Test
	@Order(4)
	void existeArista() {
		Grafo g = new Grafo(1);
		g.agregarArista(1, 2, 3);
		assertTrue(g.existeArista(1, 2));
	}
	
	@Test
	@Order(5)
	void eliminararista() {
			Grafo g = new Grafo(1);
			g.agregarArista(1, 2, 3);
			g.eliminarArista(1,2);
	}
	

	
	
	@Test
	void tamano() {
		Grafo g = new Grafo(1);
		g.agregarArista(1, 2, 3);
		g.agregarArista(3, 4, 3);
		g.agregarArista(5, 6, 3);
		assertTrue(g.tamano() == 6);
	}
	
	@Test
	void cantAristas() {
		Grafo g = new Grafo(1);
		g.agregarArista(1, 2, 3);
		g.agregarArista(3, 4, 3);
		g.agregarArista(5, 6, 3);
		assertTrue(g.cantAristas() == 3);
	}
	
	@Test
	void vecinosEnMatriz() {
		Grafo g = new Grafo(1);
		g.agregarArista(1, 2, 3);
		g.agregarArista(3, 4, 3);
		g.agregarArista(3, 6, 3);
		g.agregarArista(5, 6, 3);
		assertTrue(g.vecinos(3).size() == 2);
	}
}
