package model.data_structures;

public class HeapSort<E extends Comparable<E>> 
{
	public HeapSort()
	{
	}
	
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

	public boolean less(Comparable[] arreglo, int i, int k)
	{
		return arreglo[i].compareTo(arreglo[k])<0;
	}

	public void exch(Comparable[] arreglo, int i, int k)
	{
		E t = (E) arreglo[i];
		arreglo[i] = arreglo[k];
		arreglo[k] = t;
	}

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
