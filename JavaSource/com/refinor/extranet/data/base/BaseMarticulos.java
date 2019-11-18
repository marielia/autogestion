package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the Marticulos table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="Marticulos"
 */

public abstract class BaseMarticulos  implements Serializable {

	public static String REF = "Marticulos";
	public static String PROP_TAMAÑO = "tamaño";
	public static String PROP_ES_COMB = "esComb";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_PRESENTACION = "presentacion";
	public static String PROP_MADUREZ = "madurez";
	public static String PROP_BULTOS = "bultos";
	public static String PROP_ES_GASOIL = "esGasoil";
	public static String PROP_ESPECIE = "especie";
	public static String PROP_DESCRIPCION = "descripcion";
	public static String PROP_ARRIVO = "arrivo";
	public static String PROP_CANTIDAD = "cantidad";
	public static String PROP_ESP_VAR = "espVar";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_ACTIVO = "activo";
	public static String PROP_VARIEDAD = "variedad";
	public static String PROP_SALDO_K = "saldoK";
	public static String PROP_REC_MANUAL = "recManual";
	public static String PROP_GRUPO_DESC = "grupoDesc";
	public static String PROP_CODI_ORIGEN = "codiOrigen";
	public static String PROP_FE_ARRIVO = "feArrivo";
	public static String PROP_ALERTA_VTO = "alertaVto";
	public static String PROP_PRECIO_DEFINITIVO = "precioDefinitivo";
	public static String PROP_PROVEEDOR = "proveedor";
	public static String PROP_AFECTA_STOCK = "afectaStock";
	public static String PROP_COD_FACTURA_C = "codFacturaC";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_CON_INSU = "conInsu";
	public static String PROP_SALDO_B = "saldoB";
	public static String PROP_COD_BARRA = "codBarra";
	public static String PROP_MIN_FACT = "minFact";
	public static String PROP_CALIDAD = "calidad";
	public static String PROP_PRECIO_K = "precioK";
	public static String PROP_UN_MEDIDA = "unMedida";
	public static String PROP_DESCRIPCION_BREVE = "descripcionBreve";
	public static String PROP_ZONA = "zona";


