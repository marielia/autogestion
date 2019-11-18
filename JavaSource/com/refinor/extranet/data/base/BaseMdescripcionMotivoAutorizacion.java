package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MDescripcionMotivoAutorizacion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MDescripcionMotivoAutorizacion"
 */

public abstract class BaseMdescripcionMotivoAutorizacion  implements Serializable {

	public static String REF = "MdescripcionMotivoAutorizacion";
	public static String PROP_CODIGO_MOTIVO_AUTORIZACION = "codigoMotivoAutorizacion";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMdescripcionMotivoAutorizacion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMdescripcionMotivoAutorizacion (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String descripcion;
	private java.lang.Integer codigoMotivoAutorizacion;



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



	/**
	 * Return the value associated with the column: codigo_motivo_autorizacion
	 */
	public java.lang.Integer getCodigoMotivoAutorizacion () {
		return codigoMotivoAutorizacion;
	}

	/**
	 * Set the value related to the column: codigo_motivo_autorizacion
	 * @param codigoMotivoAutorizacion the codigo_motivo_autorizacion value
	 */
	public void setCodigoMotivoAutorizacion (java.lang.Integer codigoMotivoAutorizacion) {
		this.codigoMotivoAutorizacion = codigoMotivoAutorizacion;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MdescripcionMotivoAutorizacion)) return false;
		else {
			com.refinor.extranet.data.MdescripcionMotivoAutorizacion mdescripcionMotivoAutorizacion = (com.refinor.extranet.data.MdescripcionMotivoAutorizacion) obj;
			return (this.getCodigo() == mdescripcionMotivoAutorizacion.getCodigo());
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