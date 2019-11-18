package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MovimientoStock table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MovimientoStock"
 */

public abstract class BaseMovimientoStock  implements Serializable {

	public static String REF = "MovimientoStock";
	public static String PROP_ROWGUID = "rowguid";
	public static String PROP_COSTO = "costo";
	public static String PROP_TIPO_COMPROBANTE = "tipoComprobante";
	public static String PROP_ALMACEN_ID = "almacenId";
	public static String PROP_USUARIO_ALTA = "usuarioAlta";
	public static String PROP_SUCURSAL_COMP = "sucursalComp";
	public static String PROP_COD_VEH = "codVeh";
	public static String PROP_CANTIDAD = "cantidad";
	public static String PROP_FECHA_MOVIMIENTO = "fechaMovimiento";
	public static String PROP_TIPO_COMPROBANTE_INTERNO = "tipoComprobanteInterno";
	public static String PROP_NUMERO_COMPROBANTE_INTERNO = "numeroComprobanteInterno";
	public static String PROP_TIPO_MOVIMIENTO = "tipoMovimiento";
	public static String PROP_NUMERO_COMPROBANTE = "numeroComprobante";
	public static String PROP_ARTICULO_ID = "articuloId";
	public static String PROP_LETRA_COMPROBANTE = "letraComprobante";
	public static String PROP_ID = "id";
	public static String PROP_CODIGO_TURNO_VIGENCIA = "codigoTurnoVigencia";


