package grafoPrueba.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import grafoPrueba.Grafo;
import grafoPrueba.GrafoList;
import grafoPrueba.IGrafo;


public class EdicionDeAristaslistTest2 {
	GrafoList grafo;
	
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest()
	{
		crearGrafo();
		grafo.agregarArista(grafo);
	}

	@Test
	public void aristaExistenteTest()
	{
		crearGrafo();
		GrafoList vecino = new GrafoList(); 
		grafo.agregarArista(vecino);
		assertTrue( grafo.existeArista(vecino) );
	}

	@Test
	public void aristaOpuestaTest()
	{
		crearGrafo();
		GrafoList vecino = new GrafoList(); 
		grafo.agregarArista(vecino);
		assertTrue( vecino.existeArista(grafo) );
	}

	@Test
	public void aristaInexistenteTest()
	{
		crearGrafo();
		GrafoList vecino = new GrafoList(); 
		grafo.agregarArista(vecino);
		assertFalse( grafo.existeArista(new GrafoList()) );
	}

	@Test
	public void agregarAristaDosVecesTest()
	{
		crearGrafo();
		GrafoList vecino = new GrafoList(); 
		grafo.agregarArista(vecino);
		grafo.agregarArista(vecino);

		assertTrue( grafo.existeArista(vecino) );
	}
	
	@Test
	public void eliminarAristaExistenteTest()
	{
		crearGrafo();
		GrafoList vecino = new GrafoList(); 
		grafo.agregarArista(vecino);
		
		grafo.eliminarArista(vecino);
		assertFalse( grafo.existeArista(vecino) );
	}

	@Test
	public void eliminarAristaInexistenteTest()
	{
		crearGrafo();
		GrafoList vecino = new GrafoList();
		grafo.eliminarArista(vecino);
		assertFalse( grafo.existeArista(new GrafoList()) );
	}
	
	@Test
	public void eliminarAristaDosVecesTest()
	{
		crearGrafo();
		GrafoList vecino = new GrafoList();
		grafo.agregarArista(vecino);
		
		grafo.eliminarArista(vecino);
		grafo.eliminarArista(vecino);
		assertFalse( grafo.existeArista(vecino) );
	}
	private void crearGrafo() {
		grafo = new GrafoList();
	}
}
