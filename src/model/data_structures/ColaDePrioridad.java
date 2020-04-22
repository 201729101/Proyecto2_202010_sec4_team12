package model.data_structures;

public class ColaDePrioridad<E extends Comparable<E>>
{
	private E[] arreglo;
	
	private int N = 0;
	
	public ColaDePrioridad() 
	{
		arreglo = (E[]) new Comparable[N+1]; 
	}
	
	public ColaDePrioridad(int m) 
	{
		arreglo = (E[]) new Comparable[m+1]; 
	}
	
	public void insertar(E elem)
	{
		arreglo[++N] = elem;
		swim(N);

	}
	
	public E eliminarMax()
	{
		E max = arreglo[1];           // Retrieve max key from top.      
		exch(1, N--);              // Exchange with last item.      
		arreglo[N+1] = null;            // Avoid loitering.      
		sink(1);                   // Restore heap property.      
		return max;
	}
	
	public boolean esVacia()
	{
		return N==0;
	}
	
	public int tamano()
	{
		return N;
	}
	
	public boolean less(int i, int k)
	{
		Comparendo compi = (Comparendo) arreglo[i];
		Comparendo compk = (Comparendo) arreglo[k];
		if(compi.getTipo().equals(compk.getTipo()))
		{
			return compi.getInfr().compareTo(compk.getInfr())<0;
		}
		else
		{
			return compk.getClase().equals("Publico");
		}
//		return arreglo[i].compareTo(arreglo[k])<0;
	}
	
	public void exch(int i, int k)
	{
		E t = arreglo[i];
		arreglo[i] = arreglo[k];
		arreglo[k] = t;
	}
	
	private void swim(int k)
	{
		while(k>1 && less(k/2,k))
		{
			exch(k/2,k);
			k=k/2;
		}
	}
	
	private void sink(int k)
	{
		while(2*k<=N)
		{
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;      
			if (!less(k, j)) break;      
			exch(k, j);      
			k = j;
		}
	}
	
	public E[] getArreglo() {
		return arreglo;
	}
}
