package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the MPuntoVenta table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="MPuntoVenta"
 */

public abstract class BaseMpuntoVenta  implements Serializable {

	public static String REF = "MpuntoVenta";
	public static String PROP_ID = "id";


	// constructors
	public BaseMpuntoVenta () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMpuntoVenta (com.refinor.extranet.data.MpuntoVentaId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.MpuntoVentaId id;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.MpuntoVentaId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.MpuntoVentaId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MpuntoVenta)) return false;
		else {
			com.refinor.extranet.data.MpuntoVenta mpuntoVenta = (com.refinor.extranet.data.MpuntoVenta) obj;
			if (null == this.getId() || null == mpuntoVenta.getId()) return false;
			else return (this.getId().equals(mpuntoVenta.getId()));
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