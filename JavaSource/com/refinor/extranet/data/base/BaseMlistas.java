package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MListas table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MListas"
 */

public abstract class BaseMlistas  implements Serializable {

	public static String REF = "Mlistas";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_FECHA_H = "fechaH";
	public static String PROP_COD_ESP_VAR = "codEspVar";
	public static String PROP_COD_LISTA = "codLista";
	public static String PROP_ID_USER_LOCK = "idUserLock";
	public static String PROP_FH_ALTA = "fhAlta";
	public static String PROP_CODIGO = "codigo";
	public static String PROP_COD_EXT = "codExt";
	public static String PROP_PRECIO_K = "precioK";
	public static String PROP_FECHA_D = "fechaD";


	// constructors
	public BaseMlistas () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMlistas (int codigo) {
		this.setCodigo(codigo);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int codigo;

	// fields
	private java.lang.Integer codLista;
	private java.lang.Integer codEspVar;
	private java.util.Date fechaD;
	private java.util.Date fechaH;
	private java.math.BigDecimal precioK;
	private java.util.Date fhAlta;
	private java.lang.String codExt;
	private java.lang.Integer idUserLock;
	private java.util.Date fhBaja;



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
	 * Return the value associated with the column: CodLista
	 */
	public java.lang.Integer getCodLista () {
		return codLista;
	}

	/**
	 * Set the value related to the column: CodLista
	 * @param codLista the CodLista value
	 */
	public void setCodLista (java.lang.Integer codLista) {
		this.codLista = codLista;
	}



	/**
	 * Return the value associated with the column: CodEspVar
	 */
	public java.lang.Integer getCodEspVar () {
		return codEspVar;
	}

	/**
	 * Set the value related to the column: CodEspVar
	 * @param codEspVar the CodEspVar value
	 */
	public void setCodEspVar (java.lang.Integer codEspVar) {
		this.codEspVar = codEspVar;
	}



	/**
	 * Return the value associated with the column: FechaD
	 */
	public java.util.Date getFechaD () {
		return fechaD;
	}

	/**
	 * Set the value related to the column: FechaD
	 * @param fechaD the FechaD value
	 */
	public void setFechaD (java.util.Date fechaD) {
		this.fechaD = fechaD;
	}



	/**
	 * Return the value associated with the column: FechaH
	 */
	public java.util.Date getFechaH () {
		return fechaH;
	}

	/**
	 * Set the value related to the column: FechaH
	 * @param fechaH the FechaH value
	 */
	public void setFechaH (java.util.Date fechaH) {
		this.fechaH = fechaH;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mlistas)) return false;
		else {
			com.refinor.extranet.data.Mlistas mlistas = (com.refinor.extranet.data.Mlistas) obj;
			return (this.getCodigo() == mlistas.getCodigo());
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