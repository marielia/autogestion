package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseVsaldoPorComprobanteClienteId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.Integer clteProv;
	private java.lang.Integer nroOper;
	private java.math.BigDecimal imp;


	public BaseVsaldoPorComprobanteClienteId () {}
	
	public BaseVsaldoPorComprobanteClienteId (
		java.lang.Integer clteProv,
		java.lang.Integer nroOper,
		java.math.BigDecimal imp) {

		this.setClteProv(clteProv);
		this.setNroOper(nroOper);
		this.setImp(imp);
	}


	/**
	 * Return the value associated with the column: ClteProv
	 */
	public java.lang.Integer getClteProv () {
		return clteProv;
	}

	/**
	 * Set the value related to the column: ClteProv
	 * @param clteProv the ClteProv value
	 */
	public void setClteProv (java.lang.Integer clteProv) {
		this.clteProv = clteProv;
	}



	/**
	 * Return the value associated with the column: NroOper
	 */
	public java.lang.Integer getNroOper () {
		return nroOper;
	}

	/**
	 * Set the value related to the column: NroOper
	 * @param nroOper the NroOper value
	 */
	public void setNroOper (java.lang.Integer nroOper) {
		this.nroOper = nroOper;
	}



	/**
	 * Return the value associated with the column: imp
	 */
	public java.math.BigDecimal getImp () {
		return imp;
	}

	/**
	 * Set the value related to the column: imp
	 * @param imp the imp value
	 */
	public void setImp (java.math.BigDecimal imp) {
		this.imp = imp;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.VsaldoPorComprobanteClienteId)) return false;
		else {
			com.refinor.extranet.data.VsaldoPorComprobanteClienteId mObj = (com.refinor.extranet.data.VsaldoPorComprobanteClienteId) obj;
			if (null != this.getClteProv() && null != mObj.getClteProv()) {
				if (!this.getClteProv().equals(mObj.getClteProv())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getNroOper() && null != mObj.getNroOper()) {
				if (!this.getNroOper().equals(mObj.getNroOper())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getImp() && null != mObj.getImp()) {
				if (!this.getImp().equals(mObj.getImp())) {
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
			if (null != this.getClteProv()) {
				sb.append(this.getClteProv().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getNroOper()) {
				sb.append(this.getNroOper().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getImp()) {
				sb.append(this.getImp().hashCode());
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