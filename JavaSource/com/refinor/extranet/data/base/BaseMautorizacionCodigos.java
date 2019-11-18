package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MAutorizacionCodigos table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MAutorizacionCodigos"
 */

public abstract class BaseMautorizacionCodigos  implements Serializable {

	public static String REF = "MautorizacionCodigos";
	public static String PROP_F_USADO = "FUsado";
	public static String PROP_USER_OTORGADO = "userOtorgado";
	public static String PROP_USER_USADO = "userUsado";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_F_OTORGADO = "FOtorgado";
	public static String PROP_NRO_AUTO = "nroAuto";


	// constructors
	public BaseMautorizacionCodigos () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMautorizacionCodigos (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.String nroAuto;
	private java.util.Date fOtorgado;
	private java.lang.Integer userOtorgado;
	private java.util.Date fUsado;
	private java.lang.Integer userUsado;



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
	 * Return the value associated with the column: nro_auto
	 */
	public java.lang.String getNroAuto () {
		return nroAuto;
	}

	/**
	 * Set the value related to the column: nro_auto
	 * @param nroAuto the nro_auto value
	 */
	public void setNroAuto (java.lang.String nroAuto) {
		this.nroAuto = nroAuto;
	}



	/**
	 * Return the value associated with the column: f_otorgado
	 */
	public java.util.Date getFOtorgado () {
		return fOtorgado;
	}

	/**
	 * Set the value related to the column: f_otorgado
	 * @param fOtorgado the f_otorgado value
	 */
	public void setFOtorgado (java.util.Date fOtorgado) {
		this.fOtorgado = fOtorgado;
	}



	/**
	 * Return the value associated with the column: user_otorgado
	 */
	public java.lang.Integer getUserOtorgado () {
		return userOtorgado;
	}

	/**
	 * Set the value related to the column: user_otorgado
	 * @param userOtorgado the user_otorgado value
	 */
	public void setUserOtorgado (java.lang.Integer userOtorgado) {
		this.userOtorgado = userOtorgado;
	}



	/**
	 * Return the value associated with the column: f_usado
	 */
	public java.util.Date getFUsado () {
		return fUsado;
	}

	/**
	 * Set the value related to the column: f_usado
	 * @param fUsado the f_usado value
	 */
	public void setFUsado (java.util.Date fUsado) {
		this.fUsado = fUsado;
	}



	/**
	 * Return the value associated with the column: user_usado
	 */
	public java.lang.Integer getUserUsado () {
		return userUsado;
	}

	/**
	 * Set the value related to the column: user_usado
	 * @param userUsado the user_usado value
	 */
	public void setUserUsado (java.lang.Integer userUsado) {
		this.userUsado = userUsado;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MautorizacionCodigos)) return false;
		else {
			com.refinor.extranet.data.MautorizacionCodigos mautorizacionCodigos = (com.refinor.extranet.data.MautorizacionCodigos) obj;
			return (this.getCodigo() == mautorizacionCodigos.getCodigo());
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