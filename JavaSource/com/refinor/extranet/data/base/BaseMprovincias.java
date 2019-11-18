package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MProvincias table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MProvincias"
 */

public abstract class BaseMprovincias  implements Serializable {

	public static String REF = "Mprovincias";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_FH_ALTA = "fhAlta";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMprovincias () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMprovincias (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String descripcion;
	private java.util.Date fhAlta;
	private java.util.Date fhBaja;
	private java.lang.Integer idUserLock;



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
	 * Return the value associated with the column: idUserLock
	 */
	public java.lang.Integer getIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: idUserLock
	 * @param idUserLock the idUserLock value
	 */
	public void setIdUserLock (java.lang.Integer idUserLock) {
		this.idUserLock = idUserLock;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mprovincias)) return false;
		else {
			com.refinor.extranet.data.Mprovincias mprovincias = (com.refinor.extranet.data.Mprovincias) obj;
			return (this.getCodigo() == mprovincias.getCodigo());
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