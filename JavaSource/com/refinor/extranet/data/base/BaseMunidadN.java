package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the mUnidadN table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="mUnidadN"
 */

public abstract class BaseMunidadN  implements Serializable {

	public static String REF = "MunidadN";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_COD_GRUPO_UN = "codGrupoUn";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMunidadN () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMunidadN (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String descripcion;
	private java.lang.Integer codGrupoUn;
	private java.lang.Boolean activo;
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
	 * Return the value associated with the column: codGrupoUN
	 */
	public java.lang.Integer getCodGrupoUn () {
		return codGrupoUn;
	}

	/**
	 * Set the value related to the column: codGrupoUN
	 * @param codGrupoUn the codGrupoUN value
	 */
	public void setCodGrupoUn (java.lang.Integer codGrupoUn) {
		this.codGrupoUn = codGrupoUn;
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
	public java.lang.Boolean isIdUserLock () {
		return idUserLock;
	}

	/**
	 * Set the value related to the column: IdUserLock
	 * @param idUserLock the IdUserLock value
	 */
	public void setIdUserLock (java.lang.Boolean idUserLock) {
		this.idUserLock = idUserLock;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MunidadN)) return false;
		else {
			com.refinor.extranet.data.MunidadN munidadN = (com.refinor.extranet.data.MunidadN) obj;
			return (this.getCodigo() == munidadN.getCodigo());
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