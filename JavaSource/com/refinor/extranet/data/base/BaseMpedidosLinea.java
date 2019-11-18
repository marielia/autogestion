package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the mPedidosLinea table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="mPedidosLinea"
 */

public abstract class BaseMpedidosLinea  implements Serializable {

	public static String REF = "MpedidosLinea";
	public static String PROP_LUGAR = "lugar";
	public static String PROP_IMPUESTO_GR4 = "impuestoGr4";
	public static String PROP_BULTOS2 = "bultos2";
	public static String PROP_PROV_COD = "provCod";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_BULTOS1 = "bultos1";
	public static String PROP_COD_MODIF = "codModif";
	public static String PROP_CODIGO_ENV = "codigoEnv";
	public static String PROP_DESCRIPCION = "descripcion";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_COD_ARTICULO = "codArticulo";
	public static String PROP_CANTIDAD1 = "cantidad1";
	public static String PROP_ESP_VAR = "espVar";
	public static String PROP_CANTIDAD_ENV = "cantidadEnv";
	public static String PROP_COD_EXT = "codExt";
	public static String PROP_BULTOS3 = "bultos3";
	public static String PROP_CANTIDAD3 = "cantidad3";
	public static String PROP_CANTIDAD2 = "cantidad2";
	public static String PROP_PRECIO_KILO = "precioKilo";
	public static String PROP_ID = "id";


