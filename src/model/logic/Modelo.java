package model.logic;

import java.util.Date;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import model.data_structures.Comparendo;
import model.data_structures.HeapSort;
import model.data_structures.ListaEncadenada;
import model.data_structures.Nodo;
import model.data_structures.TablaHashES;
import model.data_structures.Cola;
import model.data_structures.ColaDePrioridad;
import model.data_structures.ColaDePrioridadFecha;

import java.time.*; 
import java.time.DayOfWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{
	/**
	 * Tabla hash que tendrá los comparendos con llave de mmes y dia semanal
	 */
	private TablaHashES tabla;

	/**
	 * Cola de priordad con los comparendos con piroridad igual a graved
	 */
	private ColaDePrioridad cola;

	/**
	 * Arreglo de los comparedos 
	 */
	private Comparable[] arreglo;

	/**
	 * Tabla hash con los comparendos con llave de mes y dia del año
	 */
	private TablaHashES pros;

	/**
	 * Constructor
	 */
	public Modelo ()
	{
		tabla = new TablaHashES();
		cola = new ColaDePrioridad(527660);
		pros = new TablaHashES();
	}

	/**
	 * Inicia la lectura del archivo JSON y rellena la lista
	 * @param path, ruta del archivo a leer
	 */
	public Comparendo cargarDatos(String PATH) 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();

			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Comparendo mayor = null;

			int i = 0;
			for(JsonElement e: e2) 
			{
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				String[] s1 = s.split("T");
				String[] s2 = s1[0].split("-");
				String s3 = s2[0]+"/"+s2[1]+"/"+s2[2]+" "+s1[1];
				Date FECHA_HORA = parser.parse(s3); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();
				
				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, MUNICIPIO, longitud, latitud);
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
				String key = s2[1]+simpleDateformat.format(FECHA_HORA);

				String llave = s2[1]+"/"+s2[2];
				agregarES(key, c);
				agregarESDia(llave,c);
				cola.insertar(c);
				if(mayor == null) 
					mayor =c;
				else if(c.getId()>mayor.getId())
					mayor = c;
			}
			arreglo = cola.getArreglo().clone();
			return mayor;
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}	
	}

	/**
	 * Retorna la cola de prioridad
	 * @return
	 */
	public ColaDePrioridad getCola() {
		return cola;
	}

	/**
	 * Agrega un allave valor a la tabla hash 
	 * @param key Llave a agregar
	 * @param value Valor a agregar
	 */
	public void agregarES(String key, Comparendo value)
	{
		ListaEncadenada b = (ListaEncadenada) tabla.dar(key);

		if(b==null)
		{
			b = new ListaEncadenada<Comparendo>();
			b.agregarFinal(value);
			tabla.agregar(key, b);
		}
		else
		{
			b.agregarFinal(value);
		}
	}

	/**
	 * Agrega un allave valor a la tabla hash
	 * @param key Llave a agregar
	 * @param value Valor a agregar
	 */
	public void agregarESDia(String key, Comparendo value)
	{
		Cola b = (Cola) pros.dar(key);
		try
		{
			if(b==null)
			{
				b = new Cola<Comparendo>();
				b.agregar(value);
				pros.agregar(key, b);
			}
			else
			{
				b.agregar(value);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Primer requerimiento funcional
	 * @param m Numeor de comparendo a buscar
	 * @return Lista con los comparendos buscados
	 */
	public ListaEncadenada unoA(int m)
	{
		ListaEncadenada lista = new ListaEncadenada();

		for(int i = 0 ; i< m && cola.tamano()>0 ; i++)
		{
			lista.agregarFinal(cola.eliminarMax());
		}

		return lista;
	}

	/**
	 * Segundo requerimiento funcional
	 * @param date Mes-DiaSemana a buscar
	 * @return Lista encadenada con los comparendos buscados
	 */
	public ListaEncadenada dosA(String date)
	{
		return (ListaEncadenada) tabla.dar(date);
	}

	/**
	 * Tercer requerimiento funcional
	 * @param max Fecha maxima
	 * @param min Fecha minima
	 * @param loc Localidad
	 * @param N Numeor de  comparendos a buscar
	 * @return
	 */
	public ListaEncadenada tresA(Date max, Date min, String loc, int N)
	{
		ListaEncadenada lista= new ListaEncadenada();
		HeapSort sort = new HeapSort();
		sort.sort(arreglo);

		for(int i=1 ; i<arreglo.length && lista.darTamano()<N && min.compareTo(max)<=0 ; i++)
		{
			Comparendo comp = (Comparendo) arreglo[i];
			if(comp.getFecha().compareTo(min)>=0)
			{
				if(comp.getLocalidad().equals(loc))
					lista.agregarFinal(arreglo[i]);
				min=((Comparendo) arreglo[i]).getFecha();
			}
		}
		return lista;
	}

	/**
	 * SEptimo requerimiento funcional
	 * @param D Tamñano de los intevralos
	 * @return Lista Enacdenada con los comparendos buscados
	 */
	public ListaEncadenada unoC(int D)
	{
		HeapSort sort = new HeapSort();
		sort.sort(arreglo);
		//		Comparable[] a = arreglo;
		try
		{
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			Date min = parser.parse("2018/01/01");
			Calendar c = Calendar.getInstance();
			c.setTime(min);
			c.add(Calendar.DATE, 6);
			Date max = c.getTime();
			ListaEncadenada retorno = new ListaEncadenada(); 
			int cont = 0;

			for(Comparable comp : arreglo)
			{
				if(comp!=null)
				{
					Date act = ((Comparendo) comp).getFecha();
					if(act.compareTo(max)>0)
					{
						retorno.agregarFinal(cont);
						cont = 0;
						c.setTime(max);
						c.add(Calendar.DATE, 1);
						min = c.getTime();
						c.add(Calendar.DATE, D-1);
						max = c.getTime();
					}
					if(act.compareTo(min)>=0 && act.compareTo(max)<=0)
						cont++;
				}

			}

			return retorno;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Octavo requerimiento funcional
	 * @return Arreglo de dos listas encadenadas con la información
	 */
	public ListaEncadenada[] dosC()
	{
		ListaEncadenada retorno = new ListaEncadenada();
		try
		{
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			Date min = parser.parse("2018/01/01");
			Date fin = parser.parse("2019/01/01");
			Calendar c = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			c.setTime(min);
			int dif = 0;
			Cola comps;
			Cola espera=new Cola();
			int min400 = 10000000;int max400 = 0;int sum400 = 0;int cont400 = 0;
			int min40 = 10000000;int max40 = 0;int sum40 = 0;int cont40 = 0;
			int min4 = 10000000;int max4 = 0;int sum4 = 0;int cont4 = 0;
			for(Date i = c.getTime() ; i.compareTo(fin)<0 ; c.add(Calendar.DATE, 1))
			{
				i=c.getTime();
				int mes = i.getMonth()+1;
				int dia = i.getDate();
				String MES=""+mes;
				String DIA=""+dia;
				if(mes<10)
					MES="0"+mes;
				if(dia<10)
					DIA="0"+dia;

				comps = (Cola) pros.dar(MES+"/"+DIA);
				int cant = dif;
				if(comps!=null)
				{
					cant += comps.darTamano();

					String dato = MES+","+DIA+",";
					if(cant>1500)
					{
						dif = cant-1500;
						dato=dato+1500+","+dif;
					}
					else
					{
						dif = 0;
						dato=dato+cant+","+0;
					}
					retorno.agregarFinal(dato);

					int j=0;
					while(j<1500 && !espera.esVacia())
					{
						Comparendo act = (Comparendo) espera.eliminar();
						cal.setTime(act.getFecha());
						int des = c.get(Calendar.DAY_OF_YEAR)-cal.get(Calendar.DAY_OF_YEAR);
//						int des= (int) TimeUnit.DAYS.convert(Math.abs(act.getFecha().getTime() - i.getTime()),TimeUnit.MILLISECONDS);
						if(act.getDesc().contains("SERA INMOVILIZADO")||act.getDesc().contains("SERÁ INMOVILIZADO"))
						{
							if(des>max400)
								max400=des;
							if(des<min400)
								min400=des;
							sum400 += des;
							cont400++;
						}
						else if(act.getDesc().contains("LICENCIA DE CONDUCCIÓN"))
						{
							if(des>max40)
								max40=des;
							if(des<min40)
								min40=des;
							sum40 += des;
							cont40++;
						}
						else
						{
							if(des>max4)
								max4=des;
							if(des<min4)
								min4=des;
							sum4 += des;
							cont4++;
						}

						j++;
					}
					while(j<1500 && !comps.esVacia())
					{
						Comparendo act = (Comparendo) comps.eliminar();
						j++;
					}
					while(!comps.esVacia())
						espera.agregar(comps.eliminar());
				}
			}
			ListaEncadenada<String> lista = new ListaEncadenada<String>();
			if(cont400==0)
			{cont400=1; min400=0;}
			int prom400 = (int) sum400/cont400;
			lista.agregarFinal("400,"+min400+","+prom400+","+max400);
			if(cont40==0)
			{cont40=1; min40=0;}
			int prom40 = (int) sum40/cont40;
			lista.agregarFinal(" 40,"+min40+","+prom40+","+max40);
			if(cont4==0)
			{cont4=1; min4=0;}
			int prom4 = (int) sum4/cont4;
			lista.agregarFinal("  4,"+min4+","+prom4+","+max4);

			ListaEncadenada<String>[] ret = new ListaEncadenada[2];
			ret[0] = lista;
			ret[1] = retorno;
			return ret;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Noveno requerimiento funcional
	 * @return Arreglo de dos listas encadenadas con la informacion
	 */
	public ListaEncadenada[] tresC()
	{
		ListaEncadenada retorno = new ListaEncadenada();
		try
		{
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			Date min = parser.parse("2018/01/01");
			Date fin = parser.parse("2019/01/01");
			Calendar c = Calendar.getInstance();
			Calendar cal = Calendar.getInstance();
			c.setTime(min);
			int dif = 0;
			Cola comps;
			ColaDePrioridadFecha espera=new ColaDePrioridadFecha(527660);
			int min400 = 10000000;int max400 = 0;int sum400 = 0;int cont400 = 0;
			int min40 = 10000000;int max40 = 0;int sum40 = 0;int cont40 = 0;
			int min4 = 10000000;int max4 = 0;int sum4 = 0;int cont4 = 0;
			for(Date i = c.getTime() ; i.compareTo(fin)<0 ; c.add(Calendar.DATE, 1))
			{
				i=c.getTime();
				int mes = i.getMonth()+1;
				int dia = i.getDate();
				String MES=""+mes;
				String DIA=""+dia;
				if(mes<10)
					MES="0"+mes;
				if(dia<10)
					DIA="0"+dia;

				comps = (Cola) pros.dar(MES+"/"+DIA);
				int cant = dif;
				if(comps!=null)
				{
					cant += comps.darTamano();

					String dato = MES+","+DIA+",";
					if(cant>1500)
					{
						dif = cant-1500;
						dato=dato+1500+","+dif;
					}
					else
					{
						dif = 0;
						dato=dato+cant+","+0;
					}
					retorno.agregarFinal(dato);

					int j=0;
					while(j<1500 && !espera.esVacia())
					{
						Comparendo act = (Comparendo) espera.eliminar();
						cal.setTime(act.getFecha());
						int des = c.get(Calendar.DAY_OF_YEAR)-cal.get(Calendar.DAY_OF_YEAR);
//						int des= (int) TimeUnit.DAYS.convert(Math.abs(act.getFecha().getTime() - i.getTime()),TimeUnit.MILLISECONDS);
						if(act.getDesc().contains("SERA INMOVILIZADO")||act.getDesc().contains("SERÁ INMOVILIZADO"))
						{
							if(des>max400)
								max400=des;
							if(des<min400)
								min400=des;
							sum400 += des;
							cont400++;
						}
						else if(act.getDesc().contains("LICENCIA DE CONDUCCIÓN"))
						{
							if(des>max40)
								max40=des;
							if(des<min40)
								min40=des;
							sum40 += des;
							cont40++;
						}
						else
						{
							if(des>max4)
								max4=des;
							if(des<min4)
								min4=des;
							sum4 += des;
							cont4++;
						}

						j++;
					}
					while(j<1500 && !comps.esVacia())
					{
						Comparendo act = (Comparendo) comps.eliminar();
						j++;
					}
					while(!comps.esVacia())
						espera.insertar(comps.eliminar());
				}

			}
			ListaEncadenada<String> lista = new ListaEncadenada<String>();
			if(cont400==0)
			{cont400=1; min400=0;}
			int prom400 = (int) sum400/cont400;
			lista.agregarFinal("400,"+min400+","+prom400+","+max400);
			if(cont40==0)
			{cont40=1; min40=0;}
			int prom40 = (int) sum40/cont40;
			lista.agregarFinal(" 40,"+min40+","+prom40+","+max40);
			if(cont4==0)
			{cont4=1; min4=0;}
			int prom4 = (int) sum4/cont4;
			lista.agregarFinal("  4,"+min4+","+prom4+","+max4);

			ListaEncadenada<String>[] ret = new ListaEncadenada[2];
			ret[0] = lista;
			ret[1] = retorno;
			return ret;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
