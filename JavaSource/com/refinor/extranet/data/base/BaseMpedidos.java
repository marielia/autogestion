package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MPedidos table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MPedidos"
 */

public abstract class BaseMpedidos  implements Serializable {

	public static String REF = "Mpedidos";
	public static String PROP_IMPUESTO_GR1 = "impuestoGr1";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_IVA_BASICO = "ivaBasico";
	public static String PROP_BULTOS1 = "bultos1";
	public static String PROP_ARRIVO = "arrivo";
	public static String PROP_IMPUESTO_GR2 = "impuestoGr2";
	public static String PROP_PIVA = "piva";
	public static String PROP_IIBB = "iibb";
	public static String PROP_COD_REMITO = "codRemito";
	public static String PROP_CLIENTE = "cliente";
	public static String PROP_MSG1 = "msg1";
	public static String PROP_BULTOS2 = "bultos2";
	public static String PROP_IMPUESTO_GR4 = "impuestoGr4";
	public static String PROP_FECHA = "fecha";
	public static String PROP_ESTADO_LIQUIDACION_REFIPASS = "estadoLiquidacionRefipass";
	public static String PROP_IMPUESTO_GR3 = "impuestoGr3";
	public static String PROP_NRO_AUTO = "nroAuto";
	public static String PROP_NRO_EXT_PEDIDO = "nroExtPedido";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_MSG2 = "msg2";
	public static String PROP_FEVTO = "fevto";
	public static String PROP_NRO_LIQUIDACION_REFIPASS = "nroLiquidacionRefipass";
	public static String PROP_FECHA_LIQUIDACION_REFIPASS = "fechaLiquidacionRefipass";
	public static String PROP_ID = "id";
	public static String PROP_IVA_ADICIONAL = "ivaAdicional";


