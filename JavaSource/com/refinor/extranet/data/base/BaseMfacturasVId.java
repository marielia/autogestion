package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMfacturasVId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int orden;
	private short nroSucursal;


	public BaseMfacturasVId () {}
	
	public BaseMfacturasVId (
		int orden,
		short nroSucursal) {

		this.setOrden(orden);
		this.setNroSucursal(nroSucursal);
	}


	/**
	 * Return the value associated with the column: Orden
	 */
	public int getOrden () {
		return orden;
	}

	/**
	 * Set the value related to the column: Orden
	 * @param orden the Orden value
	 */
	public void setOrden (int orden) {
		this.orden = orden;
	}



	/**
	 * Return the value associated with the column: NroSucursal
	 */
	public short getNroSucursal () {
		return nroSucursal;
	}

	/**
	 * Set the value related to the column: NroSucursal
	 * @param nroSucursal the NroSucursal value
	 */
	public void setNroSucursal (short nroSucursal) {
		this.nroSucursal = nroSucursal;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MfacturasVId)) return false;
		else {
			com.refinor.extranet.data.MfacturasVId mObj = (com.refinor.extranet.data.MfacturasVId) obj;
			if (this.getOrden() != mObj.getOrden()) {
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
			sb.append(new java.lang.Integer(this.getOrden()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getNroSucursal()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}