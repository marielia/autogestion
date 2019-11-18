package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MIBImpuesto table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MIBImpuesto"
 */

public abstract class BaseMibimpuesto  implements Serializable {

	public static String REF = "Mibimpuesto";
	public static String PROP_PNETO = "pneto";
	public static String PROP_SOBRE_PTOTAL = "sobrePtotal";
	public static String PROP_PORCENTAJE = "porcentaje";
	public static String PROP_F_BAJA = "FBaja";
	public static String PROP_MONTO_MIN = "montoMin";
	public static String PROP_SOBRE_PNETO = "sobrePneto";
	public static String PROP_TASAS = "tasas";
	public static String PROP_ITC = "itc";
	public static String PROP_SOBRE_IIBB = "sobreIibb";
	public static String PROP_IMP_INT = "impInt";
	public static String PROP_ID = "id";
	public static String PROP_PERMITE_NEG = "permiteNeg";
	public static String PROP_PTOTAL = "ptotal";
	public static String PROP_SOBRE_PNETO_ITC_TASA = "sobrePnetoItcTasa";


	// constructors
	public BaseMibimpuesto () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMibimpuesto (com.refinor.extranet.data.MibimpuestoId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MibimpuestoId id;

	// fields
	private java.lang.Boolean pneto;
	private java.lang.Boolean impInt;
	private java.lang.Boolean itc;
	private java.lang.Boolean tasas;
	private java.lang.Boolean ptotal;
	private java.math.BigDecimal porcentaje;
	private java.math.BigDecimal montoMin;
	private java.lang.Boolean sobrePneto;
	private java.lang.Boolean sobrePtotal;
	private java.lang.Boolean sobrePnetoItcTasa;
	private java.lang.Boolean sobreIibb;
	private java.lang.Boolean permiteNeg;
	private java.util.Date fBaja;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MibimpuestoId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MibimpuestoId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: pneto
	 */
	public java.lang.Boolean isPneto () {
		return pneto;
	}

	/**
	 * Set the value related to the column: pneto
	 * @param pneto the pneto value
	 */
	public void setPneto (java.lang.Boolean pneto) {
		this.pneto = pneto;
	}



	/**
	 * Return the value associated with the column: imp_int
	 */
	public java.lang.Boolean isImpInt () {
		return impInt;
	}

	/**
	 * Set the value related to the column: imp_int
	 * @param impInt the imp_int value
	 */
	public void setImpInt (java.lang.Boolean impInt) {
		this.impInt = impInt;
	}



	/**
	 * Return the value associated with the column: itc
	 */
	public java.lang.Boolean isItc () {
		return itc;
	}

	/**
	 * Set the value related to the column: itc
	 * @param itc the itc value
	 */
	public void setItc (java.lang.Boolean itc) {
		this.itc = itc;
	}



	/**
	 * Return the value associated with the column: tasas
	 */
	public java.lang.Boolean isTasas () {
		return tasas;
	}

	/**
	 * Set the value related to the column: tasas
	 * @param tasas the tasas value
	 */
	public void setTasas (java.lang.Boolean tasas) {
		this.tasas = tasas;
	}



	/**
	 * Return the value associated with the column: ptotal
	 */
	public java.lang.Boolean isPtotal () {
		return ptotal;
	}

	/**
	 * Set the value related to the column: ptotal
	 * @param ptotal the ptotal value
	 */
	public void setPtotal (java.lang.Boolean ptotal) {
		this.ptotal = ptotal;
	}



	/**
	 * Return the value associated with the column: porcentaje
	 */
	public java.math.BigDecimal getPorcentaje () {
		return porcentaje;
	}

	/**
	 * Set the value related to the column: porcentaje
	 * @param porcentaje the porcentaje value
	 */
	public void setPorcentaje (java.math.BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}



	/**
	 * Return the value associated with the column: monto_min
	 */
	public java.math.BigDecimal getMontoMin () {
		return montoMin;
	}

	/**
	 * Set the value related to the column: monto_min
	 * @param montoMin the monto_min value
	 */
	public void setMontoMin (java.math.BigDecimal montoMin) {
		this.montoMin = montoMin;
	}



	/**
	 * Return the value associated with the column: sobre_pneto
	 */
	public java.lang.Boolean isSobrePneto () {
		return sobrePneto;
	}

	/**
	 * Set the value related to the column: sobre_pneto
	 * @param sobrePneto the sobre_pneto value
	 */
	public void setSobrePneto (java.lang.Boolean sobrePneto) {
		this.sobrePneto = sobrePneto;
	}



	/**
	 * Return the value associated with the column: sobre_ptotal
	 */
	public java.lang.Boolean isSobrePtotal () {
		return sobrePtotal;
	}

	/**
	 * Set the value related to the column: sobre_ptotal
	 * @param sobrePtotal the sobre_ptotal value
	 */
	public void setSobrePtotal (java.lang.Boolean sobrePtotal) {
		this.sobrePtotal = sobrePtotal;
	}



	/**
	 * Return the value associated with the column: sobre_pneto_itc_tasa
	 */
	public java.lang.Boolean isSobrePnetoItcTasa () {
		return sobrePnetoItcTasa;
	}

	/**
	 * Set the value related to the column: sobre_pneto_itc_tasa
	 * @param sobrePnetoItcTasa the sobre_pneto_itc_tasa value
	 */
	public void setSobrePnetoItcTasa (java.lang.Boolean sobrePnetoItcTasa) {
		this.sobrePnetoItcTasa = sobrePnetoItcTasa;
	}



	/**
	 * Return the value associated with the column: sobre_iibb
	 */
	public java.lang.Boolean isSobreIibb () {
		return sobreIibb;
	}

	/**
	 * Set the value related to the column: sobre_iibb
	 * @param sobreIibb the sobre_iibb value
	 */
	public void setSobreIibb (java.lang.Boolean sobreIibb) {
		this.sobreIibb = sobreIibb;
	}



	/**
	 * Return the value associated with the column: permite_neg
	 */
	public java.lang.Boolean isPermiteNeg () {
		return permiteNeg;
	}

	/**
	 * Set the value related to the column: permite_neg
	 * @param permiteNeg the permite_neg value
	 */
	public void setPermiteNeg (java.lang.Boolean permiteNeg) {
		this.permiteNeg = permiteNeg;
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
		if (!(obj instanceof com.refinor.extranet.data.Mibimpuesto)) return false;
		else {
			com.refinor.extranet.data.Mibimpuesto mibimpuesto = (com.refinor.extranet.data.Mibimpuesto) obj;
			if (null == this.getId() || null == mibimpuesto.getId()) return false;
			else return (this.getId().equals(mibimpuesto.getId()));
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