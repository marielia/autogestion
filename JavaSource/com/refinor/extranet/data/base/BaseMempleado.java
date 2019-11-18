package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MEmpleado table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MEmpleado"
 */

public abstract class BaseMempleado  implements Serializable {

	public static String REF = "Mempleado";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_NRO_LEGAJO = "nroLegajo";
	public static String PROP_ID = "id";
	public static String PROP_DNI = "dni";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMempleado () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMempleado (com.refinor.extranet.data.MempleadoId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MempleadoId id;

	// fields
	private java.lang.String descripcion;
	private java.lang.Integer nroLegajo;
	private java.lang.Boolean activo;
	private int idUserLock;
	private java.lang.Integer dni;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MempleadoId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MempleadoId id) {
		this.id = id;
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
	 * Return the value associated with the column: nro_legajo
	 */
	public java.lang.Integer getNroLegajo () {
		return nroLegajo;
	}

	/**
	 * Set the value related to the column: nro_legajo
	 * @param nroLegajo the nro_legajo value
	 */
	public void setNroLegajo (java.lang.Integer nroLegajo) {
		this.nroLegajo = nroLegajo;
	}



	/**
	 * Return the value associated with the column: activo
	 */
	public java.lang.Boolean isActivo () {
		return activo;
	}

	/**
	 * Set the value related to the column: activo
	 * @param activo the activo value
	 */
	public void setActivo (java.lang.Boolean activo) {
		this.activo = activo;
	}



	/**
	 * Return the value associated with the column: IdUserLock
	 */
	public int getIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: IdUserLock
	 * @param idUserLock the IdUserLock value
	 */
	public void setIdUserLock (int idUserLock) {
		this.idUserLock = idUserLock;
	}



	/**
	 * Return the value associated with the column: dni
	 */
	public java.lang.Integer getDni () {
		return dni;
	}

	/**
	 * Set the value related to the column: dni
	 * @param dni the dni value
	 */
	public void setDni (java.lang.Integer dni) {
		this.dni = dni;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mempleado)) return false;
		else {
			com.refinor.extranet.data.Mempleado mempleado = (com.refinor.extranet.data.Mempleado) obj;
			if (null == this.getId() || null == mempleado.getId()) return false;
			else return (this.getId().equals(mempleado.getId()));
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