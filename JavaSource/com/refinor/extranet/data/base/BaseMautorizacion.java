package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MAutorizacion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MAutorizacion"
 */

public abstract class BaseMautorizacion  implements Serializable {

	public static String REF = "Mautorizacion";
	public static String PROP_ID_CODIGO_AUTO = "idCodigoAuto";
	public static String PROP_COD_CLIENTE = "codCliente";
	public static String PROP_COD_TIPO_AUTO = "codTipoAuto";
	public static String PROP_SUCURSAL_ANULAR = "sucursalAnular";
	public static String PROP_LITROS_ACARGAR = "litrosAcargar";
	public static String PROP_FECHA = "fecha";
	public static String PROP_TIPO_COMPROBANTE_MANUAL = "tipoComprobanteManual";
	public static String PROP_COD_VENDEDOR = "codVendedor";
	public static String PROP_SUCURSAL_MANUAL = "sucursalManual";
	public static String PROP_NRO_REMITO2 = "nroRemito2";
	public static String PROP_COD_MOTIVO = "codMotivo";
	public static String PROP_COD_DESCRIPCION = "codDescripcion";
	public static String PROP_DESCRIPCION = "descripcion";
	public static String PROP_COD_VEHICULO = "codVehiculo";
	public static String PROP_NRO_REMITO1 = "nroRemito1";
	public static String PROP_COD_CHOFER = "codChofer";
	public static String PROP_COD_CCSS = "codCcss";
	public static String PROP_COD_EMISOR = "codEmisor";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_TIPO_COMPROBANTE_ANULAR = "tipoComprobanteAnular";
	public static String PROP_FECHA_PEDIDO = "fechaPedido";
	public static String PROP_PRODUCTO = "producto";


