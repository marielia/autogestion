package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMctasCtesId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int ccssid;
	private int ctaCteReg;


	public BaseMctasCtesId () {}
	
	public BaseMctasCtesId (
		int ccssid,
		int ctaCteReg) {

		this.setCcssid(ccssid);
		this.setCtaCteReg(ctaCteReg);
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
	 * Return the value associated with the column: CtaCteReg
	 */
	public int getCtaCteReg () {
		return ctaCteReg;
	}

	/**
	 * Set the value related to the column: CtaCteReg
	 * @param ctaCteReg the CtaCteReg value
	 */
	public void setCtaCteReg (int ctaCteReg) {
		this.ctaCteReg = ctaCteReg;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MctasCtesId)) return false;
		else {
			com.refinor.extranet.data.MctasCtesId mObj = (com.refinor.extranet.data.MctasCtesId) obj;
			if (this.getCcssid() != mObj.getCcssid()) {
				return false;
			}
			if (this.getCtaCteReg() != mObj.getCtaCteReg()) {
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
			sb.append(new java.lang.Integer(this.getCtaCteReg()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}