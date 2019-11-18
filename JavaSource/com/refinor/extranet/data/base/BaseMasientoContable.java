package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MAsientoContable table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MAsientoContable"
 */

public abstract class BaseMasientoContable  implements Serializable {

	public static String REF = "MasientoContable";
	public static String PROP_DEB_HAB = "debHab";
	public static String PROP_VALOR = "valor";
	public static String PROP_LEYENDA = "leyenda";
	public static String PROP_NRO_EJERCICIO = "nroEjercicio";
	public static String PROP_FECHA = "fecha";
	public static String PROP_NRO_ASIENTO = "nroAsiento";
	public static String PROP_CUENTA = "cuenta";
	public static String PROP_ID = "id";


	// constructors
	public BaseMasientoContable () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMasientoContable (com.refinor.extranet.data.MasientoContableId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MasientoContableId id;

	// fields
	private java.lang.Long nroEjercicio;
	private long nroAsiento;
	private java.lang.String cuenta;
	private java.lang.String debHab;
	private java.math.BigDecimal valor;
	private java.lang.String leyenda;
	private java.util.Date fecha;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MasientoContableId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MasientoContableId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: NroEjercicio
	 */
	public java.lang.Long getNroEjercicio () {
		return nroEjercicio;
	}

	/**
	 * Set the value related to the column: NroEjercicio
	 * @param nroEjercicio the NroEjercicio value
	 */
	public void setNroEjercicio (java.lang.Long nroEjercicio) {
		this.nroEjercicio = nroEjercicio;
	}



	/**
	 * Return the value associated with the column: NroAsiento
	 */
	public long getNroAsiento () {
		return nroAsiento;
	}

	/**
	 * Set the value related to the column: NroAsiento
	 * @param nroAsiento the NroAsiento value
	 */
	public void setNroAsiento (long nroAsiento) {
		this.nroAsiento = nroAsiento;
	}



	/**
	 * Return the value associated with the column: Cuenta
	 */
	public java.lang.String getCuenta () {
		return cuenta;
	}

	/**
	 * Set the value related to the column: Cuenta
	 * @param cuenta the Cuenta value
	 */
	public void setCuenta (java.lang.String cuenta) {
		this.cuenta = cuenta;
	}



	/**
	 * Return the value associated with the column: DebHab
	 */
	public java.lang.String getDebHab () {
		return debHab;
	}

	/**
	 * Set the value related to the column: DebHab
	 * @param debHab the DebHab value
	 */
	public void setDebHab (java.lang.String debHab) {
		this.debHab = debHab;
	}



	/**
	 * Return the value associated with the column: valor
	 */
	public java.math.BigDecimal getValor () {
		return valor;
	}

	/**
	 * Set the value related to the column: valor
	 * @param valor the valor value
	 */
	public void setValor (java.math.BigDecimal valor) {
		this.valor = valor;
	}



	/**
	 * Return the value associated with the column: leyenda
	 */
	public java.lang.String getLeyenda () {
		return leyenda;
	}

	/**
	 * Set the value related to the column: leyenda
	 * @param leyenda the leyenda value
	 */
	public void setLeyenda (java.lang.String leyenda) {
		this.leyenda = leyenda;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MasientoContable)) return false;
		else {
			com.refinor.extranet.data.MasientoContable masientoContable = (com.refinor.extranet.data.MasientoContable) obj;
			if (null == this.getId() || null == masientoContable.getId()) return false;
			else return (this.getId().equals(masientoContable.getId()));
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