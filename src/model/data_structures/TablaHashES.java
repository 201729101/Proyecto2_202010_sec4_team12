package model.data_structures;

public class TablaHashES<K extends Comparable<K> , V> 
{	
	/**
	 * Arreglo de valores de la tabla
	 */
	private Chain[] values;

	/**
	 * Numero de dupla en la tabla
	 */
	private int N;

	/**
	 * Capacidad de la tabla
	 */
	private int M = 20;

	/**
	 * Capacidad inicial de la tabla
	 */
	private int Mi;

	/**
	 * Numero de rehashes en la tabla
	 */
	private int R;

	/**
	 * Clase que representa los nodos encadenados 
	 */
	public class Node<K extends Comparable<K> , V > 
	{
		/**
		 * Referencia al siguiente nodo
		 */
		private Node siguiente;


		/**
		 * Valor generico contenido en el nodo
		 */
		private V value;

		/**
		 * Llave del nodo
		 */
		private K key;

		/**
		 * Constructor con una llave y un valor pasados por parametro
		 * @param pElelemtno valor a añadir en el nodo
		 * @param pKey llave a añadir al nodo
		 */
		public Node(K pkey, V pElelemtno)
		{
			key = pkey;
			siguiente = null;
			value = pElelemtno;
		}

		/**
		 * Retorna el valor del nodo
		 * @return valor del nodo
		 */
		public V getValue()
		{
			return value;
		}

		/**
		 * Retorna la llave del nodo
		 * @return Llave del nodo
		 */
		public K getKey() {
			return key;
		}

		/**
		 * Cambia el valor de la tabla por uno recibido por parámetro
		 * @param value2 Nuevo valor a insertar
		 */
		public void setValue(V value2)
		{
			value = value2;
		}

		/**
		 * Cambia el la llave de la tabla por una recibida pr parámetro
		 * @param pkey Nueva llave a insertar
		 */
		public void cambiarKey(K pkey)
		{
			key = pkey;
		}

		/**
		 * Retorna el siguiente nodo
		 * @return siguiente nodo
		 */
		public Node darSiguiente()
		{
			return siguiente;
		}

		/**
		 * Cambia el siguiento nodo por uno recibido por parámetro
		 * @param pSiguiente nuevo siguiente nodo
		 */
		public void cambiarSiguiente(Node pSiguiente)
		{
			siguiente = pSiguiente;
		}

	}

	/**
	 * Clase que repreenta las cadenas de la tabla
	 */
	public class Chain<K extends Comparable<K>, V>
	{
		/**
		 * PRimer nodo de la cadena
		 */
		private Node first;
		
		/**
		 * Ultimo nodo de la cadena
		 */
		private Node last;

		/**
		 * Constructor de la cadena
		 */
		public Chain()
		{
			first = null;
			last = null;
		}

		/**
		 * Retorna el primer nodo de la cadena
		 * @return Primer nodo de la cadena
		 */
		public Node getFirst() {
			return first;
		}

		/**
		 * Añade un nodo al final de la cadena con un allave y un valor recibidos por parámetro
		 * @param key llave del nodo a inseratar
		 * @param value Valor del nodo a insertar
		 */
		public void add(K key, V value)
		{
			Node nodo = new Node( key, value );
			if(first == null)
			{
				first = nodo;
				last = nodo;
			}
			else
			{
				last.cambiarSiguiente(nodo);
				last = nodo;
			}
		}
	}

	/**
	 * Constructor de la tabla
	 */
	public TablaHashES() 
	{
		values = new Chain[M];
		Mi=M;
	}

	/**
	 * Constructor con una capcidad definida por parametro
	 * @param cap Capacidad de la tabla
	 */
	public TablaHashES(int cap)
	{
		values = new Chain[cap];
	}

	/**
	 * Agrega una nueva dupla de llave y valor recibidad por parámetro
	 * @param key Llave a agregar
	 * @param value Valor a agregar
	 */
	public void agregar(K key , V value)
	{
		//		if (2 >= N/M)
		//			resize((int) M/2);
		//		else 
		if (5 <= N/M)
			resize(2*M);


		int hash = hash(key);

		if(values[hash]!=null)
			for(Node n = values[hash].getFirst() ; n!= null ; n=n.darSiguiente())
				if(n.getKey().equals(key))
				{
					n.setValue(value);
					return;
				}

		if(values[hash]==null)
			values[hash] = new Chain();

		values[hash].add(key, value);;
		N++;
	}

	/**
	 * Retorna un valor correspondiente a la llave recibida por parámetro
	 * @param key Llave a buscar 
	 * @return Valor correspondiente a la llave
	 */
	public V dar(K key)
	{
		int hash = hash(key);

		if(values[hash]!=null)
			for(Node n = values[hash].getFirst() ; n!= null ; n = n.darSiguiente())
			{
				if(n.getKey().equals(key))
				{
					return (V) n.getValue();
				}
			}
		return null;
	}

	
	/**
	 * Retorna la capacidad actual de la tabla
	 * @return Capacidad actual de la tabla
	 */
	public int getM() {
		return M;
	}

	/**
	 * Retorna el numero de duplas en la tabla
	 * @return Numero de duplas en la tabla
	 */
	public int getN() {
		return N;
	}
	
	/**
	 * Retorna la capacidad inicial de la tabla
	 * @return Capacida inicial de la tabla
	 */
	public int getMi() {
		return Mi;
	}
	
	/**
	 * Retorna el numero de rahashes en la tabla
	 * @return Número de rehashes en la tabla
	 */
	public int getR() {
		return R;
	}

	/**
	 * Retorna el arreglo de cadenas de la tabla
	 * @return
	 */
	public Chain[] getValues() {
		return values;
	}

	/**
	 * Cambia el tamaño de la tabla por uno recibido por parámetro
	 * @param cap Nuevo tamaño de la tabla
	 */
	private void resize(int cap) 
	{    
		TablaHashES<K, V> t;    
		t = new TablaHashES<K, V>(cap);    
		for (int i = 0; i < M; i++)       
			if (values[i] != null)
				for(Node n = values[i].getFirst() ; n!= null ; n = n.darSiguiente())
					t.agregar((K) n.getKey(), (V) n.getValue());        
		values = t.values;    
		M    = t.M; 
		R++;
	}

	/** 
	 * Genera el código hash de una llave recibida por parámetro
	 * @param key Llave de la cual se requiere el hash
	 * @return Código hash de la llave
	 */
	public int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % M; 
	}
}
