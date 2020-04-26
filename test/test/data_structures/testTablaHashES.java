package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data_structures.TablaHashES;

public class testTablaHashES 
{
	private TablaHashES tabla;
	
	private void setUp1()
	{
		tabla = new TablaHashES();
	}
	
	private void setUp2()
	{
		tabla = new TablaHashES<>(6);
		tabla.agregar("Uno", 1);
		tabla.agregar("Dos", 2);
		tabla.agregar("Tres", 3);
		tabla.agregar("Cuatro", 4);
		tabla.agregar("Cinco", 5);
		tabla.agregar("Seis", 6);
		tabla.agregar("Siete", 7);
		tabla.agregar("Ocho", 8);
		tabla.agregar("Nueve", 9);
		tabla.agregar("Dies", 10);
	}
	
	@Test
	public void testTablaHashES()
	{
		setUp1();
		assertEquals(0,tabla.getN());
	}
	
	@Test
	public void testDar()
	{
		setUp2();
		assertEquals(2, tabla.dar("Dos"));
		assertEquals(5, tabla.dar("Cinco"));
		assertEquals(8, tabla.dar("Ocho"));
		assertEquals(10, tabla.dar("Dies"));
	}
	
	@Test
	public void testAgregar()
	{
		tabla = new TablaHashES<>(3);
		tabla.agregar("Cero", 0);
		assertEquals(0, tabla.dar("Cero"));
		setUp2();
		tabla.agregar("Once", 11);
		tabla.agregar("Dies", 0);
		assertEquals(11, tabla.dar("Once"));
		assertEquals(0,tabla.dar("Dies"));
	}
	
}
