package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the vSaldoPorComprobanteCliente table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="vSaldoPorComprobanteCliente"
 */

public abstract class BaseVsaldoPorComprobanteCliente  implements Serializable {

	public static String REF = "VsaldoPorComprobanteCliente";
	public static String PROP_ID = "id";


	// constructors
	public BaseVsaldoPorComprobanteCliente () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseVsaldoPorComprobanteCliente (com.refinor.extranet.data.VsaldoPorComprobanteClienteId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.VsaldoPorComprobanteClienteId id;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.VsaldoPorComprobanteClienteId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.VsaldoPorComprobanteClienteId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.VsaldoPorComprobanteCliente)) return false;
		else {
			com.refinor.extranet.data.VsaldoPorComprobanteCliente vsaldoPorComprobanteCliente = (com.refinor.extranet.data.VsaldoPorComprobanteCliente) obj;
			if (null == this.getId() || null == vsaldoPorComprobanteCliente.getId()) return false;
			else return (this.getId().equals(vsaldoPorComprobanteCliente.getId()));
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