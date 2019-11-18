package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MVendedor table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MVendedor"
 */

public abstract class BaseMvendedor  implements Serializable {

	public static String REF = "Mvendedor";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_NOMBRE = "nombre";
	public static String PROP_APELLIDO = "apellido";


	// constructors
	public BaseMvendedor () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMvendedor (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String nombre;
	private java.lang.String apellido;
	private java.lang.Boolean idUserLock;



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
	 * Return the value associated with the column: nombre
	 */
	public java.lang.String getNombre () {
		return nombre;
	}

	/**
	 * Set the value related to the column: nombre
	 * @param nombre the nombre value
	 */
	public void setNombre (java.lang.String nombre) {
		this.nombre = nombre;
	}



	/**
	 * Return the value associated with the column: apellido
	 */
	public java.lang.String getApellido () {
		return apellido;
	}

	/**
	 * Set the value related to the column: apellido
	 * @param apellido the apellido value
	 */
	public void setApellido (java.lang.String apellido) {
		this.apellido = apellido;
	}



	/**
	 * Return the value associated with the column: idUserLock
	 */
	public java.lang.Boolean isIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: idUserLock
	 * @param idUserLock the idUserLock value
	 */
	public void setIdUserLock (java.lang.Boolean idUserLock) {
		this.idUserLock = idUserLock;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mvendedor)) return false;
		else {
			com.refinor.extranet.data.Mvendedor mvendedor = (com.refinor.extranet.data.Mvendedor) obj;
			return (this.getCodigo() == mvendedor.getCodigo());
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