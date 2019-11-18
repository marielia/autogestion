package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MCaja table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MCaja"
 */

public abstract class BaseMcaja  implements Serializable {

	public static String REF = "Mcaja";
	public static String PROP_COD_TRANS = "codTrans";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_TPO_PAGO = "tpoPago";
	public static String PROP_IMPORTE = "importe";
	public static String PROP_NRO_CERT_IVA = "nroCertIva";
	public static String PROP_RET_GCIAS = "retGcias";
	public static String PROP_ORDEN_PAGO = "ordenPago";
	public static String PROP_FH_PROC_CONF = "fhProcConf";
	public static String PROP_NRO_OPER = "nroOper";
	public static String PROP_JUR_INGR_BRT = "jurIngrBrt";
	public static String PROP_PAGO = "pago";
	public static String PROP_NRO_MACRO_OPER = "nroMacroOper";
	public static String PROP_CERT_INGR_BRT = "certIngrBrt";
	public static String PROP_DESCUENTO = "descuento";
	public static String PROP_RET_INGR_BRT = "retIngrBrt";
	public static String PROP_FECHA_CERT_GAN = "fechaCertGan";
	public static String PROP_RET_SIJP = "retSijp";
	public static String PROP_FH_TRANS = "fhTrans";
	public static String PROP_FECHA_CERT_IVA = "fechaCertIva";
	public static String PROP_NRO_CERT_SEG_SOC = "nroCertSegSoc";
	public static String PROP_FECHA_CERT_SEG_SOC = "fechaCertSegSoc";
	public static String PROP_CONFORMADO = "conformado";
	public static String PROP_ORDEN_FC = "ordenFc";
	public static String PROP_FH_PROC = "fhProc";
	public static String PROP_COD_INGR_EGR = "codIngrEgr";
	public static String PROP_USER = "user";
	public static String PROP_FECHA_CERT_TRS = "fechaCertTrs";
	public static String PROP_TRS = "trs";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_NRO_CAJA = "nroCaja";
	public static String PROP_RET_IVA = "retIva";
	public static String PROP_CERT_GCIAS = "certGcias";
	public static String PROP_USER_CONF = "userConf";
	public static String PROP_CLTE_PROV = "clteProv";
	public static String PROP_NRO_CERT_TRS = "nroCertTrs";
	public static String PROP_JUR_CERT_TRS = "jurCertTrs";
	public static String PROP_ID_CHEQUE = "idCheque";
	public static String PROP_ID = "id";
	public static String PROP_CODIGO_TURNO_VIGENCIA = "codigoTurnoVigencia";


