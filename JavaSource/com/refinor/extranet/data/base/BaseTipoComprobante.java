package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the TipoComprobante table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TipoComprobante"
 */

public abstract class BaseTipoComprobante  implements Serializable {

	public static String REF = "TipoComprobante";
	public static String PROP_INTERNO = "interno";
	public static String PROP_SIGLA = "sigla";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_NOMBRE = "nombre";
	public static String PROP_ES_DOC_VENTA = "esDocVenta";
	public static String PROP_ID = "id";
	public static String PROP_LETRA = "letra";


	// constructors
	public BaseTipoComprobante () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTipoComprobante (int id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int id;

	// fields
	private java.lang.String nombre;
	private java.lang.String sigla;
	private java.lang.Short interno;
	private java.lang.String letra;
	private java.lang.Short activo;
	private java.lang.Boolean esDocVenta;



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
	 * Return the value associated with the column: Nombre
	 */
	public java.lang.String getNombre () {
		return nombre;
	}

	/**
	 * Set the value related to the column: Nombre
	 * @param nombre the Nombre value
	 */
	public void setNombre (java.lang.String nombre) {
		this.nombre = nombre;
	}



	/**
	 * Return the value associated with the column: Sigla
	 */
	public java.lang.String getSigla () {
		return sigla;
	}

	/**
	 * Set the value related to the column: Sigla
	 * @param sigla the Sigla value
	 */
	public void setSigla (java.lang.String sigla) {
		this.sigla = sigla;
	}



	/**
	 * Return the value associated with the column: Interno
	 */
	public java.lang.Short getInterno () {
		return interno;
	}

	/**
	 * Set the value related to the column: Interno
	 * @param interno the Interno value
	 */
	public void setInterno (java.lang.Short interno) {
		this.interno = interno;
	}



	/**
	 * Return the value associated with the column: Letra
	 */
	public java.lang.String getLetra () {
		return letra;
	}

	/**
	 * Set the value related to the column: Letra
	 * @param letra the Letra value
	 */
	public void setLetra (java.lang.String letra) {
		this.letra = letra;
	}



	/**
	 * Return the value associated with the column: Activo
	 */
	public java.lang.Short getActivo () {
		return activo;
	}

	/**
	 * Set the value related to the column: Activo
	 * @param activo the Activo value
	 */
	public void setActivo (java.lang.Short activo) {
		this.activo = activo;
	}



	/**
	 * Return the value associated with the column: es_doc_venta
	 */
	public java.lang.Boolean isEsDocVenta () {
		return esDocVenta;
	}

	/**
	 * Set the value related to the column: es_doc_venta
	 * @param esDocVenta the es_doc_venta value
	 */
	public void setEsDocVenta (java.lang.Boolean esDocVenta) {
		this.esDocVenta = esDocVenta;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.TipoComprobante)) return false;
		else {
			com.refinor.extranet.data.TipoComprobante tipoComprobante = (com.refinor.extranet.data.TipoComprobante) obj;
			return (this.getId() == tipoComprobante.getId());
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