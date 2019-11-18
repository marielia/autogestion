package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMpuntoVentaId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.Integer sucursal;
	private java.lang.Integer ccss;


	public BaseMpuntoVentaId () {}
	
	public BaseMpuntoVentaId (
		java.lang.Integer sucursal,
		java.lang.Integer ccss) {

		this.setSucursal(sucursal);
		this.setCcss(ccss);
	}


	/**
	 * Return the value associated with the column: Sucursal
	 */
	public java.lang.Integer getSucursal () {
		return sucursal;
	}

	/**
	 * Set the value related to the column: Sucursal
	 * @param sucursal the Sucursal value
	 */
	public void setSucursal (java.lang.Integer sucursal) {
		this.sucursal = sucursal;
	}



	/**
	 * Return the value associated with the column: ccss
	 */
	public java.lang.Integer getCcss () {
		return ccss;
	}

	/**
	 * Set the value related to the column: ccss
	 * @param ccss the ccss value
	 */
	public void setCcss (java.lang.Integer ccss) {
		this.ccss = ccss;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MpuntoVentaId)) return false;
		else {
			com.refinor.extranet.data.MpuntoVentaId mObj = (com.refinor.extranet.data.MpuntoVentaId) obj;
			if (null != this.getSucursal() && null != mObj.getSucursal()) {
				if (!this.getSucursal().equals(mObj.getSucursal())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getCcss() && null != mObj.getCcss()) {
				if (!this.getCcss().equals(mObj.getCcss())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getSucursal()) {
				sb.append(this.getSucursal().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getCcss()) {
				sb.append(this.getCcss().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}