package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MLimiteCarga table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MLimiteCarga"
 */

public abstract class BaseMlimiteCarga  implements Serializable {

	public static String REF = "MlimiteCarga";
	public static String PROP_ILIMITADO = "ilimitado";
	public static String PROP_LITROS_PDIA = "litrosPdia";
	public static String PROP_LITROS_PCARGA = "litrosPcarga";
	public static String PROP_ID = "id";
	public static String PROP_OBS = "obs";
	public static String PROP_FLIAGRUART = "fliaGruArt";
	public static String PROP_LITROS_PMES = "litrosPmes";
	

	// constructors
	public BaseMlimiteCarga () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMlimiteCarga (com.refinor.extranet.data.MlimiteCargaId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MlimiteCargaId id;

	// fields
	private java.lang.Boolean ilimitado;
	private java.math.BigDecimal litrosPcarga;
	private java.math.BigDecimal litrosPdia;
	private java.math.BigDecimal litrosPmes;
	private java.lang.String obs;
	private java.lang.String fliaGruArt;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MlimiteCargaId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MlimiteCargaId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: ilimitado
	 */
	public java.lang.Boolean isIlimitado () {
		return ilimitado;
	}

	/**
	 * Set the value related to the column: ilimitado
	 * @param ilimitado the ilimitado value
	 */
	public void setIlimitado (java.lang.Boolean ilimitado) {
		this.ilimitado = ilimitado;
	}



	/**
	 * Return the value associated with the column: litros_pcarga
	 */
	public java.math.BigDecimal getLitrosPcarga () {
		return litrosPcarga;
	}

	/**
	 * Set the value related to the column: litros_pcarga
	 * @param litrosPcarga the litros_pcarga value
	 */
	public void setLitrosPcarga (java.math.BigDecimal litrosPcarga) {
		this.litrosPcarga = litrosPcarga;
	}



	/**
	 * Return the value associated with the column: litros_pdia
	 */
	public java.math.BigDecimal getLitrosPdia () {
		return litrosPdia;
	}

	/**
	 * Set the value related to the column: litros_pdia
	 * @param litrosPdia the litros_pdia value
	 */
	public void setLitrosPdia (java.math.BigDecimal litrosPdia) {
		this.litrosPdia = litrosPdia;
	}



	/**
	 * Return the value associated with the column: litros_pmes
	 */
	public java.math.BigDecimal getLitrosPmes () {
		return litrosPmes;
	}

	/**
	 * Set the value related to the column: litros_pmes
	 * @param litrosPmes the litros_pmes value
	 */
	public void setLitrosPmes (java.math.BigDecimal litrosPmes) {
		this.litrosPmes = litrosPmes;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MlimiteCarga)) return false;
		else {
			com.refinor.extranet.data.MlimiteCarga mlimiteCarga = (com.refinor.extranet.data.MlimiteCarga) obj;
			if (null == this.getId() || null == mlimiteCarga.getId()) return false;
			else return (this.getId().equals(mlimiteCarga.getId()));
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

	public java.lang.String getFliaGruArt() {
		return fliaGruArt;
	}

	public void setFliaGruArt(java.lang.String fliaGruArt) {
		this.fliaGruArt = fliaGruArt;
	}

	public java.lang.String getObs() {
		return obs;
	}

	public void setObs(java.lang.String obs) {
		this.obs = obs;
	}


}