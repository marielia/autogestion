package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MLibroDiarioImpresion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MLibroDiarioImpresion"
 */

public abstract class BaseMlibroDiarioImpresion  implements Serializable {

	public static String REF = "MlibroDiarioImpresion";
	public static String PROP_USUARIO = "usuario";
	public static String PROP_FECHA_ALTA = "fechaAlta";
	public static String PROP_CANTIDAD_PAGINAS = "cantidadPaginas";
	public static String PROP_ANIO = "anio";
	public static String PROP_NRO_PAGINA_FIN = "nroPaginaFin";
	public static String PROP_MES = "mes";
	public static String PROP_ID = "id";
	public static String PROP_CANTIDAD_LINEAS_PAGINAS = "cantidadLineasPaginas";
	public static String PROP_NRO_PAGINA_INICIO = "nroPaginaInicio";


	// constructors
	public BaseMlibroDiarioImpresion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMlibroDiarioImpresion (long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private long id;

	// fields
	private java.util.Date fechaAlta;
	private java.lang.String mes;
	private java.lang.String anio;
	private java.lang.Long usuario;
	private java.lang.Long nroPaginaInicio;
	private java.lang.Long nroPaginaFin;
	private java.lang.Long cantidadPaginas;
	private java.lang.Integer cantidadLineasPaginas;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
     */
	public long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: fecha_alta
	 */
	public java.util.Date getFechaAlta () {
		return fechaAlta;
	}

	/**
	 * Set the value related to the column: fecha_alta
	 * @param fechaAlta the fecha_alta value
	 */
	public void setFechaAlta (java.util.Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}



	/**
	 * Return the value associated with the column: mes
	 */
	public java.lang.String getMes () {
		return mes;
	}

	/**
	 * Set the value related to the column: mes
	 * @param mes the mes value
	 */
	public void setMes (java.lang.String mes) {
		this.mes = mes;
	}



	/**
	 * Return the value associated with the column: anio
	 */
	public java.lang.String getAnio () {
		return anio;
	}

	/**
	 * Set the value related to the column: anio
	 * @param anio the anio value
	 */
	public void setAnio (java.lang.String anio) {
		this.anio = anio;
	}



	/**
	 * Return the value associated with the column: usuario
	 */
	public java.lang.Long getUsuario () {
		return usuario;
	}

	/**
	 * Set the value related to the column: usuario
	 * @param usuario the usuario value
	 */
	public void setUsuario (java.lang.Long usuario) {
		this.usuario = usuario;
	}



	/**
	 * Return the value associated with the column: nro_pagina_inicio
	 */
	public java.lang.Long getNroPaginaInicio () {
		return nroPaginaInicio;
	}

	/**
	 * Set the value related to the column: nro_pagina_inicio
	 * @param nroPaginaInicio the nro_pagina_inicio value
	 */
	public void setNroPaginaInicio (java.lang.Long nroPaginaInicio) {
		this.nroPaginaInicio = nroPaginaInicio;
	}



	/**
	 * Return the value associated with the column: nro_pagina_fin
	 */
	public java.lang.Long getNroPaginaFin () {
		return nroPaginaFin;
	}

	/**
	 * Set the value related to the column: nro_pagina_fin
	 * @param nroPaginaFin the nro_pagina_fin value
	 */
	public void setNroPaginaFin (java.lang.Long nroPaginaFin) {
		this.nroPaginaFin = nroPaginaFin;
	}



	/**
	 * Return the value associated with the column: cantidad_paginas
	 */
	public java.lang.Long getCantidadPaginas () {
		return cantidadPaginas;
	}

	/**
	 * Set the value related to the column: cantidad_paginas
	 * @param cantidadPaginas the cantidad_paginas value
	 */
	public void setCantidadPaginas (java.lang.Long cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}



	/**
	 * Return the value associated with the column: cantidad_lineas_paginas
	 */
	public java.lang.Integer getCantidadLineasPaginas () {
		return cantidadLineasPaginas;
	}

	/**
	 * Set the value related to the column: cantidad_lineas_paginas
	 * @param cantidadLineasPaginas the cantidad_lineas_paginas value
	 */
	public void setCantidadLineasPaginas (java.lang.Integer cantidadLineasPaginas) {
		this.cantidadLineasPaginas = cantidadLineasPaginas;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MlibroDiarioImpresion)) return false;
		else {
			com.refinor.extranet.data.MlibroDiarioImpresion mlibroDiarioImpresion = (com.refinor.extranet.data.MlibroDiarioImpresion) obj;
			return (this.getId() == mlibroDiarioImpresion.getId());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getId();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}