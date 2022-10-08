package grafoPrueba.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class Assert {
	// Verifica que sean iguales como conjuntos
		public static void iguales(int[] esperado, Set<Integer> obtenido)
		{
			assertEquals(esperado.length, obtenido.size());
			
			for(int i=0; i<esperado.length; ++i)
				assertTrue( obtenido.contains(esperado[i]) );
		}


}
