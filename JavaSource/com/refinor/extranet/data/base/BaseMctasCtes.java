package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MCtasCtes table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MCtasCtes"
 */

public abstract class BaseMctasCtes  implements Serializable {

	public static String REF = "MctasCtes";
	public static String PROP_ROWGUID = "rowguid";
	public static String PROP_DEPU = "depu";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_FH_PROC = "fhProc";
	public static String PROP_USER = "user";
	public static String PROP_IMPORTE = "importe";
	public static String PROP_NRO_OPER_CAJA = "nroOperCaja";
	public static String PROP_FH_OPER = "fhOper";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_TPO_CTA_CTE = "tpoCtaCte";
	public static String PROP_NRO_OPER = "nroOper";
	public static String PROP_TIPO_OPER = "tipoOper";
	public static String PROP_NRO_FACTURA = "nroFactura";
	public static String PROP_CLTE_PROV = "clteProv";
	public static String PROP_ID = "id";
	public static String PROP_CODIGO_TURNO_VIGENCIA = "codigoTurnoVigencia";
	public static String PROP_NRO_CUOTA = "nroCuota";


	// constructors
	public BaseMctasCtes () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMctasCtes (com.refinor.extranet.data.MctasCtesId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MctasCtesId id;

	// fields
	private java.lang.String tpoCtaCte;
	private java.lang.Integer clteProv;
	private java.lang.String tipoOper;
	private java.lang.Integer nroOper;
	private java.lang.Integer nroOperCaja;
	private java.lang.Integer nroFactura;
	private java.lang.Short nroCuota;
	private java.util.Date fhOper;
	private java.math.BigDecimal importe;
	private java.lang.String user;
	private java.util.Date fhProc;
	private java.util.Date fhBaja;
	private java.lang.Integer idUserLock;
	private java.util.Date depu;
	private java.lang.Long codigoTurnoVigencia;
	private java.lang.String rowguid;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MctasCtesId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MctasCtesId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: TpoCtaCte
	 */
	public java.lang.String getTpoCtaCte () {
		return tpoCtaCte;
	}

	/**
	 * Set the value related to the column: TpoCtaCte
	 * @param tpoCtaCte the TpoCtaCte value
	 */
	public void setTpoCtaCte (java.lang.String tpoCtaCte) {
		this.tpoCtaCte = tpoCtaCte;
	}



	/**
	 * Return the value associated with the column: ClteProv
	 */
	public java.lang.Integer getClteProv () {
		return clteProv;
	}

	/**
	 * Set the value related to the column: ClteProv
	 * @param clteProv the ClteProv value
	 */
	public void setClteProv (java.lang.Integer clteProv) {
		this.clteProv = clteProv;
	}



	/**
	 * Return the value associated with the column: TipoOper
	 */
	public java.lang.String getTipoOper () {
		return tipoOper;
	}

	/**
	 * Set the value related to the column: TipoOper
	 * @param tipoOper the TipoOper value
	 */
	public void setTipoOper (java.lang.String tipoOper) {
		this.tipoOper = tipoOper;
	}



	/**
	 * Return the value associated with the column: NroOper
	 */
	public java.lang.Integer getNroOper () {
		return nroOper;
	}

	/**
	 * Set the value related to the column: NroOper
	 * @param nroOper the NroOper value
	 */
	public void setNroOper (java.lang.Integer nroOper) {
		this.nroOper = nroOper;
	}



	/**
	 * Return the value associated with the column: NroOperCaja
	 */
	public java.lang.Integer getNroOperCaja () {
		return nroOperCaja;
	}

	/**
	 * Set the value related to the column: NroOperCaja
	 * @param nroOperCaja the NroOperCaja value
	 */
	public void setNroOperCaja (java.lang.Integer nroOperCaja) {
		this.nroOperCaja = nroOperCaja;
	}



	/**
	 * Return the value associated with the column: NroFactura
	 */
	public java.lang.Integer getNroFactura () {
		return nroFactura;
	}

	/**
	 * Set the value related to the column: NroFactura
	 * @param nroFactura the NroFactura value
	 */
	public void setNroFactura (java.lang.Integer nroFactura) {
		this.nroFactura = nroFactura;
	}



	/**
	 * Return the value associated with the column: NroCuota
	 */
	public java.lang.Short getNroCuota () {
		return nroCuota;
	}

	/**
	 * Set the value related to the column: NroCuota
	 * @param nroCuota the NroCuota value
	 */
	public void setNroCuota (java.lang.Short nroCuota) {
		this.nroCuota = nroCuota;
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
	 * Return the value associated with the column: Importe
	 */
	public java.math.BigDecimal getImporte () {
		return importe;
	}

	/**
	 * Set the value related to the column: Importe
	 * @param importe the Importe value
	 */
	public void setImporte (java.math.BigDecimal importe) {
		this.importe = importe;
	}



	/**
	 * Return the value associated with the column: User
	 */
	public java.lang.String getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: User
	 * @param user the User value
	 */
	public void setUser (java.lang.String user) {
		this.user = user;
	}



	/**
	 * Return the value associated with the column: FhProc
	 */
	public java.util.Date getFhProc () {
		return fhProc;
	}

	/**
	 * Set the value related to the column: FhProc
	 * @param fhProc the FhProc value
	 */
	public void setFhProc (java.util.Date fhProc) {
		this.fhProc = fhProc;
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
	 * Return the value associated with the column: Depu
	 */
	public java.util.Date getDepu () {
		return depu;
	}

	/**
	 * Set the value related to the column: Depu
	 * @param depu the Depu value
	 */
	public void setDepu (java.util.Date depu) {
		this.depu = depu;
	}



	/**
	 * Return the value associated with the column: CodigoTurnoVigencia
	 */
	public java.lang.Long getCodigoTurnoVigencia () {
		return codigoTurnoVigencia;
	}

	/**
	 * Set the value related to the column: CodigoTurnoVigencia
	 * @param codigoTurnoVigencia the CodigoTurnoVigencia value
	 */
	public void setCodigoTurnoVigencia (java.lang.Long codigoTurnoVigencia) {
		this.codigoTurnoVigencia = codigoTurnoVigencia;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MctasCtes)) return false;
		else {
			com.refinor.extranet.data.MctasCtes mctasCtes = (com.refinor.extranet.data.MctasCtes) obj;
			if (null == this.getId() || null == mctasCtes.getId()) return false;
			else return (this.getId().equals(mctasCtes.getId()));
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