	// constructors
	public BaseMautorizacion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMautorizacion (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.Integer codVendedor;
	private java.lang.Integer codCcss;
	private java.lang.Integer codCliente;
	private java.lang.Integer codMotivo;
	private java.lang.String descripcion;
	private java.lang.Integer codChofer;
	private java.lang.Integer codVehiculo;
	private java.lang.Integer nroRemito1;
	private java.lang.Integer nroRemito2;
	private java.lang.Integer codTipoAuto;
	private java.util.Date fecha;
	private java.lang.Integer idCodigoAuto;
	private java.lang.Integer codEmisor;
	private java.lang.Integer codDescripcion;
	private java.math.BigDecimal litrosAcargar;
	private java.lang.Integer tipoComprobanteManual;
	private java.lang.Integer tipoComprobanteAnular;
	private java.lang.Integer producto;
	private java.util.Date fechaPedido;
	private java.lang.Integer sucursalManual;
	private java.lang.Integer sucursalAnular;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="codigo"
     */
	public int getCodigo () {
		return codigo;
	}

	/**
	 * Set the unique identifier of this class
	 * @param codigo the new ID
	 */
	public void setCodigo (int codigo) {
		this.codigo = codigo;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: codVendedor
	 */
	public java.lang.Integer getCodVendedor () {
		return codVendedor;
	}

	/**
	 * Set the value related to the column: codVendedor
	 * @param codVendedor the codVendedor value
	 */
	public void setCodVendedor (java.lang.Integer codVendedor) {
		this.codVendedor = codVendedor;
	}



	/**
	 * Return the value associated with the column: codCCSS
	 */
	public java.lang.Integer getCodCcss () {
		return codCcss;
	}

	/**
	 * Set the value related to the column: codCCSS
	 * @param codCcss the codCCSS value
	 */
	public void setCodCcss (java.lang.Integer codCcss) {
		this.codCcss = codCcss;
	}



	/**
	 * Return the value associated with the column: codCliente
	 */
	public java.lang.Integer getCodCliente () {
		return codCliente;
	}

	/**
	 * Set the value related to the column: codCliente
	 * @param codCliente the codCliente value
	 */
	public void setCodCliente (java.lang.Integer codCliente) {
		this.codCliente = codCliente;
	}



	/**
	 * Return the value associated with the column: codMotivo
	 */
	public java.lang.Integer getCodMotivo () {
		return codMotivo;
	}

	/**
	 * Set the value related to the column: codMotivo
	 * @param codMotivo the codMotivo value
	 */
	public void setCodMotivo (java.lang.Integer codMotivo) {
		this.codMotivo = codMotivo;
	}



	/**
	 * Return the value associated with the column: descripcion
	 */
	public java.lang.String getDescripcion () {
		return descripcion;
	}

	/**
	 * Set the value related to the column: descripcion
	 * @param descripcion the descripcion value
	 */
	public void setDescripcion (java.lang.String descripcion) {
		this.descripcion = descripcion;
	}



	/**
	 * Return the value associated with the column: codChofer
	 */
	public java.lang.Integer getCodChofer () {
		return codChofer;
	}

	/**
	 * Set the value related to the column: codChofer
	 * @param codChofer the codChofer value
	 */
	public void setCodChofer (java.lang.Integer codChofer) {
		this.codChofer = codChofer;
	}



	/**
	 * Return the value associated with the column: codVehiculo
	 */
	public java.lang.Integer getCodVehiculo () {
		return codVehiculo;
	}

	/**
	 * Set the value related to the column: codVehiculo
	 * @param codVehiculo the codVehiculo value
	 */
	public void setCodVehiculo (java.lang.Integer codVehiculo) {
		this.codVehiculo = codVehiculo;
	}



	/**
	 * Return the value associated with the column: nroRemito1
	 */
	public java.lang.Integer getNroRemito1 () {
		return nroRemito1;
	}

	/**
	 * Set the value related to the column: nroRemito1
	 * @param nroRemito1 the nroRemito1 value
	 */
	public void setNroRemito1 (java.lang.Integer nroRemito1) {
		this.nroRemito1 = nroRemito1;
	}



	/**
	 * Return the value associated with the column: nroRemito2
	 */
	public java.lang.Integer getNroRemito2 () {
		return nroRemito2;
	}

	/**
	 * Set the value related to the column: nroRemito2
	 * @param nroRemito2 the nroRemito2 value
	 */
	public void setNroRemito2 (java.lang.Integer nroRemito2) {
		this.nroRemito2 = nroRemito2;
	}



	/**
	 * Return the value associated with the column: codTipoAuto
	 */
	public java.lang.Integer getCodTipoAuto () {
		return codTipoAuto;
	}

	/**
	 * Set the value related to the column: codTipoAuto
	 * @param codTipoAuto the codTipoAuto value
	 */
	public void setCodTipoAuto (java.lang.Integer codTipoAuto) {
		this.codTipoAuto = codTipoAuto;
	}



	/**
	 * Return the value associated with the column: fecha
	 */
	public java.util.Date getFecha () {
		return fecha;
	}

	/**
	 * Set the value related to the column: fecha
	 * @param fecha the fecha value
	 */
	public void setFecha (java.util.Date fecha) {
		this.fecha = fecha;
	}



	/**
	 * Return the value associated with the column: idCodigoAuto
	 */
	public java.lang.Integer getIdCodigoAuto () {
		return idCodigoAuto;
	}

	/**
	 * Set the value related to the column: idCodigoAuto
	 * @param idCodigoAuto the idCodigoAuto value
	 */
	public void setIdCodigoAuto (java.lang.Integer idCodigoAuto) {
		this.idCodigoAuto = idCodigoAuto;
	}



	/**
	 * Return the value associated with the column: codEmisor
	 */
	public java.lang.Integer getCodEmisor () {
		return codEmisor;
	}

	/**
	 * Set the value related to the column: codEmisor
	 * @param codEmisor the codEmisor value
	 */
	public void setCodEmisor (java.lang.Integer codEmisor) {
		this.codEmisor = codEmisor;
	}



	/**
	 * Return the value associated with the column: codDescripcion
	 */
	public java.lang.Integer getCodDescripcion () {
		return codDescripcion;
	}

	/**
	 * Set the value related to the column: codDescripcion
	 * @param codDescripcion the codDescripcion value
	 */
	public void setCodDescripcion (java.lang.Integer codDescripcion) {
		this.codDescripcion = codDescripcion;
	}



	/**
	 * Return the value associated with the column: litrosACargar
	 */
	public java.math.BigDecimal getLitrosAcargar () {
		return litrosAcargar;
	}

	/**
	 * Set the value related to the column: litrosACargar
	 * @param litrosAcargar the litrosACargar value
	 */
	public void setLitrosAcargar (java.math.BigDecimal litrosAcargar) {
		this.litrosAcargar = litrosAcargar;
	}



	/**
	 * Return the value associated with the column: TipoComprobanteManual
	 */
	public java.lang.Integer getTipoComprobanteManual () {
		return tipoComprobanteManual;
	}

	/**
	 * Set the value related to the column: TipoComprobanteManual
	 * @param tipoComprobanteManual the TipoComprobanteManual value
	 */
	public void setTipoComprobanteManual (java.lang.Integer tipoComprobanteManual) {
		this.tipoComprobanteManual = tipoComprobanteManual;
	}



	/**
	 * Return the value associated with the column: TipoComprobanteAnular
	 */
	public java.lang.Integer getTipoComprobanteAnular () {
		return tipoComprobanteAnular;
	}

	/**
	 * Set the value related to the column: TipoComprobanteAnular
	 * @param tipoComprobanteAnular the TipoComprobanteAnular value
	 */
	public void setTipoComprobanteAnular (java.lang.Integer tipoComprobanteAnular) {
		this.tipoComprobanteAnular = tipoComprobanteAnular;
	}



	/**
	 * Return the value associated with the column: Producto
	 */
	public java.lang.Integer getProducto () {
		return producto;
	}

	/**
	 * Set the value related to the column: Producto
	 * @param producto the Producto value
	 */
	public void setProducto (java.lang.Integer producto) {
		this.producto = producto;
	}



	/**
	 * Return the value associated with the column: FechaPedido
	 */
	public java.util.Date getFechaPedido () {
		return fechaPedido;
	}

	/**
	 * Set the value related to the column: FechaPedido
	 * @param fechaPedido the FechaPedido value
	 */
	public void setFechaPedido (java.util.Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}



	/**
	 * Return the value associated with the column: SucursalManual
	 */
	public java.lang.Integer getSucursalManual () {
		return sucursalManual;
	}

	/**
	 * Set the value related to the column: SucursalManual
	 * @param sucursalManual the SucursalManual value
	 */
	public void setSucursalManual (java.lang.Integer sucursalManual) {
		this.sucursalManual = sucursalManual;
	}



	/**
	 * Return the value associated with the column: SucursalAnular
	 */
	public java.lang.Integer getSucursalAnular () {
		return sucursalAnular;
	}

	/**
	 * Set the value related to the column: SucursalAnular
	 * @param sucursalAnular the SucursalAnular value
	 */
	public void setSucursalAnular (java.lang.Integer sucursalAnular) {
		this.sucursalAnular = sucursalAnular;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mautorizacion)) return false;
		else {
			com.refinor.extranet.data.Mautorizacion mautorizacion = (com.refinor.extranet.data.Mautorizacion) obj;
			return (this.getCodigo() == mautorizacion.getCodigo());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getCodigo();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}