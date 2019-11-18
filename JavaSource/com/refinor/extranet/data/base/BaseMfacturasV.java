package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the mFacturasV table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="mFacturasV"
 */

public abstract class BaseMfacturasV  implements Serializable {

	public static String REF = "MfacturasV";
	public static String PROP_IMPUESTO_GR1 = "impuestoGr1";
	public static String PROP_NRO_EJERCICIO_CONTABLE = "nroEjercicioContable";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_IVA_BASICO = "ivaBasico";
	public static String PROP_ORDEN_ASOC = "ordenAsoc";
	public static String PROP_DESC_GASTOS2 = "descGastos2";
	public static String PROP_CAT_IVA = "catIva";
	public static String PROP_PIVA = "piva";
	public static String PROP_ES_AJUSTE = "esAjuste";
	public static String PROP_BRUTO_PRODUCTOS = "brutoProductos";
	public static String PROP_BRUTO_VACIOS = "brutoVacios";
	public static String PROP_GASTOS2 = "gastos2";
	public static String PROP_GASTOS1 = "gastos1";
	public static String PROP_NRO_RENDICION_REFIPASS = "nroRendicionRefipass";
	public static String PROP_NRO_ASIENTO_CONTABLE = "nroAsientoContable";
	public static String PROP_IMPUESTO_GR3 = "impuestoGr3";
	public static String PROP_ESTADO_LIQUIDACION_REFIPASS = "estadoLiquidacionRefipass";
	public static String PROP_NRO_SUC_ASOC = "nroSucAsoc";
	public static String PROP_TIPO = "tipo";
	public static String PROP_NRO_FACTURA = "nroFactura";
	public static String PROP_DESC_GASTOS1 = "descGastos1";
	public static String PROP_LISTA_PRECIO = "listaPrecio";
	public static String PROP_FECHA_LIQUIDACION_REFIPASS = "fechaLiquidacionRefipass";
	public static String PROP_CODIGO_TURNO_VIGENCIA = "codigoTurnoVigencia";
	public static String PROP_NRO_REMITO = "nroRemito";
	public static String PROP_TIPO_COMP = "tipoComp";
	public static String PROP_FE_VTO_CAI = "feVtoCai";
	public static String PROP_NETO_FACTURA = "netoFactura";
	public static String PROP_ESTADO_RENDICION_REFIPASS = "estadoRendicionRefipass";
	public static String PROP_CODIGO_BARRAS = "codigoBarras";
	public static String PROP_IMPUESTO_GR2 = "impuestoGr2";
	public static String PROP_IIBB = "iibb";
	public static String PROP_CODIGO_BARRAS_IMPRESION = "codigoBarrasImpresion";
	public static String PROP_FORMADE_PAGO = "formadePago";
	public static String PROP_CLIENTE = "cliente";
	public static String PROP_MSG1 = "msg1";
	public static String PROP_IMPUESTO_GR4 = "impuestoGr4";
	public static String PROP_DESC_GASTOS3 = "descGastos3";
	public static String PROP_COND_VTA = "condVta";
	public static String PROP_CUOTAS = "cuotas";
	public static String PROP_FECHA = "fecha";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_GASTOS3 = "gastos3";
	public static String PROP_MSG2 = "msg2";
	public static String PROP_FEVTO = "fevto";
	public static String PROP_NRO_LIQUIDACION_REFIPASS = "nroLiquidacionRefipass";
	public static String PROP_CAI = "cai";
	public static String PROP_ID = "id";
	public static String PROP_IVA_ADICIONAL = "ivaAdicional";


