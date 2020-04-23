package model.data_structures;

public class Cola<E extends Comparable<E>> implements Comparable<Cola>
{
	/**
	 * Constante de la capacidad de la cola
	 */
	private static final int CAPACIDAD = 1000000;
	
	/**
	 * Indicador de la posicion primera en la cola
	 */
	private int p;
	
	/**
	 * Numero de elementos en la cola
	 */
	private int n;
	
	/**
	 * Arreglo de elementos en la cola
	 */
	private E[] elementos;
	
	/**
	 * Contructor de la cola
	 */
	public Cola()
	{
		elementos = (E[]) new Comparable[CAPACIDAD];
		p=0;
		n=0;
	}
	
	/**
	 * Reotrna el arreglo de elementos de la cola
	 * @return Elementos de la cola
	 */
	public E[] getElementos() {
		return elementos;
	}
	
	/**
	 * Retorna el numero de elemntos en la cola
	 * @return Numero de elementos en la cola
	 */
	public int getN() {
		return n;
	}
	
	/**
	 * Retorna la capacidad de la cola
	 * @return Cpacidad de la cola
	 */
	public static int getCapacidad() {
		return CAPACIDAD;
	}
	
	/**
	 * Indica si la cola es vacia
	 * @return true si está vacia false de lo contrario
	 */
	public boolean esVacia()
	{
		return n==0;
	}
	
	/**
	 * Retorna el tamaño de la cola
	 * @return Tamaño de la cola
	 */
	public int darTamano()
	{
		return n;
	}
	
	/**
	 * Agrega un elemento a la cola
	 * @param elemento Elemento a agregar
	 * @throws Exception En caso de que cola está llena
	 */
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
	
	/**
	 * Elimina el primer elemnto en la cola
	 * @return Primero elemento eliminado en la cola
	 */
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
	
	/**
	 * Busca un elemento en la cola
	 * @param elemento Elemento a buscar en la cola
	 * @return Elemento buscado
	 */
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
	
	/**
	 * Retorna el primer elemento en la cola
	 * @return Primer elemento en la cola
	 */
	public E darPrimero()
	{
		return elementos[p];
	}

	/**
	 * Hace la cola comparabl epara poder usarlo como lemento en otras estructuras
	 */
	@Override
	public int compareTo(Cola arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
