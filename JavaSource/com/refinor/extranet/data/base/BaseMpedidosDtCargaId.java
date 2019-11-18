package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMpedidosDtCargaId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int sucursal;
	private int codigoMp;


	public BaseMpedidosDtCargaId () {}
	
	public BaseMpedidosDtCargaId (
		int sucursal,
		int codigoMp) {

		this.setSucursal(sucursal);
		this.setCodigoMp(codigoMp);
	}


	/**
	 * Return the value associated with the column: sucursal
	 */
	public int getSucursal () {
		return sucursal;
	}

	/**
	 * Set the value related to the column: sucursal
	 * @param sucursal the sucursal value
	 */
	public void setSucursal (int sucursal) {
		this.sucursal = sucursal;
	}



	/**
	 * Return the value associated with the column: codigo_MP
	 */
	public int getCodigoMp () {
		return codigoMp;
	}

	/**
	 * Set the value related to the column: codigo_MP
	 * @param codigoMp the codigo_MP value
	 */
	public void setCodigoMp (int codigoMp) {
		this.codigoMp = codigoMp;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MpedidosDtCargaId)) return false;
		else {
			com.refinor.extranet.data.MpedidosDtCargaId mObj = (com.refinor.extranet.data.MpedidosDtCargaId) obj;
			if (this.getSucursal() != mObj.getSucursal()) {
				return false;
			}
			if (this.getCodigoMp() != mObj.getCodigoMp()) {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Integer(this.getSucursal()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getCodigoMp()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}