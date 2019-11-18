package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MLibroDiarioPagina table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MLibroDiarioPagina"
 */

public abstract class BaseMlibroDiarioPagina  implements Serializable {

	public static String REF = "MlibroDiarioPagina";
	public static String PROP_ID = "id";
	public static String PROP_CANTIDAD_LINEA_POR_PAGINA = "cantidadLineaPorPagina";


	// constructors
	public BaseMlibroDiarioPagina () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMlibroDiarioPagina (int id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int id;

	// fields
	private java.lang.Integer cantidadLineaPorPagina;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
     */
	public int getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (int id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: cantidad_linea_por_pagina
	 */
	public java.lang.Integer getCantidadLineaPorPagina () {
		return cantidadLineaPorPagina;
	}

	/**
	 * Set the value related to the column: cantidad_linea_por_pagina
	 * @param cantidadLineaPorPagina the cantidad_linea_por_pagina value
	 */
	public void setCantidadLineaPorPagina (java.lang.Integer cantidadLineaPorPagina) {
		this.cantidadLineaPorPagina = cantidadLineaPorPagina;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MlibroDiarioPagina)) return false;
		else {
			com.refinor.extranet.data.MlibroDiarioPagina mlibroDiarioPagina = (com.refinor.extranet.data.MlibroDiarioPagina) obj;
			return (this.getId() == mlibroDiarioPagina.getId());
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