	// constructors
	public BaseMpedidosLinea () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMpedidosLinea (com.refinor.extranet.data.MpedidosLineaId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MpedidosLineaId id;

	// fields
	private java.lang.String lugar;
	private java.lang.Integer codArticulo;
	private java.lang.Integer provCod;
	private java.lang.Integer espVar;
	private java.lang.String descripcion;
	private java.lang.Double cantidad1;
	private java.lang.Integer bultos1;
	private java.lang.Double cantidad2;
	private java.lang.Integer bultos2;
	private java.lang.Double cantidad3;
	private java.lang.Integer bultos3;
	private java.lang.Integer codModif;
	private java.lang.Integer codigoEnv;
	private java.lang.Integer cantidadEnv;
	private java.lang.String codExt;
	private java.math.BigDecimal precioKilo;
	private java.lang.Integer idUserLock;
	private java.util.Date fhBaja;
	private java.math.BigDecimal impuestoGr4;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MpedidosLineaId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MpedidosLineaId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: Lugar
	 */
	public java.lang.String getLugar () {
		return lugar;
	}

	/**
	 * Set the value related to the column: Lugar
	 * @param lugar the Lugar value
	 */
	public void setLugar (java.lang.String lugar) {
		this.lugar = lugar;
	}



	/**
	 * Return the value associated with the column: CodArticulo
	 */
	public java.lang.Integer getCodArticulo () {
		return codArticulo;
	}

	/**
	 * Set the value related to the column: CodArticulo
	 * @param codArticulo the CodArticulo value
	 */
	public void setCodArticulo (java.lang.Integer codArticulo) {
		this.codArticulo = codArticulo;
	}



	/**
	 * Return the value associated with the column: ProvCod
	 */
	public java.lang.Integer getProvCod () {
		return provCod;
	}

	/**
	 * Set the value related to the column: ProvCod
	 * @param provCod the ProvCod value
	 */
	public void setProvCod (java.lang.Integer provCod) {
		this.provCod = provCod;
	}



	/**
	 * Return the value associated with the column: EspVar
	 */
	public java.lang.Integer getEspVar () {
		return espVar;
	}

	/**
	 * Set the value related to the column: EspVar
	 * @param espVar the EspVar value
	 */
	public void setEspVar (java.lang.Integer espVar) {
		this.espVar = espVar;
	}



	/**
	 * Return the value associated with the column: Descripcion
	 */
	public java.lang.String getDescripcion () {
		return descripcion;
	}

	/**
	 * Set the value related to the column: Descripcion
	 * @param descripcion the Descripcion value
	 */
	public void setDescripcion (java.lang.String descripcion) {
		this.descripcion = descripcion;
	}



	/**
	 * Return the value associated with the column: Cantidad1
	 */
	public java.lang.Double getCantidad1 () {
		return cantidad1;
	}

	/**
	 * Set the value related to the column: Cantidad1
	 * @param cantidad1 the Cantidad1 value
	 */
	public void setCantidad1 (java.lang.Double cantidad1) {
		this.cantidad1 = cantidad1;
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
	 * Return the value associated with the column: Cantidad2
	 */
	public java.lang.Double getCantidad2 () {
		return cantidad2;
	}

	/**
	 * Set the value related to the column: Cantidad2
	 * @param cantidad2 the Cantidad2 value
	 */
	public void setCantidad2 (java.lang.Double cantidad2) {
		this.cantidad2 = cantidad2;
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
	 * Return the value associated with the column: Cantidad3
	 */
	public java.lang.Double getCantidad3 () {
		return cantidad3;
	}

	/**
	 * Set the value related to the column: Cantidad3
	 * @param cantidad3 the Cantidad3 value
	 */
	public void setCantidad3 (java.lang.Double cantidad3) {
		this.cantidad3 = cantidad3;
	}



	/**
	 * Return the value associated with the column: Bultos3
	 */
	public java.lang.Integer getBultos3 () {
		return bultos3;
	}

	/**
	 * Set the value related to the column: Bultos3
	 * @param bultos3 the Bultos3 value
	 */
	public void setBultos3 (java.lang.Integer bultos3) {
		this.bultos3 = bultos3;
	}



	/**
	 * Return the value associated with the column: CodModif
	 */
	public java.lang.Integer getCodModif () {
		return codModif;
	}

	/**
	 * Set the value related to the column: CodModif
	 * @param codModif the CodModif value
	 */
	public void setCodModif (java.lang.Integer codModif) {
		this.codModif = codModif;
	}



	/**
	 * Return the value associated with the column: CodigoEnv
	 */
	public java.lang.Integer getCodigoEnv () {
		return codigoEnv;
	}

	/**
	 * Set the value related to the column: CodigoEnv
	 * @param codigoEnv the CodigoEnv value
	 */
	public void setCodigoEnv (java.lang.Integer codigoEnv) {
		this.codigoEnv = codigoEnv;
	}



	/**
	 * Return the value associated with the column: CantidadEnv
	 */
	public java.lang.Integer getCantidadEnv () {
		return cantidadEnv;
	}

	/**
	 * Set the value related to the column: CantidadEnv
	 * @param cantidadEnv the CantidadEnv value
	 */
	public void setCantidadEnv (java.lang.Integer cantidadEnv) {
		this.cantidadEnv = cantidadEnv;
	}



	/**
	 * Return the value associated with the column: CodExt
	 */
	public java.lang.String getCodExt () {
		return codExt;
	}

	/**
	 * Set the value related to the column: CodExt
	 * @param codExt the CodExt value
	 */
	public void setCodExt (java.lang.String codExt) {
		this.codExt = codExt;
	}



	/**
	 * Return the value associated with the column: PrecioKilo
	 */
	public java.math.BigDecimal getPrecioKilo () {
		return precioKilo;
	}

	/**
	 * Set the value related to the column: PrecioKilo
	 * @param precioKilo the PrecioKilo value
	 */
	public void setPrecioKilo (java.math.BigDecimal precioKilo) {
		this.precioKilo = precioKilo;
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
		if (!(obj instanceof com.refinor.extranet.data.MpedidosLinea)) return false;
		else {
			com.refinor.extranet.data.MpedidosLinea mpedidosLinea = (com.refinor.extranet.data.MpedidosLinea) obj;
			if (null == this.getId() || null == mpedidosLinea.getId()) return false;
			else return (this.getId().equals(mpedidosLinea.getId()));
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