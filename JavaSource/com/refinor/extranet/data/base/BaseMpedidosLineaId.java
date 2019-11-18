package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMpedidosLineaId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int codPedido;
	private int codLinea;
	private int sucursal;


	public BaseMpedidosLineaId () {}
	
	public BaseMpedidosLineaId (
		int codPedido,
		int codLinea,
		int sucursal) {

		this.setCodPedido(codPedido);
		this.setCodLinea(codLinea);
		this.setSucursal(sucursal);
	}


	/**
	 * Return the value associated with the column: CodPedido
	 */
	public int getCodPedido () {
		return codPedido;
	}

	/**
	 * Set the value related to the column: CodPedido
	 * @param codPedido the CodPedido value
	 */
	public void setCodPedido (int codPedido) {
		this.codPedido = codPedido;
	}



	/**
	 * Return the value associated with the column: CodLinea
	 */
	public int getCodLinea () {
		return codLinea;
	}

	/**
	 * Set the value related to the column: CodLinea
	 * @param codLinea the CodLinea value
	 */
	public void setCodLinea (int codLinea) {
		this.codLinea = codLinea;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MpedidosLineaId)) return false;
		else {
			com.refinor.extranet.data.MpedidosLineaId mObj = (com.refinor.extranet.data.MpedidosLineaId) obj;
			if (this.getCodPedido() != mObj.getCodPedido()) {
				return false;
			}
			if (this.getCodLinea() != mObj.getCodLinea()) {
				return false;
			}
			if (this.getSucursal() != mObj.getSucursal()) {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Integer(this.getCodPedido()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getCodLinea()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getSucursal()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}