package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMFacturaManual_tmpId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int nroFactura;
	private int nroSucursal;


	public BaseMFacturaManual_tmpId () {}
	
	public BaseMFacturaManual_tmpId (
		int nroFactura,
		short nroSucursal) {

		this.setNroFactura(nroFactura);
		this.setNroSucursal(nroSucursal);
	}


	 


	public int getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}

	/**
	 * Return the value associated with the column: NroSucursal
	 */
	public int getNroSucursal () {
		return nroSucursal;
	}

	/**
	 * Set the value related to the column: NroSucursal
	 * @param nroSucursal the NroSucursal value
	 */
	public void setNroSucursal (int nroSucursal) {
		this.nroSucursal = nroSucursal;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MFacturaManual_tmpId)) return false;
		else {
			com.refinor.extranet.data.MFacturaManual_tmpId mObj = (com.refinor.extranet.data.MFacturaManual_tmpId) obj;
			if (this.getNroFactura() != mObj.getNroFactura()) {
				return false;
			}
			if (this.getNroSucursal() != mObj.getNroSucursal()) {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Integer(this.getNroFactura()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getNroSucursal()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}