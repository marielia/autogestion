package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MCuentasBancos table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MCuentasBancos"
 */

public abstract class BaseMcuentasBancos  implements Serializable {

	public static String REF = "McuentasBancos";
	public static String PROP_NRO_EJERCICIO_CONTABLE = "nroEjercicioContable";
	public static String PROP_ROWGUID = "rowguid";
	public static String PROP_BCO_SUC_CTA_SIST = "bcoSucCtaSist";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_FH_ALTA = "fhAlta";
	public static String PROP_NRO_ASIENTO_CONTABLE = "nroAsientoContable";
	public static String PROP_NRO_RENDICION_REFIPASS = "nroRendicionRefipass";
	public static String PROP_IMPORTE = "importe";
	public static String PROP_NRO_OPER_CAJA = "nroOperCaja";
	public static String PROP_FH_OPER = "fhOper";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_ID_CHEQUE = "idCheque";
	public static String PROP_ID = "id";


	// constructors
	public BaseMcuentasBancos () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMcuentasBancos (com.refinor.extranet.data.McuentasBancosId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.McuentasBancosId id;

	// fields
	private java.lang.String bcoSucCtaSist;
	private java.lang.Double importe;
	private java.lang.Integer idCheque;
	private java.util.Date fhOper;
	private java.util.Date fhAlta;
	private java.util.Date fhBaja;
	private java.lang.Integer idUserLock;
	private java.lang.Long nroEjercicioContable;
	private java.lang.Long nroAsientoContable;
	private java.lang.Long nroRendicionRefipass;
	private java.lang.String rowguid;
	private java.lang.Integer nroOperCaja;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.McuentasBancosId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.McuentasBancosId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: BcoSucCtaSist
	 */
	public java.lang.String getBcoSucCtaSist () {
		return bcoSucCtaSist;
	}

	/**
	 * Set the value related to the column: BcoSucCtaSist
	 * @param bcoSucCtaSist the BcoSucCtaSist value
	 */
	public void setBcoSucCtaSist (java.lang.String bcoSucCtaSist) {
		this.bcoSucCtaSist = bcoSucCtaSist;
	}



	/**
	 * Return the value associated with the column: Importe
	 */
	public java.lang.Double getImporte () {
		return importe;
	}

	/**
	 * Set the value related to the column: Importe
	 * @param importe the Importe value
	 */
	public void setImporte (java.lang.Double importe) {
		this.importe = importe;
	}



	/**
	 * Return the value associated with the column: IdCheque
	 */
	public java.lang.Integer getIdCheque () {
		return idCheque;
	}

	/**
	 * Set the value related to the column: IdCheque
	 * @param idCheque the IdCheque value
	 */
	public void setIdCheque (java.lang.Integer idCheque) {
		this.idCheque = idCheque;
	}



	/**
	 * Return the value associated with the column: FhOper
	 */
	public java.util.Date getFhOper () {
		return fhOper;
	}

	/**
	 * Set the value related to the column: FhOper
	 * @param fhOper the FhOper value
	 */
	public void setFhOper (java.util.Date fhOper) {
		this.fhOper = fhOper;
	}



	/**
	 * Return the value associated with the column: FhAlta
	 */
	public java.util.Date getFhAlta () {
		return fhAlta;
	}

	/**
	 * Set the value related to the column: FhAlta
	 * @param fhAlta the FhAlta value
	 */
	public void setFhAlta (java.util.Date fhAlta) {
		this.fhAlta = fhAlta;
	}



	/**
	 * Return the value associated with the column: FhBaja
	 */
	public java.util.Date getFhBaja () {
		return fhBaja;
	}

	/**
	 * Set the value related to the column: FhBaja
	 * @param fhBaja the FhBaja value
	 */
	public void setFhBaja (java.util.Date fhBaja) {
		this.fhBaja = fhBaja;
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



	/**
	 * Return the value associated with the column: NroEjercicioContable
	 */
	public java.lang.Long getNroEjercicioContable () {
		return nroEjercicioContable;
	}

	/**
	 * Set the value related to the column: NroEjercicioContable
	 * @param nroEjercicioContable the NroEjercicioContable value
	 */
	public void setNroEjercicioContable (java.lang.Long nroEjercicioContable) {
		this.nroEjercicioContable = nroEjercicioContable;
	}



	/**
	 * Return the value associated with the column: NroAsientoContable
	 */
	public java.lang.Long getNroAsientoContable () {
		return nroAsientoContable;
	}

	/**
	 * Set the value related to the column: NroAsientoContable
	 * @param nroAsientoContable the NroAsientoContable value
	 */
	public void setNroAsientoContable (java.lang.Long nroAsientoContable) {
		this.nroAsientoContable = nroAsientoContable;
	}



	/**
	 * Return the value associated with the column: NroRendicionRefipass
	 */
	public java.lang.Long getNroRendicionRefipass () {
		return nroRendicionRefipass;
	}

	/**
	 * Set the value related to the column: NroRendicionRefipass
	 * @param nroRendicionRefipass the NroRendicionRefipass value
	 */
	public void setNroRendicionRefipass (java.lang.Long nroRendicionRefipass) {
		this.nroRendicionRefipass = nroRendicionRefipass;
	}



	/**
	 * Return the value associated with the column: rowguid
	 */
	public java.lang.String getRowguid () {
		return rowguid;
	}

	/**
	 * Set the value related to the column: rowguid
	 * @param rowguid the rowguid value
	 */
	public void setRowguid (java.lang.String rowguid) {
		this.rowguid = rowguid;
	}



	/**
	 * Return the value associated with the column: NroOper_Caja
	 */
	public java.lang.Integer getNroOperCaja () {
		return nroOperCaja;
	}

	/**
	 * Set the value related to the column: NroOper_Caja
	 * @param nroOperCaja the NroOper_Caja value
	 */
	public void setNroOperCaja (java.lang.Integer nroOperCaja) {
		this.nroOperCaja = nroOperCaja;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.McuentasBancos)) return false;
		else {
			com.refinor.extranet.data.McuentasBancos mcuentasBancos = (com.refinor.extranet.data.McuentasBancos) obj;
			if (null == this.getId() || null == mcuentasBancos.getId()) return false;
			else return (this.getId().equals(mcuentasBancos.getId()));
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