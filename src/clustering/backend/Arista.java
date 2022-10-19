package clustering.backend;

import clustering.backend.Arista;

public class Arista implements Comparable<Arista>
{
	private int vertice1;
	private int vertice2;
	private int peso;
	
	public Arista(int vertice1, int vertice2, int peso)
	{
		if (vertice1!=vertice2)
		{
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
		this.peso = peso;
		}
		else
		{
			throw new IllegalArgumentException("No puede haber ciclos");
		}
	}
	

	public int obtenerVertice1()
	{
		return this.vertice1;
	}
	
	public int obtenerVertice2()
	{
		return this.vertice2;
	}
	
	public int obtenerPeso()
	{
		return this.peso;
	}
	
	
	
	@Override
	public int compareTo(Arista o) {

        int resultado=0;

        if (this.peso>o.peso) 
        {   
        	resultado = -1;
        }

        else if (this.peso<o.peso) 
        {
        	resultado = 1;
        }
		return resultado;       
	}
	
	@Override
	public boolean equals (Object o) {

        if (o instanceof Arista) {

            Arista arista= (Arista) o;

            if (arista.obtenerVertice1()==this.obtenerVertice1() &&
            	arista.obtenerVertice2()==this.obtenerVertice2() && arista.obtenerPeso()==this.obtenerPeso()) 
            	{
            		return true;
            	}
           
            
        }
        return false;
	}
	
	public String toString()
	{
		return this.obtenerVertice1()+", "+this.obtenerVertice2()+", "+this.obtenerPeso();
	}
}
