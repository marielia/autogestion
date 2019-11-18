package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMcajaId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private long id;
	private int ccssid;


	public BaseMcajaId () {}
	
	public BaseMcajaId (
		long id,
		int ccssid) {

		this.setId(id);
		this.setCcssid(ccssid);
	}


	/**
	 * Return the value associated with the column: Id
	 */
	public long getId () {
		return id;
	}

	/**
	 * Set the value related to the column: Id
	 * @param id the Id value
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
		if (!(obj instanceof com.refinor.extranet.data.McajaId)) return false;
		else {
			com.refinor.extranet.data.McajaId mObj = (com.refinor.extranet.data.McajaId) obj;
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