	// constructors
	public BaseMfacturasV () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMfacturasV (com.refinor.extranet.data.MfacturasVId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MfacturasVId id;

	// fields
	private java.lang.String tipo;
	private java.lang.Integer nroFactura;
	private java.lang.Integer nroRemito;
	private java.lang.Integer cliente;
	private java.util.Date fecha;
	private java.util.Date fevto;
	private java.lang.String formadePago;
	private java.lang.Short cuotas;
	private java.math.BigDecimal brutoProductos;
	private java.math.BigDecimal brutoVacios;
	private java.math.BigDecimal gastos1;
	private java.math.BigDecimal gastos2;
	private java.math.BigDecimal gastos3;
	private java.lang.String descGastos1;
	private java.lang.String descGastos2;
	private java.lang.String descGastos3;
	private java.math.BigDecimal ivaBasico;
	private java.math.BigDecimal ivaAdicional;
	private java.math.BigDecimal netoFactura;
	private java.lang.Short catIva;
	private java.lang.Short listaPrecio;
	private java.lang.Integer idUserLock;
	private java.util.Date fhBaja;
	private java.lang.String msg1;
	private java.lang.String msg2;
	private java.lang.Long codigoTurnoVigencia;
	private java.lang.Boolean esAjuste;
	private java.math.BigDecimal impuestoGr1;
	private java.math.BigDecimal impuestoGr2;
	private java.math.BigDecimal impuestoGr3;
	private java.lang.Long nroRendicionRefipass;
	private java.math.BigDecimal iibb;
	private java.math.BigDecimal piva;
	private java.lang.Long nroEjercicioContable;
	private java.lang.Long nroAsientoContable;
	private java.lang.Integer estadoRendicionRefipass;
	private java.lang.Long nroLiquidacionRefipass;
	private java.lang.Integer estadoLiquidacionRefipass;
	private java.util.Date fechaLiquidacionRefipass;
	private java.lang.String tipoComp;
	private java.lang.Integer condVta;
	private java.lang.Integer nroSucAsoc;
	private java.lang.Integer ordenAsoc;
	private java.lang.String codigoBarras;
	private java.lang.String codigoBarrasImpresion;
	private java.lang.Integer cai;
	private java.util.Date feVtoCai;
	private java.math.BigDecimal impuestoGr4;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MfacturasVId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MfacturasVId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: Tipo
	 */
	public java.lang.String getTipo () {
		return tipo;
	}

	/**
	 * Set the value related to the column: Tipo
	 * @param tipo the Tipo value
	 */
	public void setTipo (java.lang.String tipo) {
		this.tipo = tipo;
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
	 * Return the value associated with the column: NroRemito
	 */
	public java.lang.Integer getNroRemito () {
		return nroRemito;
	}

	/**
	 * Set the value related to the column: NroRemito
	 * @param nroRemito the NroRemito value
	 */
	public void setNroRemito (java.lang.Integer nroRemito) {
		this.nroRemito = nroRemito;
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
	 * Return the value associated with the column: FormadePago
	 */
	public java.lang.String getFormadePago () {
		return formadePago;
	}

	/**
	 * Set the value related to the column: FormadePago
	 * @param formadePago the FormadePago value
	 */
	public void setFormadePago (java.lang.String formadePago) {
		this.formadePago = formadePago;
	}



	/**
	 * Return the value associated with the column: Cuotas
	 */
	public java.lang.Short getCuotas () {
		return cuotas;
	}

	/**
	 * Set the value related to the column: Cuotas
	 * @param cuotas the Cuotas value
	 */
	public void setCuotas (java.lang.Short cuotas) {
		this.cuotas = cuotas;
	}



	/**
	 * Return the value associated with the column: BrutoProductos
	 */
	public java.math.BigDecimal getBrutoProductos () {
		return brutoProductos;
	}

	/**
	 * Set the value related to the column: BrutoProductos
	 * @param brutoProductos the BrutoProductos value
	 */
	public void setBrutoProductos (java.math.BigDecimal brutoProductos) {
		this.brutoProductos = brutoProductos;
	}



	/**
	 * Return the value associated with the column: BrutoVacios
	 */
	public java.math.BigDecimal getBrutoVacios () {
		return brutoVacios;
	}

	/**
	 * Set the value related to the column: BrutoVacios
	 * @param brutoVacios the BrutoVacios value
	 */
	public void setBrutoVacios (java.math.BigDecimal brutoVacios) {
		this.brutoVacios = brutoVacios;
	}



	/**
	 * Return the value associated with the column: Gastos1
	 */
	public java.math.BigDecimal getGastos1 () {
		return gastos1;
	}

	/**
	 * Set the value related to the column: Gastos1
	 * @param gastos1 the Gastos1 value
	 */
	public void setGastos1 (java.math.BigDecimal gastos1) {
		this.gastos1 = gastos1;
	}



	/**
	 * Return the value associated with the column: Gastos2
	 */
	public java.math.BigDecimal getGastos2 () {
		return gastos2;
	}

	/**
	 * Set the value related to the column: Gastos2
	 * @param gastos2 the Gastos2 value
	 */
	public void setGastos2 (java.math.BigDecimal gastos2) {
		this.gastos2 = gastos2;
	}



	/**
	 * Return the value associated with the column: Gastos3
	 */
	public java.math.BigDecimal getGastos3 () {
		return gastos3;
	}

	/**
	 * Set the value related to the column: Gastos3
	 * @param gastos3 the Gastos3 value
	 */
	public void setGastos3 (java.math.BigDecimal gastos3) {
		this.gastos3 = gastos3;
	}



	/**
	 * Return the value associated with the column: DescGastos1
	 */
	public java.lang.String getDescGastos1 () {
		return descGastos1;
	}

	/**
	 * Set the value related to the column: DescGastos1
	 * @param descGastos1 the DescGastos1 value
	 */
	public void setDescGastos1 (java.lang.String descGastos1) {
		this.descGastos1 = descGastos1;
	}



	/**
	 * Return the value associated with the column: DescGastos2
	 */
	public java.lang.String getDescGastos2 () {
		return descGastos2;
	}

	/**
	 * Set the value related to the column: DescGastos2
	 * @param descGastos2 the DescGastos2 value
	 */
	public void setDescGastos2 (java.lang.String descGastos2) {
		this.descGastos2 = descGastos2;
	}



	/**
	 * Return the value associated with the column: DescGastos3
	 */
	public java.lang.String getDescGastos3 () {
		return descGastos3;
	}

	/**
	 * Set the value related to the column: DescGastos3
	 * @param descGastos3 the DescGastos3 value
	 */
	public void setDescGastos3 (java.lang.String descGastos3) {
		this.descGastos3 = descGastos3;
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
	 * Return the value associated with the column: NetoFactura
	 */
	public java.math.BigDecimal getNetoFactura () {
		return netoFactura;
	}

	/**
	 * Set the value related to the column: NetoFactura
	 * @param netoFactura the NetoFactura value
	 */
	public void setNetoFactura (java.math.BigDecimal netoFactura) {
		this.netoFactura = netoFactura;
	}



	/**
	 * Return the value associated with the column: CatIva
	 */
	public java.lang.Short getCatIva () {
		return catIva;
	}

	/**
	 * Set the value related to the column: CatIva
	 * @param catIva the CatIva value
	 */
	public void setCatIva (java.lang.Short catIva) {
		this.catIva = catIva;
	}



	/**
	 * Return the value associated with the column: ListaPrecio
	 */
	public java.lang.Short getListaPrecio () {
		return listaPrecio;
	}

	/**
	 * Set the value related to the column: ListaPrecio
	 * @param listaPrecio the ListaPrecio value
	 */
	public void setListaPrecio (java.lang.Short listaPrecio) {
		this.listaPrecio = listaPrecio;
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
	 * Return the value associated with the column: EsAjuste
	 */
	public java.lang.Boolean isEsAjuste () {
		return esAjuste;
	}

	/**
	 * Set the value related to the column: EsAjuste
	 * @param esAjuste the EsAjuste value
	 */
	public void setEsAjuste (java.lang.Boolean esAjuste) {
		this.esAjuste = esAjuste;
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
	 * Return the value associated with the column: EstadoRendicionRefipass
	 */
	public java.lang.Integer getEstadoRendicionRefipass () {
		return estadoRendicionRefipass;
	}

	/**
	 * Set the value related to the column: EstadoRendicionRefipass
	 * @param estadoRendicionRefipass the EstadoRendicionRefipass value
	 */
	public void setEstadoRendicionRefipass (java.lang.Integer estadoRendicionRefipass) {
		this.estadoRendicionRefipass = estadoRendicionRefipass;
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
	 * Return the value associated with the column: tipoComp
	 */
	public java.lang.String getTipoComp () {
		return tipoComp;
	}

	/**
	 * Set the value related to the column: tipoComp
	 * @param tipoComp the tipoComp value
	 */
	public void setTipoComp (java.lang.String tipoComp) {
		this.tipoComp = tipoComp;
	}



	/**
	 * Return the value associated with the column: cond_vta
	 */
	public java.lang.Integer getCondVta () {
		return condVta;
	}

	/**
	 * Set the value related to the column: cond_vta
	 * @param condVta the cond_vta value
	 */
	public void setCondVta (java.lang.Integer condVta) {
		this.condVta = condVta;
	}



	/**
	 * Return the value associated with the column: nro_suc_asoc
	 */
	public java.lang.Integer getNroSucAsoc () {
		return nroSucAsoc;
	}

	/**
	 * Set the value related to the column: nro_suc_asoc
	 * @param nroSucAsoc the nro_suc_asoc value
	 */
	public void setNroSucAsoc (java.lang.Integer nroSucAsoc) {
		this.nroSucAsoc = nroSucAsoc;
	}



	/**
	 * Return the value associated with the column: orden_asoc
	 */
	public java.lang.Integer getOrdenAsoc () {
		return ordenAsoc;
	}

	/**
	 * Set the value related to the column: orden_asoc
	 * @param ordenAsoc the orden_asoc value
	 */
	public void setOrdenAsoc (java.lang.Integer ordenAsoc) {
		this.ordenAsoc = ordenAsoc;
	}



	/**
	 * Return the value associated with the column: CodigoBarras
	 */
	public java.lang.String getCodigoBarras () {
		return codigoBarras;
	}

	/**
	 * Set the value related to the column: CodigoBarras
	 * @param codigoBarras the CodigoBarras value
	 */
	public void setCodigoBarras (java.lang.String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}



	/**
	 * Return the value associated with the column: CodigoBarrasImpresion
	 */
	public java.lang.String getCodigoBarrasImpresion () {
		return codigoBarrasImpresion;
	}

	/**
	 * Set the value related to the column: CodigoBarrasImpresion
	 * @param codigoBarrasImpresion the CodigoBarrasImpresion value
	 */
	public void setCodigoBarrasImpresion (java.lang.String codigoBarrasImpresion) {
		this.codigoBarrasImpresion = codigoBarrasImpresion;
	}



	/**
	 * Return the value associated with the column: cai
	 */
	public java.lang.Integer getCai () {
		return cai;
	}

	/**
	 * Set the value related to the column: cai
	 * @param cai the cai value
	 */
	public void setCai (java.lang.Integer cai) {
		this.cai = cai;
	}



	/**
	 * Return the value associated with the column: feVtoCai
	 */
	public java.util.Date getFeVtoCai () {
		return feVtoCai;
	}

	/**
	 * Set the value related to the column: feVtoCai
	 * @param feVtoCai the feVtoCai value
	 */
	public void setFeVtoCai (java.util.Date feVtoCai) {
		this.feVtoCai = feVtoCai;
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
		if (!(obj instanceof com.refinor.extranet.data.MfacturasV)) return false;
		else {
			com.refinor.extranet.data.MfacturasV mfacturasV = (com.refinor.extranet.data.MfacturasV) obj;
			if (null == this.getId() || null == mfacturasV.getId()) return false;
			else return (this.getId().equals(mfacturasV.getId()));
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