package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MPedidosDtCarga table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MPedidosDtCarga"
 */

public abstract class BaseMpedidosDtCarga  implements Serializable {

	public static String REF = "MpedidosDtCarga";
	public static String PROP_KILOMETROS = "kilometros";
	public static String PROP_ORDEN_CPRA = "ordenCpra";
	public static String PROP_PATENTE = "patente";
	public static String PROP_UNIDAD_NEG = "unidadNeg";
	public static String PROP_ID = "id";
	public static String PROP_DNI = "dni";


	// constructors
	public BaseMpedidosDtCarga () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMpedidosDtCarga (com.refinor.extranet.data.MpedidosDtCargaId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MpedidosDtCargaId id;

	// fields
	private java.lang.String patente;
	private java.math.BigDecimal kilometros;
	private java.lang.Integer unidadNeg;
	private java.lang.Long ordenCpra;
	private java.lang.Integer dni;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MpedidosDtCargaId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MpedidosDtCargaId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: patente
	 */
	public java.lang.String getPatente () {
		return patente;
	}

	/**
	 * Set the value related to the column: patente
	 * @param patente the patente value
	 */
	public void setPatente (java.lang.String patente) {
		this.patente = patente;
	}



	/**
	 * Return the value associated with the column: kilometros
	 */
	public java.math.BigDecimal getKilometros () {
		return kilometros;
	}

	/**
	 * Set the value related to the column: kilometros
	 * @param kilometros the kilometros value
	 */
	public void setKilometros (java.math.BigDecimal kilometros) {
		this.kilometros = kilometros;
	}



	/**
	 * Return the value associated with the column: unidad_neg
	 */
	public java.lang.Integer getUnidadNeg () {
		return unidadNeg;
	}

	/**
	 * Set the value related to the column: unidad_neg
	 * @param unidadNeg the unidad_neg value
	 */
	public void setUnidadNeg (java.lang.Integer unidadNeg) {
		this.unidadNeg = unidadNeg;
	}



	/**
	 * Return the value associated with the column: orden_cpra
	 */
	public java.lang.Long getOrdenCpra () {
		return ordenCpra;
	}

	/**
	 * Set the value related to the column: orden_cpra
	 * @param ordenCpra the orden_cpra value
	 */
	public void setOrdenCpra (java.lang.Long ordenCpra) {
		this.ordenCpra = ordenCpra;
	}



	/**
	 * Return the value associated with the column: dni
	 */
	public java.lang.Integer getDni () {
		return dni;
	}

	/**
	 * Set the value related to the column: dni
	 * @param dni the dni value
	 */
	public void setDni (java.lang.Integer dni) {
		this.dni = dni;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MpedidosDtCarga)) return false;
		else {
			com.refinor.extranet.data.MpedidosDtCarga mpedidosDtCarga = (com.refinor.extranet.data.MpedidosDtCarga) obj;
			if (null == this.getId() || null == mpedidosDtCarga.getId()) return false;
			else return (this.getId().equals(mpedidosDtCarga.getId()));
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