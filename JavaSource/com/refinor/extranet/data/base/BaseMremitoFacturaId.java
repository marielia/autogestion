package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMremitoFacturaId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int sucursal;
	private long id;


	public BaseMremitoFacturaId () {}
	
	public BaseMremitoFacturaId (
		int sucursal,
		long id) {

		this.setSucursal(sucursal);
		this.setId(id);
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
	 * Return the value associated with the column: id
	 */
	public long getId () {
		return id;
	}

	/**
	 * Set the value related to the column: id
	 * @param id the id value
	 */
	public void setId (long id) {
		this.id = id;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MremitoFacturaId)) return false;
		else {
			com.refinor.extranet.data.MremitoFacturaId mObj = (com.refinor.extranet.data.MremitoFacturaId) obj;
			if (this.getSucursal() != mObj.getSucursal()) {
				return false;
			}
			if (this.getId() != mObj.getId()) {
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
			sb.append(new java.lang.Long(this.getId()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}