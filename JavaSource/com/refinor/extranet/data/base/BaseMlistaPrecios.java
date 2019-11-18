package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MListaPrecios table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MListaPrecios"
 */

public abstract class BaseMlistaPrecios  implements Serializable {

	public static String REF = "MlistaPrecios";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_CCSS_ID = "ccssId";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_FH_ALTA = "fhAlta";
	public static String PROP_GENERAL = "general";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMlistaPrecios () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMlistaPrecios (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String descripcion;
	private java.lang.Boolean general;
	private java.util.Date fhAlta;
	private java.lang.Integer idUserLock;
	private java.util.Date fhBaja;
	private java.lang.Integer ccssId;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="Codigo"
     */
	public int getCodigo () {
		return codigo;
	}

	/**
	 * Set the unique identifier of this class
	 * @param codigo the new ID
	 */
	public void setCodigo (int codigo) {
		this.codigo = codigo;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: Descripcion
	 */
	public java.lang.String getDescripcion () {
		return descripcion;
	}

	/**
	 * Set the value related to the column: Descripcion
	 * @param descripcion the Descripcion value
	 */
	public void setDescripcion (java.lang.String descripcion) {
		this.descripcion = descripcion;
	}



	/**
	 * Return the value associated with the column: General
	 */
	public java.lang.Boolean isGeneral () {
		return general;
	}

	/**
	 * Set the value related to the column: General
	 * @param general the General value
	 */
	public void setGeneral (java.lang.Boolean general) {
		this.general = general;
	}



	/**
	 * Return the value associated with the column: FhAlta
	 */
	public java.util.Date getFhAlta () {
		return fhAlta;
	}

	/**
	 * Set the value related to the column: FhAlta
	 * @param fhAlta the FhAlta value
	 */
	public void setFhAlta (java.util.Date fhAlta) {
		this.fhAlta = fhAlta;
	}



	/**
	 * Return the value associated with the column: IdUserLock
	 */
	public java.lang.Integer getIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: IdUserLock
	 * @param idUserLock the IdUserLock value
	 */
	public void setIdUserLock (java.lang.Integer idUserLock) {
		this.idUserLock = idUserLock;
	}



	/**
	 * Return the value associated with the column: FhBaja
	 */
	public java.util.Date getFhBaja () {
		return fhBaja;
	}

	/**
	 * Set the value related to the column: FhBaja
	 * @param fhBaja the FhBaja value
	 */
	public void setFhBaja (java.util.Date fhBaja) {
		this.fhBaja = fhBaja;
	}



	/**
	 * Return the value associated with the column: ccssId
	 */
	public java.lang.Integer getCcssId () {
		return ccssId;
	}

	/**
	 * Set the value related to the column: ccssId
	 * @param ccssId the ccssId value
	 */
	public void setCcssId (java.lang.Integer ccssId) {
		this.ccssId = ccssId;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MlistaPrecios)) return false;
		else {
			com.refinor.extranet.data.MlistaPrecios mlistaPrecios = (com.refinor.extranet.data.MlistaPrecios) obj;
			return (this.getCodigo() == mlistaPrecios.getCodigo());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getCodigo();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}