	// constructors
	public BaseMarticulos () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMarticulos (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.Integer codFacturaC;
	private int proveedor;
	private java.util.Date feArrivo;
	private java.lang.Integer arrivo;
	private java.lang.String descripcion;
	private java.lang.String descripcionBreve;
	private int espVar;
	private int especie;
	private int variedad;
	private java.lang.Integer zona;
	private java.lang.Integer calidad;
	private java.lang.Integer presentacion;
	private java.lang.Integer tamaño;
	private java.lang.Integer madurez;
	private java.math.BigDecimal precioK;
	private java.lang.Double cantidad;
	private java.lang.Integer bultos;
	private java.lang.Double saldoK;
	private java.lang.Integer saldoB;
	private java.lang.String precioDefinitivo;
	private java.lang.Integer idUserLock;
	private java.util.Date fhBaja;
	private java.lang.Integer codiOrigen;
	private java.math.BigDecimal codBarra;
	private java.lang.Integer unMedida;
	private java.math.BigDecimal minFact;
	private java.lang.Integer grupoDesc;
	private java.lang.Boolean conInsu;
	private java.lang.Boolean esComb;
	private java.lang.Boolean alertaVto;
	private java.lang.Boolean activo;
	private java.lang.Boolean recManual;
	private java.lang.Boolean afectaStock;
	private java.lang.Boolean esGasoil;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="Codigo"
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
	 * Return the value associated with the column: CodFacturaC
	 */
	public java.lang.Integer getCodFacturaC () {
		return codFacturaC;
	}

	/**
	 * Set the value related to the column: CodFacturaC
	 * @param codFacturaC the CodFacturaC value
	 */
	public void setCodFacturaC (java.lang.Integer codFacturaC) {
		this.codFacturaC = codFacturaC;
	}



	/**
	 * Return the value associated with the column: Proveedor
	 */
	public int getProveedor () {
		return proveedor;
	}

	/**
	 * Set the value related to the column: Proveedor
	 * @param proveedor the Proveedor value
	 */
	public void setProveedor (int proveedor) {
		this.proveedor = proveedor;
	}



	/**
	 * Return the value associated with the column: FeArrivo
	 */
	public java.util.Date getFeArrivo () {
		return feArrivo;
	}

	/**
	 * Set the value related to the column: FeArrivo
	 * @param feArrivo the FeArrivo value
	 */
	public void setFeArrivo (java.util.Date feArrivo) {
		this.feArrivo = feArrivo;
	}



	/**
	 * Return the value associated with the column: Arrivo
	 */
	public java.lang.Integer getArrivo () {
		return arrivo;
	}

	/**
	 * Set the value related to the column: Arrivo
	 * @param arrivo the Arrivo value
	 */
	public void setArrivo (java.lang.Integer arrivo) {
		this.arrivo = arrivo;
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
	 * Return the value associated with the column: DescripcionBreve
	 */
	public java.lang.String getDescripcionBreve () {
		return descripcionBreve;
	}

	/**
	 * Set the value related to the column: DescripcionBreve
	 * @param descripcionBreve the DescripcionBreve value
	 */
	public void setDescripcionBreve (java.lang.String descripcionBreve) {
		this.descripcionBreve = descripcionBreve;
	}



	/**
	 * Return the value associated with the column: EspVar
	 */
	public int getEspVar () {
		return espVar;
	}

	/**
	 * Set the value related to the column: EspVar
	 * @param espVar the EspVar value
	 */
	public void setEspVar (int espVar) {
		this.espVar = espVar;
	}



	/**
	 * Return the value associated with the column: Especie
	 */
	public int getEspecie () {
		return especie;
	}

	/**
	 * Set the value related to the column: Especie
	 * @param especie the Especie value
	 */
	public void setEspecie (int especie) {
		this.especie = especie;
	}



	/**
	 * Return the value associated with the column: Variedad
	 */
	public int getVariedad () {
		return variedad;
	}

	/**
	 * Set the value related to the column: Variedad
	 * @param variedad the Variedad value
	 */
	public void setVariedad (int variedad) {
		this.variedad = variedad;
	}



	/**
	 * Return the value associated with the column: Zona
	 */
	public java.lang.Integer getZona () {
		return zona;
	}

	/**
	 * Set the value related to the column: Zona
	 * @param zona the Zona value
	 */
	public void setZona (java.lang.Integer zona) {
		this.zona = zona;
	}



	/**
	 * Return the value associated with the column: Calidad
	 */
	public java.lang.Integer getCalidad () {
		return calidad;
	}

	/**
	 * Set the value related to the column: Calidad
	 * @param calidad the Calidad value
	 */
	public void setCalidad (java.lang.Integer calidad) {
		this.calidad = calidad;
	}



	/**
	 * Return the value associated with the column: Presentacion
	 */
	public java.lang.Integer getPresentacion () {
		return presentacion;
	}

	/**
	 * Set the value related to the column: Presentacion
	 * @param presentacion the Presentacion value
	 */
	public void setPresentacion (java.lang.Integer presentacion) {
		this.presentacion = presentacion;
	}



	/**
	 * Return the value associated with the column: Tamaño
	 */
	public java.lang.Integer getTamaño () {
		return tamaño;
	}

	/**
	 * Set the value related to the column: Tamaño
	 * @param tamaño the Tamaño value
	 */
	public void setTamaño (java.lang.Integer tamaño) {
		this.tamaño = tamaño;
	}



	/**
	 * Return the value associated with the column: Madurez
	 */
	public java.lang.Integer getMadurez () {
		return madurez;
	}

	/**
	 * Set the value related to the column: Madurez
	 * @param madurez the Madurez value
	 */
	public void setMadurez (java.lang.Integer madurez) {
		this.madurez = madurez;
	}



	/**
	 * Return the value associated with the column: PrecioK
	 */
	public java.math.BigDecimal getPrecioK () {
		return precioK;
	}

	/**
	 * Set the value related to the column: PrecioK
	 * @param precioK the PrecioK value
	 */
	public void setPrecioK (java.math.BigDecimal precioK) {
		this.precioK = precioK;
	}



	/**
	 * Return the value associated with the column: Cantidad
	 */
	public java.lang.Double getCantidad () {
		return cantidad;
	}

	/**
	 * Set the value related to the column: Cantidad
	 * @param cantidad the Cantidad value
	 */
	public void setCantidad (java.lang.Double cantidad) {
		this.cantidad = cantidad;
	}



	/**
	 * Return the value associated with the column: Bultos
	 */
	public java.lang.Integer getBultos () {
		return bultos;
	}

	/**
	 * Set the value related to the column: Bultos
	 * @param bultos the Bultos value
	 */
	public void setBultos (java.lang.Integer bultos) {
		this.bultos = bultos;
	}



	/**
	 * Return the value associated with the column: SaldoK
	 */
	public java.lang.Double getSaldoK () {
		return saldoK;
	}

	/**
	 * Set the value related to the column: SaldoK
	 * @param saldoK the SaldoK value
	 */
	public void setSaldoK (java.lang.Double saldoK) {
		this.saldoK = saldoK;
	}



	/**
	 * Return the value associated with the column: SaldoB
	 */
	public java.lang.Integer getSaldoB () {
		return saldoB;
	}

	/**
	 * Set the value related to the column: SaldoB
	 * @param saldoB the SaldoB value
	 */
	public void setSaldoB (java.lang.Integer saldoB) {
		this.saldoB = saldoB;
	}



	/**
	 * Return the value associated with the column: PrecioDefinitivo
	 */
	public java.lang.String getPrecioDefinitivo () {
		return precioDefinitivo;
	}

	/**
	 * Set the value related to the column: PrecioDefinitivo
	 * @param precioDefinitivo the PrecioDefinitivo value
	 */
	public void setPrecioDefinitivo (java.lang.String precioDefinitivo) {
		this.precioDefinitivo = precioDefinitivo;
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
	 * Return the value associated with the column: CodiOrigen
	 */
	public java.lang.Integer getCodiOrigen () {
		return codiOrigen;
	}

	/**
	 * Set the value related to the column: CodiOrigen
	 * @param codiOrigen the CodiOrigen value
	 */
	public void setCodiOrigen (java.lang.Integer codiOrigen) {
		this.codiOrigen = codiOrigen;
	}



	/**
	 * Return the value associated with the column: cod_barra
	 */
	public java.math.BigDecimal getCodBarra () {
		return codBarra;
	}

	/**
	 * Set the value related to the column: cod_barra
	 * @param codBarra the cod_barra value
	 */
	public void setCodBarra (java.math.BigDecimal codBarra) {
		this.codBarra = codBarra;
	}



	/**
	 * Return the value associated with the column: un_medida
	 */
	public java.lang.Integer getUnMedida () {
		return unMedida;
	}

	/**
	 * Set the value related to the column: un_medida
	 * @param unMedida the un_medida value
	 */
	public void setUnMedida (java.lang.Integer unMedida) {
		this.unMedida = unMedida;
	}



	/**
	 * Return the value associated with the column: min_fact
	 */
	public java.math.BigDecimal getMinFact () {
		return minFact;
	}

	/**
	 * Set the value related to the column: min_fact
	 * @param minFact the min_fact value
	 */
	public void setMinFact (java.math.BigDecimal minFact) {
		this.minFact = minFact;
	}



	/**
	 * Return the value associated with the column: grupo_desc
	 */
	public java.lang.Integer getGrupoDesc () {
		return grupoDesc;
	}

	/**
	 * Set the value related to the column: grupo_desc
	 * @param grupoDesc the grupo_desc value
	 */
	public void setGrupoDesc (java.lang.Integer grupoDesc) {
		this.grupoDesc = grupoDesc;
	}



	/**
	 * Return the value associated with the column: con_insu
	 */
	public java.lang.Boolean isConInsu () {
		return conInsu;
	}

	/**
	 * Set the value related to the column: con_insu
	 * @param conInsu the con_insu value
	 */
	public void setConInsu (java.lang.Boolean conInsu) {
		this.conInsu = conInsu;
	}



	/**
	 * Return the value associated with the column: es_comb
	 */
	public java.lang.Boolean isEsComb () {
		return esComb;
	}

	/**
	 * Set the value related to the column: es_comb
	 * @param esComb the es_comb value
	 */
	public void setEsComb (java.lang.Boolean esComb) {
		this.esComb = esComb;
	}



	/**
	 * Return the value associated with the column: alerta_vto
	 */
	public java.lang.Boolean isAlertaVto () {
		return alertaVto;
	}

	/**
	 * Set the value related to the column: alerta_vto
	 * @param alertaVto the alerta_vto value
	 */
	public void setAlertaVto (java.lang.Boolean alertaVto) {
		this.alertaVto = alertaVto;
	}



	/**
	 * Return the value associated with the column: activo
	 */
	public java.lang.Boolean isActivo () {
		return activo;
	}

	/**
	 * Set the value related to the column: activo
	 * @param activo the activo value
	 */
	public void setActivo (java.lang.Boolean activo) {
		this.activo = activo;
	}



	/**
	 * Return the value associated with the column: rec_manual
	 */
	public java.lang.Boolean isRecManual () {
		return recManual;
	}

	/**
	 * Set the value related to the column: rec_manual
	 * @param recManual the rec_manual value
	 */
	public void setRecManual (java.lang.Boolean recManual) {
		this.recManual = recManual;
	}



	/**
	 * Return the value associated with the column: afecta_stock
	 */
	public java.lang.Boolean isAfectaStock () {
		return afectaStock;
	}

	/**
	 * Set the value related to the column: afecta_stock
	 * @param afectaStock the afecta_stock value
	 */
	public void setAfectaStock (java.lang.Boolean afectaStock) {
		this.afectaStock = afectaStock;
	}



	/**
	 * Return the value associated with the column: es_gasoil
	 */
	public java.lang.Boolean isEsGasoil () {
		return esGasoil;
	}

	/**
	 * Set the value related to the column: es_gasoil
	 * @param esGasoil the es_gasoil value
	 */
	public void setEsGasoil (java.lang.Boolean esGasoil) {
		this.esGasoil = esGasoil;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Marticulos)) return false;
		else {
			com.refinor.extranet.data.Marticulos marticulos = (com.refinor.extranet.data.Marticulos) obj;
			return (this.getCodigo() == marticulos.getCodigo());
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