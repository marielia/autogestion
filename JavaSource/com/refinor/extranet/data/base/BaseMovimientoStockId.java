package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMovimientoStockId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int ccssid;
	private int movimientoStockId;


	public BaseMovimientoStockId () {}
	
	public BaseMovimientoStockId (
		int ccssid,
		int movimientoStockId) {

		this.setCcssid(ccssid);
		this.setMovimientoStockId(movimientoStockId);
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
	 * Return the value associated with the column: MovimientoStockId
	 */
	public int getMovimientoStockId () {
		return movimientoStockId;
	}

	/**
	 * Set the value related to the column: MovimientoStockId
	 * @param movimientoStockId the MovimientoStockId value
	 */
	public void setMovimientoStockId (int movimientoStockId) {
		this.movimientoStockId = movimientoStockId;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MovimientoStockId)) return false;
		else {
			com.refinor.extranet.data.MovimientoStockId mObj = (com.refinor.extranet.data.MovimientoStockId) obj;
			if (this.getCcssid() != mObj.getCcssid()) {
				return false;
			}
			if (this.getMovimientoStockId() != mObj.getMovimientoStockId()) {
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
			sb.append(new java.lang.Integer(this.getMovimientoStockId()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}