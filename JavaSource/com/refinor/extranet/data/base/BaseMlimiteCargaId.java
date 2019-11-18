package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMlimiteCargaId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int codVeh;
	private int codArt;


	public BaseMlimiteCargaId () {}
	
	public BaseMlimiteCargaId (
		int codVeh,
		int codArt) {

		this.setCodVeh(codVeh);
		this.setCodArt(codArt);
	}


	/**
	 * Return the value associated with the column: cod_veh
	 */
	public int getCodVeh () {
		return codVeh;
	}

	/**
	 * Set the value related to the column: cod_veh
	 * @param codVeh the cod_veh value
	 */
	public void setCodVeh (int codVeh) {
		this.codVeh = codVeh;
	}



	/**
	 * Return the value associated with the column: cod_art
	 */
	public int getCodArt () {
		return codArt;
	}

	/**
	 * Set the value related to the column: cod_art
	 * @param codArt the cod_art value
	 */
	public void setCodArt (int codArt) {
		this.codArt = codArt;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MlimiteCargaId)) return false;
		else {
			com.refinor.extranet.data.MlimiteCargaId mObj = (com.refinor.extranet.data.MlimiteCargaId) obj;
			if (this.getCodVeh() != mObj.getCodVeh()) {
				return false;
			}
			if (this.getCodArt() != mObj.getCodArt()) {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Integer(this.getCodVeh()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getCodArt()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}