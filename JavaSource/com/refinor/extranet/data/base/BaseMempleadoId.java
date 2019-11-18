package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMempleadoId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int codigo;
	private int ccssId;


	public BaseMempleadoId () {}
	
	public BaseMempleadoId (
		int codigo,
		int ccssId) {

		this.setCodigo(codigo);
		this.setCcssId(ccssId);
	}


	/**
	 * Return the value associated with the column: codigo
	 */
	public int getCodigo () {
		return codigo;
	}

	/**
	 * Set the value related to the column: codigo
	 * @param codigo the codigo value
	 */
	public void setCodigo (int codigo) {
		this.codigo = codigo;
	}



	/**
	 * Return the value associated with the column: ccssId
	 */
	public int getCcssId () {
		return ccssId;
	}

	/**
	 * Set the value related to the column: ccssId
	 * @param ccssId the ccssId value
	 */
	public void setCcssId (int ccssId) {
		this.ccssId = ccssId;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MempleadoId)) return false;
		else {
			com.refinor.extranet.data.MempleadoId mObj = (com.refinor.extranet.data.MempleadoId) obj;
			if (this.getCodigo() != mObj.getCodigo()) {
				return false;
			}
			if (this.getCcssId() != mObj.getCcssId()) {
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
			sb.append(new java.lang.Integer(this.getCcssId()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}