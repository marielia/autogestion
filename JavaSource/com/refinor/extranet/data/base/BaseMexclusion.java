package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MExclusion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MExclusion"
 */

public abstract class BaseMexclusion  implements Serializable {

	public static String REF = "Mexclusion";
	public static String PROP_F_HASTA = "FHasta";
	public static String PROP_COD_CLI = "codCli";
	public static String PROP_ID = "id";
	public static String PROP_PORC_EXC = "porcExc";
	public static String PROP_COD_PCIA = "codPcia";
	public static String PROP_T_EXCLUSION = "TExclusion";
	public static String PROP_F_DESDE = "FDesde";


	// constructors
	public BaseMexclusion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMexclusion (int id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int id;

	// fields
	private java.lang.Integer tExclusion;
	private java.lang.Integer codCli;
	private java.lang.Integer codPcia;
	private java.lang.Integer porcExc;
	private java.util.Date fDesde;
	private java.util.Date fHasta;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
     */
	public int getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (int id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: t_exclusion
	 */
	public java.lang.Integer getTExclusion () {
		return tExclusion;
	}

	/**
	 * Set the value related to the column: t_exclusion
	 * @param tExclusion the t_exclusion value
	 */
	public void setTExclusion (java.lang.Integer tExclusion) {
		this.tExclusion = tExclusion;
	}



	/**
	 * Return the value associated with the column: cod_cli
	 */
	public java.lang.Integer getCodCli () {
		return codCli;
	}

	/**
	 * Set the value related to the column: cod_cli
	 * @param codCli the cod_cli value
	 */
	public void setCodCli (java.lang.Integer codCli) {
		this.codCli = codCli;
	}



	/**
	 * Return the value associated with the column: cod_pcia
	 */
	public java.lang.Integer getCodPcia () {
		return codPcia;
	}

	/**
	 * Set the value related to the column: cod_pcia
	 * @param codPcia the cod_pcia value
	 */
	public void setCodPcia (java.lang.Integer codPcia) {
		this.codPcia = codPcia;
	}



	/**
	 * Return the value associated with the column: porc_exc
	 */
	public java.lang.Integer getPorcExc () {
		return porcExc;
	}

	/**
	 * Set the value related to the column: porc_exc
	 * @param porcExc the porc_exc value
	 */
	public void setPorcExc (java.lang.Integer porcExc) {
		this.porcExc = porcExc;
	}



	/**
	 * Return the value associated with the column: f_desde
	 */
	public java.util.Date getFDesde () {
		return fDesde;
	}

	/**
	 * Set the value related to the column: f_desde
	 * @param fDesde the f_desde value
	 */
	public void setFDesde (java.util.Date fDesde) {
		this.fDesde = fDesde;
	}



	/**
	 * Return the value associated with the column: f_hasta
	 */
	public java.util.Date getFHasta () {
		return fHasta;
	}

	/**
	 * Set the value related to the column: f_hasta
	 * @param fHasta the f_hasta value
	 */
	public void setFHasta (java.util.Date fHasta) {
		this.fHasta = fHasta;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mexclusion)) return false;
		else {
			com.refinor.extranet.data.Mexclusion mexclusion = (com.refinor.extranet.data.Mexclusion) obj;
			return (this.getId() == mexclusion.getId());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getId();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}