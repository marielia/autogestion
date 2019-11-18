package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MExclusionTipo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MExclusionTipo"
 */

public abstract class BaseMexclusionTipo  implements Serializable {

	public static String REF = "MexclusionTipo";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMexclusionTipo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMexclusionTipo (int codigo) {
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
		if (!(obj instanceof com.refinor.extranet.data.MexclusionTipo)) return false;
		else {
			com.refinor.extranet.data.MexclusionTipo mexclusionTipo = (com.refinor.extranet.data.MexclusionTipo) obj;
			return (this.getCodigo() == mexclusionTipo.getCodigo());
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