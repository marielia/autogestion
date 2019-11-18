package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MCcss table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MCcss"
 */

public abstract class BaseMccss  implements Serializable {

	public static String REF = "Mccss";
	public static String PROP_F_BAJA = "FBaja";
	public static String PROP_DESC_CCSS = "descCcss";
	public static String PROP_COD_CCSS = "codCcss";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_F_ALTA = "FAlta";
	public static String PROP_CTA_BCA = "ctaBca";


	// constructors
	public BaseMccss () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMccss (int codCcss) {
		this.setCodCcss(codCcss);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codCcss;

	// fields
	private java.lang.String descCcss;
	private java.lang.String ctaBca;
	private java.lang.Boolean idUserLock;
	private java.util.Date fAlta;
	private java.util.Date fBaja;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="cod_ccss"
     */
	public int getCodCcss () {
		return codCcss;
	}

	/**
	 * Set the unique identifier of this class
	 * @param codCcss the new ID
	 */
	public void setCodCcss (int codCcss) {
		this.codCcss = codCcss;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: desc_ccss
	 */
	public java.lang.String getDescCcss () {
		return descCcss;
	}

	/**
	 * Set the value related to the column: desc_ccss
	 * @param descCcss the desc_ccss value
	 */
	public void setDescCcss (java.lang.String descCcss) {
		this.descCcss = descCcss;
	}



	/**
	 * Return the value associated with the column: cta_bca
	 */
	public java.lang.String getCtaBca () {
		return ctaBca;
	}

	/**
	 * Set the value related to the column: cta_bca
	 * @param ctaBca the cta_bca value
	 */
	public void setCtaBca (java.lang.String ctaBca) {
		this.ctaBca = ctaBca;
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
		if (!(obj instanceof com.refinor.extranet.data.Mccss)) return false;
		else {
			com.refinor.extranet.data.Mccss mccss = (com.refinor.extranet.data.Mccss) obj;
			return (this.getCodCcss() == mccss.getCodCcss());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getCodCcss();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}