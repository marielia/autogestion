package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MCombustibleDespacho table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MCombustibleDespacho"
 */

public abstract class BaseMcombustibleDespacho  implements Serializable {

	public static String REF = "McombustibleDespacho";
	public static String PROP_LITROS = "litros";
	public static String PROP_ROWGUID = "rowguid";
	public static String PROP_COD_ARTICULO = "codArticulo";
	public static String PROP_TIPO_COMPROBANTE = "tipoComprobante";
	public static String PROP_FECHA = "fecha";
	public static String PROP_CCSSID = "ccssid";
	public static String PROP_ESTADO = "estado";
	public static String PROP_LETRA_COMPROBANTE = "letraComprobante";
	public static String PROP_NRO_COMPROBANTE = "nroComprobante";
	public static String PROP_ID = "id";
	public static String PROP_RESPONSABLE = "responsable";


	// constructors
	public BaseMcombustibleDespacho () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMcombustibleDespacho (long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private long id;

	// fields
	private java.lang.Integer ccssid;
	private java.lang.Long codArticulo;
	private java.math.BigDecimal litros;
	private java.util.Date fecha;
	private java.lang.Integer tipoComprobante;
	private java.lang.Character letraComprobante;
	private java.lang.Long nroComprobante;
	private java.lang.Integer estado;
	private java.lang.Long responsable;
	private java.lang.String rowguid;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="id"
     */
	public long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: CCSSId
	 */
	public java.lang.Integer getCcssid () {
		return ccssid;
	}

	/**
	 * Set the value related to the column: CCSSId
	 * @param ccssid the CCSSId value
	 */
	public void setCcssid (java.lang.Integer ccssid) {
		this.ccssid = ccssid;
	}



	/**
	 * Return the value associated with the column: CodArticulo
	 */
	public java.lang.Long getCodArticulo () {
		return codArticulo;
	}

	/**
	 * Set the value related to the column: CodArticulo
	 * @param codArticulo the CodArticulo value
	 */
	public void setCodArticulo (java.lang.Long codArticulo) {
		this.codArticulo = codArticulo;
	}



	/**
	 * Return the value associated with the column: Litros
	 */
	public java.math.BigDecimal getLitros () {
		return litros;
	}

	/**
	 * Set the value related to the column: Litros
	 * @param litros the Litros value
	 */
	public void setLitros (java.math.BigDecimal litros) {
		this.litros = litros;
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
	 * Return the value associated with the column: TipoComprobante
	 */
	public java.lang.Integer getTipoComprobante () {
		return tipoComprobante;
	}

	/**
	 * Set the value related to the column: TipoComprobante
	 * @param tipoComprobante the TipoComprobante value
	 */
	public void setTipoComprobante (java.lang.Integer tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}



	/**
	 * Return the value associated with the column: LetraComprobante
	 */
	public java.lang.Character getLetraComprobante () {
		return letraComprobante;
	}

	/**
	 * Set the value related to the column: LetraComprobante
	 * @param letraComprobante the LetraComprobante value
	 */
	public void setLetraComprobante (java.lang.Character letraComprobante) {
		this.letraComprobante = letraComprobante;
	}



	/**
	 * Return the value associated with the column: NroComprobante
	 */
	public java.lang.Long getNroComprobante () {
		return nroComprobante;
	}

	/**
	 * Set the value related to the column: NroComprobante
	 * @param nroComprobante the NroComprobante value
	 */
	public void setNroComprobante (java.lang.Long nroComprobante) {
		this.nroComprobante = nroComprobante;
	}



	/**
	 * Return the value associated with the column: Estado
	 */
	public java.lang.Integer getEstado () {
		return estado;
	}

	/**
	 * Set the value related to the column: Estado
	 * @param estado the Estado value
	 */
	public void setEstado (java.lang.Integer estado) {
		this.estado = estado;
	}



	/**
	 * Return the value associated with the column: Responsable
	 */
	public java.lang.Long getResponsable () {
		return responsable;
	}

	/**
	 * Set the value related to the column: Responsable
	 * @param responsable the Responsable value
	 */
	public void setResponsable (java.lang.Long responsable) {
		this.responsable = responsable;
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
		if (!(obj instanceof com.refinor.extranet.data.McombustibleDespacho)) return false;
		else {
			com.refinor.extranet.data.McombustibleDespacho mcombustibleDespacho = (com.refinor.extranet.data.McombustibleDespacho) obj;
			return (this.getId() == mcombustibleDespacho.getId());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getId();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}