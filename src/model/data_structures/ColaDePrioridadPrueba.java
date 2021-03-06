package model.data_structures;

import java.util.ArrayList;

public class ColaDePrioridadPrueba<E extends Comparable<E>> 
{
	/**
	 * Arreglo de la cola
	 */
	private E[] arreglo;
	
//	private ArrayList<E> arreglo;

	/**
	 * Capacidad de la cola
	 */
	private int N = 0;

	/**
	 * Contructor de la cola
	 */
	public ColaDePrioridadPrueba() 
	{
		arreglo = (E[]) new Comparable[N+1]; 
//		arreglo = new ArrayList<E>();
//		arreglo.add(null);
	}

	/**
	 * Contructor de la cola con un acapacidad inical
	 * @param m Capacidad de la cola
	 */
	public ColaDePrioridadPrueba(int m) 
	{
		arreglo = (E[]) new Comparable[m+1]; 
	}

	/**
	 * Inserta un elemnto en la cola
	 * @param elem Elemento a inserar en la cola
	 */
	public void insertar(E elem)
	{
//		arreglo.add(elem);
//		++N;
		arreglo[++N] = elem;
		swim(N);

	}

	/**
	 * Elimina el elemnto mayor de la cola
	 * @return Elemento mayor eliminado
	 */
	public E eliminar()
	{
		E max = arreglo[1];  // Retrieve max key from top.
//		E max = arreglo.get(1);
		exch(1, N--);              // Exchange with last item.      
		arreglo[N+1] = null;            // Avoid loitering.     
//		arreglo.set(N+1, null);
		sink(1);                   // Restore heap property.      
		return max;
	}

	/**
	 * Indica si la cola est� vacia
	 * @return True si est� vacia false de lo contrario
	 */
	public boolean esVacia()
	{
		return N==0;
	}

	/**
	 * Retorna el tama�o de la lista
	 * @return Tama�o d ela lista
	 */
	public int tamano()
	{
		return N;
	}

	/**
	 * Indica si el pirmer elemento es menor al segundo elemento
	 * @param i indice dle primer elemento
	 * @param k indeice dle segundo elemnto 
	 * @return True si es menor false d elo contrario
	 */
	public boolean less(int i, int k)
	{
//		Comparendo comp1 = (Comparendo) arreglo.get(i);
//		Comparendo comp2 = (Comparendo) arreglo.get(k);
		Comparendo comp1 = (Comparendo) arreglo[i];
		Comparendo comp2 = (Comparendo) arreglo[k];
		boolean inm1 = comp1.getDesc().contains("SERA INMOVILIZADO")||comp1.getDesc().contains("SER� INMOVILIZADO");
		boolean inm2 = comp2.getDesc().contains("SERA INMOVILIZADO")||comp2.getDesc().contains("SER� INMOVILIZADO");
		boolean lic1 = comp1.getDesc().contains("LICENCIA DE CONDUCCI�N");
		boolean lic2 = comp2.getDesc().contains("LICENCIA DE CONDUCCI�N");
		if((inm1&&inm2)||(lic1&&lic2))
//			return arreglo.get(i).compareTo(arreglo.get(k))<0;
			return arreglo[i].compareTo(arreglo[k])<0;
		else if(inm1)
			return false;
		else if(inm2)
			return true;
		else if(lic1)
			return false;
		else if(lic2)
			return true;
		else
//			return arreglo.get(i).compareTo(arreglo.get(k))<0;
			return arreglo[i].compareTo(arreglo[k])<0;
	}

	/**
	 * Intercambia dos elementos en l acola
	 * @param i indice del primer elemento a intercambiar
	 * @param k indice del segundo elemento a intercambiar
	 */
	public void exch(int i, int k)
	{
//		E t = arreglo.get(i);
		E t = arreglo[i];
//		arreglo.set(i, arreglo.get(k));
		arreglo[i] = arreglo[k];
		arreglo[k] = t;
	}

	/**
	 * Empuja un elemento hacia rriba del heap hasta su posicion adecuada
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
	 * @param k indice del elemento a empujar
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
	 * Reotnra el arreglo de la cola
	 * @return Arreglod ela cola
	 */
	public E[] getArreglo() {
		return arreglo;
	}
}
