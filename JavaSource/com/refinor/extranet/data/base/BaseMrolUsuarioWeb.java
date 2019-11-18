package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MRolUsuarioWeb table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MRolUsuarioWeb"
 */

public abstract class BaseMrolUsuarioWeb  implements Serializable {

	public static String REF = "MrolUsuarioWeb";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_DESCRIPCION_ROL = "descripcionRol";
	public static String PROP_MOSTRAR_ADMIN = "mostrarAdmin";


	// constructors
	public BaseMrolUsuarioWeb () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMrolUsuarioWeb (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String descripcionRol;
	private boolean mostrarAdmin;



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
	 * Return the value associated with the column: descripcion_rol
	 */
	public java.lang.String getDescripcionRol () {
		return descripcionRol;
	}

	/**
	 * Set the value related to the column: descripcion_rol
	 * @param descripcionRol the descripcion_rol value
	 */
	public void setDescripcionRol (java.lang.String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}



	/**
	 * Return the value associated with the column: mostrar_admin
	 */
	public boolean isMostrarAdmin () {
		return mostrarAdmin;
	}

	/**
	 * Set the value related to the column: mostrar_admin
	 * @param mostrarAdmin the mostrar_admin value
	 */
	public void setMostrarAdmin (boolean mostrarAdmin) {
		this.mostrarAdmin = mostrarAdmin;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MrolUsuarioWeb)) return false;
		else {
			com.refinor.extranet.data.MrolUsuarioWeb mrolUsuarioWeb = (com.refinor.extranet.data.MrolUsuarioWeb) obj;
			return (this.getCodigo() == mrolUsuarioWeb.getCodigo());
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