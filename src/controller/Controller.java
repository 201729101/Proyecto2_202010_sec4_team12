package controller;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import model.data_structures.Comparendo;
import model.data_structures.*;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	/**
	 * Corre el sistema mediante la consola 
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		Comparendo resp = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			switch(option){
			case 1:
				modelo = new Modelo(); 
				Comparendo retorno = modelo.cargarDatos("./data/Comparendos_DEI_2018_Bogotá_D.C.geojson");
				//				Comparendo[] retorno = modelo.cargarDatos("./data/comparendos_dei_2018_small2.geojson");
				view.printMessage("Numero de comparendos: "+modelo.getCola().tamano());
				view.printMessage("Comparendo con mayor id: ");
				view.printComparendo(retorno);
				System.out.println("----------------------------");
				break;

			case 2:

				view.printMessage("Ingrese el numero de comparendos a buscar");
				int l = lector.nextInt();	
				try
				{
					ListaEncadenada lista = modelo.unoA(l);
					view.printLista(lista);
				}
				catch(Exception e)
				{
					System.out.println("Hubo un error");
					e.printStackTrace();
				}
				break;

			case 3:
				view.printMessage("Ingrese número del mes <Abril=04>");
				String mes = lector.next();
				view.printMessage("Ingrese primeras tres letras del día de la semana en inglés <Miercoles = Wed>:");
				String dia = lector.next();
				try
				{
					ListaEncadenada lista2 = modelo.dosA(mes+dia);	
					view.printLista(lista2);
				}
				catch(Exception e)
				{
					System.out.println("Hubo un error");
					e.printStackTrace();
				}
				break;

			case 4:
				view.printMessage("Ingrese fecha mínima en formato <yyyy/MM/dd>");
				try
				{
					String m = lector.next();
					Date min = parser.parse(m);
					view.printMessage("Ingrese fecha máxima en formato <yyyy/MM/dd>");
					String ma = lector.next();
					Date max = parser.parse(ma);
					view.printMessage("Ingrese localidad a buscar en mayuscula (Si son dos palabras separe con un guion<->)");
					String loc = lector.next();
					String[] locs = loc.split("-");
					if(locs.length>1)
						loc = locs[0]+" "+locs[1];
					view.printMessage("Ingrese número de comparendos a buscar");
					int n = lector.nextInt();
					ListaEncadenada lista = modelo.tresA(max, min, loc, n);
					view.printLista(lista);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				break;
			
			case 5:
				view.printMessage("Ingrese tamaño de los intervalos");
				int D = lector.nextInt();
				view.printHist(modelo.unoC(D),D);
				break;
				
			case 6:
				view.printStats(modelo.dosC());
				break;
				
			case 7:
				view.printStats(modelo.tresC());
				break;
				
			case 8:
				view.printMessage("El costo de implementación del nuevo sistema es mayor a las perdidas que tiene el sistema actual, por ende no es conveniente utilizar el nuevo sistema.");
				break;
				
			case 9: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
