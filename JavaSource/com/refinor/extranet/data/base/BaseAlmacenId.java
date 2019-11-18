package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseAlmacenId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int ccssid;
	private int almacenId;


	public BaseAlmacenId () {}
	
	public BaseAlmacenId (
		int ccssid,
		int almacenId) {

		this.setCcssid(ccssid);
		this.setAlmacenId(almacenId);
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



	/**
	 * Return the value associated with the column: AlmacenId
	 */
	public int getAlmacenId () {
		return almacenId;
	}

	/**
	 * Set the value related to the column: AlmacenId
	 * @param almacenId the AlmacenId value
	 */
	public void setAlmacenId (int almacenId) {
		this.almacenId = almacenId;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.AlmacenId)) return false;
		else {
			com.refinor.extranet.data.AlmacenId mObj = (com.refinor.extranet.data.AlmacenId) obj;
			if (this.getCcssid() != mObj.getCcssid()) {
				return false;
			}
			if (this.getAlmacenId() != mObj.getAlmacenId()) {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Integer(this.getCcssid()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getAlmacenId()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}