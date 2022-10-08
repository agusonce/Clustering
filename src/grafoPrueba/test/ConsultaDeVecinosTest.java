package grafoPrueba.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import grafoPrueba.Grafo;
import grafoPrueba.IGrafo;


public class ConsultaDeVecinosTest
{	
	IGrafo grafo;
	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest()
	{	
		crearGrafo();
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest()
	{
		crearGrafo();
		grafo.vecinos(5);
	}

	@Test
	public void todosAisladosTest()
	{
		crearGrafo();
		Grafo grafo = new Grafo(5);
		assertEquals(0, grafo.vecinos(2).size());
	}
	
	@Test
	public void verticeUniversalTest()
	{
		crearGrafo();
		Grafo grafo = new Grafo(4);
		grafo.agregarArista(1, 0);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		
		int[] esperado = {0, 2, 3};
		Assert.iguales(esperado, grafo.vecinos(1));
	}
	
	@Test
	public void verticeNormalTest()
	{
		crearGrafo();
		Grafo grafo = new Grafo(5);
		grafo.agregarArista(1, 3);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 4);
		
		int[] esperados = {1, 2};
		Assert.iguales(esperados, grafo.vecinos(3));
	}
	private void crearGrafo() {
		grafo = new Grafo(5);
	}
}



