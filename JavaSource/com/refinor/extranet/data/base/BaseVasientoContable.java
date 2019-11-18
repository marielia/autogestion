package com.refinor.extranet.data.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the vAsientoContable table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="vAsientoContable"
 */

public abstract class BaseVasientoContable  implements Serializable {

	public static String REF = "VasientoContable";
	public static String PROP_ID = "id";


	// constructors
	public BaseVasientoContable () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseVasientoContable (com.refinor.extranet.data.VasientoContableId id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.refinor.extranet.data.VasientoContableId id;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.refinor.extranet.data.VasientoContableId getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.refinor.extranet.data.VasientoContableId id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.VasientoContable)) return false;
		else {
			com.refinor.extranet.data.VasientoContable vasientoContable = (com.refinor.extranet.data.VasientoContable) obj;
			if (null == this.getId() || null == vasientoContable.getId()) return false;
			else return (this.getId().equals(vasientoContable.getId()));
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