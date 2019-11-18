package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MSecuencias table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MSecuencias"
 */

public abstract class BaseMsecuencias  implements Serializable {

	public static String REF = "Msecuencias";
	public static String PROP_SECUENCIA = "secuencia";
	public static String PROP_NOMB_TABLA = "nombTabla";


	// constructors
	public BaseMsecuencias () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMsecuencias (java.lang.String nombTabla) {
		this.setNombTabla(nombTabla);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String nombTabla;

	// fields
	private java.lang.Integer secuencia;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="increment"
     *  column="NombTabla"
     */
	public java.lang.String getNombTabla () {
		return nombTabla;
	}

	/**
	 * Set the unique identifier of this class
	 * @param nombTabla the new ID
	 */
	public void setNombTabla (java.lang.String nombTabla) {
		this.nombTabla = nombTabla;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: Secuencia
	 */
	public java.lang.Integer getSecuencia () {
		return secuencia;
	}

	/**
	 * Set the value related to the column: Secuencia
	 * @param secuencia the Secuencia value
	 */
	public void setSecuencia (java.lang.Integer secuencia) {
		this.secuencia = secuencia;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.Msecuencias)) return false;
		else {
			com.refinor.extranet.data.Msecuencias msecuencias = (com.refinor.extranet.data.Msecuencias) obj;
			if (null == this.getNombTabla() || null == msecuencias.getNombTabla()) return false;
			else return (this.getNombTabla().equals(msecuencias.getNombTabla()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getNombTabla()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getNombTabla().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}