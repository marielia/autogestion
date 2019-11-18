package com.refinor.extranet.data.base;

import java.io.Serializable;


public abstract class BaseMibimpuestoId implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private int pciaCcss;
	private int inscripIb;
	private int inscripIva;
	private int actividadCli;
	private int tipoProd;


	public BaseMibimpuestoId () {}
	
	public BaseMibimpuestoId (
		int pciaCcss,
		int inscripIb,
		int inscripIva,
		int actividadCli,
		int tipoProd) {

		this.setPciaCcss(pciaCcss);
		this.setInscripIb(inscripIb);
		this.setInscripIva(inscripIva);
		this.setActividadCli(actividadCli);
		this.setTipoProd(tipoProd);
	}


	/**
	 * Return the value associated with the column: pcia_ccss
	 */
	public int getPciaCcss () {
		return pciaCcss;
	}

	/**
	 * Set the value related to the column: pcia_ccss
	 * @param pciaCcss the pcia_ccss value
	 */
	public void setPciaCcss (int pciaCcss) {
		this.pciaCcss = pciaCcss;
	}



	/**
	 * Return the value associated with the column: inscrip_ib
	 */
	public int getInscripIb () {
		return inscripIb;
	}

	/**
	 * Set the value related to the column: inscrip_ib
	 * @param inscripIb the inscrip_ib value
	 */
	public void setInscripIb (int inscripIb) {
		this.inscripIb = inscripIb;
	}



	/**
	 * Return the value associated with the column: inscrip_iva
	 */
	public int getInscripIva () {
		return inscripIva;
	}

	/**
	 * Set the value related to the column: inscrip_iva
	 * @param inscripIva the inscrip_iva value
	 */
	public void setInscripIva (int inscripIva) {
		this.inscripIva = inscripIva;
	}



	/**
	 * Return the value associated with the column: actividad_cli
	 */
	public int getActividadCli () {
		return actividadCli;
	}

	/**
	 * Set the value related to the column: actividad_cli
	 * @param actividadCli the actividad_cli value
	 */
	public void setActividadCli (int actividadCli) {
		this.actividadCli = actividadCli;
	}



	/**
	 * Return the value associated with the column: tipo_prod
	 */
	public int getTipoProd () {
		return tipoProd;
	}

	/**
	 * Set the value related to the column: tipo_prod
	 * @param tipoProd the tipo_prod value
	 */
	public void setTipoProd (int tipoProd) {
		this.tipoProd = tipoProd;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.refinor.extranet.data.MibimpuestoId)) return false;
		else {
			com.refinor.extranet.data.MibimpuestoId mObj = (com.refinor.extranet.data.MibimpuestoId) obj;
			if (this.getPciaCcss() != mObj.getPciaCcss()) {
				return false;
			}
			if (this.getInscripIb() != mObj.getInscripIb()) {
				return false;
			}
			if (this.getInscripIva() != mObj.getInscripIva()) {
				return false;
			}
			if (this.getActividadCli() != mObj.getActividadCli()) {
				return false;
			}
			if (this.getTipoProd() != mObj.getTipoProd()) {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			sb.append(new java.lang.Integer(this.getPciaCcss()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getInscripIb()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getInscripIva()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getActividadCli()).hashCode());
			sb.append(":");
			sb.append(new java.lang.Integer(this.getTipoProd()).hashCode());
			sb.append(":");
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}