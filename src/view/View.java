package view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.data_structures.Cola;
import model.data_structures.Comparendo;

import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.logic.Modelo;

public class View 
{
	/**
	 * Metodo constructor
	 */
	public View()
	{

	}

	/**
	 *Imprime el menú 
	 */
	public void printMenu()
	{
		System.out.println("1. Cargar Comaprendos");
		System.out.println("2. 1A");
		System.out.println("3. 2A");
		System.out.println("4. 3A");
		System.out.println("5. 1C");
		System.out.println("6. 2C (Solo si no ha corrido la opción 3C)");
		System.out.println("7. 3C (Solo si no ha corrido la opción 2C)");
		System.out.println("8. Conclusión");
		System.out.println("9. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	/**
	 * Imprime un mensaje recibido por parámetro
	 * @param mensaje mensaje a imprimir
	 */
	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	/**
	 * Imprimer la informacion de un comparendo
	 * @param inf Comaprendo a imprimir
	 */
	public void printComparendo(Comparendo inf)
	{
		System.out.println("[");
		System.out.println("ID: "+inf.getId());
		System.out.println("Fecha: "+inf.getFecha());
		System.out.println("Medio de detección: " + inf.getMedio());
		System.out.println("Clase de vehículo: "+inf.getClase());
		System.out.println("Tipo de servicio: "+inf.getTipo());
		System.out.println("Infracción: "+inf.getInfr());
		System.out.println("Descripción: "+inf.getDesc());
		System.out.println("Localidad: "+inf.getLocalidad());
		System.out.println("Municipio: "+inf.getMunicipio());
		System.out.println("Coordenadas: "+inf.getLatitud()+" , "+inf.getLongitud());
		System.out.println("]");
	}

	/**
	 * Imprime todo un modelo recibido por parámetro
	 * @param modelo Modelo a imprimir
	 */
	public void printLista(ListaEncadenada lista)
	{
		System.out.println("Comparendos buscados: {");
		if(lista!=null)
			for(Nodo i=lista.darPrimero() ; i!=null ; i=i.darSiguiente())
			{
				Comparendo inf = (Comparendo) i.darElemento();
				printComparendo(inf);
			}
		else
			System.out.println("No se encontró comparendo");
		System.out.println("}");
	}

	/**
	 * Imprime le histograma
	 * @param lista Lista con al informacion
	 * @param D Tamaño de los intervalos
	 */
	public void printHist(ListaEncadenada lista,int D)
	{
		System.out.println("Intervalo              |Numero de comparendos");
		try
		{
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			Date min = parser.parse("2018/01/01");
			Date max = null;
			Calendar c = Calendar.getInstance();
			for(Nodo n = lista.darPrimero() ; n!=null ; n=n.darSiguiente())
			{
				c.setTime(min);
				c.add(Calendar.DATE, D-1);
				max = c.getTime();
				int cant = (Integer) n.darElemento();
				String asts = "";
				for(int i=0 ; i<cant ; i++)
				{
					asts += "*";
				}
				System.out.println(min+"-"+max+": "+asts);
				c.setTime(max);
				c.add(Calendar.DATE, 1);
				min=c.getTime();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Imprime las estadísticas
	 * @param listas Arreglo de dols listas encadenadas con la información
	 */
	public void printStats(ListaEncadenada[] listas)
	{
		try
		{
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			Date min = parser.parse("2018/01/01");
			Date fin = parser.parse("2019/01/01");
			Calendar c = Calendar.getInstance();
			c.setTime(min);
			System.out.println("Fecha        | Comparendos Procesados *******");
			System.out.println("             | Comparendos en espera  #######");
			ListaEncadenada retorno = listas[1];
			for(Nodo n = retorno.darPrimero() ; n!=null ; n=n.darSiguiente())
			{
				String info = (String) n.darElemento();
				String[] infos = info.split(",");
				String asts = "";
				String nums = "";
				for(int i=0 ; i<Integer.parseInt(infos[2]);i++)
				{
					asts+="*";
				}
				for(int i=0 ; i<Integer.parseInt(infos[3]);i++)
				{
					nums+="#";
				}
				System.out.println("2018/"+infos[0]+"/"+infos[1]+"|"+asts);
				System.out.println("          |"+nums);
			}
			System.out.println("Tablas de estadísticas");
			System.out.println("Costo Diario | Tiempo Minimo | Tiempo Promedio | Tiempo Máximo");
			ListaEncadenada lista = listas[0];
			for(Nodo n = lista.darPrimero() ; n!= null ; n=n.darSiguiente())
			{
				String info = (String) n.darElemento();
				String[] infos = info.split(",");
				System.out.println(infos[0]+"          |"+infos[1]+"              |"+infos[2]+"                |"+infos[3]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
