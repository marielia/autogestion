package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MGrupoUN table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MGrupoUN"
 */

public abstract class BaseMgrupoUn  implements Serializable {

	public static String REF = "MgrupoUn";
	public static String PROP_COD_CLIENTE = "codCliente";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_DESCRIPCION = "descripcion";


	// constructors
	public BaseMgrupoUn () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMgrupoUn (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String descripcion;
	private java.lang.Integer codCliente;
	private java.lang.Boolean activo;
	private java.lang.Integer idUserLock;



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
	 * Return the value associated with the column: codCliente
	 */
	public java.lang.Integer getCodCliente () {
		return codCliente;
	}

	/**
	 * Set the value related to the column: codCliente
	 * @param codCliente the codCliente value
	 */
	public void setCodCliente (java.lang.Integer codCliente) {
		this.codCliente = codCliente;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MgrupoUn)) return false;
		else {
			com.refinor.extranet.data.MgrupoUn mgrupoUn = (com.refinor.extranet.data.MgrupoUn) obj;
			return (this.getCodigo() == mgrupoUn.getCodigo());
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