package model.data_structures;

public class HeapSort<E extends Comparable<E>> 
{
	/**
	 * Constructor del heapsort
	 */
	public HeapSort()
	{
	}
	
	/**
	 * Organiza un arreglo qu eentra por parámetro
	 * @param a Arreglo a organizar
	 */
	public void sort(Comparable[] a) 
	{   
		int N = a.length-1;   
		for (int k = N/2; k >= 1; k--)      
			sink(a, k, N);   
		while (N > 1)   
		{      
			exch(a, 1, N--);      
			sink(a, 1, N);   
		} 
	}

	/**
	 * Inidica si el pirmer elemento es menor al segundo elemento
	 * @param arreglo Arreglo a organizar
	 * @param i indice dle primer elemento
	 * @param k indice del segundo elemento 
	 * @return True si es menor false d elo contrario
	 */
	public boolean less(Comparable[] arreglo, int i, int k)
	{
		return arreglo[i].compareTo(arreglo[k])<0;
	}

	/**
	 * Intercambia dos elementos de un arreglo
	 * @param arreglo Arreglo a organizar
	 * @param i indice del primer elemento
	 * @param k indice del segund elemento
	 */
	public void exch(Comparable[] arreglo, int i, int k)
	{
		E t = (E) arreglo[i];
		arreglo[i] = arreglo[k];
		arreglo[k] = t;
	}

	/**
	 * Empuja un elemento hacia abajo en el heap hasta su posicion adecuada
	 * @param arreglo Arrelo a organizar
	 * @param k indice del elemento a empujar
	 * @param N capacidad del heap
	 */
	public void sink(Comparable[] arreglo ,int k,int N)
	{
		while(2*k<=N)
		{
			int j = 2*k;
			if (j < N && less(arreglo, j, j+1)) j++;      
			if (!less(arreglo, k, j)) break;      
			exch(arreglo, k, j);      
			k = j;
		}
	}
}