	// constructors
	public BaseMovimientoStock () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMovimientoStock (com.refinor.extranet.data.MovimientoStockId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MovimientoStockId id;

	// fields
	private int almacenId;
	private int articuloId;
	private java.math.BigDecimal cantidad;
	private java.math.BigDecimal costo;
	private java.util.Date fechaMovimiento;
	private java.lang.String usuarioAlta;
	private java.lang.Short tipoComprobante;
	private java.lang.String letraComprobante;
	private java.lang.Integer sucursalComp;
	private java.lang.Integer numeroComprobante;
	private java.lang.Integer tipoMovimiento;
	private java.lang.Integer tipoComprobanteInterno;
	private java.lang.Integer numeroComprobanteInterno;
	private java.lang.Long codigoTurnoVigencia;
	private java.lang.Integer codVeh;
	private java.lang.String rowguid;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MovimientoStockId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MovimientoStockId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: AlmacenId
	 */
	public int getAlmacenId () {
		return almacenId;
	}

	/**
	 * Set the value related to the column: AlmacenId
	 * @param almacenId the AlmacenId value
	 */
	public void setAlmacenId (int almacenId) {
		this.almacenId = almacenId;
	}



	/**
	 * Return the value associated with the column: ArticuloId
	 */
	public int getArticuloId () {
		return articuloId;
	}

	/**
	 * Set the value related to the column: ArticuloId
	 * @param articuloId the ArticuloId value
	 */
	public void setArticuloId (int articuloId) {
		this.articuloId = articuloId;
	}



	/**
	 * Return the value associated with the column: Cantidad
	 */
	public java.math.BigDecimal getCantidad () {
		return cantidad;
	}

	/**
	 * Set the value related to the column: Cantidad
	 * @param cantidad the Cantidad value
	 */
	public void setCantidad (java.math.BigDecimal cantidad) {
		this.cantidad = cantidad;
	}



	/**
	 * Return the value associated with the column: Costo
	 */
	public java.math.BigDecimal getCosto () {
		return costo;
	}

	/**
	 * Set the value related to the column: Costo
	 * @param costo the Costo value
	 */
	public void setCosto (java.math.BigDecimal costo) {
		this.costo = costo;
	}



	/**
	 * Return the value associated with the column: FechaMovimiento
	 */
	public java.util.Date getFechaMovimiento () {
		return fechaMovimiento;
	}

	/**
	 * Set the value related to the column: FechaMovimiento
	 * @param fechaMovimiento the FechaMovimiento value
	 */
	public void setFechaMovimiento (java.util.Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}



	/**
	 * Return the value associated with the column: UsuarioAlta
	 */
	public java.lang.String getUsuarioAlta () {
		return usuarioAlta;
	}

	/**
	 * Set the value related to the column: UsuarioAlta
	 * @param usuarioAlta the UsuarioAlta value
	 */
	public void setUsuarioAlta (java.lang.String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}



	/**
	 * Return the value associated with the column: TipoComprobante
	 */
	public java.lang.Short getTipoComprobante () {
		return tipoComprobante;
	}

	/**
	 * Set the value related to the column: TipoComprobante
	 * @param tipoComprobante the TipoComprobante value
	 */
	public void setTipoComprobante (java.lang.Short tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}



	/**
	 * Return the value associated with the column: LetraComprobante
	 */
	public java.lang.String getLetraComprobante () {
		return letraComprobante;
	}

	/**
	 * Set the value related to the column: LetraComprobante
	 * @param letraComprobante the LetraComprobante value
	 */
	public void setLetraComprobante (java.lang.String letraComprobante) {
		this.letraComprobante = letraComprobante;
	}



	/**
	 * Return the value associated with the column: sucursalComp
	 */
	public java.lang.Integer getSucursalComp () {
		return sucursalComp;
	}

	/**
	 * Set the value related to the column: sucursalComp
	 * @param sucursalComp the sucursalComp value
	 */
	public void setSucursalComp (java.lang.Integer sucursalComp) {
		this.sucursalComp = sucursalComp;
	}



	/**
	 * Return the value associated with the column: NumeroComprobante
	 */
	public java.lang.Integer getNumeroComprobante () {
		return numeroComprobante;
	}

	/**
	 * Set the value related to the column: NumeroComprobante
	 * @param numeroComprobante the NumeroComprobante value
	 */
	public void setNumeroComprobante (java.lang.Integer numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}



	/**
	 * Return the value associated with the column: TipoMovimiento
	 */
	public java.lang.Integer getTipoMovimiento () {
		return tipoMovimiento;
	}

	/**
	 * Set the value related to the column: TipoMovimiento
	 * @param tipoMovimiento the TipoMovimiento value
	 */
	public void setTipoMovimiento (java.lang.Integer tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}



	/**
	 * Return the value associated with the column: TipoComprobanteInterno
	 */
	public java.lang.Integer getTipoComprobanteInterno () {
		return tipoComprobanteInterno;
	}

	/**
	 * Set the value related to the column: TipoComprobanteInterno
	 * @param tipoComprobanteInterno the TipoComprobanteInterno value
	 */
	public void setTipoComprobanteInterno (java.lang.Integer tipoComprobanteInterno) {
		this.tipoComprobanteInterno = tipoComprobanteInterno;
	}



	/**
	 * Return the value associated with the column: NumeroComprobanteInterno
	 */
	public java.lang.Integer getNumeroComprobanteInterno () {
		return numeroComprobanteInterno;
	}

	/**
	 * Set the value related to the column: NumeroComprobanteInterno
	 * @param numeroComprobanteInterno the NumeroComprobanteInterno value
	 */
	public void setNumeroComprobanteInterno (java.lang.Integer numeroComprobanteInterno) {
		this.numeroComprobanteInterno = numeroComprobanteInterno;
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
	 * Return the value associated with the column: cod_veh
	 */
	public java.lang.Integer getCodVeh () {
		return codVeh;
	}

	/**
	 * Set the value related to the column: cod_veh
	 * @param codVeh the cod_veh value
	 */
	public void setCodVeh (java.lang.Integer codVeh) {
		this.codVeh = codVeh;
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
		if (!(obj instanceof com.refinor.extranet.data.MovimientoStock)) return false;
		else {
			com.refinor.extranet.data.MovimientoStock movimientoStock = (com.refinor.extranet.data.MovimientoStock) obj;
			if (null == this.getId() || null == movimientoStock.getId()) return false;
			else return (this.getId().equals(movimientoStock.getId()));
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