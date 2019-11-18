package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMcuentasBancosId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int nroOper;
	private java.lang.String codOper;
	private int ccssid;


	public BaseMcuentasBancosId () {}
	
	public BaseMcuentasBancosId (
		int nroOper,
		java.lang.String codOper,
		int ccssid) {

		this.setNroOper(nroOper);
		this.setCodOper(codOper);
		this.setCcssid(ccssid);
	}


	/**
	 * Return the value associated with the column: NroOper
	 */
	public int getNroOper () {
		return nroOper;
	}

	/**
	 * Set the value related to the column: NroOper
	 * @param nroOper the NroOper value
	 */
	public void setNroOper (int nroOper) {
		this.nroOper = nroOper;
	}



	/**
	 * Return the value associated with the column: CodOper
	 */
	public java.lang.String getCodOper () {
		return codOper;
	}

	/**
	 * Set the value related to the column: CodOper
	 * @param codOper the CodOper value
	 */
	public void setCodOper (java.lang.String codOper) {
		this.codOper = codOper;
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
		if (!(obj instanceof com.refinor.extranet.data.McuentasBancosId)) return false;
		else {
			com.refinor.extranet.data.McuentasBancosId mObj = (com.refinor.extranet.data.McuentasBancosId) obj;
			if (this.getNroOper() != mObj.getNroOper()) {
				return false;
			}
			if (null != this.getCodOper() && null != mObj.getCodOper()) {
				if (!this.getCodOper().equals(mObj.getCodOper())) {
					return false;
				}
			}
			else {
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
			sb.append(new java.lang.Integer(this.getNroOper()).hashCode());
			sb.append(":");
			if (null != this.getCodOper()) {
				sb.append(this.getCodOper().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			sb.append(new java.lang.Integer(this.getCcssid()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}