package model.data_structures;

public class Cola<E extends Comparable<E>> implements Comparable<Cola>
{
	private static final int CAPACIDAD = 1000000;
	
	private int p;
	
	private int n;
	
	private E[] elementos;
	
	public Cola()
	{
		elementos = (E[]) new Comparable[CAPACIDAD];
		p=0;
		n=0;
	}
	
	public E[] getElementos() {
		return elementos;
	}
	
	public int getN() {
		return n;
	}
	
	public int getP() {
		return p;
	}
	
	public static int getCapacidad() {
		return CAPACIDAD;
	}
	
	public void setElementos(E[] elementos) {
		this.elementos = elementos;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public void setP(int p) {
		this.p = p;
	}
	
	public boolean esVacia()
	{
		return n==0;
	}
	
	public int darTamano()
	{
		return n;
	}
	
	public void agregar(E elemento) throws Exception
	{
		if(n==elementos.length)
		{
			throw new Exception("La lista está llena");
		}
		
		int pos = (p+n) % elementos.length;
		
		elementos[pos] = elemento;
		n++;
	}
	
	public E eliminar()
	{
		if(esVacia())
		{
			return null;
		}
		E resp = elementos[p];
		elementos[p] = null;
		p = (p+1) % elementos.length;
		n --;
		return resp;
	}
	
	public E buscar(E elemento)
	{
		if(esVacia())
		{
			return null;
		}
		else
		{
			for(int i=p ; i<n ; i++)
			{
				if(elementos[i].compareTo(elemento)==0)
				{
					return elementos[i];
				}
			}
			return null;
		}
	}
	
	public E darPrimero()
	{
		return elementos[p];
	}

	@Override
	public int compareTo(Cola arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
