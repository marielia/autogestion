package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMasientoContableId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private long id;
	private int ccssid;


	public BaseMasientoContableId () {}
	
	public BaseMasientoContableId (
		long id,
		int ccssid) {

		this.setId(id);
		this.setCcssid(ccssid);
	}


	/**
	 * Return the value associated with the column: id
	 */
	public long getId () {
		return id;
	}

	/**
	 * Set the value related to the column: id
	 * @param id the id value
	 */
	public void setId (long id) {
		this.id = id;
	}



	/**
	 * Return the value associated with the column: CCSSId
	 */
	public int getCcssid () {
		return ccssid;
	}

	/**
	 * Set the value related to the column: CCSSId
	 * @param ccssid the CCSSId value
	 */
	public void setCcssid (int ccssid) {
		this.ccssid = ccssid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MasientoContableId)) return false;
		else {
			com.refinor.extranet.data.MasientoContableId mObj = (com.refinor.extranet.data.MasientoContableId) obj;
			if (this.getId() != mObj.getId()) {
				return false;
			}
			if (this.getCcssid() != mObj.getCcssid()) {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Long(this.getId()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getCcssid()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}