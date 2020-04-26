package test.data_structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import model.data_structures.ColaDePrioridad;
import model.data_structures.ColaDePrioridadPrueba;
import model.data_structures.Comparendo;

public class TestColasDePrioridad {

	private ColaDePrioridad cola1;
	
	private ColaDePrioridadPrueba cola2;
	
	private void setUp1()
	{
		cola1 = new ColaDePrioridad<>();
		
		cola2 = new ColaDePrioridadPrueba<>();
	}
	
	private void setUp2()
	{
		cola1 = new ColaDePrioridad<Comparendo>(1000000);
		cola2 = new ColaDePrioridadPrueba<Comparendo>(100000);
		Date f = new Date("2018/01/01");
		
		Comparendo comp1 = new Comparendo(1,new Date("2018/01/01"),"","","Público","A1","SERÁ INMOVILIZADO","","",0.0,0.0);
		Comparendo comp2 = new Comparendo(2,new Date("2018/02/01"),"","","Particular","B2","SERÁ INMOVILIZADO","","",0.0,0.0);
		Comparendo comp3 = new Comparendo(3,new Date("2018/03/01"),"","","Oficial","C3","SERÁ INMOVILIZADO","","",0.0,0.0);
		Comparendo comp4 = new Comparendo(4,new Date("2018/04/01"),"","","Público","D4","LICENCIA DE CONDUCCIÓN","","",0.0,0.0);
		Comparendo comp5 = new Comparendo(5,new Date("2018/05/01"),"","","Particular","E5","LICENCIA DE CONDUCCION","","",0.0,0.0);
		Comparendo comp6 = new Comparendo(6,new Date("2018/06/01"),"","","Oficial","F6","LICENCIA DE CONDUCCIÓN","","",0.0,0.0);
		Comparendo comp7 = new Comparendo(7,new Date("2018/07/01"),"","","Público","G7","Otra cosa","","",0.0,0.0);
		Comparendo comp8 = new Comparendo(8,new Date("2018/08/01"),"","","Particular","H8","Otra cosa","","",0.0,0.0);
		Comparendo comp9 = new Comparendo(9,new Date("2018/09/01"),"","","Oficial","I9","Otra cosa","","",0.0,0.0);
		Comparendo comp10 = new Comparendo(10,new Date("2018/10/01"),"","","Público","J10","Otra cosa","","",0.0,0.0);
		
		cola1.insertar(comp1);
		cola2.insertar(comp1);
		cola1.insertar(comp2);
		cola2.insertar(comp2);
		cola1.insertar(comp3);
		cola2.insertar(comp3);
		cola1.insertar(comp4);
		cola2.insertar(comp4);
		cola1.insertar(comp5);
		cola2.insertar(comp5);
		cola1.insertar(comp6);
		cola2.insertar(comp6);
		cola1.insertar(comp7);
		cola2.insertar(comp7);
		cola1.insertar(comp8);
		cola2.insertar(comp8);
		cola1.insertar(comp9);
		cola2.insertar(comp9);
		cola1.insertar(comp10);
		cola2.insertar(comp10);
	}
	
	@Test
	public void testColaDePRioridad()
	{
		setUp1();
		assertTrue(cola1.esVacia());
		assertEquals(0,cola1.tamano());
		assertTrue(cola2.esVacia());
		assertEquals(0,cola2.tamano());
	}

	@Test
	public void testInsertar()
	{
		setUp2();
		Comparable[] arreglo1 = cola1.getArreglo();
//		ArrayList arreglo2 = cola2.getArreglo();
		Comparable[] arreglo2 = cola2.getArreglo();
		
		for(int i=2 ; i<11 ; i++)
		{
			assertNotNull(arreglo1[i]);
			assertTrue(cola1.less(i, i/2));
//			assertNotNull(arreglo2.get(i));
			assertNotNull(arreglo2[i]);
			assertTrue(cola2.less(i, i/2));
		}
	}
	
	@Test
	public void testEliminarMax()
	{
		setUp2();
		Comparendo r1 = (Comparendo) cola1.eliminarMax();
		Comparendo r2 = (Comparendo) cola1.eliminarMax();
		Comparendo r3 = (Comparendo) cola1.eliminarMax();
		Comparendo l1 = (Comparendo) cola2.eliminar();
		Comparendo l2 = (Comparendo) cola2.eliminar();
		Comparendo l3 = (Comparendo) cola2.eliminar();
		
		assertEquals(10,r1.getId());
		assertEquals("Otra cosa",r1.getDesc());
		assertEquals(7,r2.getId());
		assertEquals("Otra cosa",r2.getDesc());
		assertEquals(4,r3.getId());
		assertEquals("LICENCIA DE CONDUCCIÓN",r3.getDesc());
		
		assertEquals(3,l1.getId());
		assertEquals("Oficial",l1.getTipo());
		assertEquals(2,l2.getId());
		assertEquals("Particular",l2.getTipo());
		assertEquals(1,l3.getId());
		assertEquals("Público",l3.getTipo());
		
	}
}
