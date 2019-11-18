package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the mVehiculo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="mVehiculo"
 */

public abstract class BaseMvehiculo  implements Serializable {

	public static String REF = "Mvehiculo";
	public static String PROP_F_BAJA = "FBaja";
	public static String PROP_COD_CLIENTE = "codCliente";
	public static String PROP_INICIALIZADO = "inicializado";
	public static String PROP_COD_BARRA = "codBarra";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_F_ALTA = "FAlta";
	public static String PROP_COD_UNIDAD_N = "codUnidadN";
	public static String PROP_DOMINIO = "dominio";


	// constructors
	public BaseMvehiculo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMvehiculo (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.Integer codCliente;
	private java.lang.String dominio;
	private java.lang.String codBarra;
	private java.lang.Boolean inicializado;
	private java.lang.Integer codUnidadN;
	private java.lang.Boolean activo;
	private java.lang.Boolean idUserLock;
	private java.util.Date fAlta;
	private java.util.Date fBaja;



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
	 * Return the value associated with the column: dominio
	 */
	public java.lang.String getDominio () {
		return dominio;
	}

	/**
	 * Set the value related to the column: dominio
	 * @param dominio the dominio value
	 */
	public void setDominio (java.lang.String dominio) {
		this.dominio = dominio;
	}



	/**
	 * Return the value associated with the column: codBarra
	 */
	public java.lang.String getCodBarra () {
		return codBarra;
	}

	/**
	 * Set the value related to the column: codBarra
	 * @param codBarra the codBarra value
	 */
	public void setCodBarra (java.lang.String codBarra) {
		this.codBarra = codBarra;
	}



	/**
	 * Return the value associated with the column: inicializado
	 */
	public java.lang.Boolean isInicializado () {
		return inicializado;
	}

	/**
	 * Set the value related to the column: inicializado
	 * @param inicializado the inicializado value
	 */
	public void setInicializado (java.lang.Boolean inicializado) {
		this.inicializado = inicializado;
	}



	/**
	 * Return the value associated with the column: codUnidadN
	 */
	public java.lang.Integer getCodUnidadN () {
		return codUnidadN;
	}

	/**
	 * Set the value related to the column: codUnidadN
	 * @param codUnidadN the codUnidadN value
	 */
	public void setCodUnidadN (java.lang.Integer codUnidadN) {
		this.codUnidadN = codUnidadN;
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



	/**
	 * Return the value associated with the column: f_alta
	 */
	public java.util.Date getFAlta () {
		return fAlta;
	}

	/**
	 * Set the value related to the column: f_alta
	 * @param fAlta the f_alta value
	 */
	public void setFAlta (java.util.Date fAlta) {
		this.fAlta = fAlta;
	}



	/**
	 * Return the value associated with the column: f_baja
	 */
	public java.util.Date getFBaja () {
		return fBaja;
	}

	/**
	 * Set the value related to the column: f_baja
	 * @param fBaja the f_baja value
	 */
	public void setFBaja (java.util.Date fBaja) {
		this.fBaja = fBaja;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mvehiculo)) return false;
		else {
			com.refinor.extranet.data.Mvehiculo mvehiculo = (com.refinor.extranet.data.Mvehiculo) obj;
			return (this.getCodigo() == mvehiculo.getCodigo());
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