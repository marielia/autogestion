package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MRecibo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MRecibo"
 */

public abstract class BaseMrecibo  implements Serializable {

	public static String REF = "Mrecibo";
	public static String PROP_FH_BAJA = "fhBaja";
	public static String PROP_NRO_REC = "nroRec";
	public static String PROP_FECHA = "fecha";
	public static String PROP_ID_USER = "idUser";
	public static String PROP_IMPORTE = "importe";
	public static String PROP_NRO_OPER_CAJA = "nroOperCaja";
	public static String PROP_TURNO_VIG = "turnoVig";
	public static String PROP_CCSS = "ccss";
	public static String PROP_CLIENTE = "cliente";


	// constructors
	public BaseMrecibo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMrecibo (int nroRec) {
		this.setNroRec(nroRec);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private int nroRec;

	// fields
	private java.lang.Integer ccss;
	private java.lang.Integer cliente;
	private java.math.BigDecimal importe;
	private java.lang.Integer nroOperCaja;
	private java.util.Date fhBaja;
	private java.lang.String idUser;
	private java.util.Date fecha;
	private java.lang.Long turnoVig;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="nro_rec"
     */
	public int getNroRec () {
		return nroRec;
	}

	/**
	 * Set the unique identifier of this class
	 * @param nroRec the new ID
	 */
	public void setNroRec (int nroRec) {
		this.nroRec = nroRec;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: ccss
	 */
	public java.lang.Integer getCcss () {
		return ccss;
	}

	/**
	 * Set the value related to the column: ccss
	 * @param ccss the ccss value
	 */
	public void setCcss (java.lang.Integer ccss) {
		this.ccss = ccss;
	}



	/**
	 * Return the value associated with the column: cliente
	 */
	public java.lang.Integer getCliente () {
		return cliente;
	}

	/**
	 * Set the value related to the column: cliente
	 * @param cliente the cliente value
	 */
	public void setCliente (java.lang.Integer cliente) {
		this.cliente = cliente;
	}



	/**
	 * Return the value associated with the column: importe
	 */
	public java.math.BigDecimal getImporte () {
		return importe;
	}

	/**
	 * Set the value related to the column: importe
	 * @param importe the importe value
	 */
	public void setImporte (java.math.BigDecimal importe) {
		this.importe = importe;
	}



	/**
	 * Return the value associated with the column: nroOperCaja
	 */
	public java.lang.Integer getNroOperCaja () {
		return nroOperCaja;
	}

	/**
	 * Set the value related to the column: nroOperCaja
	 * @param nroOperCaja the nroOperCaja value
	 */
	public void setNroOperCaja (java.lang.Integer nroOperCaja) {
		this.nroOperCaja = nroOperCaja;
	}



	/**
	 * Return the value associated with the column: fh_baja
	 */
	public java.util.Date getFhBaja () {
		return fhBaja;
	}

	/**
	 * Set the value related to the column: fh_baja
	 * @param fhBaja the fh_baja value
	 */
	public void setFhBaja (java.util.Date fhBaja) {
		this.fhBaja = fhBaja;
	}



	/**
	 * Return the value associated with the column: id_user
	 */
	public java.lang.String getIdUser () {
		return idUser;
	}

	/**
	 * Set the value related to the column: id_user
	 * @param idUser the id_user value
	 */
	public void setIdUser (java.lang.String idUser) {
		this.idUser = idUser;
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
	 * Return the value associated with the column: turno_vig
	 */
	public java.lang.Long getTurnoVig () {
		return turnoVig;
	}

	/**
	 * Set the value related to the column: turno_vig
	 * @param turnoVig the turno_vig value
	 */
	public void setTurnoVig (java.lang.Long turnoVig) {
		this.turnoVig = turnoVig;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Mrecibo)) return false;
		else {
			com.refinor.extranet.data.Mrecibo mrecibo = (com.refinor.extranet.data.Mrecibo) obj;
			return (this.getNroRec() == mrecibo.getNroRec());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getNroRec();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}