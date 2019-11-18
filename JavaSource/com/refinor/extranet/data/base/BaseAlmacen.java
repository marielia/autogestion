package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the Almacen table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Almacen"
 */

public abstract class BaseAlmacen  implements Serializable {

	public static String REF = "Almacen";
	public static String PROP_ES_TANQUE = "esTanque";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_ID = "id";
	public static String PROP_EMPLEADO_ID_RESPONSABLE = "empleadoIdResponsable";
	public static String PROP_ALMACEN_DESCRIPCION = "almacenDescripcion";


	// constructors
	public BaseAlmacen () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAlmacen (com.refinor.extranet.data.AlmacenId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.AlmacenId id;

	// fields
	private int empleadoIdResponsable;
	private java.lang.String almacenDescripcion;
	private boolean activo;
	private boolean idUserLock;
	private boolean esTanque;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.AlmacenId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.AlmacenId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: EmpleadoIdResponsable
	 */
	public int getEmpleadoIdResponsable () {
		return empleadoIdResponsable;
	}

	/**
	 * Set the value related to the column: EmpleadoIdResponsable
	 * @param empleadoIdResponsable the EmpleadoIdResponsable value
	 */
	public void setEmpleadoIdResponsable (int empleadoIdResponsable) {
		this.empleadoIdResponsable = empleadoIdResponsable;
	}



	/**
	 * Return the value associated with the column: AlmacenDescripcion
	 */
	public java.lang.String getAlmacenDescripcion () {
		return almacenDescripcion;
	}

	/**
	 * Set the value related to the column: AlmacenDescripcion
	 * @param almacenDescripcion the AlmacenDescripcion value
	 */
	public void setAlmacenDescripcion (java.lang.String almacenDescripcion) {
		this.almacenDescripcion = almacenDescripcion;
	}



	/**
	 * Return the value associated with the column: Activo
	 */
	public boolean isActivo () {
		return activo;
	}

	/**
	 * Set the value related to the column: Activo
	 * @param activo the Activo value
	 */
	public void setActivo (boolean activo) {
		this.activo = activo;
	}



	/**
	 * Return the value associated with the column: IdUserLock
	 */
	public boolean isIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: IdUserLock
	 * @param idUserLock the IdUserLock value
	 */
	public void setIdUserLock (boolean idUserLock) {
		this.idUserLock = idUserLock;
	}



	/**
	 * Return the value associated with the column: esTanque
	 */
	public boolean isEsTanque () {
		return esTanque;
	}

	/**
	 * Set the value related to the column: esTanque
	 * @param esTanque the esTanque value
	 */
	public void setEsTanque (boolean esTanque) {
		this.esTanque = esTanque;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Almacen)) return false;
		else {
			com.refinor.extranet.data.Almacen almacen = (com.refinor.extranet.data.Almacen) obj;
			if (null == this.getId() || null == almacen.getId()) return false;
			else return (this.getId().equals(almacen.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}