	// constructors
	public BaseMpedidos () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMpedidos (com.refinor.extranet.data.MpedidosId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MpedidosId id;

	// fields
	private java.lang.Integer cliente;
	private java.util.Date fecha;
	private java.util.Date fevto;
	private long arrivo;
	private java.lang.Integer bultos1;
	private java.lang.Integer bultos2;
	private java.lang.String msg1;
	private java.lang.String msg2;
	private java.lang.Integer codRemito;
	private java.lang.Integer idUserLock;
	private java.util.Date fhBaja;
	private java.lang.String nroExtPedido;
	private java.lang.Integer nroAuto;
	private java.lang.Long nroLiquidacionRefipass;
	private java.lang.Integer estadoLiquidacionRefipass;
	private java.util.Date fechaLiquidacionRefipass;
	private java.math.BigDecimal ivaBasico;
	private java.math.BigDecimal ivaAdicional;
	private java.math.BigDecimal impuestoGr1;
	private java.math.BigDecimal impuestoGr2;
	private java.math.BigDecimal impuestoGr3;
	private java.math.BigDecimal iibb;
	private java.math.BigDecimal piva;
	private java.math.BigDecimal impuestoGr4;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MpedidosId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MpedidosId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: Cliente
	 */
	public java.lang.Integer getCliente () {
		return cliente;
	}

	/**
	 * Set the value related to the column: Cliente
	 * @param cliente the Cliente value
	 */
	public void setCliente (java.lang.Integer cliente) {
		this.cliente = cliente;
	}



	/**
	 * Return the value associated with the column: Fecha
	 */
	public java.util.Date getFecha () {
		return fecha;
	}

	/**
	 * Set the value related to the column: Fecha
	 * @param fecha the Fecha value
	 */
	public void setFecha (java.util.Date fecha) {
		this.fecha = fecha;
	}



	/**
	 * Return the value associated with the column: Fevto
	 */
	public java.util.Date getFevto () {
		return fevto;
	}

	/**
	 * Set the value related to the column: Fevto
	 * @param fevto the Fevto value
	 */
	public void setFevto (java.util.Date fevto) {
		this.fevto = fevto;
	}



	/**
	 * Return the value associated with the column: Arrivo
	 */
	public long getArrivo () {
		return arrivo;
	}

	/**
	 * Set the value related to the column: Arrivo
	 * @param arrivo the Arrivo value
	 */
	public void setArrivo (long arrivo) {
		this.arrivo = arrivo;
	}



	/**
	 * Return the value associated with the column: Bultos1
	 */
	public java.lang.Integer getBultos1 () {
		return bultos1;
	}

	/**
	 * Set the value related to the column: Bultos1
	 * @param bultos1 the Bultos1 value
	 */
	public void setBultos1 (java.lang.Integer bultos1) {
		this.bultos1 = bultos1;
	}



	/**
	 * Return the value associated with the column: Bultos2
	 */
	public java.lang.Integer getBultos2 () {
		return bultos2;
	}

	/**
	 * Set the value related to the column: Bultos2
	 * @param bultos2 the Bultos2 value
	 */
	public void setBultos2 (java.lang.Integer bultos2) {
		this.bultos2 = bultos2;
	}



	/**
	 * Return the value associated with the column: Msg1
	 */
	public java.lang.String getMsg1 () {
		return msg1;
	}

	/**
	 * Set the value related to the column: Msg1
	 * @param msg1 the Msg1 value
	 */
	public void setMsg1 (java.lang.String msg1) {
		this.msg1 = msg1;
	}



	/**
	 * Return the value associated with the column: Msg2
	 */
	public java.lang.String getMsg2 () {
		return msg2;
	}

	/**
	 * Set the value related to the column: Msg2
	 * @param msg2 the Msg2 value
	 */
	public void setMsg2 (java.lang.String msg2) {
		this.msg2 = msg2;
	}



	/**
	 * Return the value associated with the column: CodRemito
	 */
	public java.lang.Integer getCodRemito () {
		return codRemito;
	}

	/**
	 * Set the value related to the column: CodRemito
	 * @param codRemito the CodRemito value
	 */
	public void setCodRemito (java.lang.Integer codRemito) {
		this.codRemito = codRemito;
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
	 * Return the value associated with the column: NroExtPedido
	 */
	public java.lang.String getNroExtPedido () {
		return nroExtPedido;
	}

	/**
	 * Set the value related to the column: NroExtPedido
	 * @param nroExtPedido the NroExtPedido value
	 */
	public void setNroExtPedido (java.lang.String nroExtPedido) {
		this.nroExtPedido = nroExtPedido;
	}



	/**
	 * Return the value associated with the column: nro_auto
	 */
	public java.lang.Integer getNroAuto () {
		return nroAuto;
	}

	/**
	 * Set the value related to the column: nro_auto
	 * @param nroAuto the nro_auto value
	 */
	public void setNroAuto (java.lang.Integer nroAuto) {
		this.nroAuto = nroAuto;
	}



	/**
	 * Return the value associated with the column: NroLiquidacionRefipass
	 */
	public java.lang.Long getNroLiquidacionRefipass () {
		return nroLiquidacionRefipass;
	}

	/**
	 * Set the value related to the column: NroLiquidacionRefipass
	 * @param nroLiquidacionRefipass the NroLiquidacionRefipass value
	 */
	public void setNroLiquidacionRefipass (java.lang.Long nroLiquidacionRefipass) {
		this.nroLiquidacionRefipass = nroLiquidacionRefipass;
	}



	/**
	 * Return the value associated with the column: EstadoLiquidacionRefipass
	 */
	public java.lang.Integer getEstadoLiquidacionRefipass () {
		return estadoLiquidacionRefipass;
	}

	/**
	 * Set the value related to the column: EstadoLiquidacionRefipass
	 * @param estadoLiquidacionRefipass the EstadoLiquidacionRefipass value
	 */
	public void setEstadoLiquidacionRefipass (java.lang.Integer estadoLiquidacionRefipass) {
		this.estadoLiquidacionRefipass = estadoLiquidacionRefipass;
	}



	/**
	 * Return the value associated with the column: FechaLiquidacionRefipass
	 */
	public java.util.Date getFechaLiquidacionRefipass () {
		return fechaLiquidacionRefipass;
	}

	/**
	 * Set the value related to the column: FechaLiquidacionRefipass
	 * @param fechaLiquidacionRefipass the FechaLiquidacionRefipass value
	 */
	public void setFechaLiquidacionRefipass (java.util.Date fechaLiquidacionRefipass) {
		this.fechaLiquidacionRefipass = fechaLiquidacionRefipass;
	}



	/**
	 * Return the value associated with the column: IvaBasico
	 */
	public java.math.BigDecimal getIvaBasico () {
		return ivaBasico;
	}

	/**
	 * Set the value related to the column: IvaBasico
	 * @param ivaBasico the IvaBasico value
	 */
	public void setIvaBasico (java.math.BigDecimal ivaBasico) {
		this.ivaBasico = ivaBasico;
	}



	/**
	 * Return the value associated with the column: IvaAdicional
	 */
	public java.math.BigDecimal getIvaAdicional () {
		return ivaAdicional;
	}

	/**
	 * Set the value related to the column: IvaAdicional
	 * @param ivaAdicional the IvaAdicional value
	 */
	public void setIvaAdicional (java.math.BigDecimal ivaAdicional) {
		this.ivaAdicional = ivaAdicional;
	}



	/**
	 * Return the value associated with the column: impuestoGr1
	 */
	public java.math.BigDecimal getImpuestoGr1 () {
		return impuestoGr1;
	}

	/**
	 * Set the value related to the column: impuestoGr1
	 * @param impuestoGr1 the impuestoGr1 value
	 */
	public void setImpuestoGr1 (java.math.BigDecimal impuestoGr1) {
		this.impuestoGr1 = impuestoGr1;
	}



	/**
	 * Return the value associated with the column: impuestoGr2
	 */
	public java.math.BigDecimal getImpuestoGr2 () {
		return impuestoGr2;
	}

	/**
	 * Set the value related to the column: impuestoGr2
	 * @param impuestoGr2 the impuestoGr2 value
	 */
	public void setImpuestoGr2 (java.math.BigDecimal impuestoGr2) {
		this.impuestoGr2 = impuestoGr2;
	}



	/**
	 * Return the value associated with the column: impuestoGr3
	 */
	public java.math.BigDecimal getImpuestoGr3 () {
		return impuestoGr3;
	}

	/**
	 * Set the value related to the column: impuestoGr3
	 * @param impuestoGr3 the impuestoGr3 value
	 */
	public void setImpuestoGr3 (java.math.BigDecimal impuestoGr3) {
		this.impuestoGr3 = impuestoGr3;
	}



	/**
	 * Return the value associated with the column: iibb
	 */
	public java.math.BigDecimal getIibb () {
		return iibb;
	}

	/**
	 * Set the value related to the column: iibb
	 * @param iibb the iibb value
	 */
	public void setIibb (java.math.BigDecimal iibb) {
		this.iibb = iibb;
	}



	/**
	 * Return the value associated with the column: piva
	 */
	public java.math.BigDecimal getPiva () {
		return piva;
	}

	/**
	 * Set the value related to the column: piva
	 * @param piva the piva value
	 */
	public void setPiva (java.math.BigDecimal piva) {
		this.piva = piva;
	}



	/**
	 * Return the value associated with the column: impuestoGr4
	 */
	public java.math.BigDecimal getImpuestoGr4 () {
		return impuestoGr4;
	}

	/**
	 * Set the value related to the column: impuestoGr4
	 * @param impuestoGr4 the impuestoGr4 value
	 */
	public void setImpuestoGr4 (java.math.BigDecimal impuestoGr4) {
		this.impuestoGr4 = impuestoGr4;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mpedidos)) return false;
		else {
			com.refinor.extranet.data.Mpedidos mpedidos = (com.refinor.extranet.data.Mpedidos) obj;
			if (null == this.getId() || null == mpedidos.getId()) return false;
			else return (this.getId().equals(mpedidos.getId()));
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