package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Cola;

public class TestCola {

	public Cola cola;

	@Before
	public void before()
	{
		cola = new Cola();
	}
	
	public void setUp1()
	{
		try
		{
			for(int i = 0; i<10 ; i++)
			{
				cola.agregar(""+i);
			}
		}
		catch(Exception e)
		{
			fail("No deberia generar excepcion");
		}
	}

	@Test
	public void testCola() {
		try
		{
			assertTrue(cola.esVacia());
			setUp1();
			assertFalse(cola.esVacia());
			assertEquals(10,cola.darTamano());
			assertEquals("0",cola.darPrimero());
		}
		catch(Exception e)
		{
			fail("No debería generar excepción");
		}
	}

	@Test
	public void testAgregar()
	{
		setUp1();
		try
		{
			cola.agregar("10");
			assertEquals(11,cola.darTamano());
		}
		catch(Exception e)
		{
			fail("No debería generar excepción");
		}
	}

	@Test
	public void testEliminar(){
		assertNull(cola.eliminar());
		setUp1();
		String r = (String) cola.eliminar();
		assertEquals("0",r);
		assertEquals(9,cola.darTamano());
	}

	@Test
	public void testBuscar(){
		assertNull(cola.buscar("2"));
		setUp1();
		assertEquals("3",cola.buscar("3"));
		assertNull(cola.buscar("11"));
	}


}
