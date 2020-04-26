package model.data_structures;

public class ColaDePrioridad<E extends Comparable<E>>
{
	/**
	 * Arreglo de cola
	 */
	private E[] arreglo;
	
	/**
	 * Capacidad de la cola
	 */
	private int N = 0;
	
	/**
	 * Contructor de la cola de prioridad
	 */
	public ColaDePrioridad() 
	{
		arreglo = (E[]) new Comparable[N+1]; 
	}
	
	/**
	 * Constructor de la cola de prioridad con una capcidadpor parametro
	 * @param m Capacidad de la cola de prioridad
	 */
	public ColaDePrioridad(int m) 
	{
		arreglo = (E[]) new Comparable[m+1]; 
	}
	
	/**
	 * Inserta un elemento en la cola
	 * @param elem Elemento a insertar
	 */
	public void insertar(E elem)
	{
		arreglo[++N] = elem;
		swim(N);

	}
	
	/**
	 * Elimina el elemento mayo de la cola
	 * @return Elemnto mayor leiminado de la lista
	 */
	public E eliminarMax()
	{
		E max = arreglo[1];           // Retrieve max key from top.      
		exch(1, N--);              // Exchange with last item.      
		arreglo[N+1] = null;            // Avoid loitering.      
		sink(1);                   // Restore heap property.      
		return max;
	}
	
	/**
	 * Indica si la cola está vacia
	 * @return true si esta vacia false de lo contrario
	 */
	public boolean esVacia()
	{
		return N==0;
	}
	
	/**
	 * Indica el tamaño de la cola
	 * @return Tamaño de la cola
	 */
	public int tamano()
	{
		return N;
	}
	
	/**
	 * Indica si el pirmer elemento es menor al segundo elemento
	 * @param i indice del primer lemento
	 * @param k indice del segundo elemento 
	 * @return True si es mayor false de lo contrario
	 */
	public boolean less(int i, int k)
	{
		Comparendo compi = (Comparendo) arreglo[i];
		Comparendo compk = (Comparendo) arreglo[k];
		if(compi.getTipo().equals(compk.getTipo()))
		{
			return compi.getInfr().compareTo(compk.getInfr())<0;
		}
		else if(!compi.getTipo().equals("Oficial")&&!compk.getTipo().equals("Oficial"))
		{
			return compi.getTipo().equals("Particular");
		}
		else
		{
			return compi.getTipo().equals("Oficial");
		}
//		return arreglo[i].compareTo(arreglo[k])<0;
	}
	
	/**
	 * Intercambia dos elementos de la cola
	 * @param i indice dle primer elemnto a intercambiar
	 * @param k indice del segundo eleemnto a intercambiar
	 */
	public void exch(int i, int k)
	{
		E t = arreglo[i];
		arreglo[i] = arreglo[k];
		arreglo[k] = t;
	}
	
	/**
	 * Empuja un elelnto hacia arriba del heap hasta su posicion adecuada
	 * @param k indice del elemento a empujar
	 */
	private void swim(int k)
	{
		while(k>1 && less(k/2,k))
		{
			exch(k/2,k);
			k=k/2;
		}
	}
	
	/**
	 * Empuja un elemento hacia abajo del heap hasta su posicion adecuada
	 * @param k indice del elemnto a empujar
	 */
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
	
	/**
	 * Retorna el arreglo de la cola
	 * @return Arreglo de la cola
	 */
	public E[] getArreglo() {
		return arreglo;
	}
}
