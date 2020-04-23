package test.data_structures;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.data_structures.Comparendo;
import model.logic.Modelo;


import org.junit.Test;

import model.data_structures.ListaEncadenada;

public class testListaEncadenada
{
	private ListaEncadenada lista;


	@Before
	public void before()
	{
		lista = new ListaEncadenada();
	}
	
	public void setUp1()
	{
		for(int i=0 ; i<10 ; i++)
		{
			lista.agregarFinal(""+i);
		}
	}


	@Test 
	public void testListaEncadenada()
	{
		assertNull(lista.darPrimero());
		assertNull(lista.darUltimo());
		assertEquals(0,lista.darTamano());
	}


	@Test public void testDarTamaño()
	{
		setUp1();
		assertEquals(10, lista.darTamano());
	}

	@Test public void testDarPrimero()
	{
		setUp1();
		assertEquals("0",lista.darPrimero().darElemento());
	}

	@Test public void testDarUltimo()
	{
		setUp1();
		assertEquals("9",lista.darUltimo().darElemento());
	}

	@Test public void testBuscar()
	{
		setUp1();
		assertEquals("4",lista.buscar(4));
	}

	@Test public void testAgregarFinal()
	{
		setUp1();
		lista.agregarFinal("10");
		assertEquals("10", lista.darUltimo().darElemento());
		assertEquals(11,lista.darTamano());
	}

	@Test public void testAgregarInicio()
	{
		setUp1();
		lista.agregarInicio("-1");
		assertEquals("-1", lista.darPrimero().darElemento());
		assertEquals(11, lista.darTamano());
	}

	@Test public void testEliminar()
	{
		assertNull(lista.buscar("3"));
		setUp1();
		String r = (String) lista.eliminar("3");
		assertEquals("3",r);
		assertNull(lista.buscar("3"));
	}

}
