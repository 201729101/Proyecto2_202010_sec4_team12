package model.data_structures;

/**
 * Clase del nodo de una lista encadaneda
 */
public class Nodo<E extends Comparable<E>> 
{
	/**
	 * Referencia al siguiente nodo
	 */
	private Nodo siguiente;
	
	
	/**
	 * Elelemtno generico contenido en el nodo
	 */
	private E elemento;
	
//	private K key;
	/**
	 * Constructor con un elemento pasado por parametro
	 * @param pElelemtno elemento a añadir en el nodo
	 */
	public Nodo( E pElelemtno)
	{
		siguiente = null;
		elemento = pElelemtno;
	}
	
	/**
	 * Retorna el elemento del nodo
	 * @return elemento del nodo
	 */
	public E darElemento()
	{
		return elemento;
	}
	
	
	public void cambiarElemento(E elem)
	{
		elemento = elem;
	}
	
//	public void cambiarKey(K pkey)
//	{
//		key = pkey;
//	}

	/**
	 * Retorna el siguiente nodo
	 * @return siguiente nodo
	 */
	public Nodo darSiguiente()
	{
		return siguiente;
	}
	
	/**
	 * Cambia el siguiento nodo por uno recibido por parámetro
	 * @param pSiguiente nuevo siguiente nodo
	 */
	public void cambiarSiguiente(Nodo pSiguiente)
	{
		siguiente = pSiguiente;
	}
}
