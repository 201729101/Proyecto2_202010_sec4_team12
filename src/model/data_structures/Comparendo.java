package model.data_structures;

import java.util.Date;

public class Comparendo implements Comparable<Comparendo>
{
	/**
	 * ID del comparendo
	 */
	private int id;

	/**
	 * Fecha del comparendo en String
	 */
	private Date fecha;

	/**
	 * Medio de detección del comparendo
	 */
	private String medio;

	/**
	 * Clase de vehículo comparentado
	 */
	private String clase;

	/**
	 * Tipo de servicio del vehículo comparentado
	 */
	private String tipo;

	/**
	 * Código de la infracción colocada
	 */
	private String infr;

	/**
	 * Descripción del comparendo
	 */
	private String desc;

	/**
	 * Localidad en que se colocó el comparendo
	 */
	private String localidad;

	/**
	 * Latitud en que se colocó el comparendo
	 */
	private double latitud;

	/**
	 * Longitud en que se colocó el comparendo
	 */
	private double longitud;

	/**
	 * Contructor
	 * @param pId ID del comparendo
	 * @param pFecha Fecha del comparendo
	 * @param pMedio Medio de detección del comparendo
	 * @param pClase Clase de vehículo comparentado
	 * @param pTipo Tipo de servicio del vahículo comparentado
	 * @param pInfr Código de la infracción colocada
	 * @param pDesc Descripcion del comparendo
	 * @param pLoc Localidad donde se puso el comparendo
	 * @param pLat Latitud donde se puso el comparendo
	 * @param pLong Longitud donde se puso el comparendo
	 */
	public Comparendo(int pId, Date pFecha, String pMedio, String pClase, String pTipo, String pInfr, String pDesc, String pLoc, double pLat, double pLong)
	{
		id = pId;
		fecha = pFecha;
		medio = pMedio;
		clase = pClase;
		tipo = pTipo;
		infr = pInfr;
		desc = pDesc;
		localidad = pLoc;
		latitud = pLat;
		longitud = pLong;
	}

	/**
	 * Reotna el tipo de servicio
	 * @return tipo de servicio
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Retorna la latidud 
	 * @return latitud
	 */
	public double getLatitud() {
		return latitud;
	}

	/**
	 * Retorna la longitud
	 * @return longitud
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * Retorna la descripción
	 * @return descripción
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Retorna la fecha
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/** 
	 * Retorna el id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retorna la infracción
	 * @return infracción
	 */
	public String getInfr() {
		return infr;
	}

	/**
	 * Retorna el medio
	 * @return medio
	 */
	public String getMedio() {
		return medio;
	}

	/**
	 * Retorna la clase
	 * @return clase
	 */
	public String getClase() {
		return clase;
	}

	/**
	 * Retorna la localidad
	 * @return
	 */
	public String getLocalidad() {
		return localidad;
	}


	@Override
	public int compareTo(Comparendo arg0) 
	{
		return fecha.compareTo(arg0.getFecha());
	}
	
	public boolean equals(Comparendo obj) 
	{
		return obj.getId() == this.id;
	}
	
	@Override
	public int hashCode() 
	{
		int hash = 17; 
		hash = 31*hash + fecha.hashCode(); 
		hash = 31*hash + clase.hashCode(); 
		hash = 31*hash + infr.hashCode(); 
		return hash;
	}
}
