package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMpedidosId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int codigo;
	private int nroSucursal;


	public BaseMpedidosId () {}
	
	public BaseMpedidosId (
		int codigo,
		int nroSucursal) {

		this.setCodigo(codigo);
		this.setNroSucursal(nroSucursal);
	}


	/**
	 * Return the value associated with the column: Codigo
	 */
	public int getCodigo () {
		return codigo;
	}

	/**
	 * Set the value related to the column: Codigo
	 * @param codigo the Codigo value
	 */
	public void setCodigo (int codigo) {
		this.codigo = codigo;
	}



	/**
	 * Return the value associated with the column: nroSucursal
	 */
	public int getNroSucursal () {
		return nroSucursal;
	}

	/**
	 * Set the value related to the column: nroSucursal
	 * @param nroSucursal the nroSucursal value
	 */
	public void setNroSucursal (int nroSucursal) {
		this.nroSucursal = nroSucursal;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MpedidosId)) return false;
		else {
			com.refinor.extranet.data.MpedidosId mObj = (com.refinor.extranet.data.MpedidosId) obj;
			if (this.getCodigo() != mObj.getCodigo()) {
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
			sb.append(new java.lang.Integer(this.getCodigo()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getNroSucursal()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}