	// constructors
	public BaseMcaja () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMcaja (com.refinor.extranet.data.McajaId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.McajaId id;

	// fields
	private java.lang.Integer nroCaja;
	private int nroOper;
	private int nroMacroOper;
	private java.lang.Integer clteProv;
	private int codTrans;
	private java.util.Date fhTrans;
	private java.math.BigDecimal importe;
	private java.math.BigDecimal pago;
	private java.math.BigDecimal descuento;
	private java.math.BigDecimal retIva;
	private java.math.BigDecimal retGcias;
	private java.math.BigDecimal retIngrBrt;
	private java.math.BigDecimal retSijp;
	private java.lang.Integer codIngrEgr;
	private java.lang.Integer ordenPago;
	private java.lang.Integer certGcias;
	private java.lang.Integer certIngrBrt;
	private java.lang.Integer jurIngrBrt;
	private java.lang.Integer idCheque;
	private java.util.Date fhProc;
	private java.lang.String tpoPago;
	private java.lang.String conformado;
	private java.lang.String userConf;
	private java.util.Date fhProcConf;
	private java.lang.String user;
	private java.lang.Long codigoTurnoVigencia;
	private java.util.Date fhBaja;
	private java.lang.Integer idUserLock;
	private java.lang.Integer ordenFc;
	private java.lang.Long nroCertIva;
	private java.util.Date fechaCertIva;
	private java.lang.Long nroCertSegSoc;
	private java.util.Date fechaCertSegSoc;
	private java.util.Date fechaCertGan;
	private java.lang.Long trs;
	private java.lang.Long nroCertTrs;
	private java.util.Date fechaCertTrs;
	private java.lang.Integer jurCertTrs;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.McajaId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.McajaId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: NroCaja
	 */
	public java.lang.Integer getNroCaja () {
		return nroCaja;
	}

	/**
	 * Set the value related to the column: NroCaja
	 * @param nroCaja the NroCaja value
	 */
	public void setNroCaja (java.lang.Integer nroCaja) {
		this.nroCaja = nroCaja;
	}



	/**
	 * Return the value associated with the column: NroOper
	 */
	public int getNroOper () {
		return nroOper;
	}

	/**
	 * Set the value related to the column: NroOper
	 * @param nroOper the NroOper value
	 */
	public void setNroOper (int nroOper) {
		this.nroOper = nroOper;
	}



	/**
	 * Return the value associated with the column: NroMacroOper
	 */
	public int getNroMacroOper () {
		return nroMacroOper;
	}

	/**
	 * Set the value related to the column: NroMacroOper
	 * @param nroMacroOper the NroMacroOper value
	 */
	public void setNroMacroOper (int nroMacroOper) {
		this.nroMacroOper = nroMacroOper;
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
	 * Return the value associated with the column: CodTrans
	 */
	public int getCodTrans () {
		return codTrans;
	}

	/**
	 * Set the value related to the column: CodTrans
	 * @param codTrans the CodTrans value
	 */
	public void setCodTrans (int codTrans) {
		this.codTrans = codTrans;
	}



	/**
	 * Return the value associated with the column: FhTrans
	 */
	public java.util.Date getFhTrans () {
		return fhTrans;
	}

	/**
	 * Set the value related to the column: FhTrans
	 * @param fhTrans the FhTrans value
	 */
	public void setFhTrans (java.util.Date fhTrans) {
		this.fhTrans = fhTrans;
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
	 * Return the value associated with the column: Pago
	 */
	public java.math.BigDecimal getPago () {
		return pago;
	}

	/**
	 * Set the value related to the column: Pago
	 * @param pago the Pago value
	 */
	public void setPago (java.math.BigDecimal pago) {
		this.pago = pago;
	}



	/**
	 * Return the value associated with the column: Descuento
	 */
	public java.math.BigDecimal getDescuento () {
		return descuento;
	}

	/**
	 * Set the value related to the column: Descuento
	 * @param descuento the Descuento value
	 */
	public void setDescuento (java.math.BigDecimal descuento) {
		this.descuento = descuento;
	}



	/**
	 * Return the value associated with the column: RetIva
	 */
	public java.math.BigDecimal getRetIva () {
		return retIva;
	}

	/**
	 * Set the value related to the column: RetIva
	 * @param retIva the RetIva value
	 */
	public void setRetIva (java.math.BigDecimal retIva) {
		this.retIva = retIva;
	}



	/**
	 * Return the value associated with the column: RetGcias
	 */
	public java.math.BigDecimal getRetGcias () {
		return retGcias;
	}

	/**
	 * Set the value related to the column: RetGcias
	 * @param retGcias the RetGcias value
	 */
	public void setRetGcias (java.math.BigDecimal retGcias) {
		this.retGcias = retGcias;
	}



	/**
	 * Return the value associated with the column: RetIngrBrt
	 */
	public java.math.BigDecimal getRetIngrBrt () {
		return retIngrBrt;
	}

	/**
	 * Set the value related to the column: RetIngrBrt
	 * @param retIngrBrt the RetIngrBrt value
	 */
	public void setRetIngrBrt (java.math.BigDecimal retIngrBrt) {
		this.retIngrBrt = retIngrBrt;
	}



	/**
	 * Return the value associated with the column: RetSIJP
	 */
	public java.math.BigDecimal getRetSijp () {
		return retSijp;
	}

	/**
	 * Set the value related to the column: RetSIJP
	 * @param retSijp the RetSIJP value
	 */
	public void setRetSijp (java.math.BigDecimal retSijp) {
		this.retSijp = retSijp;
	}



	/**
	 * Return the value associated with the column: CodIngrEgr
	 */
	public java.lang.Integer getCodIngrEgr () {
		return codIngrEgr;
	}

	/**
	 * Set the value related to the column: CodIngrEgr
	 * @param codIngrEgr the CodIngrEgr value
	 */
	public void setCodIngrEgr (java.lang.Integer codIngrEgr) {
		this.codIngrEgr = codIngrEgr;
	}



	/**
	 * Return the value associated with the column: OrdenPago
	 */
	public java.lang.Integer getOrdenPago () {
		return ordenPago;
	}

	/**
	 * Set the value related to the column: OrdenPago
	 * @param ordenPago the OrdenPago value
	 */
	public void setOrdenPago (java.lang.Integer ordenPago) {
		this.ordenPago = ordenPago;
	}



	/**
	 * Return the value associated with the column: CertGcias
	 */
	public java.lang.Integer getCertGcias () {
		return certGcias;
	}

	/**
	 * Set the value related to the column: CertGcias
	 * @param certGcias the CertGcias value
	 */
	public void setCertGcias (java.lang.Integer certGcias) {
		this.certGcias = certGcias;
	}



	/**
	 * Return the value associated with the column: CertIngrBrt
	 */
	public java.lang.Integer getCertIngrBrt () {
		return certIngrBrt;
	}

	/**
	 * Set the value related to the column: CertIngrBrt
	 * @param certIngrBrt the CertIngrBrt value
	 */
	public void setCertIngrBrt (java.lang.Integer certIngrBrt) {
		this.certIngrBrt = certIngrBrt;
	}



	/**
	 * Return the value associated with the column: JurIngrBrt
	 */
	public java.lang.Integer getJurIngrBrt () {
		return jurIngrBrt;
	}

	/**
	 * Set the value related to the column: JurIngrBrt
	 * @param jurIngrBrt the JurIngrBrt value
	 */
	public void setJurIngrBrt (java.lang.Integer jurIngrBrt) {
		this.jurIngrBrt = jurIngrBrt;
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
	 * Return the value associated with the column: TpoPago
	 */
	public java.lang.String getTpoPago () {
		return tpoPago;
	}

	/**
	 * Set the value related to the column: TpoPago
	 * @param tpoPago the TpoPago value
	 */
	public void setTpoPago (java.lang.String tpoPago) {
		this.tpoPago = tpoPago;
	}



	/**
	 * Return the value associated with the column: Conformado
	 */
	public java.lang.String getConformado () {
		return conformado;
	}

	/**
	 * Set the value related to the column: Conformado
	 * @param conformado the Conformado value
	 */
	public void setConformado (java.lang.String conformado) {
		this.conformado = conformado;
	}



	/**
	 * Return the value associated with the column: UserConf
	 */
	public java.lang.String getUserConf () {
		return userConf;
	}

	/**
	 * Set the value related to the column: UserConf
	 * @param userConf the UserConf value
	 */
	public void setUserConf (java.lang.String userConf) {
		this.userConf = userConf;
	}



	/**
	 * Return the value associated with the column: FhProcConf
	 */
	public java.util.Date getFhProcConf () {
		return fhProcConf;
	}

	/**
	 * Set the value related to the column: FhProcConf
	 * @param fhProcConf the FhProcConf value
	 */
	public void setFhProcConf (java.util.Date fhProcConf) {
		this.fhProcConf = fhProcConf;
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
	 * Return the value associated with the column: orden_fc
	 */
	public java.lang.Integer getOrdenFc () {
		return ordenFc;
	}

	/**
	 * Set the value related to the column: orden_fc
	 * @param ordenFc the orden_fc value
	 */
	public void setOrdenFc (java.lang.Integer ordenFc) {
		this.ordenFc = ordenFc;
	}



	/**
	 * Return the value associated with the column: NroCertIva
	 */
	public java.lang.Long getNroCertIva () {
		return nroCertIva;
	}

	/**
	 * Set the value related to the column: NroCertIva
	 * @param nroCertIva the NroCertIva value
	 */
	public void setNroCertIva (java.lang.Long nroCertIva) {
		this.nroCertIva = nroCertIva;
	}



	/**
	 * Return the value associated with the column: FechaCertIva
	 */
	public java.util.Date getFechaCertIva () {
		return fechaCertIva;
	}

	/**
	 * Set the value related to the column: FechaCertIva
	 * @param fechaCertIva the FechaCertIva value
	 */
	public void setFechaCertIva (java.util.Date fechaCertIva) {
		this.fechaCertIva = fechaCertIva;
	}



	/**
	 * Return the value associated with the column: NroCertSegSoc
	 */
	public java.lang.Long getNroCertSegSoc () {
		return nroCertSegSoc;
	}

	/**
	 * Set the value related to the column: NroCertSegSoc
	 * @param nroCertSegSoc the NroCertSegSoc value
	 */
	public void setNroCertSegSoc (java.lang.Long nroCertSegSoc) {
		this.nroCertSegSoc = nroCertSegSoc;
	}



	/**
	 * Return the value associated with the column: FechaCertSegSoc
	 */
	public java.util.Date getFechaCertSegSoc () {
		return fechaCertSegSoc;
	}

	/**
	 * Set the value related to the column: FechaCertSegSoc
	 * @param fechaCertSegSoc the FechaCertSegSoc value
	 */
	public void setFechaCertSegSoc (java.util.Date fechaCertSegSoc) {
		this.fechaCertSegSoc = fechaCertSegSoc;
	}



	/**
	 * Return the value associated with the column: FechaCertGan
	 */
	public java.util.Date getFechaCertGan () {
		return fechaCertGan;
	}

	/**
	 * Set the value related to the column: FechaCertGan
	 * @param fechaCertGan the FechaCertGan value
	 */
	public void setFechaCertGan (java.util.Date fechaCertGan) {
		this.fechaCertGan = fechaCertGan;
	}



	/**
	 * Return the value associated with the column: TRS
	 */
	public java.lang.Long getTrs () {
		return trs;
	}

	/**
	 * Set the value related to the column: TRS
	 * @param trs the TRS value
	 */
	public void setTrs (java.lang.Long trs) {
		this.trs = trs;
	}



	/**
	 * Return the value associated with the column: NroCertTRS
	 */
	public java.lang.Long getNroCertTrs () {
		return nroCertTrs;
	}

	/**
	 * Set the value related to the column: NroCertTRS
	 * @param nroCertTrs the NroCertTRS value
	 */
	public void setNroCertTrs (java.lang.Long nroCertTrs) {
		this.nroCertTrs = nroCertTrs;
	}



	/**
	 * Return the value associated with the column: FechaCertTRS
	 */
	public java.util.Date getFechaCertTrs () {
		return fechaCertTrs;
	}

	/**
	 * Set the value related to the column: FechaCertTRS
	 * @param fechaCertTrs the FechaCertTRS value
	 */
	public void setFechaCertTrs (java.util.Date fechaCertTrs) {
		this.fechaCertTrs = fechaCertTrs;
	}



	/**
	 * Return the value associated with the column: JurCertTRS
	 */
	public java.lang.Integer getJurCertTrs () {
		return jurCertTrs;
	}

	/**
	 * Set the value related to the column: JurCertTRS
	 * @param jurCertTrs the JurCertTRS value
	 */
	public void setJurCertTrs (java.lang.Integer jurCertTrs) {
		this.jurCertTrs = jurCertTrs;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mcaja)) return false;
		else {
			com.refinor.extranet.data.Mcaja mcaja = (com.refinor.extranet.data.Mcaja) obj;
			if (null == this.getId() || null == mcaja.getId()) return false;
			else return (this.getId().equals(mcaja.getId()));
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