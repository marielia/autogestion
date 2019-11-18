package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MAutorizacionMotivo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MAutorizacionMotivo"
 */

public abstract class BaseMautorizacionMotivo  implements Serializable {

	public static String REF = "MautorizacionMotivo";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMautorizacionMotivo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMautorizacionMotivo (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String descripcion;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="codigo"
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
	 * Return the value associated with the column: descripcion
	 */
	public java.lang.String getDescripcion () {
		return descripcion;
	}

	/**
	 * Set the value related to the column: descripcion
	 * @param descripcion the descripcion value
	 */
	public void setDescripcion (java.lang.String descripcion) {
		this.descripcion = descripcion;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MautorizacionMotivo)) return false;
		else {
			com.refinor.extranet.data.MautorizacionMotivo mautorizacionMotivo = (com.refinor.extranet.data.MautorizacionMotivo) obj;
			return (this.getCodigo() == mautorizacionMotivo.getCodigo());
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